package graph.basics;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.function.DoublePredicate;
import java.util.stream.Collectors;

import graph.interfaces.Edge;
import graph.interfaces.Graph;
import graph.interfaces.Vertex;

public abstract class BasicAbstractGraph<T> implements Graph<T> {
	
	protected Set<Vertex<T>> vertices = new HashSet<>();

	// constructors
	
	public BasicAbstractGraph() {
		super();
	}

	public BasicAbstractGraph(Set<Vertex<T>> verticesSet) {
		super();
		addAllVertices(verticesSet);
	}
	
	public BasicAbstractGraph(Set<Vertex<T>> verticesSet, Set<Edge<T>> edges) {
		super();
		addAllVertices(verticesSet);
		addAllEdges(edges);
	}
	
	// vertices
	
	@Override
	public boolean exists(Vertex<T> vertex) {
		return vertices.contains(vertex);
	}

	@Override
	public boolean addVertex(Vertex<T> vertex) {
		if(vertex == null) throwExc("addVertex: argument is null");
		return vertices.add(vertex);
	}
	
	@Override
	public Set<Vertex<T>>getAllVertices() {
		return new HashSet<Vertex<T>>(vertices);
	}

	@Override
	public boolean removeVertex(Vertex<T> vertex) {
		if(!exists(vertex)) return false;
		vertices.stream().flatMap(v -> v.getEdges().stream())
		 				 .filter(edge -> edge.getTo().equals(vertex))
		 				 .forEach(edge -> removeEdge(edge));
		vertices.remove(vertex);
		return true;
	}

	@Override
	public int countVertices() {
		return vertices.size();
	}
	
	// edges

	@Override
	public boolean exists(Edge<T> edge) {
		return checkEdgeVertices(edge) && edge.getFrom().containsEdge(edge);
	}

	@Override
	public boolean checkEdgeVertices(Edge<T> edge) {
		if(edge == null) throwExc("checkEdgevertices: argument is null");
		return exists(edge.getFrom()) && exists(edge.getTo());
	}

	@Override
	public boolean addEdge(Edge<T> edge) {
		return checkEdgeVertices(edge) && edge.getFrom().addEdge(edge);
	}
	
	@Override
	public boolean removeEdge(Edge<T> edge) {
		if (!checkEdgeVertices(edge)) return false;
		return edge.getFrom().removeEdge(edge);
	}

	@Override
	public Set<Edge<T>> getAllEdges() {
		return vertices.stream().flatMap(vertex -> vertex.getEdges().stream()).collect(Collectors.toSet());
	}
	
	@Override
	public int countEdges() {
		return getAllEdges().size();
	}
	
	public double computeCheckedWeight(Vertex<T> from, Vertex<T> to, DoublePredicate limitation) {
		double computedWeight = computeWeight(from, to);
		if (!limitation.test(computedWeight)) throwExc("computeCheckedWeight: illegal computed weight " + computedWeight);
		return computedWeight;
	}
	
	
	// utilities

	public  List<Vertex<T>> backWay(Vertex<T> dest, boolean clean) {
		LinkedList<Vertex<T>> way = new LinkedList<>();
		Vertex<T> current = dest;

		while(current != null) {
			way.addFirst(current);
			current = current.getPrevious();
		}

		if (clean) vertices.forEach(v -> {v.setWeight(0.); v.setPrevious(null);});
		return way;
	}

	public List<T> verticesToData(List<Vertex<T>> list){
		return list == null ? null : list.stream().map(v -> v.getData()).collect(Collectors.toList());
	}

	// abstract
	
	abstract protected Set<Vertex<T>> getAdjacents(Vertex<T> vertex);
	abstract protected double computeWeight(Vertex<T> from, Vertex<T> to);
	
	// Lee algorithm

	public List<T> findWay(Vertex<T> origin, Vertex<T> dest) {
		return verticesToData(findWayVertices(origin, dest));
	}
	
	public List<Vertex<T>> findWayVertices(Vertex<T> origin, Vertex<T> dest) {
		
		if (!exists(origin)) throwExc("getWay: origin vertex with id " + origin + " does not exist");
		if (!exists(dest)) throwExc("getWay: destination vertex with id " + dest + " does not exist");
		
		if (origin.equals(dest)) return new ArrayList<Vertex<T>>(List.of(origin));
		
		Deque<Vertex<T>> toVisit = new LinkedList<>();
		
		toVisit.addLast(origin);
		origin.setPrevious(origin);
		
		boolean success = false;
		while(toVisit.size() > 0) {
			
			Vertex<T> current = toVisit.removeFirst();
			if(dest.equals(current)) {
				success = true;
				break;
			}
			for (Vertex<T> adj : getAdjacents(current)) {
						
				if (adj.getPrevious() == null) {
					adj.setPrevious(current);
					toVisit.addLast(adj);
				}
			}
		}
		origin.setPrevious(null);
		return success ? backWay(dest, true) : null;
	}
	
	// Djxtra algorithm
	
	public List<T> findOptimalWay(Vertex<T> origin, Vertex<T> dest) {
		return verticesToData(findOptimalWayVertices(origin, dest));
	}
	
	public List<Vertex<T>> findOptimalWayVertices(Vertex<T> origin, Vertex<T> dest){
		
		if (!exists(origin)) throwExc("getWay: origin vertex with id " + origin + " does not exist");
		if (!exists(dest)) throwExc("getWay: destination vertex with id " + dest + " does not exist");
		
		if (origin.equals(dest)) return new ArrayList<Vertex<T>>(List.of(origin));
		
		Comparator<Vertex<T>> vertexComparator = (v1, v2) -> Double.compare(v1.getWeight(),v2.getWeight());		
		PriorityQueue<Vertex<T>> toVisit = new PriorityQueue<>(vertexComparator);
		toVisit.offer(origin);
		origin.setPrevious(origin);
		
		boolean success = false;
		while(toVisit.size() > 0) {
			
			Vertex<T> current = toVisit.poll();
			if(dest.equals(current)) {
				success = true;
				break;
			}

			double currentWeight = current.getWeight();
				
			for (Vertex<T> adj : getAdjacents(current)) {
				if (Double.isFinite(adj.getWeight())) {
					double weight = currentWeight + computeCheckedWeight(current, adj, x -> x >= 0.);
					if (adj.getPrevious() == null || weight < adj.getWeight()) {
						adj.setWeight(weight);
						adj.setPrevious(current);
						toVisit.offer(adj);
					}
				}
			}
			current.setWeight(Double.NEGATIVE_INFINITY);
		}
		
		origin.setPrevious(null);
		return success ? backWay(dest, true) : null;
	}
}