package com.david.giczi.calculator.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
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
	
	public EventDisplayer(String textOfEvent, String evenDate) {
		getDisplayer();
		addTextAreaToFrame(textOfEvent, evenDate);
	}
	
	private void getDisplayer() {
		jFrame = new JFrame("Esemény");
		jFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		jFrame.setSize(450, 300);
		jFrame.setLocationRelativeTo(null);
		jFrame.setAlwaysOnTop(true);
		jFrame.setResizable(false);
	}
	
	private void addTextAreaToFrame(String textOfEvent, String eventDate) {

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
		jPanel.setBorder (new TitledBorder (new EtchedBorder(), eventDate));
		jPanel.add(scrollPane);
		JButton closeButton = new JButton("Bezár");
		closeButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				jFrame.setVisible(false);
				
			}
		});
		jPanel.add(closeButton);
		jFrame.add(jPanel);
		
	}
	
	
}
