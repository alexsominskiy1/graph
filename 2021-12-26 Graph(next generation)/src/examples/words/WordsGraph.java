package examples.words;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import graph.basics.BasicAbstractGraph;
import graph.interfaces.Vertex;

public class WordsGraph extends BasicAbstractGraph<String>{
	
	private HashMap<String, Vertex<String>> verticesMap = new HashMap<>();

	public WordsGraph(Set<Vertex<String>> vertices) {
		super(vertices);
		for (Vertex<String> vertex : vertices)verticesMap.put(vertex.getData(), vertex);
	}

	@Override
	public HashSet<Vertex<String>> getAdjacents(Vertex<String> strVertex) {
		HashSet<Vertex<String>> result = new HashSet<>();
		String str = strVertex.getData();
		for (int i = 0; i < str.length(); i++) {
			char[] copy = str.toCharArray();
			for (char c = 'a'; c <= 'z'; c++) {
				copy[i] = c;
				Vertex<String> vertex = verticesMap.get(new String(copy));
				if (vertex != null) result.add(vertex);
			}
		}
		result.remove(strVertex);
		return result;
	}

	@Override
	protected double computeWeight(Vertex<String> from, Vertex<String> to) {
		return 0.;
	}
}
