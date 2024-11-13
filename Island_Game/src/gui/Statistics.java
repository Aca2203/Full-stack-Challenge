package gui;

import java.awt.*;
import java.awt.event.*;
import java.io.*;

@SuppressWarnings("serial")
public class Statistics extends Frame {
	
	private Menu menu;
	private Panel panel = new Panel(new GridLayout(4, 2));
	private TextField gamesField = new TextField();
	private TextField winsField = new TextField();
	private TextField lossesField = new TextField();
	private TextField winRateField = new TextField();
	
	private int numberOfWins;
	private int numberOfLosses;
	
	public Statistics(Menu menu) {
		this.menu = menu;
		loadStatistics();
		
		setBounds(500, 50, 300, 150);
		setResizable(false);
		setTitle("Player statistics");
		
		fillWindow();
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				dispose();
				menu.setVisible(true);
			}
		});
		
		setVisible(true);
	}
	
	private void loadStatistics() {
		File file = new File("PlayerStatistics.txt");
		if(!file.exists()) {
			try {
				file.createNewFile();
				FileWriter fw = new FileWriter(file);
				fw.write("0,0");
				fw.close();
			} catch (IOException e) {
				System.out.println("Error with opening the file.");
			}
			
		}
		
		try {
			FileReader fr = new FileReader(file);
			StringBuilder sb = new StringBuilder();
			int ch;
			while((ch = fr.read()) != -1) sb.append((char)ch);
			String content = sb.toString();
			String[] elements = content.split(",", 2);
			this.numberOfWins = Integer.parseInt(elements[0]);
			this.numberOfLosses = Integer.parseInt(elements[1]);			
		} catch (IOException e) {
			System.out.println("Error with reading from the file.");
		}
		
	}

	private void fillWindow() {
		panel.add(new Label("Number of games:"));
		gamesField.setText(this.numberOfWins + this.numberOfLosses + "");
		gamesField.setEnabled(false);
		panel.add(gamesField);
		panel.add(new Label("Number of wins:"));
		winsField.setText(this.numberOfWins + "");
		winsField.setEnabled(false);
		panel.add(winsField);
		panel.add(new Label("Number of losses:"));
		lossesField.setText(this.numberOfLosses + "");
		lossesField.setEnabled(false);
		panel.add(lossesField);
		panel.add(new Label("Win rate:"));
		winRateField.setText(String.format("%.1f", (double) this.numberOfWins / (this.numberOfWins + this.numberOfLosses) * 100) + "%");
		winRateField.setEnabled(false);
		panel.add(winRateField);
		this.add(panel);
	}
}
