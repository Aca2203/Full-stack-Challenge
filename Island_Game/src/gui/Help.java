package gui;

import java.awt.*;
import java.awt.event.*;

import javax.swing.JLabel;

@SuppressWarnings("serial")
public class Help extends Frame {
	
	private Menu menu;
	
	public Help(Menu menu) {
		this.menu = menu;
		
		setBounds(500, 50, 300, 200);
		setResizable(false);
		setTitle("Help");
		
		setLayout(new GridLayout(0, 1));
		
		JLabel label = new JLabel("<html>The Island Game is a small game where your goal is to"
				+ " find the island with the greatest average height. You have three attempts to guess the correct island "
				+ "and if you don't, you can restart and try again. <br> <br>Good luck!</html>");
		this.add(label, BorderLayout.NORTH);
		
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				dispose();
				menu.setVisible(true);
			}
		});
		
		setVisible(true);
	}
}
