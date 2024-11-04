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
	
	public Game() {
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
						
		} catch (Exception e) { System.out.println(e.getMessage()); }		
		
		setLocation(500, 50);
		setResizable(false);
		setTitle("Game");
		
		fillWindow();
		pack();
		
		setVisible(true);
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});
	}
	
	private void fillWindow() {
		Cell[][] cells = map.cells;		
		for(int i = 0; i < cells.length; i++)
			for(int j = 0; j < cells[i].length; j++){
				mapPanel.add(cells[i][j]);
			}
		this.add(mapPanel);
	}

	public static void main(String[] args) {
		new Game();
	}

}
