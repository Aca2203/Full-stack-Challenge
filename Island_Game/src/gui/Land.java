package gui;

@SuppressWarnings("serial")
public class Land extends Cell {

	private int islandID = -1;
	
	public int getIslandID() {
		return islandID;
	}

	public void setIslandID(int islandID) {
		this.islandID = islandID;
	}

	public Land(int height) throws HeightException {		
		super(height);
	}

}
