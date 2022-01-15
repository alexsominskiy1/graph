package graph.basics;

import java.util.Set;
import graph.interfaces.Vertex;
import graph.interfaces.Edge;

public class StandardGraph<T> extends BasicAbstractGraph<T>{

	public StandardGraph(Set<Vertex<T>> verticesSet, Set<Edge<T>> edgesSet) {
		super(verticesSet, edgesSet);
	}

	@Override
	public Set<Vertex<T>> getAdjacents(Vertex<T> vertex) {
		if(vertex == null) throwExc("StandardGraph getAdjacents: argument is null");
		return vertex.getVertexAdjacents();
	}

	@Override
	public double computeWeight(Vertex<T> from, Vertex<T> to) {
		if(from == null) throwExc("StandardGraph computeWeight: from is null");
		if(to == null) throwExc("StandardGraph computeWeight: to is null");
		return from.getWeightTo(to);
	}
}
