package com.david.giczi.calculator.listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import com.david.giczi.calculator.view.EventDisplayer;

public class ShowEventListener implements MouseListener{

	private EventDisplayer eventDisplayer;
	private int eventDayNumber;
	
		
	public ShowEventListener(String eventText, String eventDate, int eventDayNumber) {

		eventDisplayer = new EventDisplayer(eventText, eventDate);
		this.eventDayNumber = eventDayNumber;
	}
	
	public int getEventDayNumber() {
		return eventDayNumber;
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
