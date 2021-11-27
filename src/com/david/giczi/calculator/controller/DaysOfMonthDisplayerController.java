package com.david.giczi.calculator.controller;

import java.util.List;

import com.david.giczi.calculator.model.Day;
import com.david.giczi.calculator.model.MonthManager;
import com.david.giczi.calculator.view.DaysOfMonthDisplayer;

public class DaysOfMonthDisplayerController {

	private MonthManager monthManager;
	private DaysOfMonthDisplayer daysOfMonthDisplayer;
	
	
	public DaysOfMonthDisplayerController(){
		monthManager = new MonthManager();
		daysOfMonthDisplayer = new DaysOfMonthDisplayer();
	}
	
	public void createDaysOfMonthDisplayer() {
		
		List<Day> actualMonthDays =	monthManager.createMonth(MonthManager.ACTUAL_YEAR, MonthManager.ACTUAL_MONTH);
		daysOfMonthDisplayer.getDisplayer();
		daysOfMonthDisplayer.addNamesOfDaysPanelToTheFrame();
		daysOfMonthDisplayer.addButtonsOfDaysToTheFrame(actualMonthDays);
		daysOfMonthDisplayer
		.addOtherMonthAskingLabelsToTheFrame(MonthManager.ACTUAL_YEAR + ". " + monthManager.getMonthName(MonthManager.ACTUAL_MONTH));
	}
	
	public void increaseMonth(String yearDotMonth) {
		
		Integer[] nextYearMonthComponents = monthManager.increaseMonth(yearDotMonth);
		int nextMonth = nextYearMonthComponents[0];
		int nextYear = nextYearMonthComponents[1];
		List<Day> nextMonthDays =	monthManager.createMonth(nextYear, nextMonth);
		daysOfMonthDisplayer.getDisplayer();
		daysOfMonthDisplayer.addNamesOfDaysPanelToTheFrame();
		daysOfMonthDisplayer.addButtonsOfDaysToTheFrame(nextMonthDays);
		daysOfMonthDisplayer.addOtherMonthAskingLabelsToTheFrame(nextYear + ". " + monthManager.getMonthName(nextMonth));
	}
	
	public void decreaseMonth(String yearDotMonth) {
		
		Integer[] previousYearMonthComponents = monthManager.decreaseMonth(yearDotMonth);
		int previousMonth = previousYearMonthComponents[0];
		int previousYear = previousYearMonthComponents[1];
		List<Day> previousMonthDays =	monthManager.createMonth(previousYear, previousMonth);
		daysOfMonthDisplayer.getDisplayer();
		daysOfMonthDisplayer.addNamesOfDaysPanelToTheFrame();
		daysOfMonthDisplayer.addButtonsOfDaysToTheFrame(previousMonthDays);
		daysOfMonthDisplayer.addOtherMonthAskingLabelsToTheFrame(previousYear + ". " + monthManager.getMonthName(previousMonth));
	}
	
}
