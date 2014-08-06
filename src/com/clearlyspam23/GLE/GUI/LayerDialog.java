package com.clearlyspam23.GLE.GUI;

import java.awt.Frame;

import javax.swing.JDialog;

public class LayerDialog extends JDialog{
	
	public LayerDialog(Frame frame)
	{
		super(frame);
		setAlwaysOnTop(true);
	}

}
