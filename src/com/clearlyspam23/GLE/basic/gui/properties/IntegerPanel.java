package com.clearlyspam23.GLE.basic.gui.properties;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.text.DocumentFilter;
import javax.swing.text.PlainDocument;

import com.clearlyspam23.GLE.GUI.SubPanel;
import com.clearlyspam23.GLE.util.IntegerDocumentFilter;

public class IntegerPanel extends SubPanel{
	private JTextField minField;
	private JTextField maxField;
	private JTextField defaultField;
	public IntegerPanel() {
		
		DocumentFilter filter = new IntegerDocumentFilter();
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
		
		minField = new JTextField();
		PlainDocument doc = new PlainDocument();
		doc.setDocumentFilter(filter);
		minField.setDocument(doc);
		GridBagConstraints gbc_minField = new GridBagConstraints();
		gbc_minField.insets = new Insets(0, 0, 5, 0);
		gbc_minField.fill = GridBagConstraints.HORIZONTAL;
		gbc_minField.gridx = 1;
		gbc_minField.gridy = 0;
		add(minField, gbc_minField);
		minField.setColumns(10);
		
		JLabel lblMaxValue = new JLabel("Max Value");
		GridBagConstraints gbc_lblMaxValue = new GridBagConstraints();
		gbc_lblMaxValue.anchor = GridBagConstraints.WEST;
		gbc_lblMaxValue.insets = new Insets(0, 0, 5, 5);
		gbc_lblMaxValue.gridx = 0;
		gbc_lblMaxValue.gridy = 1;
		add(lblMaxValue, gbc_lblMaxValue);
		
		maxField = new JTextField();
		doc = new PlainDocument();
		doc.setDocumentFilter(filter);
		maxField.setDocument(doc);
		GridBagConstraints gbc_maxField = new GridBagConstraints();
		gbc_maxField.insets = new Insets(0, 0, 5, 0);
		gbc_maxField.fill = GridBagConstraints.HORIZONTAL;
		gbc_maxField.gridx = 1;
		gbc_maxField.gridy = 1;
		add(maxField, gbc_maxField);
		maxField.setColumns(10);
		
		JLabel lblDefaultValue = new JLabel("Default Value");
		GridBagConstraints gbc_lblDefaultValue = new GridBagConstraints();
		gbc_lblDefaultValue.anchor = GridBagConstraints.WEST;
		gbc_lblDefaultValue.insets = new Insets(0, 0, 0, 5);
		gbc_lblDefaultValue.gridx = 0;
		gbc_lblDefaultValue.gridy = 2;
		add(lblDefaultValue, gbc_lblDefaultValue);
		
		defaultField = new JTextField();
		doc = new PlainDocument();
		doc.setDocumentFilter(filter);
		defaultField.setDocument(doc);
		GridBagConstraints gbc_defaultField = new GridBagConstraints();
		gbc_defaultField.fill = GridBagConstraints.HORIZONTAL;
		gbc_defaultField.gridx = 1;
		gbc_defaultField.gridy = 2;
		add(defaultField, gbc_defaultField);
		defaultField.setColumns(10);
	
	}
	
	public int getMin(){
		if(minField.getText().length()<=0)
			return 0;
		return Integer.parseInt(minField.getText());
	}
	
	public int getMax(){
		if(maxField.getText().length()<=0)
			return 0;
		return Integer.parseInt(maxField.getText());
	}
	
	public int getDefault(){
		if(defaultField.getText().length()<=0)
			return 0;
		return Integer.parseInt(defaultField.getText());
	}
	
	public void setMin(int min){
		minField.setText(Integer.toString(min));
	}
	public void setMax(int max){
		maxField.setText(Integer.toString(max));
	}
	public void setDefault(int def){
		defaultField.setText(Integer.toString(def));
	}
	@Override
	public void reset() {
		setMin(0);
		setMax(255);
		setDefault(0);
	}

}
