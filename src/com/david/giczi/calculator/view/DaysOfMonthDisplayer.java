package com.david.giczi.calculator.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import com.david.giczi.calculator.controller.DaysOfMonthDisplayerController;
import com.david.giczi.calculator.listener.ShowEventListener;
import com.david.giczi.calculator.model.Day;
import com.david.giczi.calculator.model.EventFileManager;
import com.david.giczi.calculator.model.MonthManager;
import com.david.giczi.calculator.model.PdfManager;
import com.david.giczi.calculator.model.TemplateFileManager;

public class DaysOfMonthDisplayer {

	private String yearDotMonth;
			JFrame jFrame;
	private Font font;
	private JButton[] jButtonStoreForDays;
	private JLabel yearDotMonthLabel;
	private List<ShowEventListener> showEventListeners;
	private EventSettingDisplayer eventSettingDisplayer;
	private List<Day> eventDays;
	public static Color BLUE = new Color(212, 235, 242);
	public static Color YELLOW = new Color(255, 255, 212);

	
	public DaysOfMonthDisplayer(String yearDotMonth) {
		this.yearDotMonth = yearDotMonth;
		jFrame = new JFrame("Munkanapok megadása");
		eventSettingDisplayer = new EventSettingDisplayer(this);
		showEventListeners = new ArrayList<>();
		eventDays = new EventFileManager().getEventDaystInMonth(yearDotMonth);
	}
		
	public String getYearDotMonth() {
		return yearDotMonth;
	}

	private String[] getActualMonthDaysValue() {
		
		List<String> daysValueAsList = new ArrayList<>();
 		
		for(int i = 0; i < jButtonStoreForDays.length; i++) {
			if( !jButtonStoreForDays[i].getText().isEmpty() ) {
				daysValueAsList.add(jButtonStoreForDays[i].getText());
			}
		}
		
		return daysValueAsList.toArray(new String[daysValueAsList.size()]);
	}
	
	public void setTitle(String fileName) {
		jFrame.setTitle("Munkanapok megadása - " + fileName);
	}
	
	public void getDisplayer() {
		
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.setSize(700, 500);
		jFrame.setLayout(new GridLayout(8, 7));
		jFrame.setLocationRelativeTo(null);
		jFrame.setResizable(false);
		jFrame.setJMenuBar(getMenuBar());
		jFrame.setVisible(true);
		
	}
	
	private JMenuBar getMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		JMenu chooseTemplate = new JMenu("Dolgozói adatok fájl választása");
		chooseTemplate.addMouseListener(new MouseListener() {
			
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
		JMenu createPdfFile = new JMenu("Nyomtatvány fájl létrehozása");
		createPdfFile.addMouseListener(new MouseListener() {
			
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
				
			PdfManager pdfManager =	new PdfManager();
			pdfManager.setDaysButtonStore(jButtonStoreForDays);
			String yearDotMonth = yearDotMonthLabel.getText();
			
			if(!pdfManager.isPDFileExist(yearDotMonth)) {
					pdfManager.createAndOpenPDFile(yearDotMonth);
					getInfoMessage("\"" + pdfManager.createPDFileName(yearDotMonth) + "\" fájl mentve:\n" 
					+ pdfManager.pdfAbsolutePath, "Fájl mentése");
			}
			else {
				
				if (JOptionPane.showConfirmDialog(null, "Felülírod?\n\"" +  pdfManager.createPDFileName(yearDotMonth) + "\"", "Létezõ fájl",
				        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					pdfManager.createAndOpenPDFile(yearDotMonth);
					getInfoMessage("\"" + pdfManager.createPDFileName(yearDotMonth) + "\" fájl mentve:\n"
							+ pdfManager.pdfAbsolutePath, "Fájl mentése");
				}
				
			}
				
			}
		});
		JMenu addEventMenu; addEventMenu = new JMenu("Esemény hozzáadása");
		addEventMenu.addMouseListener(new MouseListener() {
			
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
			eventSettingDisplayer.getDisplayer(getActualMonthDaysValue());
			jFrame.setEnabled(false);
			}
		});
		chooseTemplate.setCursor(new Cursor(Cursor.HAND_CURSOR));
		createPdfFile.setCursor(new Cursor(Cursor.HAND_CURSOR));
		addEventMenu.setCursor(new Cursor(Cursor.HAND_CURSOR));
		menuBar.add(chooseTemplate);
		menuBar.add(createPdfFile);
		menuBar.add(addEventMenu);
		return menuBar;
	}
	
	public void addNamesOfDaysPanelToTheFrame() {
		
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
	

	
	public void addButtonsOfDaysToTheFrame(List<Day> dayStore) {
		
		jButtonStoreForDays = new JButton[42];
			
		for(int i = 0; i < 6; i++) {
			
			JPanel rowPanel = new JPanel();
			rowPanel.setLayout(new GridLayout(1, 7));
			
			for(int j = 0; j < 7; j++) {
				jButtonStoreForDays[i * 7 + j] = new JButton();
				jButtonStoreForDays[i * 7 + j].setCursor(new Cursor(Cursor.HAND_CURSOR));
				if(dayStore.get(i * 7 + j).isWorkDay()) {
				jButtonStoreForDays[i * 7 + j].setBackground(BLUE); 
				}
				else {
				jButtonStoreForDays[i * 7 + j].setBackground(YELLOW);
				}
				
				if(dayStore.get(i * 7 + j).getNumberOfMonth() != -1) {
					
	
					if(addShowEventListener(dayStore.get(i * 7 + j).getNumberOfMonth())) {
						signEventDay(true, dayStore.get(i * 7 + j).getNumberOfMonth());
					}
					
					jButtonStoreForDays[i * 7 + j].setFont(font);
					jButtonStoreForDays[i * 7 + j].setText(String.valueOf(dayStore.get(i * 7 + j).getNumberOfMonth()));
					JButton dayButton = jButtonStoreForDays[i * 7 + j];
					jButtonStoreForDays[i * 7 + j].addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
				
						DaysOfMonthDisplayerController daysOfMonthDisplayerController = 
								new DaysOfMonthDisplayerController();
						
						String displayedYearDotMonth = yearDotMonthLabel.getText();
						
						if(daysOfMonthDisplayerController.isEqualActualMonthAndSavedMonthText(displayedYearDotMonth)) {	
							
							if(dayButton.getBackground().equals(BLUE)) {
								dayButton.setBackground(YELLOW);
							}
							else {
								dayButton.setBackground(BLUE);
							}
						
							daysOfMonthDisplayerController
							.saveDisplayer(jButtonStoreForDays, displayedYearDotMonth);
							
							
						}else {
							
							if (JOptionPane.showConfirmDialog(null,  "Felülírod a mentett hónap "
									+ "(" + daysOfMonthDisplayerController.getSavedYearAndMonthAsText() +  ") adatait?", "Létezõ fájl",
							        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
								
								if(dayButton.getBackground().equals(BLUE)) {
									dayButton.setBackground(YELLOW);
								}
								else {
									dayButton.setBackground(BLUE);
								}
							
								daysOfMonthDisplayerController
								.saveDisplayer(jButtonStoreForDays, yearDotMonthLabel.getText());
								
							}
							
						}
							
						}
					});
				}
				else {
					jButtonStoreForDays[i * 7 + j].setEnabled(false);
				}
				
				rowPanel.add(jButtonStoreForDays[i * 7 + j]);
			}
			jFrame.add(rowPanel);
		}
		
	}
	
	public void addOtherMonthAskingLabelsToTheFrame() {

		JPanel lastRowPanel = new JPanel();
		lastRowPanel.setLayout(new BorderLayout());
		JLabel nextMonth = new JLabel(">>          ");
		nextMonth.setFont(font);
		nextMonth.setCursor(new Cursor(Cursor.HAND_CURSOR));
		nextMonth.addMouseListener(new MouseListener() {
			
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
				new DaysOfMonthDisplayerController().increaseMonth(yearDotMonth);
	
			}
		});
		JLabel prevMonth= new JLabel("          <<");
		prevMonth.setFont(font);
		prevMonth.setCursor(new Cursor(Cursor.HAND_CURSOR));
		prevMonth.addMouseListener(new MouseListener() {
			
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
				new DaysOfMonthDisplayerController().decreaseMonth(yearDotMonth);
			
			}
		});
		yearDotMonthLabel= new JLabel(yearDotMonth);
		yearDotMonthLabel.setFont(font);
		yearDotMonthLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lastRowPanel.add(prevMonth, BorderLayout.WEST);
		lastRowPanel.add(yearDotMonthLabel);
		lastRowPanel.add(nextMonth, BorderLayout.EAST);
		jFrame.add(lastRowPanel);
	}
	
	public void getInfoMessage(String infoMessage, String titleMessage) {
		JOptionPane.showMessageDialog(null, infoMessage, titleMessage, JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void signEventDay(boolean isEventDay, int eventDayNumber) {
		
		int eventDayIndex = createEventDayArrayIndex(eventDayNumber);
		
		if(isEventDay) {
			
		jButtonStoreForDays[eventDayIndex].setForeground(Color.RED);
			
		}
		else {
			
		jButtonStoreForDays[eventDayIndex].setForeground(Color.BLACK);
		
		}
			
	}
		
	public boolean addShowEventListener(int eventDayNumber) {
	
	int eventDayIndex = createEventDayArrayIndex(eventDayNumber);
	
	for (Day eventDay : eventDays) {
		
		if(eventDay.getNumberOfMonth() == eventDayNumber) {
		ShowEventListener showEventListener = 
		new ShowEventListener(eventDay.getEventText(), getYearDotMonth() + " " + eventDay.getNumberOfMonth() + ".", eventDayNumber);
		showEventListeners.add(showEventListener);
		jButtonStoreForDays[eventDayIndex].addMouseListener(showEventListener);
		return true;
		}
		
	}
	return false;
	}
	
	private int createEventDayArrayIndex(int eventDayNumber) {
		int dayIndex =  new MonthManager().getFirstDayNumberOfFirstWeek();
		return eventDayNumber == 1 ? dayIndex - 1 : dayIndex + eventDayNumber - 2;
	}
	
	public void removeShowEventListener(int eventDayNumber) {
		
		int eventDayIndex = createEventDayArrayIndex(eventDayNumber);
		ShowEventListener removedListener = null;
		for (ShowEventListener showEventListener : showEventListeners) {
			if(showEventListener.getEvenDayNumber() == eventDayNumber) {
				removedListener = showEventListener;
			}
			
		}
		
		if(removedListener != null) {
			jButtonStoreForDays[eventDayIndex].removeMouseListener(removedListener);	
		}
		
	}
	
}
