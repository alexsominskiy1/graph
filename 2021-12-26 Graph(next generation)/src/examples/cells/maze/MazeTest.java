package examples.cells.maze;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import graph.basics.BasicVertex;
import graph.interfaces.Vertex;


public class MazeTest {

	private static final int WIDTH = 30;
	private static final int HEIGHT = 30;
	private static final double BLOCK_PROBABILITY = 0.3;

	public static void main(String[] args) {
		
		List<BasicVertex<MazeCell>> problem = new ArrayList<>();
		
		for (int i=0; i < WIDTH*HEIGHT; i++) {
			MazeCell cell = new MazeCell(i/WIDTH, i%WIDTH);
			if (Math.random() < BLOCK_PROBABILITY)cell.setBlocked(true);
			problem.add(new BasicVertex<MazeCell>(cell));
		}
		
		BasicVertex<MazeCell> start = problem.get(0);
		BasicVertex<MazeCell> finish = problem.get(problem.size() - 1);
		start.getData().setBlocked(false);
		finish.getData().setBlocked(false);
		
		HashSet<Vertex<MazeCell>> problemSet = new HashSet<>();
		problemSet.addAll(problem);
		 
		List<MazeCell> solution = new MazeGraph(problemSet, WIDTH, HEIGHT).findWay(start, finish);
		
		if(solution == null) System.out.println("No way!");
		display(problem, solution);

	}
	
	private static void display(List<BasicVertex<MazeCell>> problem, List<MazeCell> solution) {
		
		for(int row = 0; row < HEIGHT; row++) {
			for (int column = 0; column < WIDTH; column++) {
				MazeCell cell = problem.get(row*WIDTH + column).getData();
				if (solution != null && solution.contains(cell)) System.out.print(" O ");
				else System.out.print(cell.isBlocked() ? " X " : "   ");
			}
			System.out.println();
		}
	}

}
