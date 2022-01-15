package examples.cells.traveller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import graph.basics.BasicAbstractGraph;
import graph.interfaces.Vertex;

public class TravellerGraph extends BasicAbstractGraph<ValuedCell>{
	
	private int width;
	private int height;
	private List<Vertex<ValuedCell>> cellsGuide;

	public TravellerGraph(HashSet<Vertex<ValuedCell>> cells, int width, int height) {
		super(cells);
		this.width = width;
		this.height = height;
		
		this.cellsGuide = cells.stream().sorted((v1, v2)->v1.getData().compareTo(v2.getData()))
				 .collect(Collectors.toCollection(()-> new ArrayList<Vertex<ValuedCell>>()));
	}
	
	private Vertex<ValuedCell> getValuedCell(int row, int column) {
		return cellsGuide.get(column + width*row);
	}

	@Override
	public HashSet<Vertex<ValuedCell>> getAdjacents(Vertex<ValuedCell> vertex) {
		HashSet<Vertex<ValuedCell>> result = new HashSet<>();
		
		int row = vertex.getData().getRow();
		int column = vertex.getData().getColumn();
		if (row != 0) result.add(getValuedCell(row - 1, column));
		if (row != height - 1) result.add(getValuedCell(row + 1, column));
		if (column != 0) result.add(getValuedCell(row, column - 1));
		if (column != width - 1) result.add(getValuedCell(row, column + 1));
		
		return result;
	}

	@Override
	public double computeWeight(Vertex<ValuedCell> from, Vertex<ValuedCell> to) {
		double weight = getValuedCell(to.getData().getRow(), to.getData().getColumn()).getData().getValue();
		return weight;
	}

}
