package graph.basics;

import graph.interfaces.Edge;
import graph.interfaces.Vertex;

public class BasicEdge<T> implements Edge<T>{
	
	private Vertex<T> from;
	private Vertex<T> to;
	
	private double weight;

	public BasicEdge(Vertex<T> from, Vertex<T> to, double weight) {
		super();
		this.from = from;
		this.to = to;
		this.weight = weight;
	}

	public BasicEdge(Vertex<T> from, Vertex<T> to) {
		super();
		this.from = from;
		this.to = to;
	}

	@Override
	public Vertex<T> getFrom() {
		return from;
	}

	@Override
	public Vertex<T> getTo() {
		return to;
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
		result = prime * result + ((from == null) ? 0 : from.hashCode());
		result = prime * result + ((to == null) ? 0 : to.hashCode());
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
		BasicEdge<T> other = (BasicEdge<T>) obj;
		if (from == null) {
			if (other.from != null)
				return false;
		} else if (!from.equals(other.from))
			return false;
		if (to == null) {
			if (other.to != null)
				return false;
		} else if (!to.equals(other.to))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "BasicEdge [from=" + from + ", to=" + to + ", weight=" + weight + "]";
	}
}
