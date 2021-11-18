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

import com.david.giczi.calculator.model.Month;

public class TemplateFileDisplayer {

	private JFrame jFrame;
	private Font font;
	private JTextField jTextFieldForWorkerName = new JTextField(50);
	private JTextField jTextFieldForWorkerAddress = new JTextField(50);
	private JTextField jTextFieldForEmployeeName = new JTextField(50);
	private JTextField jTextFieldForEmployeeAddress = new JTextField(50);
	private JTextField jTextFieldForTravelDistance = new JTextField(10);
	private JTextField jTextFieldForTravelCost = new JTextField(20);

	public TemplateFileDisplayer() {
		jFrame = new JFrame("Sablon fájl választása");
		addComboBoxToFrame();
		getDisplayer();
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
		createTemplate.setCursor(new Cursor(Cursor.HAND_CURSOR));
		addWorkDays.setCursor(new Cursor(Cursor.HAND_CURSOR));
		menuBar.add(createTemplate);
		menuBar.add(addWorkDays);
		return menuBar;
	}
	
	private void addComboBoxToFrame() {
		JPanel jPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JComboBox<String> jComboBox = new JComboBox<String>(Month.HOLIDAYS);
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
		jPanelForWorkerName.add(jTextFieldForWorkerName);
		
		JPanel jPanelForWorkerAddress = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel jLabelForWorkerAddress = new JLabel("A dolgozó címe:");
		jPanelForWorkerAddress.add(jLabelForWorkerAddress);
		jPanelForWorkerAddress.add(Box.createHorizontalStrut(30));
		jPanelForWorkerAddress.add(jTextFieldForWorkerAddress);
		
		JPanel jPanelForEmployeeName = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel jLabelForEmployeeName = new JLabel("A munkahely neve:");
		jPanelForEmployeeName.add(jLabelForEmployeeName);
		jPanelForEmployeeName.add(Box.createHorizontalStrut(12));
		jPanelForEmployeeName.add(jTextFieldForEmployeeName);
		
		JPanel jPanelForEmployeeAddress = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel jLabelForEmployeeAddress = new JLabel("A munkahely címe:");
		jPanelForEmployeeAddress.add(jLabelForEmployeeAddress);
		jPanelForEmployeeAddress.add(Box.createHorizontalStrut(12));
		jPanelForEmployeeAddress.add(jTextFieldForEmployeeAddress);
		
		JPanel jPanelForTravelCostData = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel jLabelForTravelDistance = new JLabel("Távolság:");
		jPanelForTravelCostData.add(jLabelForTravelDistance);
		jPanelForTravelCostData.add(Box.createHorizontalStrut(60));
		jPanelForTravelCostData.add(jTextFieldForTravelDistance);
		
		jFrame.add(jPanelForWorkerName);
		jFrame.add(jPanelForWorkerAddress);
		jFrame.add(jPanelForEmployeeName);
		jFrame.add(jPanelForEmployeeAddress);
		jFrame.add(jPanelForTravelCostData);
	}

}
