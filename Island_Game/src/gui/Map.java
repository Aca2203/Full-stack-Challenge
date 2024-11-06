package gui;

import java.util.ArrayList;

public class Map {
	protected Cell[][] cells;
	protected ArrayList<Island> islands = new ArrayList<>();
	
	public Map(int numOfRows, int numOfColumns) {
		cells = new Cell[numOfRows][numOfColumns];
	}
	
	public void refresh() {
		for(int i = 0; i < cells.length; i++)
			for(int j = 0; j < cells[i].length; j++)
				cells[i][j].repaint();
	}
	
	public void addIsland(Island island) {
		if(!islands.contains(island)) islands.add(island);
	}
	
	public void deleteMap() {				
		islands.clear();
	}
}
