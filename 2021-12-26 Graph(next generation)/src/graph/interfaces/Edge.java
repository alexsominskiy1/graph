package graph.interfaces;

public interface Edge<T> {
	
	Vertex<T> getFrom();
	Vertex<T> getTo();
	
	double getWeight();
	void setWeight(double weight);
}
