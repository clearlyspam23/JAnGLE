package com.clearlyspam23.GLE.test;

import org.piccolo2d.event.PBasicInputEventHandler;
import org.piccolo2d.event.PInputEvent;

public class ClickListener extends PBasicInputEventHandler{
	
	private int x;
	private int y;
	
	public ClickListener(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public void mouseClicked(PInputEvent e)
	{
		System.out.println(x + ", " + y);
	}

}
