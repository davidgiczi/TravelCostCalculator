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
		daysOfMonthDisplayer = new DaysOfMonthDisplayer();
		templateFileManager = new TemplateFileManager();
	}
	
	public void createDaysOfMonthDisplayer() {
		
		List<Day> actualMonthDays;
		
		if(isEqualDisplayedMonthAndSavedMonth(monthManager.getActualYearAndMonthAsText())) {
			
			templateFileManager.readTemplateFile(TemplateFileManager.ACTUAL_TEMPLATE_FILE_NAME);
			actualMonthDays = new ArrayList<>(templateFileManager.getSavedMonth());
		}
		else {
			actualMonthDays =	monthManager.createMonth(MonthManager.ACTUAL_YEAR, MonthManager.ACTUAL_MONTH);
		}
		
		daysOfMonthDisplayer.getDisplayer();
		daysOfMonthDisplayer.setTitle(TemplateFileManager.ACTUAL_TEMPLATE_FILE_NAME);
		daysOfMonthDisplayer.addNamesOfDaysPanelToTheFrame();
		daysOfMonthDisplayer.addButtonsOfDaysToTheFrame(actualMonthDays);
		daysOfMonthDisplayer
		.addOtherMonthAskingLabelsToTheFrame(monthManager.getActualYearAndMonthAsText());
	}
	
	public void increaseMonth(String yearDotMonth) {
		
		Integer[] nextYearMonthComponents = monthManager.increaseMonth(yearDotMonth);
		int nextMonth = nextYearMonthComponents[0];
		int nextYear = nextYearMonthComponents[1];
		List<Day> nextMonthDays;
		
		if(isEqualDisplayedMonthAndSavedMonth(nextYear + ". " + new MonthManager().getMonthName(nextMonth))) {
			
			templateFileManager.readTemplateFile(TemplateFileManager.ACTUAL_TEMPLATE_FILE_NAME);
			nextMonthDays = new ArrayList<>(templateFileManager.getSavedMonth());
			
		}
		else {
			 nextMonthDays =	monthManager.createMonth(nextYear, nextMonth);
		}
		
		daysOfMonthDisplayer.getDisplayer();
		daysOfMonthDisplayer.setTitle(TemplateFileManager.ACTUAL_TEMPLATE_FILE_NAME);
		daysOfMonthDisplayer.addNamesOfDaysPanelToTheFrame();
		daysOfMonthDisplayer.addButtonsOfDaysToTheFrame(nextMonthDays);
		daysOfMonthDisplayer.addOtherMonthAskingLabelsToTheFrame(nextYear + ". " + monthManager.getMonthName(nextMonth));
	}
	
	public void decreaseMonth(String yearDotMonth) {
		
		Integer[] previousYearMonthComponents = monthManager.decreaseMonth(yearDotMonth);
		int previousMonth = previousYearMonthComponents[0];
		int previousYear = previousYearMonthComponents[1];
		List<Day> previousMonthDays;
		
		if(isEqualDisplayedMonthAndSavedMonth(previousYear + ". " + new MonthManager().getMonthName(previousMonth))) {
			
			templateFileManager.readTemplateFile(TemplateFileManager.ACTUAL_TEMPLATE_FILE_NAME);
			previousMonthDays = new ArrayList<>(templateFileManager.getSavedMonth());
			
		}
		else {
			 previousMonthDays = monthManager.createMonth(previousYear, previousMonth);
		}

		daysOfMonthDisplayer.getDisplayer();
		daysOfMonthDisplayer.setTitle(TemplateFileManager.ACTUAL_TEMPLATE_FILE_NAME);
		daysOfMonthDisplayer.addNamesOfDaysPanelToTheFrame();
		daysOfMonthDisplayer.addButtonsOfDaysToTheFrame(previousMonthDays);
		daysOfMonthDisplayer.addOtherMonthAskingLabelsToTheFrame(previousYear + ". " + monthManager.getMonthName(previousMonth));
	}
	
	
	public String getSavedYearAndMonthAsText() {
		
		templateFileManager.readTemplateFile(TemplateFileManager.ACTUAL_TEMPLATE_FILE_NAME);
		return templateFileManager.getSavedYearMonth();
	}
	
	public Boolean isEqualDisplayedMonthAndSavedMonth(String displayedYearDotMonth) {
		
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
