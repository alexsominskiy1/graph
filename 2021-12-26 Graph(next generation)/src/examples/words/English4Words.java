package examples.words;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import graph.basics.BasicVertex;
import graph.interfaces.Vertex;


public class English4Words {

	public static void main(String[] args) throws IOException {
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
		System.out.println(dtf.format(LocalDateTime.now()) + " Start");
		
		Set<Vertex<String>> dictionary = new HashSet<>();
		
		Files.lines(Paths.get("src/examples/words/gistfile1.txt")).forEach(str -> dictionary.add(new BasicVertex<String>(str)));
		System.out.println(dtf.format(LocalDateTime.now()) + " Dictionnary created " + dictionary.size() + " entries");
		
		Vertex<String> start = getRandomElement(dictionary);
		Vertex<String> finish = getRandomElement(dictionary);
		System.out.println("Start: " + start.getData());
		System.out.println("Finish: " + finish.getData());
		
		List<String> way = new WordsGraph(dictionary).findWay(start, finish);
		System.out.println("\n" + dtf.format(LocalDateTime.now()) + " " + (way == null ? "Way not found" : way));
		
	}
	
	private static <T> T getRandomElement(Set<T> hs) {
		return hs.stream().skip(ThreadLocalRandom.current().nextInt(hs.size())).findFirst().orElse(null);
	}
}
