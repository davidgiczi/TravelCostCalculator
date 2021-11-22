package com.david.giczi.calculator.view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;


public class TemplateFileCreatingDisplayer {
	
	
	private JFrame jFrame;
	private Font font = new Font("Times New Roman", Font.BOLD, 20);
	private Color textColor = new Color(112, 128, 180);
	private JTextField jTextFieldForWorkerName = new JTextField(30);
	private JTextField jTextFieldForWorkerAddress = new JTextField(30);
	private JTextField jTextFieldForEmployerName = new JTextField(30);
	private JTextField jTextFieldForEmployerAddress = new JTextField(30);
	private JTextField jTextFieldForTravelDistance = new JTextField(10);
	private JTextField jTextFieldForTravelPrice = new JTextField(10);
	private JTextField jTextFieldForFileName = new JTextField(10);
	private JButton jButtonForSaveData = new JButton("Mentés");
	
	public TemplateFileCreatingDisplayer() {
	
		jFrame = new JFrame("Sablon fájl létrehozása/módosítása");
		getDisplayer();
		addTemplateFileDataToFrame();
		addSeparatorToFrame();
		addSaveButtonToFrame();
}
	
	private void getDisplayer() {

		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.setSize(700, 500);
		jFrame.setLayout(new GridLayout(8, 1));
		jFrame.setLocationRelativeTo(null);
		jFrame.setResizable(false);
		jFrame.setJMenuBar(getMenuBar());
		jFrame.setVisible(true);

	}
	
private JMenuBar getMenuBar() {
		
		JMenuBar menuBar = new JMenuBar();
		JMenu createTemplate = new JMenu("Sablon fájl választása");
		createTemplate.addMenuListener(new MenuListener() {
			
			@Override
			public void menuSelected(MenuEvent e) {
				
				jFrame.setVisible(false);
				new TemplateFileDisplayer("Giczi Dávid", "1125 Budapest, Diós árok 25/B.",
						 "GeoLink3D Kft.", "2120 Dunakeszi, Barátság út 4 A. lház. IV. em. 6.", "22", "15");
				
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
		menuBar.add(createTemplate);
		
		return menuBar;
	}
	
private void addTemplateFileDataToFrame() {
	JPanel jPanelForWorkerName = new JPanel(new FlowLayout(FlowLayout.LEFT));
	JLabel jLabelForWorkerName = new JLabel("A dolgozó neve:");
	jPanelForWorkerName.add(jLabelForWorkerName);
	jPanelForWorkerName.add(Box.createHorizontalStrut(30));
	jTextFieldForWorkerName.setFont(font);
	jTextFieldForWorkerName.setForeground(textColor);
	jPanelForWorkerName.add(jTextFieldForWorkerName);
	
	JPanel jPanelForWorkerAddress = new JPanel(new FlowLayout(FlowLayout.LEFT));
	JLabel jLabelForWorkerAddress = new JLabel("A dolgozó címe:");
	jPanelForWorkerAddress.add(jLabelForWorkerAddress);
	jPanelForWorkerAddress.add(Box.createHorizontalStrut(32));
	jTextFieldForWorkerAddress.setFont(font);
	jTextFieldForWorkerAddress.setForeground(textColor);
	jPanelForWorkerAddress.add(jTextFieldForWorkerAddress);
	
	JPanel jPanelForEmployeeName = new JPanel(new FlowLayout(FlowLayout.LEFT));
	JLabel jLabelForEmployeeName = new JLabel("A munkahely neve:");
	jPanelForEmployeeName.add(jLabelForEmployeeName);
	jPanelForEmployeeName.add(Box.createHorizontalStrut(15));
	jTextFieldForEmployerName.setFont(font);
	jTextFieldForEmployerName.setForeground(textColor);
	jPanelForEmployeeName.add(jTextFieldForEmployerName);
	
	JPanel jPanelForEmployeeAddress = new JPanel(new FlowLayout(FlowLayout.LEFT));
	JLabel jLabelForEmployeeAddress = new JLabel("A munkahely címe:");
	jPanelForEmployeeAddress.add(jLabelForEmployeeAddress);
	jPanelForEmployeeAddress.add(Box.createHorizontalStrut(18));
	jTextFieldForEmployerAddress.setFont(font);
	jTextFieldForEmployerAddress.setForeground(textColor);
	jPanelForEmployeeAddress.add(jTextFieldForEmployerAddress);
	
	JPanel jPanelForTravelCostData = new JPanel(new FlowLayout(FlowLayout.LEFT));
	JLabel jLabelForTravelDistance = new JLabel("Távolság:");
	JLabel jLabelForDistanceUnit = new JLabel("km");
	jPanelForTravelCostData.add(jLabelForTravelDistance);
	jPanelForTravelCostData.add(Box.createHorizontalStrut(66));
	jTextFieldForTravelDistance.setFont(font);
	jTextFieldForTravelDistance.setForeground(textColor);
	jPanelForTravelCostData.add(jTextFieldForTravelDistance);
	jPanelForTravelCostData.add(jLabelForDistanceUnit);
	jPanelForTravelCostData.add(Box.createHorizontalStrut(20));
	JLabel jLabelForTravelPrice = new JLabel("Elszámolási díj:");
	jPanelForTravelCostData.add(jLabelForTravelPrice);
	jPanelForTravelCostData.add(Box.createHorizontalStrut(18));
	jTextFieldForTravelPrice.setFont(font);
	jTextFieldForTravelPrice.setForeground(textColor);
	jPanelForTravelCostData.add(jTextFieldForTravelPrice);
	JLabel jLabelForPriceUnit = new JLabel("Ft/km");
	jPanelForTravelCostData.add(jLabelForPriceUnit);
	
	JPanel jPanelForFileName = new JPanel(new FlowLayout(FlowLayout.LEFT));
	JLabel jLabelForFileName = new JLabel("Sablon fájl neve:");
	jPanelForFileName.add(jLabelForFileName);
	jPanelForFileName.add(Box.createHorizontalStrut(26));
	jTextFieldForFileName.setFont(font);
	jTextFieldForFileName.setForeground(textColor);
	jPanelForFileName.add(jTextFieldForFileName);
	JLabel jLabelForFile = new JLabel(".txt");
	jPanelForFileName.add(jLabelForFile);
	
	jFrame.add(jPanelForWorkerName);
	jFrame.add(jPanelForWorkerAddress);
	jFrame.add(jPanelForEmployeeName);
	jFrame.add(jPanelForEmployeeAddress);
	jFrame.add(jPanelForTravelCostData);
	jFrame.add(jPanelForFileName);
}
	
private void addSeparatorToFrame() {
	
	JSeparator separator = new JSeparator();
	jFrame.add(separator);
	
}

private void addSaveButtonToFrame() {
	
	JPanel jPanelForSaveButton = new JPanel();
	jButtonForSaveData.setFont(font);
	jPanelForSaveButton.add(jButtonForSaveData);
	jFrame.add(jPanelForSaveButton);
	
}

}
