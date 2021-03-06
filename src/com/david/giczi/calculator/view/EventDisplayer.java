package com.david.giczi.calculator.view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
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
	
	public EventDisplayer(String textOfEvent, String eventDate) {
		getDisplayer();
		addTextAreaToFrame(textOfEvent, eventDate);
	}
	
	private void getDisplayer() {
		jFrame = new JFrame("Esem?ny");
		jFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		jFrame.addWindowListener(new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowClosing(WindowEvent e) {
				jFrame.setVisible(false);
			}
			
			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
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
		eventTextArea.setBackground(new Color(220, 220, 220));
		JScrollPane scrollPane = new JScrollPane(eventTextArea,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setPreferredSize(new Dimension(400, 200));
		jPanel.setBorder (new TitledBorder (new EtchedBorder(), eventDate));
		jPanel.add(scrollPane);
		addCloseButtonToFrame(jPanel);
		jFrame.add(jPanel);
		
	}
	
	private void addCloseButtonToFrame(JPanel jPanel) {
		
		JButton closeButton = new JButton("Bez?r");
		closeButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		closeButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				jFrame.setVisible(false);
				
			}
		});
		jPanel.add(closeButton);
	}
	
}
