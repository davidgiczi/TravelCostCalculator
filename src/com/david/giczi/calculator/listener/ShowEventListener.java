package com.david.giczi.calculator.listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import com.david.giczi.calculator.view.EventDisplayer;

public class ShowEventListener implements MouseListener{

	private EventDisplayer eventDisplayer;
	private int evenDayNumber;
	
		
	public ShowEventListener(String eventText, String eventDate, int evenDayNumber) {

		eventDisplayer = new EventDisplayer(eventText, eventDate);
		this.evenDayNumber = evenDayNumber;
	}
	
	public int getEvenDayNumber() {
		return evenDayNumber;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		eventDisplayer.jFrame.setVisible(true);
	}

	@Override
	public void mouseExited(MouseEvent e) {
	
	}

}
