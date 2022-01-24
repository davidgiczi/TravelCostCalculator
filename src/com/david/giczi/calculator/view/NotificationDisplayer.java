package com.david.giczi.calculator.view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class NotificationDisplayer {

	private JFrame jFrame;
	private JPanel jPanel;
	public static boolean isNotify = true;
	private Font font = new Font("Times New Roman", Font.BOLD, 20);
	private Color textColor = new Color(112, 128, 180);
	
	public NotificationDisplayer(String date, String firstNames, String officialEvent, String ownerEvent) {
		getDisplayer(date, officialEvent);
		addTextAreaToFrame(ownerEvent, firstNames);
	}
	
	
	private void getDisplayer(String date, String officialEvent) {
		jFrame = new JFrame(date + officialEvent);
		jFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		jFrame.setSize(600, 200);
		Double locationX = (getScreenWidth() - jFrame.getSize().getWidth())  / 2;
		jFrame.setLocation(locationX.intValue(), 30);
		jFrame.setResizable(false);
	}
	
	private Double getScreenWidth() {
		return Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	}
	
	private void addTextAreaToFrame(String ownerEvent, String firstNames) {

		JTextArea eventTextArea = new JTextArea();
		jPanel = new JPanel();
		eventTextArea.setFont(font);
		eventTextArea.setForeground(textColor);
		eventTextArea.setLineWrap(true);
		eventTextArea.setText(ownerEvent);
		eventTextArea.setEditable(false);
		eventTextArea.setBackground(new Color(220, 220, 220));
		JScrollPane scrollPane = new JScrollPane(eventTextArea,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setPreferredSize(new Dimension(550, 100));
		jPanel.setBorder (new TitledBorder (new EtchedBorder(), firstNames));
		jPanel.add(scrollPane);
		jFrame.add(jPanel);
		addCheckBoxToFrame();
		jFrame.setVisible(true);
		
	}
	
	private void addCheckBoxToFrame() {
		JCheckBox jCheckBox = new JCheckBox("Ne jelenjen meg többé");
		jCheckBox.setCursor(new Cursor(Cursor.HAND_CURSOR));
		jCheckBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				isNotify = false;
			}
		});
		jPanel.add(jCheckBox);
		jFrame.add(jPanel);
	}
	
}
