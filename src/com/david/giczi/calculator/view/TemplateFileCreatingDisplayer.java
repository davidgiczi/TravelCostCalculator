package com.david.giczi.calculator.view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

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
import com.david.giczi.calculator.model.InputDataValidator;
import com.david.giczi.calculator.model.TemplateFileManager;


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
	private JTextField jTextFieldForPlateLetter = new JTextField(3);
	private JTextField jTextFieldForPlateNumber = new JTextField(3);
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
		createTemplate.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				jFrame.setVisible(false);
				new TemplateFileDisplayer(new TemplateFileManager().getFileNames());
			}
		});
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
	
	JPanel jPanelForPlateNumberAndFileName = new JPanel(new FlowLayout(FlowLayout.LEFT));
	JLabel jLabelForPlate = new JLabel("Rendsz�m:");
	JLabel jLabelForFileName = new JLabel("Ment�si f�jl neve:");
	jPanelForPlateNumberAndFileName.add(jLabelForPlate);
	jPanelForPlateNumberAndFileName.add(Box.createHorizontalStrut(59));
	jTextFieldForPlateLetter.setFont(font);
	jTextFieldForPlateLetter.setForeground(textColor);
	jPanelForPlateNumberAndFileName.add(jTextFieldForPlateLetter);
	JLabel jLabelForHyphen = new JLabel("-");
	jLabelForHyphen.setFont(font);
	jPanelForPlateNumberAndFileName.add(jLabelForHyphen);
	jTextFieldForPlateNumber.setFont(font);
	jTextFieldForPlateNumber.setForeground(textColor);
	jPanelForPlateNumberAndFileName.add(jTextFieldForPlateNumber);
	jPanelForPlateNumberAndFileName.add(Box.createHorizontalStrut(72));
	
	jPanelForPlateNumberAndFileName.add(jLabelForFileName);
	jPanelForPlateNumberAndFileName.add(Box.createHorizontalStrut(20));
	jTextFieldForFileName.setFont(font);
	jTextFieldForFileName.setForeground(textColor);
	jPanelForPlateNumberAndFileName.add(jTextFieldForFileName);
	JLabel jLabelForFile = new JLabel(".txt");
	jPanelForPlateNumberAndFileName.add(jLabelForFile);
	
	jFrame.add(jPanelForWorkerName);
	jFrame.add(jPanelForWorkerAddress);
	jFrame.add(jPanelForEmployeeName);
	jFrame.add(jPanelForEmployeeAddress);
	jFrame.add(jPanelForTravelCostData);
	jFrame.add(jPanelForPlateNumberAndFileName);
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
			
			TemplateFileCreatingDisplayerController templateFileCreatingDisplayerController 
			= new TemplateFileCreatingDisplayerController();
			
			if( !InputDataValidator
					.isValidInputString(jTextFieldForWorkerName.getText(), 
										jTextFieldForWorkerAddress.getText(),
										jTextFieldForEmployerName.getText(), 
										jTextFieldForEmployerAddress.getText(), 
										jTextFieldForTravelDistance.getText(),
										jTextFieldForTravelPrice.getText(), 
										jTextFieldForFileName.getText(),
										jTextFieldForPlateLetter.getText(),
										jTextFieldForPlateNumber.getText())) {
				
				getWarningMessage("Minden adatbeviteli mez� kit�lt�se sz�ks�ges.", "Hi�nyz� adat");
				return;
			}
			
			if( !InputDataValidator
					.isValidInputNumber(jTextFieldForTravelDistance.getText(),
										jTextFieldForTravelPrice.getText())) {
				
				getWarningMessage("A \"T�vols�g\" �s az \"Elsz�mol�si d�j\" mez�k �rt�ke csak eg�sz sz�m lehet.", "Hib�s adat");
				return;
			}
			
			if( !InputDataValidator.isValidPlateLetter(jTextFieldForPlateLetter.getText())) {
				getWarningMessage("A rendsz�m bet�mez�j�nek hossza 3 karakter �s csak �kezet n�lk�li bet� lehet.", "Hib�s adat");
				return;
			}
			
			if( !InputDataValidator.isValidPlateNumber(jTextFieldForPlateNumber.getText())) {
				getWarningMessage("A rendsz�m sz�mmez�j�nek hossza 3 karakter �s csak sz�mjegy lehet.", "Hib�s adat");
				return;
			}
				
			if(templateFileCreatingDisplayerController.isTemplateFileExist(jTextFieldForFileName.getText())) {
				
				if (JOptionPane.showConfirmDialog(null,  "Fel�l�rod?", "L�tez� f�jl: " + jTextFieldForFileName.getText() + ".txt",
				        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				    
					templateFileCreatingDisplayerController
					.saveInputData(jTextFieldForWorkerName.getText(), 
					jTextFieldForWorkerAddress.getText(),
					jTextFieldForEmployerName.getText(), 
					jTextFieldForEmployerAddress.getText(), 
					jTextFieldForTravelDistance.getText(),
					jTextFieldForTravelPrice.getText(),
					jTextFieldForPlateLetter.getText(),
					jTextFieldForPlateNumber.getText(),
					jTextFieldForFileName.getText());
				} else {
				    return;
				}
				
			}
			else {
				
				templateFileCreatingDisplayerController
				.saveInputData(jTextFieldForWorkerName.getText(), 
				jTextFieldForWorkerAddress.getText(),
				jTextFieldForEmployerName.getText(), 
				jTextFieldForEmployerAddress.getText(), 
				jTextFieldForTravelDistance.getText(),
				jTextFieldForTravelPrice.getText(),
				jTextFieldForPlateLetter.getText(),
				jTextFieldForPlateNumber.getText(),
				jTextFieldForFileName.getText());
			}
			getInfoMessage("\"" + jTextFieldForFileName.getText() + ".txt\" f�jl mentve.", "F�jl ment�se");
			jFrame.setVisible(false);
			new TemplateFileDisplayer(new TemplateFileManager().getFileNames());
		}
	});
	
	jPanelForSaveButton.add(jButtonForSaveData);
	jFrame.add(jPanelForSaveButton);
	
}

public void setData(String workerName, String workerAddress, String employerName, 
		String employerAddress, String plateLetter, String plateNumber, 
		String distance, String price, String fileName) {
	jTextFieldForWorkerName.setText(workerName);
	jTextFieldForWorkerAddress.setText(workerAddress);
	jTextFieldForEmployerName.setText(employerName);
	jTextFieldForEmployerAddress.setText(employerAddress);
	jTextFieldForPlateLetter.setText(plateLetter);
	jTextFieldForPlateNumber.setText(plateNumber);
	jTextFieldForTravelDistance.setText(distance);
	jTextFieldForTravelPrice.setText(price);
	jTextFieldForFileName.setText(fileName);
}

public void getInfoMessage(String infoMessage, String titleMessage) {
	JOptionPane.showMessageDialog(null, infoMessage, titleMessage, JOptionPane.INFORMATION_MESSAGE);
}

public void getWarningMessage(String warningMessage, String titleMessage) {
	JOptionPane.showMessageDialog(null, warningMessage, titleMessage, JOptionPane.WARNING_MESSAGE);
}

}
