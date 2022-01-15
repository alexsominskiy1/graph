package examples.cells.maze;

import examples.cells.Cell;

public class MazeCell extends Cell{

	private boolean blocked = false;
	
	public MazeCell (int row, int column) {
		super(row, column);
	}

	public boolean isBlocked() {
		return blocked;
	}

	public void setBlocked(boolean blocked) {
		this.blocked = blocked;
	}

}
