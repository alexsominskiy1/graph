package examples.cells.traveller;

import examples.cells.Cell;

public class ValuedCell extends Cell{
	
	private double value;
	
	public ValuedCell(int row, int column, double value) {
		super(row, column);
		this.value = value;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}
	
}
