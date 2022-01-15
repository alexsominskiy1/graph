package examples.standard;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import graph.basics.BasicEdge;
import graph.basics.BasicVertex;
import graph.basics.StandardGraph;
import graph.interfaces.Edge;
import graph.interfaces.Vertex;

public class Lee {

	public static void main(String[] args) {
		
		HashMap<String, BasicVertex<String>> verticesMap = new HashMap<>();
		String[] ids = {"A","B","C","D","E","F","G","H"};
		for (String id : ids) verticesMap.putIfAbsent(id, new BasicVertex<String>(id));
		
		HashSet<Edge<String>> edges = new HashSet<>();
		edges.add(new BasicEdge<String>(verticesMap.get("D"), verticesMap.get("B")));
		edges.add(new BasicEdge<String>(verticesMap.get("E"), verticesMap.get("B")));
		edges.add(new BasicEdge<String>(verticesMap.get("B"), verticesMap.get("E")));
		edges.add(new BasicEdge<String>(verticesMap.get("F"), verticesMap.get("C")));
		edges.add(new BasicEdge<String>(verticesMap.get("G"), verticesMap.get("C")));
		edges.add(new BasicEdge<String>(verticesMap.get("B"), verticesMap.get("A")));
		edges.add(new BasicEdge<String>(verticesMap.get("C"), verticesMap.get("A")));
		
		edges.add(new BasicEdge<String>(verticesMap.get("A"), verticesMap.get("F")));
		
		List<String> way = new StandardGraph<String>(new HashSet<Vertex<String>>(verticesMap.values()), edges)
				           	.findWay(verticesMap.get("D"), verticesMap.get("C"));
		System.out.println(way);
	}

}
