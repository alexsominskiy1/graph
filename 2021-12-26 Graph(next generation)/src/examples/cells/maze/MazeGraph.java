package examples.cells.maze;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import graph.basics.BasicAbstractGraph;
import graph.interfaces.Vertex;

public class MazeGraph extends BasicAbstractGraph<MazeCell>{
	
	private int width;
	private int height;
	private List<Vertex<MazeCell>> cellsGuide;
	
	public MazeGraph(HashSet<Vertex<MazeCell>> cells, int width, int height) {
		super(cells);
		this.width = width;
		this.height = height;
		
		this.cellsGuide = cells.stream().sorted((v1, v2)->v1.getData().compareTo(v2.getData())).collect(Collectors.toList());
	}
	
	private Vertex<MazeCell> getMazeCell(int row, int column) {
		return cellsGuide.get(column + width*row);
	}

	@Override
	public HashSet<Vertex<MazeCell>> getAdjacents(Vertex<MazeCell> vertex) {
		HashSet<Vertex<MazeCell>> result = new HashSet<>();
		
		int row = vertex.getData().getRow();
		int column = vertex.getData().getColumn();
		if (row != 0) result.add(getMazeCell(row - 1, column));
		if (row != height - 1) result.add(getMazeCell(row + 1, column));
		if (column != 0) result.add(getMazeCell(row, column - 1));
		if (column != width - 1) result.add(getMazeCell(row, column + 1));
				
		result.removeIf(cell -> cell.getData().isBlocked());
		
		return result;
	}

	@Override
	protected double computeWeight(Vertex<MazeCell> from, Vertex<MazeCell> to) {
		return 0.;
	}	
}
