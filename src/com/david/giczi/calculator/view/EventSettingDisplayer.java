package com.david.giczi.calculator.view;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;


public class EventSettingDisplayer {

	private JFrame jFrame;
	private JComboBox<String> jComboBox;
	private JTextArea eventTextArea;
	private JButton jOkButton = new JButton("Létrehoz");
	private JPanel jPanel;
	private Font font = new Font("Times New Roman", Font.BOLD, 20);
	
	
	public EventSettingDisplayer(String monthDotYear) {
		jFrame = new JFrame("Esemény létrehozása");
		jPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		jPanel.setBorder ( new TitledBorder ( new EtchedBorder (), monthDotYear ) );
		jComboBox = new JComboBox<>();
		jComboBox.addItem("-");
		addComboBoxToFrame();
		addInputTextAreaToFrame();
		addOkButtonToFrame();
	}
	
	public void getDisplayer() {
		
		jFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		jFrame.setSize(450, 350);
		jFrame.setLocationRelativeTo(null);
		jFrame.setResizable(false);
		jFrame.setVisible(true);
	}
	
	private void addComboBoxToFrame() {
		DefaultListCellRenderer renderer = new DefaultListCellRenderer();
		renderer.setHorizontalAlignment(DefaultListCellRenderer.CENTER);
		jComboBox.setRenderer(renderer);
		jComboBox.setCursor(new Cursor(Cursor.HAND_CURSOR));
		jComboBox.setPreferredSize(new Dimension(50, 30));
		JLabel jLabel = new JLabel("Az esemény napjának választása:");
		jPanel.add(jLabel);
		jPanel.add(Box.createHorizontalStrut(10));
		jPanel.add(jComboBox);
		jFrame.add(jPanel);
		
	}
	
	private void addInputTextAreaToFrame() {
		
		eventTextArea = new JTextArea();
		eventTextArea.setFont(font);
		eventTextArea.setLineWrap(true);
		JScrollPane scrollPane = new JScrollPane(eventTextArea,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setPreferredSize(new Dimension(400, 200));
		jPanel.add(scrollPane);
	}
	
	private void addOkButtonToFrame() {
		jOkButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		jPanel.add(jOkButton);
		jFrame.add(jPanel);
	}
		
	public static void main(String[] args) {
		
		new EventSettingDisplayer("2022. január").getDisplayer();
	}
}
