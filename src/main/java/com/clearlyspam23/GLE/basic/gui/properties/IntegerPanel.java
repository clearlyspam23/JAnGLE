package com.clearlyspam23.GLE.basic.gui.properties;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;

import com.clearlyspam23.GLE.GUI.SubPanel;
import com.clearlyspam23.GLE.GUI.util.IntegerComponent;
import javax.swing.JCheckBox;

public class IntegerPanel extends SubPanel{
	private IntegerComponent minField;
	private IntegerComponent maxField;
	private IntegerComponent defaultField;
	private JCheckBox chckbxUseSlider;
	public IntegerPanel() {
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{80, 60, 0};
		gridBagLayout.rowHeights = new int[]{14, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblValue = new JLabel("Min Value");
		GridBagConstraints gbc_lblValue = new GridBagConstraints();
		gbc_lblValue.insets = new Insets(0, 0, 5, 5);
		gbc_lblValue.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblValue.gridx = 0;
		gbc_lblValue.gridy = 0;
		add(lblValue, gbc_lblValue);
		
		minField = new IntegerComponent();
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
		
		maxField = new IntegerComponent();
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
		gbc_lblDefaultValue.insets = new Insets(0, 0, 5, 5);
		gbc_lblDefaultValue.gridx = 0;
		gbc_lblDefaultValue.gridy = 2;
		add(lblDefaultValue, gbc_lblDefaultValue);
		
		defaultField = new IntegerComponent();
		GridBagConstraints gbc_defaultField = new GridBagConstraints();
		gbc_defaultField.insets = new Insets(0, 0, 5, 0);
		gbc_defaultField.fill = GridBagConstraints.HORIZONTAL;
		gbc_defaultField.gridx = 1;
		gbc_defaultField.gridy = 2;
		add(defaultField, gbc_defaultField);
		defaultField.setColumns(10);
		
		chckbxUseSlider = new JCheckBox("Use Slider");
		GridBagConstraints gbc_chckbxUseSlider = new GridBagConstraints();
		gbc_chckbxUseSlider.anchor = GridBagConstraints.WEST;
		gbc_chckbxUseSlider.gridx = 1;
		gbc_chckbxUseSlider.gridy = 3;
		add(chckbxUseSlider, gbc_chckbxUseSlider);
	
	}
	
	public int getMin(){
		return minField.getValue();
	}
	
	public int getMax(){
		return maxField.getValue();
	}
	
	public int getDefault(){
		return defaultField.getValue();
	}
	
	public boolean useSlider(){
		return chckbxUseSlider.isSelected();
	}
	
	public void setMin(int min){
		minField.setValue(min);
	}
	public void setMax(int max){
		maxField.setValue(max);
	}
	public void setDefault(int def){
		defaultField.setValue(def);
	}
	public void setUseSlider(boolean flag){
		chckbxUseSlider.setSelected(flag);
	}
	@Override
	public void reset() {
		setMin(0);
		setMax(255);
		setDefault(0);
		setUseSlider(false);
	}

}
