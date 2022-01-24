package com.david.giczi.calculator.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.NoSuchElementException;
import javax.swing.JButton;
import com.david.giczi.calculator.model.Day;
import com.david.giczi.calculator.model.EventFileManager;
import com.david.giczi.calculator.model.MonthManager;
import com.david.giczi.calculator.model.TemplateFileManager;
import com.david.giczi.calculator.months.namedays.MonthData;
import com.david.giczi.calculator.view.DaysOfMonthDisplayer;
import com.david.giczi.calculator.view.NotificationDisplayer;

public class DaysOfMonthDisplayerController {

	private MonthManager monthManager;
	private DaysOfMonthDisplayer daysOfMonthDisplayer;
	private TemplateFileManager templateFileManager;

	
	public DaysOfMonthDisplayerController(){
		monthManager = new MonthManager();
		daysOfMonthDisplayer = new DaysOfMonthDisplayer(monthManager.getActualYearAndMonthAsText());
		templateFileManager = new TemplateFileManager();
	}
	
	public DaysOfMonthDisplayer getDaysOfMonthDisplayer() {
		return daysOfMonthDisplayer;
	}

	public void createDaysOfMonthDisplayer(boolean isNotify) {
		
		List<Day> actualMonthDays;
		
		if(isEqualActualMonthAndSavedMonthText(monthManager.getActualYearAndMonthAsText())) {
			
			templateFileManager.readTemplateFile(TemplateFileManager.ACTUAL_TEMPLATE_FILE_NAME);
			actualMonthDays = new ArrayList<>(templateFileManager.getSavedMonth());
		}
		else {
			actualMonthDays =	monthManager.createMonth();
		}
		
		daysOfMonthDisplayer.getDisplayer();
		daysOfMonthDisplayer.setTitle(templateFileManager.getTemplateFileData().getWorkerName());
		daysOfMonthDisplayer.addNamesOfDaysPanelToTheFrame();
		daysOfMonthDisplayer.addButtonsOfDaysToTheFrame(actualMonthDays);
		daysOfMonthDisplayer.addOtherMonthAskingLabelsToTheFrame();
		if(isNotify) {
		showNotificationDisplayer();
		}
	}
	
	public void increaseMonth(String yearDotMonth) {
		
		monthManager.increaseMonth(yearDotMonth);
		List<Day> nextMonthDays;
		
		if(isEqualActualMonthAndSavedMonthText(MonthManager.ACTUAL_YEAR + ". " + 
		new MonthManager().getMonthName(MonthManager.ACTUAL_MONTH))) {
			
			templateFileManager.readTemplateFile(TemplateFileManager.ACTUAL_TEMPLATE_FILE_NAME);
			nextMonthDays = new ArrayList<>(templateFileManager.getSavedMonth());
			
		}
		else {
			 nextMonthDays =	monthManager.createMonth();
		}
		
		daysOfMonthDisplayer = new DaysOfMonthDisplayer(new MonthManager().getActualYearAndMonthAsText());
		daysOfMonthDisplayer.getDisplayer();
		daysOfMonthDisplayer.setTitle(templateFileManager.getTemplateFileData().getWorkerName());
		daysOfMonthDisplayer.addNamesOfDaysPanelToTheFrame();
		daysOfMonthDisplayer.addButtonsOfDaysToTheFrame(nextMonthDays);
		daysOfMonthDisplayer.addOtherMonthAskingLabelsToTheFrame();
	}
	
	public void decreaseMonth(String yearDotMonth) {
		
		monthManager.decreaseMonth(yearDotMonth);
		List<Day> previousMonthDays;
		
		if(isEqualActualMonthAndSavedMonthText(MonthManager.ACTUAL_YEAR + ". " + 
		new MonthManager().getMonthName(MonthManager.ACTUAL_MONTH))) {
			
			templateFileManager.readTemplateFile(TemplateFileManager.ACTUAL_TEMPLATE_FILE_NAME);
			previousMonthDays = new ArrayList<>(templateFileManager.getSavedMonth());
			
		}
		else {
			 previousMonthDays = monthManager.createMonth();
		}
		
		daysOfMonthDisplayer = new DaysOfMonthDisplayer(new MonthManager().getActualYearAndMonthAsText());
		daysOfMonthDisplayer.getDisplayer();
		daysOfMonthDisplayer.setTitle(templateFileManager.getTemplateFileData().getWorkerName());
		daysOfMonthDisplayer.addNamesOfDaysPanelToTheFrame();
		daysOfMonthDisplayer.addButtonsOfDaysToTheFrame(previousMonthDays);
		daysOfMonthDisplayer.addOtherMonthAskingLabelsToTheFrame();
	}
	
	
	public String getSavedYearAndMonthAsText() {
		
		templateFileManager.readTemplateFile(TemplateFileManager.ACTUAL_TEMPLATE_FILE_NAME);
		return templateFileManager.getSavedYearMonth();
	}
	
	public Boolean isEqualActualMonthAndSavedMonthText(String displayedYearDotMonth) {
		
		templateFileManager.readTemplateFile(TemplateFileManager.ACTUAL_TEMPLATE_FILE_NAME);
			
		if(displayedYearDotMonth.equals(templateFileManager.getSavedYearMonth())) {
			return true;
		}
		
		return false;
	}
	
	public void saveDisplayer(JButton[] dayButtonStore, String yearDotMonth) {
		templateFileManager.saveDaysOfMonthDisplayer(dayButtonStore, yearDotMonth);
	}
	
	private void showNotificationDisplayer() {
		
		int actualDay = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
		int actualMonth = Calendar.getInstance().get(Calendar.MONTH);
		int actualYear = Calendar.getInstance().get(Calendar.YEAR);
		
		String date = actualYear + ". " + monthManager.getMonthName(actualMonth) + 
						(actualDay < 10 ? " 0" + actualDay + "." : " " + actualDay + ".");
		String firstNames = MonthData.getMonthDayNameStore(actualMonth)
									.get(actualDay - 1); 
		String officialEvent;
		try {
			
			officialEvent = " - " +  MonthData
							.getMonthDayDataStore(actualMonth)
							.stream()
							.filter(day -> day.getNumberOfMonth() == actualDay)
							.findFirst()
							.get()
							.getEventText();
			
		} catch (NoSuchElementException e) {
			officialEvent = "";
		}
		
		String eventFileName = "Event_" + monthManager
							   .getDateOfDay(actualYear + ". " + monthManager.getMonthName(actualMonth), new Day(actualDay))
							   .replace(".", "_");
		String ownerEvent = new EventFileManager(templateFileManager.getTemplateFileData().getWorkerName())
							.readEventFile(eventFileName);

		if(NotificationDisplayer.isNotify) {
			new NotificationDisplayer(date, firstNames, officialEvent, ownerEvent);
		}
		
	}
	
}
