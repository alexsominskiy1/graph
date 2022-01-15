package graph.interfaces;

import java.util.Collection;
import java.util.Set;

import graph.exceptions.InvalidParametersException;

public interface Graph<T> {
	
	// exception
	
	default void throwExc(String msg) {
		throw new InvalidParametersException(msg);
	}
	
	// vertices
	
	boolean exists(Vertex<T> vertex);
	
	boolean addVertex(Vertex<T> vertex);
	default boolean addAllVertices(Collection<Vertex<T>> verticesCollection) {
		if (verticesCollection == null) throwExc("addAllvertices: argument is null");
		return verticesCollection.stream().allMatch(vertex -> addVertex(vertex));
	}
	
	boolean removeVertex(Vertex<T> vertex);
	
	Set<Vertex<T>> getAllVertices();
	int countVertices();
	
	// edges
	
	boolean exists(Edge<T> edge);
	
	boolean checkEdgeVertices(Edge<T> edge);
	boolean addEdge(Edge<T> edge);
	default boolean addAllEdges(Collection<Edge<T>> edges) {
		if (edges == null) throwExc("addAllEdges: argument is null");
		return edges.stream().allMatch(edge -> addEdge(edge));
	}
	
	boolean removeEdge(Edge<T> edge);
	
	Set<Edge<T>> getAllEdges();
	int countEdges();

}
