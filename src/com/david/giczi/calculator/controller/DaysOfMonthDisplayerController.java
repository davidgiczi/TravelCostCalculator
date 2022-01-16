package com.david.giczi.calculator.controller;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import com.david.giczi.calculator.model.Day;
import com.david.giczi.calculator.model.MonthManager;
import com.david.giczi.calculator.model.TemplateFileManager;
import com.david.giczi.calculator.view.DaysOfMonthDisplayer;

public class DaysOfMonthDisplayerController {

	private MonthManager monthManager;
	private DaysOfMonthDisplayer daysOfMonthDisplayer;
	private TemplateFileManager templateFileManager;

	
	public DaysOfMonthDisplayerController(){
		monthManager = new MonthManager();
		daysOfMonthDisplayer = new DaysOfMonthDisplayer(monthManager.getActualYearAndMonthAsText());
		templateFileManager = new TemplateFileManager();
	}
	
	public void createDaysOfMonthDisplayer() {
		
		List<Day> actualMonthDays;
		
		if(isEqualActualMonthAndSavedMonthText(monthManager.getActualYearAndMonthAsText())) {
			
			templateFileManager.readTemplateFile(TemplateFileManager.ACTUAL_TEMPLATE_FILE_NAME);
			actualMonthDays = new ArrayList<>(templateFileManager.getSavedMonth());
		}
		else {
			actualMonthDays =	monthManager.createMonth();
		}
		
		daysOfMonthDisplayer.getDisplayer();
		daysOfMonthDisplayer.setTitle(TemplateFileManager.ACTUAL_TEMPLATE_FILE_NAME);
		daysOfMonthDisplayer.addNamesOfDaysPanelToTheFrame();
		daysOfMonthDisplayer.addButtonsOfDaysToTheFrame(actualMonthDays);
		daysOfMonthDisplayer.addOtherMonthAskingLabelsToTheFrame();
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
		daysOfMonthDisplayer.setTitle(TemplateFileManager.ACTUAL_TEMPLATE_FILE_NAME);
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
		daysOfMonthDisplayer.setTitle(TemplateFileManager.ACTUAL_TEMPLATE_FILE_NAME);
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
	
}
