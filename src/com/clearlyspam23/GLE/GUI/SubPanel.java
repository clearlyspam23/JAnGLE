package com.clearlyspam23.GLE.GUI;

import java.awt.LayoutManager;

import javax.swing.JPanel;

public abstract class SubPanel extends JPanel {
	
	public SubPanel()
	{
		
	}
	
	public SubPanel(boolean isDoubleBuffered)
	{
		super(isDoubleBuffered);
	}
	
	public SubPanel(LayoutManager layout){
		super(layout);
	}
	
	public SubPanel(LayoutManager layout, boolean isDoubleBuffered){
		super(layout, isDoubleBuffered);
	}
	
	public abstract void reset();

}
