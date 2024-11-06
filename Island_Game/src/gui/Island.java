package gui;

public class Island {
	static int nextID = 0;
	private int id = nextID++;
	private double averageHeight = 0;
	private Map map;
	
	public Island(Map map) { this.map = map; }
	
	public void addLand(int i, int j) {
		Land land = (Land) map.cells[i][j];
		land.setIslandID(id);
		if(i > 0 && map.cells[i-1][j] instanceof Land && ((Land) map.cells[i-1][j]).getIslandID() == -1){
			addLand(i - 1, j);
		}
		if(i < map.cells.length - 1 && map.cells[i+1][j] instanceof Land && ((Land) map.cells[i+1][j]).getIslandID() == -1){
			addLand(i + 1, j);
		}
		if(j > 0 && map.cells[i][j-1] instanceof Land && ((Land) map.cells[i][j-1]).getIslandID() == -1){
			addLand(i, j - 1);
		}
		if(j < map.cells[i].length - 1 && map.cells[i][j+1] instanceof Land && ((Land) map.cells[i][j+1]).getIslandID() == -1){
			addLand(i, j + 1);
		}
	}
	
	public void calculateAverageHeight() {
		double sum = 0;
		int cellCount = 0;
		
		for(int i = 0; i < map.cells.length; i++)
			for(int j = 0; j < map.cells[i].length; j++) {
				if(map.cells[i][j] instanceof Land) {					
					Land land = (Land) map.cells[i][j];
					if(land.getIslandID() == this.id) {						
						sum += land.height;
						cellCount++;
					}
				}
			}
				
		double average = sum / cellCount;
		
		this.averageHeight = average;
	}

	public double getAverageHeight() {
		return averageHeight;
	}	
	
}
