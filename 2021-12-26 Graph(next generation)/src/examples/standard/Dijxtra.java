package examples.standard;

import java.util.HashMap;
import java.util.HashSet;

import graph.basics.BasicEdge;
import graph.basics.BasicVertex;
import graph.basics.StandardGraph;
import graph.interfaces.Edge;
import graph.interfaces.Vertex;

public class Dijxtra {
	
		public static void main(String[] args) {
						
		HashMap<String, BasicVertex<String>> verticesMap = new HashMap<>();
		String[] ids = {"A","B","C","D","E","F","H","I","J"};
		for (String id : ids) verticesMap.putIfAbsent(id, new BasicVertex<String>(id, 0.));
		
		HashSet<Edge<String>> edges = new HashSet<>();
		edges.add(new BasicEdge<String>(verticesMap.get("A"), verticesMap.get("B"), 8.));
		edges.add(new BasicEdge<String>(verticesMap.get("A"), verticesMap.get("C"), 5.));
		edges.add(new BasicEdge<String>(verticesMap.get("B"), verticesMap.get("E"), 2.));
		edges.add(new BasicEdge<String>(verticesMap.get("B"), verticesMap.get("H"), 2.));
		edges.add(new BasicEdge<String>(verticesMap.get("B"), verticesMap.get("J"), 4.));
		edges.add(new BasicEdge<String>(verticesMap.get("C"), verticesMap.get("B"), 3.));
		edges.add(new BasicEdge<String>(verticesMap.get("C"), verticesMap.get("D"), 1.));
		edges.add(new BasicEdge<String>(verticesMap.get("C"), verticesMap.get("F"), 9.));
		edges.add(new BasicEdge<String>(verticesMap.get("D"), verticesMap.get("B"), 1.));
		edges.add(new BasicEdge<String>(verticesMap.get("D"), verticesMap.get("E"), 5.));
		edges.add(new BasicEdge<String>(verticesMap.get("D"), verticesMap.get("F"), 8.));
		edges.add(new BasicEdge<String>(verticesMap.get("E"), verticesMap.get("F"), 4.));
		edges.add(new BasicEdge<String>(verticesMap.get("H"), verticesMap.get("E"), 3.));
		edges.add(new BasicEdge<String>(verticesMap.get("F"), verticesMap.get("I"), 3.));
		
		StandardGraph<String> graph = new StandardGraph<String>(new HashSet<Vertex<String>>(verticesMap.values()), edges);
		System.out.println(graph.findOptimalWay(verticesMap.get("A"), verticesMap.get("F")));
		System.out.println(graph.findWay(verticesMap.get("A"), verticesMap.get("F")));
	}

}
