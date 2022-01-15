package examples.cells.traveller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import graph.basics.BasicVertex;
import graph.interfaces.Vertex;

public class TravellerTest {

	private static final int WIDTH = 20;
	private static final int HEIGHT = 20;

	public static void main(String[] args) {
		
		List<BasicVertex<ValuedCell>> problem = new ArrayList<>();
		
		for (int i=0; i < WIDTH*HEIGHT; i++) {
			ValuedCell cell = new ValuedCell(i/WIDTH, i%WIDTH, (double)(int)(Math.random()*100.));
			problem.add(new BasicVertex<ValuedCell>(cell));
		}
		
		BasicVertex<ValuedCell> start = problem.get(0);
		BasicVertex<ValuedCell> finish = problem.get(problem.size() - 1);
		
		HashSet<Vertex<ValuedCell>> problemSet = new HashSet<>();
		problemSet.addAll(problem);
		 
		List<ValuedCell> solution = new TravellerGraph(problemSet, WIDTH, HEIGHT).findOptimalWay(start, finish);
		
		if(solution == null) System.out.println("No way!");
		display(problem, solution);

	}
	
	private static void display(List<BasicVertex<ValuedCell>> problem, List<ValuedCell> solution) {
		for(int row = 0; row < HEIGHT; row++) {
			for (int column = 0; column < WIDTH; column++) {
				ValuedCell cell = problem.get(row*WIDTH + column).getData();
				if (solution != null && solution.contains(cell)) System.out.printf(" *%2d* ",(int)cell.getValue());
				else System.out.printf("  %2d  ", (int)cell.getValue());
			}
			System.out.println();
		}
	}

}
