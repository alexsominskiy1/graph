package graph.interfaces;

import java.util.HashSet;

public interface Vertex<T> {
	
	T getData();
	void setData(T data);
	
	HashSet<Edge<T>> getEdges();
	
	boolean addEdge(Edge<T> edge);
	boolean containsEdge(Edge<T> edge);
	boolean removeEdge(Edge<T> edge);
	
	Vertex<T> getPrevious();
	void setPrevious(Vertex<T> previous);
	
	double getWeight();
	void setWeight(double weight);
	
	HashSet<Vertex<T>> getVertexAdjacents();
	double getWeightTo(Vertex<T> vertex);

}
