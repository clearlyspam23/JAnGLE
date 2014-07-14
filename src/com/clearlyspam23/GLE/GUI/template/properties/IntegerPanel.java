package com.clearlyspam23.GLE.GUI.template.properties;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;

public class IntegerPanel extends JPanel{
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	public IntegerPanel() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{80, 60, 0};
		gridBagLayout.rowHeights = new int[]{14, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblValue = new JLabel("Min Value");
		GridBagConstraints gbc_lblValue = new GridBagConstraints();
		gbc_lblValue.insets = new Insets(0, 0, 5, 5);
		gbc_lblValue.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblValue.gridx = 0;
		gbc_lblValue.gridy = 0;
		add(lblValue, gbc_lblValue);
		
		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 0);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 0;
		add(textField, gbc_textField);
		textField.setColumns(10);
		
		JLabel lblMaxValue = new JLabel("Max Value");
		GridBagConstraints gbc_lblMaxValue = new GridBagConstraints();
		gbc_lblMaxValue.anchor = GridBagConstraints.WEST;
		gbc_lblMaxValue.insets = new Insets(0, 0, 5, 5);
		gbc_lblMaxValue.gridx = 0;
		gbc_lblMaxValue.gridy = 1;
		add(lblMaxValue, gbc_lblMaxValue);
		
		textField_1 = new JTextField();
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.insets = new Insets(0, 0, 5, 0);
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 1;
		gbc_textField_1.gridy = 1;
		add(textField_1, gbc_textField_1);
		textField_1.setColumns(10);
		
		JLabel lblDefaultValue = new JLabel("Default Value");
		GridBagConstraints gbc_lblDefaultValue = new GridBagConstraints();
		gbc_lblDefaultValue.anchor = GridBagConstraints.WEST;
		gbc_lblDefaultValue.insets = new Insets(0, 0, 0, 5);
		gbc_lblDefaultValue.gridx = 0;
		gbc_lblDefaultValue.gridy = 2;
		add(lblDefaultValue, gbc_lblDefaultValue);
		
		textField_2 = new JTextField();
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_2.gridx = 1;
		gbc_textField_2.gridy = 2;
		add(textField_2, gbc_textField_2);
		textField_2.setColumns(10);
	}

}
