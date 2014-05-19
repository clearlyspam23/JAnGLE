package com.clearlyspam23.GLE.GUI.util;

import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class VectorPanel extends Container {
	private JTextField textField1;
	private JTextField textField2;

	/**
	 * Create the panel.
	 */
	public VectorPanel() {
		setLayout(null);
		setPreferredSize(new Dimension(198, 20));
		
		textField1 = new JTextField();
		textField1.setBounds(0, 0, 86, 20);
		textField1.setColumns(10);
		add(textField1);
		
		JLabel label = new JLabel("x");
		label.setBounds(96, 3, 6, 14);
		add(label);
		
		textField2 = new JTextField();
		textField2.setColumns(10);
		textField2.setBounds(112, 0, 86, 20);
		add(textField2);
		
	}
	
	public JTextField getField1()
	{
		return textField1;
	}
	
	public JTextField getField2()
	{
		return textField2;
	}

}
