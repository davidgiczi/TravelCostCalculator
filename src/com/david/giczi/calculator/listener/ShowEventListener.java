package com.david.giczi.calculator.listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import com.david.giczi.calculator.view.EventDisplayer;

public class ShowEventListener implements MouseListener{

	private String eventText;
	private String eventDate;
	private EventDisplayer eventDisplayer;
	
		
	public ShowEventListener(String eventText, String eventDate) {
		this.eventText = eventText;
		this.eventDate = eventDate;
		eventDisplayer = new EventDisplayer(eventText, eventText);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
