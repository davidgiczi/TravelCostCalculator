package com.david.giczi.calculator.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.david.giczi.calculator.model.Day;

public class DaysOfMonthDisplayer {

	private JFrame jFrame;
	private Font font;
	private JButton[] jButtonStoreForDays;
	
	public DaysOfMonthDisplayer() {
		
		jFrame = new JFrame("Munkanapok megadása");
		getDisplayer();
		addNamesOfDaysPanelToTheFrame();
	}
	
	private void getDisplayer() {
		
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.setSize(700, 500);
		jFrame.setLayout(new GridLayout(7, 7));
		jFrame.setLocationRelativeTo(null);
		jFrame.setResizable(false);
		jFrame.setJMenuBar(getMenuBar());
		jFrame.setVisible(true);
		
	}
	
	private JMenuBar getMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		JMenu chooseTemplate = new JMenu("Sablon fájl választása");
		JMenu createPdfFile = new JMenu("Pdf fájl létrehozása");
		chooseTemplate.setCursor(new Cursor(Cursor.HAND_CURSOR));
		createPdfFile.setCursor(new Cursor(Cursor.HAND_CURSOR));
		menuBar.add(chooseTemplate);
		menuBar.add(createPdfFile);
		return menuBar;
	}
	
	private void addNamesOfDaysPanelToTheFrame() {
		
		String[] namesOfDays = {"Hétfõ", "Kedd", "Szerda", "Csütörtök",
								"Péntek", "Szombat", "Vasárnap"};
		
		JPanel jPanelForNamesOfDays = new JPanel();
		jPanelForNamesOfDays.setLayout(new GridLayout(1, 7));
		font = new Font("Times New Roman", Font.BOLD, 20);
		for(int i = 0; i < 7; i++) {
			JLabel jButtonForNameOfDay = new JLabel(namesOfDays[i], SwingConstants.CENTER);
			jButtonForNameOfDay.setFont(font);
			jPanelForNamesOfDays.add(jButtonForNameOfDay);
		}
		jFrame.add(jPanelForNamesOfDays);
	}
	

	
	private void addButtonsOfDaysToTheFrame(List<Day> dayStore) {
		
		jButtonStoreForDays = new JButton[35];
			
		for(int i = 0; i < 5; i++) {
			
			JPanel rowPanel = new JPanel();
			rowPanel.setLayout(new GridLayout(1, 7));
			
			for(int j = 0; j < 7; j++) {
				jButtonStoreForDays[i * 7 + j] = new JButton();
				jButtonStoreForDays[i * 7 + j].setCursor(new Cursor(Cursor.HAND_CURSOR));
				if(dayStore.get(i * 7 + j).isWorkDay()) {
				jButtonStoreForDays[i * 7 + j].setBackground(new Color(212, 235, 242)); 
				}
				else {
				jButtonStoreForDays[i * 7 + j].setBackground(new Color(255, 255, 212));
				}
				
				if(dayStore.get(i * 7 + j).getNumberOfMonth() != -1) {
					jButtonStoreForDays[i * 7 + j].setText(String.valueOf(dayStore.get(i * 7 + j).getNumberOfMonth()));
				}
				else {
					jButtonStoreForDays[i * 7 + j].setEnabled(false);
				}
				rowPanel.add(jButtonStoreForDays[i * 7 + j]);
			}
			
			jFrame.add(rowPanel);
		}
	}
	
	public void addOtherMonthAskingLabelsToTheFrame(String yearDotMonth) {

		JPanel lastRowPanel = new JPanel();
		lastRowPanel.setLayout(new BorderLayout());
		JLabel nextMonth = new JLabel(">>          ");
		nextMonth.setFont(font);
		nextMonth.setCursor(new Cursor(Cursor.HAND_CURSOR));
		JLabel prevMonth= new JLabel("          <<");
		prevMonth.setFont(font);
		JLabel actualMonth= new JLabel(yearDotMonth);
		actualMonth.setFont(font);
		actualMonth.setHorizontalAlignment(SwingConstants.CENTER);
		prevMonth.setCursor(new Cursor(Cursor.HAND_CURSOR));
		lastRowPanel.add(prevMonth, BorderLayout.WEST);
		lastRowPanel.add(actualMonth);
		lastRowPanel.add(nextMonth, BorderLayout.EAST);
		jFrame.add(lastRowPanel);
	}
	
}
