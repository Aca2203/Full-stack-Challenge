package gui;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class Statistics extends Frame {
	
	private Menu menu;
	private Panel panel = new Panel(new GridLayout(6, 2));
	private TextField gamesField = new TextField();
	private TextField winsField = new TextField();
	private TextField lossesField = new TextField();
	private TextField winRateField = new TextField();
	private TextField timeField = new TextField();
	
	private Button resetButton = new Button("Reset statistics");
	
	private int numberOfWins;
	private int numberOfLosses;
	private ArrayList<Integer> times = new ArrayList<>();
	private double averageGuessTime;	
	
	public Statistics(Menu menu) {
		this.menu = menu;
		loadStatistics();
		
		setBounds(500, 50, 300, 200);
		setResizable(false);
		setTitle("Player statistics");
		
		fillWindow();
		
		resetButton.addActionListener((ae) -> {
			try {
				File file = new File("PlayerStatistics.txt");
				FileWriter fw = new FileWriter(file);
				fw.write("0,0");
				fw.close();
			} catch (IOException e) {
				System.out.println("Error writing into a file.");
			}
			
			times.clear();
			loadStatistics();
			
			gamesField.setText(this.numberOfWins + this.numberOfLosses + "");
			winsField.setText(this.numberOfWins + "");
			lossesField.setText(this.numberOfLosses + "");
			if(this.numberOfWins + this.numberOfLosses == 0) winRateField.setText("-");
			else winRateField.setText(String.format("%.1f", (double) this.numberOfWins / (this.numberOfWins + this.numberOfLosses) * 100) + "%");
			if(times.size() == 0) timeField.setText("-");
			else timeField.setText(String.format("%.2f", this.averageGuessTime) + "s");
		});
		
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
			fr.close();
			String content = sb.toString();
			String[] elements = content.split(",");
			this.numberOfWins = Integer.parseInt(elements[0]);
			this.numberOfLosses = Integer.parseInt(elements[1]);
			
			double sum = 0;
			for(int i = 2; i < elements.length; i++) {
				int time = Integer.parseInt(elements[i]);
				sum += time;
				times.add(time);
			}			
			
			this.averageGuessTime = sum / (elements.length - 2);
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
		
		if(this.numberOfWins + this.numberOfLosses == 0) winRateField.setText("-");
		else winRateField.setText(String.format("%.1f", (double) this.numberOfWins / (this.numberOfWins + this.numberOfLosses) * 100) + "%");
		
		winRateField.setEnabled(false);
		panel.add(winRateField);
		panel.add(new Label("Average guess time:"));
		if(times.size() == 0) timeField.setText("-");
		else timeField.setText(String.format("%.2f", this.averageGuessTime) + "s");
		timeField.setEnabled(false);
		panel.add(timeField);
		
		panel.add(resetButton);
		
		this.add(panel);
	}
}
