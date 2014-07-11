package com.clearlyspam23.GLE.GUI.util;

import java.awt.LayoutManager;

import javax.swing.JPanel;

import com.clearlyspam23.GLE.GUI.NamedPanel;

public abstract class DockablePanel extends NamedPanel {
	public DockablePanel() {
		super();
	}

	public DockablePanel(boolean arg0) {
		super(arg0);
	}

	public DockablePanel(LayoutManager arg0, boolean arg1) {
		super(arg0, arg1);
	}

	public DockablePanel(LayoutManager arg0) {
		super(arg0);
	}

	private static final long serialVersionUID = 1L;

}
