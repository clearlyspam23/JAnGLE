package com.clearlyspam23.GLE.piccolo;

import org.piccolo2d.event.PBasicInputEventHandler;
import org.piccolo2d.event.PInputEvent;

public class ScrollZoom extends PBasicInputEventHandler{
	
	public void mouseWheelRotated(PInputEvent e)
	{
		System.out.println(e.getWheelRotation());
	}
	
	public void mouseClicked(PInputEvent e)
	{
		System.out.println(e.getButton());
		System.out.println(e.getPosition());
	}

}
