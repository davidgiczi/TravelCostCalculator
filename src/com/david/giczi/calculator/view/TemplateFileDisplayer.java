package com.david.giczi.calculator.view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.Box;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;


public class TemplateFileDisplayer {

	private JFrame jFrame;
	private Font font = new Font("Times New Roman", Font.BOLD, 20);
	private Color textColor = new Color(112, 128, 180);
	private JComboBox<String> jComboBox = new JComboBox<>();
	private JLabel jLabelForWorkerName = new JLabel();
	private JLabel jLabelForWorkerAddress = new JLabel();
	private JLabel jLabelForEmployerName = new JLabel();
	private JLabel jLabelForEmployerAddress = new JLabel();
	private JLabel jLabelForTravelDistance = new JLabel();
	private JLabel jLabelForTravelPrice = new JLabel();
	
	
	public TemplateFileDisplayer(String workerName, String workerAddress,
			String employerName, String employerAddress, String distance, String price) {
		jFrame = new JFrame("Sablon fájl választása");
		addComboBoxToFrame();
		addSeparatorToFrame();
		getDisplayer();
		jLabelForWorkerName.setText(workerName);
		jLabelForWorkerAddress.setText(workerAddress);
		jLabelForEmployerName.setText(employerName);
		jLabelForEmployerAddress.setText(employerAddress);
		jLabelForTravelDistance.setText(distance);
		jLabelForTravelPrice.setText(price);
		addTemplateFileDataToFrame();
	}

	private void getDisplayer() {

		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.setSize(700, 500);
		jFrame.setLayout(new GridLayout(7, 1));
		jFrame.setLocationRelativeTo(null);
		jFrame.setResizable(false);
		jFrame.setJMenuBar(getMenuBar());
		jFrame.setVisible(true);

	}

	private JMenuBar getMenuBar() {
		
		JMenuBar menuBar = new JMenuBar();
		JMenu createTemplate = new JMenu("Sablon fájl létrehozása/módosítása");
		JMenu addWorkDays = new JMenu("Munkanapok megadása");
		addWorkDays.addMenuListener(new MenuListener() {
			
			@Override
			public void menuSelected(MenuEvent e) {
				
				jFrame.setVisible(false);
				new DaysOfMonthDisplayer();
				
			}
			
			@Override
			public void menuDeselected(MenuEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void menuCanceled(MenuEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		createTemplate.setCursor(new Cursor(Cursor.HAND_CURSOR));
		addWorkDays.setCursor(new Cursor(Cursor.HAND_CURSOR));
		menuBar.add(createTemplate);
		menuBar.add(addWorkDays);
		
		return menuBar;
	}
	
	private void addComboBoxToFrame() {
		jComboBox.addItem("    -     ");
		jComboBox.setCursor(new Cursor(Cursor.HAND_CURSOR));
		JPanel jPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel jLabel = new JLabel("Sablon fájl választása:");
		jPanel.add(jLabel);
		jPanel.add(Box.createHorizontalStrut(30));
		jPanel.add(jComboBox);
		jFrame.add(jPanel);
		
	}
	
	private void addSeparatorToFrame() {
		JSeparator separator = new JSeparator();
		jFrame.add(separator);
	}
	
	private void addTemplateFileDataToFrame() {
		JPanel jPanelForWorkerName = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel workerName = new JLabel("A dolgozó neve:");
		jPanelForWorkerName.add(workerName);
		jPanelForWorkerName.add(Box.createHorizontalStrut(30));
		jLabelForWorkerName.setFont(font);
		jLabelForWorkerName.setForeground(textColor);
		jPanelForWorkerName.add(jLabelForWorkerName);
		
		JPanel jPanelForWorkerAddress = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel workerAddress = new JLabel("A dolgozó címe:");
		jPanelForWorkerAddress.add(workerAddress);
		jPanelForWorkerAddress.add(Box.createHorizontalStrut(30));
		jLabelForWorkerAddress.setFont(font);
		jLabelForWorkerAddress.setForeground(textColor);
		jPanelForWorkerAddress.add(jLabelForWorkerAddress);
		
		JPanel jPanelForEmployeeName = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel employeeName = new JLabel("A munkahely neve:");
		jPanelForEmployeeName.add(employeeName);
		jPanelForEmployeeName.add(Box.createHorizontalStrut(15));
		jLabelForEmployerName.setFont(font);
		jLabelForEmployerName.setForeground(textColor);
		jPanelForEmployeeName.add(jLabelForEmployerName);
		
		JPanel jPanelForEmployeeAddress = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel employeeAddress = new JLabel("A munkahely címe:");
		jPanelForEmployeeAddress.add(employeeAddress);
		jPanelForEmployeeAddress.add(Box.createHorizontalStrut(15));
		jLabelForEmployerAddress.setFont(font);
		jLabelForEmployerAddress.setForeground(textColor);
		jPanelForEmployeeAddress.add(jLabelForEmployerAddress);
		
		JPanel jPanelForTravelCostData = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel travelDistance = new JLabel("Távolság:");
		JLabel jLabelForDistanceUnit = new JLabel("km");
		jPanelForTravelCostData.add(travelDistance);
		jPanelForTravelCostData.add(Box.createHorizontalStrut(65));
		jLabelForTravelDistance.setFont(font);
		jLabelForTravelDistance.setForeground(textColor);
		jPanelForTravelCostData.add(jLabelForTravelDistance);
		jPanelForTravelCostData.add(Box.createHorizontalStrut(10));
		jPanelForTravelCostData.add(jLabelForDistanceUnit);
		jPanelForTravelCostData.add(Box.createHorizontalStrut(30));
		JLabel travelPrice = new JLabel("Elszámolási díj:");
		jPanelForTravelCostData.add(travelPrice);
		jPanelForTravelCostData.add(Box.createHorizontalStrut(30));
		jLabelForTravelPrice.setFont(font);
		jLabelForTravelPrice.setForeground(textColor);
		jPanelForTravelCostData.add(jLabelForTravelPrice);
		jPanelForTravelCostData.add(Box.createHorizontalStrut(10));
		JLabel jLabelForPriceUnit = new JLabel("Ft/km");
		jPanelForTravelCostData.add(jLabelForPriceUnit);
		
		jFrame.add(jPanelForWorkerName);
		jFrame.add(jPanelForWorkerAddress);
		jFrame.add(jPanelForEmployeeName);
		jFrame.add(jPanelForEmployeeAddress);
		jFrame.add(jPanelForTravelCostData);
	}

}
