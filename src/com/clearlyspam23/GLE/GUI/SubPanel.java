package com.clearlyspam23.GLE.GUI;

import java.awt.LayoutManager;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public abstract class SubPanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<ChangeListener> listeners = new ArrayList<ChangeListener>();

	public SubPanel() {
	}

	public void addChangeListener(ChangeListener l)
	{
		listeners.add(l);
	}
	
	public void registerChange()
	{
		ChangeEvent e = new ChangeEvent(this);
		for(ChangeListener l : listeners)
			l.stateChanged(e);
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
