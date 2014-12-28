package com.clearlyspam23.GLE.basic.properties;

import javax.swing.JCheckBox;

import com.clearlyspam23.GLE.PropertyTemplate;

public class BooleanPropertyTemplate extends PropertyTemplate<JCheckBox, Boolean> {
	
	private boolean defaultValue;
	
	public BooleanPropertyTemplate(boolean defaultValue){
		this.defaultValue = defaultValue;
	}

	@Override
	public JCheckBox getEditorComponent() {
		JCheckBox box = new JCheckBox();
		box.setSelected(defaultValue);
		return box;
	}

	@Override
	public void setToValue(JCheckBox component, Boolean value) {
		component.setSelected(value.booleanValue());
	}

	@Override
	public Boolean getValueFrom(JCheckBox component) {
		return component.isSelected();
	}

	@Override
	public Boolean getDefaultValue() {
		return Boolean.FALSE;
	}

	public boolean defaultsToTrue() {
		return defaultValue;
	}

	public void setDefaultTrue(boolean defaultValue) {
		this.defaultValue = defaultValue;
	}

}
