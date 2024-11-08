package gui;

import java.util.ArrayList;

public class Map {
	protected Cell[][] cells;
	protected ArrayList<Island> islands = new ArrayList<>();
	private static double maximumAverageHeight = 0;
	private static int highestIslandID = -1;
	
	public static double getMaximumAverageHeight() {
		return maximumAverageHeight;
	}

	public static int getHighestIslandID() {
		return highestIslandID;
	}

	public static void setMaximumAverageHeight(Island island) {
		if(maximumAverageHeight < island.getAverageHeight()) {
			maximumAverageHeight = island.getAverageHeight();
			highestIslandID = island.getID();
		}
	}

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
		maximumAverageHeight = 0;
		highestIslandID = -1;
	}
}
