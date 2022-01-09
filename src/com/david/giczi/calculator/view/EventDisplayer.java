package com.david.giczi.calculator.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class EventDisplayer {

	public JFrame jFrame;
	private Font font = new Font("Times New Roman", Font.BOLD, 20);
	private Color textColor = new Color(112, 128, 180);
	
	public EventDisplayer(String textOfEvent, String yearDotMonth) {
		getDisplayer();
		addTextAreaToFrame(textOfEvent, yearDotMonth);
	}
	
	private void getDisplayer() {
		jFrame = new JFrame("Esemény");
		jFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		jFrame.setSize(450, 280);
		jFrame.setLocationRelativeTo(null);
		jFrame.setResizable(false);
		
	}
	
	private void addTextAreaToFrame(String textOfEvent, String yearDotMonth) {

		JTextArea eventTextArea = new JTextArea();
		JPanel jPanel = new JPanel();
		eventTextArea.setFont(font);
		eventTextArea.setForeground(textColor);
		eventTextArea.setLineWrap(true);
		eventTextArea.setText(textOfEvent);
		eventTextArea.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(eventTextArea,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setPreferredSize(new Dimension(400, 200));
		jPanel.setBorder (new TitledBorder (new EtchedBorder(), yearDotMonth));
		jPanel.add(scrollPane);
		jFrame.add(jPanel);
		jFrame.setVisible(true);
	}
	
}
