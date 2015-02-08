package com.clearlyspam23.GLE.GUI;

import java.awt.LayoutManager;

import javax.swing.JPanel;

public abstract class NamedPanel extends JPanel {

	public NamedPanel() {
		super();
	}

	public NamedPanel(boolean isDoubleBuffered) {
		super(isDoubleBuffered);
	}

	public NamedPanel(LayoutManager layout, boolean isDoubleBuffered) {
		super(layout, isDoubleBuffered);
	}

	public NamedPanel(LayoutManager layout) {
		super(layout);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public abstract String getPanelName();

}
