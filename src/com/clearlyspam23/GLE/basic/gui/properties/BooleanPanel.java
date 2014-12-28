package com.clearlyspam23.GLE.basic.gui.properties;

import java.awt.FlowLayout;

import javax.swing.JCheckBox;

import com.clearlyspam23.GLE.GUI.SubPanel;

public class BooleanPanel extends SubPanel {
	private JCheckBox chckbxDefaultValue;
	public BooleanPanel() {
		FlowLayout flowLayout = (FlowLayout) getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		
		chckbxDefaultValue = new JCheckBox("Default True");
		add(chckbxDefaultValue);
	}
	
	public boolean getFlag(){
		return chckbxDefaultValue.isSelected();
	}
	
	public void setFlag(boolean flag){
		chckbxDefaultValue.setSelected(flag);
	}

	@Override
	public void reset() {
		chckbxDefaultValue.setSelected(false);
	}

}
