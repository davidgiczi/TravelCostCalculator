package com.david.giczi.calculator.view;

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
import javax.swing.JTextField;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;


public class TemplateFileDisplayer {

	private JFrame jFrame;
	private Font font = new Font("Times New Roman", Font.BOLD, 20);
	private JComboBox<String> jComboBox = new JComboBox<>();
	private JTextField jTextFieldForWorkerName = new JTextField(30);
	private JTextField jTextFieldForWorkerAddress = new JTextField(30);
	private JTextField jTextFieldForEmployerName = new JTextField(30);
	private JTextField jTextFieldForEmployerAddress = new JTextField(30);
	private JTextField jTextFieldForTravelDistance = new JTextField(10);
	private JTextField jTextFieldForTravelPrice = new JTextField(10);

	public TemplateFileDisplayer(String workerName, String workerAddress,
			String employerName, String employerAddress, String distance, String price) {
		jFrame = new JFrame("Sablon fájl választása");
		addComboBoxToFrame();
		getDisplayer();
		jTextFieldForWorkerName.setText(workerName);
		jTextFieldForWorkerAddress.setText(workerAddress);
		jTextFieldForEmployerName.setText(employerName);
		jTextFieldForEmployerAddress.setText(employerAddress);
		jTextFieldForTravelDistance.setText(distance);
		jTextFieldForTravelPrice.setText(price);
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
		JPanel jPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel jLabel = new JLabel("Sablon fájl választása:");
		jPanel.add(jLabel);
		jPanel.add(Box.createHorizontalStrut(30));
		jPanel.add(jComboBox);
		jFrame.add(jPanel);
		JSeparator separator = new JSeparator();
		jFrame.add(separator);
		
		
	}
	
	private void addTemplateFileDataToFrame() {
		JPanel jPanelForWorkerName = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel jLabelForWorkerName = new JLabel("A dolgozó neve:");
		jPanelForWorkerName.add(jLabelForWorkerName);
		jPanelForWorkerName.add(Box.createHorizontalStrut(30));
		jTextFieldForWorkerName.setFont(font);
		jPanelForWorkerName.add(jTextFieldForWorkerName);
		
		JPanel jPanelForWorkerAddress = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel jLabelForWorkerAddress = new JLabel("A dolgozó címe:");
		jPanelForWorkerAddress.add(jLabelForWorkerAddress);
		jPanelForWorkerAddress.add(Box.createHorizontalStrut(31));
		jTextFieldForWorkerAddress.setFont(font);
		jPanelForWorkerAddress.add(jTextFieldForWorkerAddress);
		
		JPanel jPanelForEmployeeName = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel jLabelForEmployeeName = new JLabel("A munkahely neve:");
		jPanelForEmployeeName.add(jLabelForEmployeeName);
		jPanelForEmployeeName.add(Box.createHorizontalStrut(14));
		jTextFieldForEmployerName.setFont(font);
		jPanelForEmployeeName.add(jTextFieldForEmployerName);
		
		JPanel jPanelForEmployeeAddress = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel jLabelForEmployeeAddress = new JLabel("A munkahely címe:");
		jPanelForEmployeeAddress.add(jLabelForEmployeeAddress);
		jPanelForEmployeeAddress.add(Box.createHorizontalStrut(15));
		jTextFieldForEmployerAddress.setFont(font);
		jPanelForEmployeeAddress.add(jTextFieldForEmployerAddress);
		
		JPanel jPanelForTravelCostData = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel jLabelForTravelDistance = new JLabel("Távolság:");
		JLabel jLabelForDistanceUnit = new JLabel("km");
		jPanelForTravelCostData.add(jLabelForTravelDistance);
		jPanelForTravelCostData.add(Box.createHorizontalStrut(62));
		jTextFieldForTravelDistance.setFont(font);
		jPanelForTravelCostData.add(jTextFieldForTravelDistance);
		jPanelForTravelCostData.add(jLabelForDistanceUnit);
		jPanelForTravelCostData.add(Box.createHorizontalStrut(20));
		JLabel jLabelForTravelPrice = new JLabel("Elszámolási díj:");
		jPanelForTravelCostData.add(jLabelForTravelPrice);
		jPanelForTravelCostData.add(Box.createHorizontalStrut(18));
		jTextFieldForTravelPrice.setFont(font);
		jPanelForTravelCostData.add(jTextFieldForTravelPrice);
		JLabel jLabelForPriceUnit = new JLabel("Ft/km");
		jPanelForTravelCostData.add(jLabelForPriceUnit);
		
		jFrame.add(jPanelForWorkerName);
		jFrame.add(jPanelForWorkerAddress);
		jFrame.add(jPanelForEmployeeName);
		jFrame.add(jPanelForEmployeeAddress);
		jFrame.add(jPanelForTravelCostData);
	}

}
