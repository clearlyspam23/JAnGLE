package com.clearlyspam23.GLE.GUI.util;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.text.DecimalFormat;
import java.text.ParseException;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.DocumentFilter;
import javax.swing.text.PlainDocument;

import com.clearlyspam23.GLE.util.FloatDocumentFilter;
import com.clearlyspam23.GLE.util.Vector2;

public class VectorComponent extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textField1;
	private JTextField textField2;
	
	private static final DecimalFormat format = new DecimalFormat("#.####");

	/**
	 * Create the panel.
	 */
	public VectorComponent() {
		
		DocumentFilter filter = new FloatDocumentFilter();
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{86, 6, 86, 0};
		gridBagLayout.rowHeights = new int[]{20, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		textField1 = new JTextField();
		PlainDocument doc = new PlainDocument();
		doc.setDocumentFilter(filter);
		textField1.setDocument(doc);
		textField1.setColumns(10);
		GridBagConstraints gbc_textField1 = new GridBagConstraints();
		gbc_textField1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField1.anchor = GridBagConstraints.NORTH;
		gbc_textField1.insets = new Insets(0, 0, 0, 5);
		gbc_textField1.gridx = 0;
		gbc_textField1.gridy = 0;
		add(textField1, gbc_textField1);
		
		JLabel label = new JLabel("x");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.insets = new Insets(0, 0, 0, 5);
		gbc_label.gridx = 1;
		gbc_label.gridy = 0;
		add(label, gbc_label);
		
		textField2 = new JTextField();
		doc = new PlainDocument();
		doc.setDocumentFilter(filter);
		textField2.setDocument(doc);
		textField2.setColumns(10);
		GridBagConstraints gbc_textField2 = new GridBagConstraints();
		gbc_textField2.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField2.anchor = GridBagConstraints.NORTH;
		gbc_textField2.gridx = 2;
		gbc_textField2.gridy = 0;
		add(textField2, gbc_textField2);
		
		setXField(0);
		setYField(0);
		
	}
	
	public JTextField getField1()
	{
		return textField1;
	}
	
	public JTextField getField2()
	{
		return textField2;
	}
	
	public double getXField(){
		if(textField1.getText().length()<=0)
			return 0;
		try {
			return format.parse(textField1.getText()).doubleValue();
		} catch (ParseException e) {
			//should never happen, thanks java
		}
		return 0;
	}
	
	public double getYField(){
		if(textField2.getText().length()<=0)
			return 0;
		try {
			return format.parse(textField2.getText()).doubleValue();
		} catch (ParseException e) {
			//should never happen, thanks java
		}
		return 0;
	}
	
	public void setXField(double x){
		textField1.setText(format.format(x));
	}
	
	public void setYField(double y){
		textField2.setText(format.format(y));
	}
	
	public Vector2 getVector(){
		return new Vector2(getXField(), getYField());
	}
	
	public void setToVector(Vector2 vec){
		setXField(vec.x);
		setYField(vec.y);
	}

}
