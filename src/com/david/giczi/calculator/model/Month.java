package com.david.giczi.calculator.model;

import java.util.ArrayList;
import java.util.List;

public class Month {

	private List<Day> month = new ArrayList<>();
	public static String[] HOLIDAYS = {"JAN-1", "MAR-15", "MAJ-1", "AUG-20", "OKT-23", "NOV-1", "DEC-25", "DEC-26"};

	public List<Day> getMonth() {
		return month;
	}
	
	public void createMonth(String nameOfMonth) {
		
		
	}
}
