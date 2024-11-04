package gui;

public class Map {
	protected Cell[][] cells;
	
	public Map(int numOfRows, int numOfColumns) {
		cells = new Cell[numOfRows][numOfColumns];		
	}
	
	public void refresh() {
		for(int i = 0; i < cells.length; i++)
			for(int j = 0; j < cells[i].length; j++)
				cells[i][j].repaint();
	}
}
