package gui;

import java.awt.*;
import java.awt.event.*;

import javax.swing.GroupLayout.Alignment;

@SuppressWarnings("serial")
public class Menu extends Frame {

	private Panel centerPanel = new Panel(new GridLayout(0, 1));
	private Button playButton = new Button("Play!");
	
	public Menu() {
		setBounds(500, 50, 500, 500);
		setResizable(false);
		setTitle("Main menu");
		
		fillWindow();
		addListeners();
		
		setVisible(true);
	}
	
	private void fillWindow() {
		centerPanel.add(new Label("Island game"));
		centerPanel.add(playButton);
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
	}

	public static void main(String[] args) {
		new Menu();
	}

}
