package gui;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

@SuppressWarnings("serial")
public class Game extends Frame {
		
	private static int numberOfCells = 30;
	private Panel mapPanel = new Panel(new GridLayout(30, 30));
	private Map map = new Map(numberOfCells, numberOfCells);	
	private Button restartButton = new Button("Restart game");
	private Label numberOfAttemptsLabel = new Label("Number of attempts: 3");
	private Label message = new Label();
	
	private int numberOfAttempts;
	
	public Game() {
		generateMap();
		
		setLocation(500, 50);
		setResizable(false);
		setTitle("Game");
		
		fillWindow();
		pack();
		
		addListeners();
		
		setVisible(true);
	}
	
	@SuppressWarnings("deprecation")
	private void generateMap() {
		this.numberOfAttempts = 3;
		numberOfAttemptsLabel.setText("Number of attempts: " + numberOfAttempts);
		message.setText("");
		URL url;
		HttpURLConnection con;
		try {			
			url = new URL("https://jobfair.nordeus.com/jf24-fullstack-challenge/test");
			con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			String words[] = new String[numberOfCells];
			int i = 0;
			while ((inputLine = in.readLine()) != null && i < numberOfCells) {
				words = inputLine.split(" ", numberOfCells);
				for(int j = 0; j < numberOfCells; j++) {
					int height = Integer.parseInt(words[j]);
					if(height == 0) map.cells[i][j] = new Water();
					else map.cells[i][j] = new Land(height);				
				}
				
				i++;
			}
			
			in.close();
			
			findIslands();
						
		} catch (Exception e) { System.out.println(e.getMessage()); }	
	}
	
	private void addListeners() {		
		restartButton.addActionListener((ae) -> {
			restartButton.setEnabled(false);
			Cell[][] oldCells = new Cell[numberOfCells][numberOfCells];
			for(int i = 0; i < numberOfCells; i++)
				for(int j = 0; j < numberOfCells; j++) {
					oldCells[i][j] = map.cells[i][j];
					mapPanel.remove(map.cells[i][j]);					
				}
			map.deleteMap();
			generateMap();
			addCellListeners();
			for(int i = 0; i < numberOfCells; i++)
				for(int j = 0; j < numberOfCells; j++) {
					map.cells[i][j].setBounds(oldCells[i][j].getBounds());
					mapPanel.add(map.cells[i][j]);
				}
		});
		
		addCellListeners();
		
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});
	}

	private void addCellListeners() {
		for(int i = 0; i < numberOfCells; i++)
			for(int j = 0; j < numberOfCells; j++) {
				final int ii = i;
				final int jj = j;
				map.cells[i][j].addMouseListener(new MouseAdapter() {
					public void mousePressed(MouseEvent e) {						
						if(map.cells[ii][jj] instanceof Water) message.setText("Water!");
						else {
							numberOfAttempts--;							
							if(((Land) map.cells[ii][jj]).getIslandID() == Map.getHighestIslandID()) {
								message.setText("You won!");
								deactivateMap();
								restartButton.setEnabled(true);									
							} else {									 
								 if(numberOfAttempts == 0) {
									 message.setText("Game over!");
									 numberOfAttemptsLabel.setText("Number of attempts: 0");
									 deactivateMap();
									 restartButton.setEnabled(true);
								 }
								 else {
									 message.setText("You are wrong, try again!");
									 numberOfAttemptsLabel.setText("Number of attempts: " + numberOfAttempts);
								 }
							}
						}
					}
				});
			}
	}

	protected void deactivateMap() {
		for(int i = 0; i < numberOfCells; i++)
			for(int j = 0; j < numberOfCells; j++)
				map.cells[i][j].setEnabled(false);
	}

	private void findIslands() {
		for(int i = 0; i < numberOfCells; i++)
			for(int j = 0; j < numberOfCells; j++) {
				if(map.cells[i][j] instanceof Land && ((Land) map.cells[i][j]).getIslandID() == -1) {
					Island island = new Island(map);
					map.addIsland(island);
					island.addLand(i, j);
					island.calculateAverageHeight();
					Map.setMaximumAverageHeight(island);
				}
			}		
	}

	private void fillWindow() {
		Cell[][] cells = map.cells;
		for(int i = 0; i < cells.length; i++)
			for(int j = 0; j < cells[i].length; j++){
				mapPanel.add(cells[i][j]);
			}
		this.add(mapPanel, BorderLayout.CENTER);
		Panel controlPanel = new Panel(new GridLayout(0, 1));
		restartButton.setEnabled(false);
		controlPanel.add(numberOfAttemptsLabel);
		controlPanel.add(message);
		controlPanel.add(restartButton);
		this.add(controlPanel, BorderLayout.EAST);
	}

	public static void main(String[] args) {
		new Game();
	}

}
