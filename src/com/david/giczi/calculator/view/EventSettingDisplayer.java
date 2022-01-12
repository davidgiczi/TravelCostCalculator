package com.david.giczi.calculator.view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Arrays;
import javax.swing.Box;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import com.david.giczi.calculator.model.Day;
import com.david.giczi.calculator.model.EventFileManager;
import com.david.giczi.calculator.model.MonthManager;



public class EventSettingDisplayer {

	public JFrame jFrame;
	private JComboBox<String> jComboBox;
	private JTextArea eventTextArea;
	private JButton jOkButton;
	private JPanel jPanel;
	private Font font = new Font("Times New Roman", Font.BOLD, 20);
	private Color textColor = new Color(112, 128, 180);
	private DaysOfMonthDisplayer daysOfMonthDisplayer;
	
	
	public EventSettingDisplayer(DaysOfMonthDisplayer daysOfMonthDisplayer) {
		this.daysOfMonthDisplayer = daysOfMonthDisplayer;
	}
	
	public void setDaysOfMonthDisplayer(DaysOfMonthDisplayer daysOfMonthDisplayer) {
		this.daysOfMonthDisplayer = daysOfMonthDisplayer;
	}

	public void getDisplayer(String[] actualDays) {
		jFrame = new JFrame("Esemény létrehozása");
		jPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		jComboBox = new JComboBox<>(actualDays);
		jFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		jFrame.addWindowListener(new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowClosing(WindowEvent e) {
			closeEventWindow();
			}
			
			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		jFrame.setSize(450, 350);
		jFrame.setLocationRelativeTo(null);
		jFrame.setResizable(false);
		jFrame.setAlwaysOnTop(true);
		jFrame.setVisible(true);
		addComboBoxToFrame();
		addInputTextAreaToFrame();
		addOkButtonToFrame();
	}
	
	private void addComboBoxToFrame() {
		DefaultListCellRenderer renderer = new DefaultListCellRenderer();
		renderer.setHorizontalAlignment(DefaultListCellRenderer.CENTER);
		jComboBox.setRenderer(renderer);
		jComboBox.setCursor(new Cursor(Cursor.HAND_CURSOR));
		jComboBox.setPreferredSize(new Dimension(50, 30));
		jComboBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			
				int dayValue = Integer.parseInt(jComboBox.getSelectedItem().toString());
				String dayOfMonthFileName = createEventFileName(daysOfMonthDisplayer.getActualYearDotMonthAsString(), new Day(dayValue));
				eventTextArea.setText(new EventFileManager().readEventFile(dayOfMonthFileName));
			}
		});
		JLabel jLabel = new JLabel("Az esemény napjának választása:");
		jPanel.add(jLabel);
		jPanel.add(Box.createHorizontalStrut(10));
		jPanel.add(jComboBox);
		jFrame.add(jPanel);
		
	}
	
	private void addInputTextAreaToFrame() {
		eventTextArea = new JTextArea();
		eventTextArea.setFont(font);
		eventTextArea.setForeground(textColor);
		eventTextArea.setLineWrap(true);
		String firstDayOfMonthFileName = createEventFileName(daysOfMonthDisplayer.getActualYearDotMonthAsString(), new Day(1));
		eventTextArea.setText(new EventFileManager().readEventFile(firstDayOfMonthFileName));
		JScrollPane scrollPane = new JScrollPane(eventTextArea,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setPreferredSize(new Dimension(400, 200));
		jPanel.setBorder (new TitledBorder (new EtchedBorder(), daysOfMonthDisplayer.getActualYearDotMonthAsString() ));
		jPanel.add(scrollPane);
	}
	
	private void addOkButtonToFrame() {
		jOkButton = new JButton("Létrehoz");
		jOkButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		jOkButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			
			String eventFileName;
			String eventDay = jComboBox.getSelectedItem().toString();
				
			if(eventTextArea.getText().trim().isBlank()) {
				eventFileName = createEventFileName(daysOfMonthDisplayer.getActualYearDotMonthAsString(), 
						new Day(Integer.parseInt(eventDay)));
				new EventFileManager().deleteEventFile(eventFileName);
				daysOfMonthDisplayer.signEventDay(false, eventDay);
				closeEventWindow();
				return;
			}
					
			String[] eventTextStore = eventTextArea.getText().split("\\n");
			eventFileName = createEventFileName(daysOfMonthDisplayer.getActualYearDotMonthAsString(), 
					new Day(Integer.parseInt(jComboBox.getSelectedItem().toString()), Arrays.asList(eventTextStore)));
			
			new EventFileManager().saveEventFile(eventFileName, Arrays.asList(eventTextStore));
			daysOfMonthDisplayer.signEventDay(true, eventDay);
			closeEventWindow();
			
			}
		});
		
		jPanel.add(jOkButton);
		jFrame.add(jPanel);
	}
	
	private String createEventFileName(String yearDotMonth, Day dayOfEvent) {
		
		return "Event_"  + new MonthManager()
				.getDateOfDay(daysOfMonthDisplayer
				.getActualYearDotMonthAsString(), dayOfEvent)
				.replace(".", "_");
	}
	
	private void closeEventWindow() {
		jFrame.setVisible(false);
		daysOfMonthDisplayer.jFrame.setEnabled(true);
	}
	
}
