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
import javax.swing.DefaultListCellRenderer;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import com.david.giczi.calculator.controller.DaysOfMonthDisplayerController;
import com.david.giczi.calculator.model.InputDataValidator;
import com.david.giczi.calculator.model.TemplateFileManager;


public class TemplateFileDisplayer {

	private JFrame jFrame;
	private Font font = new Font("Times New Roman", Font.BOLD, 20);
	private Color textColor = new Color(112, 128, 180);
	private JComboBox<String> jComboBox;
	private JLabel jLabelForWorkerName = new JLabel();
	private JLabel jLabelForWorkerAddress = new JLabel();
	private JLabel jLabelForEmployerName = new JLabel();
	private JLabel jLabelForEmployerAddress = new JLabel();
	private JLabel jLabelForTravelDistance = new JLabel();
	private JLabel jLabelForTravelPrice = new JLabel();
	private JLabel jLabelForPlate = new JLabel();
	private JMenu addWorkDays = new JMenu("Munkanapok megadása");
	
	
	public TemplateFileDisplayer(String[] fileNames) {
		jFrame = new JFrame("Dolgozói adatok fájl választása");
		jComboBox = new JComboBox<>(fileNames);
		addWorkDays.setEnabled(false);
		addComboBoxToFrame();
		addSeparatorToFrame();
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
		JMenu createTemplate = new JMenu("Dolgozói adatok fájl létrehozása/módosítása");
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
				TemplateFileCreatingDisplayer templateFileCreatingDisplayer = new TemplateFileCreatingDisplayer(true);
				TemplateFileManager templateFileManager = new TemplateFileManager();
				String chosenTemplateFileName = jComboBox.getSelectedItem().toString();
				if( !"-".equals(chosenTemplateFileName)) {
				templateFileManager.readTemplateFile(chosenTemplateFileName);
				templateFileCreatingDisplayer.setData(templateFileManager.getTemplateFileData().getWorkerName(),
													  templateFileManager.getTemplateFileData().getWorkerAddress(),
													  templateFileManager.getTemplateFileData().getEmployerName(), 
													  templateFileManager.getTemplateFileData().getEmployerAddress(), 
													  templateFileManager.getTemplateFileData().getPlate().split("-")[0], 
													  templateFileManager.getTemplateFileData().getPlate().split("-")[1], 
													  templateFileManager.getTemplateFileData().getDistance(), 
													  templateFileManager.getTemplateFileData().getPricePerDistance(), 
													  templateFileManager.getTemplateFileData().getFileName()
													  .substring(0, chosenTemplateFileName.length() - 4));
				}
			}
		});
		
		addWorkDays.addMouseListener(new MouseListener() {
			
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
				if(addWorkDays.isEnabled()) {
				 jFrame.setVisible(false);
				 new DaysOfMonthDisplayerController().createDaysOfMonthDisplayer();
				}
			}
		});
		
		createTemplate.setCursor(new Cursor(Cursor.HAND_CURSOR));
		addWorkDays.setCursor(new Cursor(Cursor.HAND_CURSOR));
		menuBar.add(createTemplate);
		menuBar.add(addWorkDays);
		
		return menuBar;
	}
	
	private void addComboBoxToFrame() {
		DefaultListCellRenderer renderer = new DefaultListCellRenderer();
		renderer.setHorizontalAlignment(DefaultListCellRenderer.CENTER);
		jComboBox.setRenderer(renderer);
		jComboBox.setCursor(new Cursor(Cursor.HAND_CURSOR));
		jComboBox.addActionListener(new ActionListener() {
			
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			if("-".equals(jComboBox.getSelectedItem().toString())) {	
			displayData(" ", " ", " ", " ", " ", " ", " ");
			addWorkDays.setEnabled(false);
			}
			else {
				
			String chosenTemplateFileName = jComboBox.getSelectedItem().toString();
			TemplateFileManager templateFileManager = new TemplateFileManager();
			templateFileManager.readTemplateFile(chosenTemplateFileName);
				if( !InputDataValidator.isValidInputFile(
						templateFileManager.getTemplateFileData().getWorkerName(), 
						templateFileManager.getTemplateFileData().getWorkerAddress(), 
						templateFileManager.getTemplateFileData().getEmployerName(), 
						templateFileManager.getTemplateFileData().getEmployerAddress(),
						templateFileManager.getTemplateFileData().getDistance(),
						templateFileManager.getTemplateFileData().getPricePerDistance(),
						templateFileManager.getTemplateFileData().getFileName(),
						templateFileManager.getTemplateFileData().getPlate())) {
					
					getWarningMessage("\"" + chosenTemplateFileName + "\" fájl nem beolvasható.", "Hibás tartalmú fájl");
					jComboBox.setSelectedIndex(0);
					return;
				}
				
				addWorkDays.setEnabled(true);
				
				displayData(templateFileManager.getTemplateFileData().getWorkerName(), 
						templateFileManager.getTemplateFileData().getWorkerAddress(), 
						templateFileManager.getTemplateFileData().getEmployerName(), 
						templateFileManager.getTemplateFileData().getEmployerAddress(),
						templateFileManager.getTemplateFileData().getPlate(),
						templateFileManager.getTemplateFileData().getDistance(),
						templateFileManager.getTemplateFileData().getPricePerDistance());
			}
		}
	});
		
		JPanel jPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel jLabel = new JLabel("Dolgozói adatok fájl választása:");
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
		
		JPanel jPanelForPlateAndTravelCostData = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel plate = new JLabel("Rendszám:");
		JLabel travelDistance = new JLabel("Távolság:");
		JLabel jLabelForDistanceUnit = new JLabel("km");
		jPanelForPlateAndTravelCostData.add(plate);
		jLabelForPlate.setFont(font);
		jLabelForPlate.setForeground(textColor);
		jPanelForPlateAndTravelCostData.add(Box.createHorizontalStrut(55));
		jPanelForPlateAndTravelCostData.add(jLabelForPlate);
		jPanelForPlateAndTravelCostData.add(Box.createHorizontalStrut(60));
		jPanelForPlateAndTravelCostData.add(travelDistance);
		jPanelForPlateAndTravelCostData.add(Box.createHorizontalStrut(10));
		jLabelForTravelDistance.setFont(font);
		jLabelForTravelDistance.setForeground(textColor);
		jPanelForPlateAndTravelCostData.add(jLabelForTravelDistance);
		jPanelForPlateAndTravelCostData.add(Box.createHorizontalStrut(10));
		jPanelForPlateAndTravelCostData.add(jLabelForDistanceUnit);
		jPanelForPlateAndTravelCostData.add(Box.createHorizontalStrut(60));
		JLabel travelPrice = new JLabel("Elszámolási díj:");
		jPanelForPlateAndTravelCostData.add(travelPrice);
		jPanelForPlateAndTravelCostData.add(Box.createHorizontalStrut(10));
		jLabelForTravelPrice.setFont(font);
		jLabelForTravelPrice.setForeground(textColor);
		jPanelForPlateAndTravelCostData.add(jLabelForTravelPrice);
		jPanelForPlateAndTravelCostData.add(Box.createHorizontalStrut(10));
		JLabel jLabelForPriceUnit = new JLabel("Ft/km");
		jPanelForPlateAndTravelCostData.add(jLabelForPriceUnit);
		
		jFrame.add(jPanelForWorkerName);
		jFrame.add(jPanelForWorkerAddress);
		jFrame.add(jPanelForEmployeeName);
		jFrame.add(jPanelForEmployeeAddress);
		jFrame.add(jPanelForPlateAndTravelCostData);
	}

	public void displayData(String workerName, String workerAddress,
			String employerName, String employerAddress, String plate, String distance, String price) {
		jLabelForWorkerName.setText(workerName);
		jLabelForWorkerAddress.setText(workerAddress);
		jLabelForEmployerName.setText(employerName);
		jLabelForEmployerAddress.setText(employerAddress);
		jLabelForPlate.setText(plate);
		jLabelForTravelDistance.setText(distance);
		jLabelForTravelPrice.setText(price);
	}
	
	public void getWarningMessage(String warningMessage, String titleMessage) {
		JOptionPane.showMessageDialog(null, warningMessage, titleMessage, JOptionPane.WARNING_MESSAGE);
	}
	
}
