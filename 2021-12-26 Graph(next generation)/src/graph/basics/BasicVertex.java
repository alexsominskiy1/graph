package graph.basics;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import graph.interfaces.Edge;
import graph.interfaces.Vertex;

public class BasicVertex<T> implements Vertex<T>{
	
	private T data;
	private double weight;
	private Vertex<T> previous;
	private HashSet<Edge<T>> edges = new HashSet<>();
	
	public BasicVertex(T data, double weight) {
		super();
		this.data = data;
		this.weight = weight;
	}

	public BasicVertex(T data) {
		super();
		this.data = data;
	}

	@Override
	public T getData() {
		return data;
	}
	
	@Override
	public void setData(T data) {
		this.data = data;	
	}
	
	@Override
	public HashSet<Edge<T>> getEdges() {
		return edges;
	}

	@Override
	public boolean addEdge(Edge<T> edge) {
			return edges.add(edge);
	}

	@Override
	public boolean containsEdge(Edge<T> edge) {
		return edges.contains(edge);
	}

	@Override
	public boolean removeEdge(Edge<T> edge) {
		return edges.remove(edge);
	}

	
	@Override
	public Vertex<T> getPrevious() {
		return previous;
	}
	
	@Override
	public void setPrevious(Vertex<T> previous) {
		this.previous = previous;
	}
	@Override
	public double getWeight() {
		return weight;
	}
	@Override
	public void setWeight(double weight) {
		this.weight = weight;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BasicVertex<T> other = (BasicVertex<T>) obj;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		return true;
	}

	@Override
	public HashSet<Vertex<T>> getVertexAdjacents() {
		return edges.stream().map(edge -> edge.getTo()).collect(Collectors.toCollection(()-> new HashSet<Vertex<T>>()));
	}

	@Override
	public double getWeightTo(Vertex<T> vertex) {
		List<Edge<T>> edgesList = edges.stream().filter(edge -> edge.getTo().equals(vertex)).collect(Collectors.toList()); 
		return edgesList.size() == 0 ? Double.POSITIVE_INFINITY : edgesList.get(0).getWeight();	
	}

	@Override
	public String toString() {
		return "BasicVertex [data=" + data + ", weight=" + weight +"]";
	}
	
}
