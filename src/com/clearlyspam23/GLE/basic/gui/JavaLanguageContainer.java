package com.clearlyspam23.GLE.basic.gui;

import java.awt.Container;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JList;

public class JavaLanguageContainer extends Container {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textField;

	public JavaLanguageContainer() {
		
		JLabel lblJavaLocation = new JLabel("Java Location");
		lblJavaLocation.setBounds(10, 11, 66, 14);
		add(lblJavaLocation);
		
		textField = new JTextField();
		textField.setBounds(101, 8, 159, 20);
		add(textField);
		textField.setColumns(10);
		
		JButton btnBrowse = new JButton("Browse");
		btnBrowse.setBounds(270, 7, 89, 23);
		add(btnBrowse);
		
		JLabel lblJvmArguments = new JLabel("JVM Arguments");
		lblJvmArguments.setBounds(10, 36, 96, 14);
		add(lblJvmArguments);
		
		JList list = new JList();
		list.setBounds(101, 35, 159, 94);
		add(list);
	}

}
