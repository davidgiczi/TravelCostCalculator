package com.david.giczi.calculator.controller;

import com.david.giczi.calculator.model.MonthManager;
import com.david.giczi.calculator.view.DaysOfMonthDisplayer;
import com.david.giczi.calculator.view.TemplateFileCreatingDisplayer;
import com.david.giczi.calculator.view.TemplateFileDisplayer;

public class TravelCostCalculatorController {

	private MonthManager monthManager;
	private TemplateFileDisplayer templateFileDisplayer;
	private TemplateFileCreatingDisplayer templateFileCreatingDisplayer;
	private DaysOfMonthDisplayer daysOfMonthDisplayer;
	
	
	public TravelCostCalculatorController() {
		monthManager = new MonthManager();
	}
	
	public void createDaysOfMonthDisplayer() {
		
	}

}
