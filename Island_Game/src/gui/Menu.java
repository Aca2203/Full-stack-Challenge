package gui;

import java.awt.*;
import java.awt.event.*;

@SuppressWarnings("serial")
public class Menu extends Frame {

	private Panel centerPanel = new Panel(new GridLayout(0, 1));
	private Button playButton = new Button("Play!");
	private Button statisticsButton = new Button("Statistics");
	private Button helpButton = new Button("Help");	
	
	public Menu() {
		setBounds(500, 50, 300, 200);
		setResizable(false);
		setTitle("Main menu");
		
		fillWindow();
		addListeners();
		
		setVisible(true);
	}
	
	private void fillWindow() {
		centerPanel.add(new Label("Welcome to The Island Game!"));
		centerPanel.add(playButton);
		centerPanel.add(statisticsButton);
		centerPanel.add(helpButton);
		this.add(centerPanel, BorderLayout.CENTER);
	}

	private void addListeners() {
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});
		
		playButton.addActionListener((ae) -> {
			this.setVisible(false);
			new Game(this);
		});
		
		statisticsButton.addActionListener((ae) -> {
			this.setVisible(false);
			new Statistics(this);
		});
		
		helpButton.addActionListener((ae) -> {
			this.setVisible(false);
			new Help(this);
		});
	}

	public static void main(String[] args) {
		new Menu();
	}

}
