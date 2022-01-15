package examples.words;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import graph.basics.BasicVertex;
import graph.interfaces.Vertex;

public class RandomWords {
	
	private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
	
	private static final int NUM_LETTERS = 6;
	private static final double PART = 0.015;

	public static void main(String[] args) {
		
		System.out.println(dtf.format(LocalDateTime.now()) + " Start");
		Set<Vertex<String>> dict = createDictionnary(NUM_LETTERS, PART);
		System.out.println(dtf.format(LocalDateTime.now()) + " Dictionnary created " + dict.size() + " entries");
		
		Vertex<String> start = dict.stream().skip(ThreadLocalRandom.current().nextInt(dict.size())).findFirst().get();
		Vertex<String> finish = dict.stream().skip(ThreadLocalRandom.current().nextInt(dict.size())).findFirst().get();
		
		System.out.println("start: " + start.getData());
		System.out.println("finish: " + finish.getData());
		System.out.println("Processing...");
		List<String> way = new WordsGraph(dict).findWay(start, finish);
		System.out.println("\n" + dtf.format(LocalDateTime.now()) + " " + (way == null ? "Way not found" : 
			                                                            				(way.size() - 1) + " moves\n" + way));

	}
	
	private static Set<Vertex<String>> createDictionnary(int wordLength, double dictionnaryPart) {
		
		HashSet<Vertex<String>> result = new HashSet<>();
		int dictionnaryLength = (int)(intPower(26, wordLength)*dictionnaryPart);
		while (result.size() < dictionnaryLength) result
		                        .add(new BasicVertex<String>(new String(randomString(wordLength))));
		return result;
	}
	
	private static long intPower(long base, int exponent) {
		if (exponent == 1) return base;
		return base*intPower(base, exponent-1);
	}
	
	private static char[] randomString(int length) {
		char[] result = new char[length];
		for (int i=0; i < length; i++)result[i] = (char) ThreadLocalRandom.current().nextInt(97, 123);
		return result;
	}

}
