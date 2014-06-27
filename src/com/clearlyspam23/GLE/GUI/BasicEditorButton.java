package com.clearlyspam23.GLE.GUI;

import java.awt.Dimension;

import javax.swing.Icon;
import javax.swing.JButton;

public class BasicEditorButton extends JButton{
	
	public BasicEditorButton(Icon icon, String name, String info)
	{
		super(icon);
		setSize(32, 32);
//		setPreferredSize(new Dimension(32, 32));
		this.setToolTipText(name + " tool : " + info);
	}
	
	

}
