package com.david.giczi.calculator.view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;

import com.david.giczi.calculator.controller.TemplateFileCreatingDisplayerController;


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
	private JButton jButtonForSaveData = new JButton("Ment�s");
	public Boolean isActiveDisplayer;
	
	public void setIsActiveDisplayer(Boolean isActiveDisplayer) {
		this.isActiveDisplayer = isActiveDisplayer;
	}

	public TemplateFileCreatingDisplayer(Boolean isActiveDisplayer) {
		
		this.isActiveDisplayer = isActiveDisplayer;
		jFrame = new JFrame("Dolgoz�i adatok f�jl l�trehoz�sa/m�dos�t�sa");
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
		JMenu createTemplate = new JMenu("Dolgoz�i adatok f�jl v�laszt�sa");
		createTemplate.setCursor(new Cursor(Cursor.HAND_CURSOR));
		menuBar.add(createTemplate);
		if( !isActiveDisplayer ) {
			createTemplate.setEnabled(false);
		}
		return menuBar;
	}
	
private void addTemplateFileDataToFrame() {
	JPanel jPanelForWorkerName = new JPanel(new FlowLayout(FlowLayout.LEFT));
	JLabel jLabelForWorkerName = new JLabel("A dolgoz� neve:");
	jPanelForWorkerName.add(jLabelForWorkerName);
	jPanelForWorkerName.add(Box.createHorizontalStrut(30));
	jTextFieldForWorkerName.setFont(font);
	jTextFieldForWorkerName.setForeground(textColor);
	jPanelForWorkerName.add(jTextFieldForWorkerName);
	
	JPanel jPanelForWorkerAddress = new JPanel(new FlowLayout(FlowLayout.LEFT));
	JLabel jLabelForWorkerAddress = new JLabel("A dolgoz� c�me:");
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
	JLabel jLabelForEmployeeAddress = new JLabel("A munkahely c�me:");
	jPanelForEmployeeAddress.add(jLabelForEmployeeAddress);
	jPanelForEmployeeAddress.add(Box.createHorizontalStrut(18));
	jTextFieldForEmployerAddress.setFont(font);
	jTextFieldForEmployerAddress.setForeground(textColor);
	jPanelForEmployeeAddress.add(jTextFieldForEmployerAddress);
	
	JPanel jPanelForTravelCostData = new JPanel(new FlowLayout(FlowLayout.LEFT));
	JLabel jLabelForTravelDistance = new JLabel("T�vols�g:");
	JLabel jLabelForDistanceUnit = new JLabel("km");
	jPanelForTravelCostData.add(jLabelForTravelDistance);
	jPanelForTravelCostData.add(Box.createHorizontalStrut(66));
	jTextFieldForTravelDistance.setFont(font);
	jTextFieldForTravelDistance.setForeground(textColor);
	jPanelForTravelCostData.add(jTextFieldForTravelDistance);
	jPanelForTravelCostData.add(jLabelForDistanceUnit);
	jPanelForTravelCostData.add(Box.createHorizontalStrut(20));
	JLabel jLabelForTravelPrice = new JLabel("Elsz�mol�si d�j:");
	jPanelForTravelCostData.add(jLabelForTravelPrice);
	jPanelForTravelCostData.add(Box.createHorizontalStrut(18));
	jTextFieldForTravelPrice.setFont(font);
	jTextFieldForTravelPrice.setForeground(textColor);
	jPanelForTravelCostData.add(jTextFieldForTravelPrice);
	JLabel jLabelForPriceUnit = new JLabel("Ft/km");
	jPanelForTravelCostData.add(jLabelForPriceUnit);
	
	JPanel jPanelForFileName = new JPanel(new FlowLayout(FlowLayout.LEFT));
	JLabel jLabelForFileName = new JLabel("Ment�si f�jl neve:");
	jPanelForFileName.add(jLabelForFileName);
	jPanelForFileName.add(Box.createHorizontalStrut(20));
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
	jButtonForSaveData.setCursor(new Cursor(Cursor.HAND_CURSOR));
	jButtonForSaveData.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			if( !new TemplateFileCreatingDisplayerController()
					.isValidInputString(jTextFieldForWorkerName.getText(), 
										jTextFieldForWorkerAddress.getText(),
										jTextFieldForEmployerName.getText(), 
										jTextFieldForEmployerAddress.getText(), 
										jTextFieldForTravelDistance.getText(),
										jTextFieldForTravelPrice.getText(), 
										jTextFieldForFileName.getText())) {
				
				getWarningMessage("Minden adatbeviteli mez� kit�lt�se sz�ks�ges.", "Hi�nyz� adat");
				return;
			}
			
			if( !new TemplateFileCreatingDisplayerController()
					.isValidInputNumber(jTextFieldForTravelDistance.getText(),
										jTextFieldForTravelPrice.getText())) {
				
				getWarningMessage("A \"T�vols�g\" �s az \"Elsz�mol�si d�j\" mez�k �rt�ke csak sz�m lehet.", "Hib�s adat");
				return;
			}
			
			System.out.println(new TemplateFileCreatingDisplayerController()
					.readInputData(jTextFieldForWorkerName.getText(), 
					jTextFieldForWorkerAddress.getText(),
					jTextFieldForEmployerName.getText(), 
					jTextFieldForEmployerAddress.getText(), 
					jTextFieldForTravelDistance.getText(),
					jTextFieldForTravelPrice.getText(), 
					jTextFieldForFileName.getText()));
			
		}
	});
	
	jPanelForSaveButton.add(jButtonForSaveData);
	jFrame.add(jPanelForSaveButton);
	
}

public void getInfoMessage(String infoMessage, String titleMessage) {
	JOptionPane.showMessageDialog(null, infoMessage, titleMessage, JOptionPane.INFORMATION_MESSAGE);
}

public void getWarningMessage(String warningMessage, String titleMessage) {
	JOptionPane.showMessageDialog(null, warningMessage, titleMessage, JOptionPane.WARNING_MESSAGE);
}

}
