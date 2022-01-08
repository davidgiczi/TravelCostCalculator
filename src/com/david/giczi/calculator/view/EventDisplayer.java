package com.david.giczi.calculator.view;

import javax.swing.JFrame;

public class EventDisplayer {

	
public JFrame jFrame;
	
	
	public EventDisplayer() {
		jFrame = new JFrame("Esemény");
	}
	
	public void getDisplayer() {
		
		jFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		jFrame.setSize(450, 350);
		jFrame.setLocationRelativeTo(null);
		jFrame.setResizable(false);
		jFrame.setVisible(true);
	}
}
