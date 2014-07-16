package com.clearlyspam23.GLE.basic.properties;

import com.clearlyspam23.GLE.PropertyTemplate;
import com.clearlyspam23.GLE.basic.gui.properties.IntegerComponent;

public class IntPropertyTemplate extends PropertyTemplate<IntegerComponent, Integer> {
	
	private String name;
	private int def;
	private int min;
	private int max;
	
	public IntPropertyTemplate(){
		
	}
	
	public IntPropertyTemplate(String name, int def, int min, int max){
		this.name = name;
		this.def = def;
		this.min = min;
		this.max = max;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public IntegerComponent getEditorComponent() {
		return new IntegerComponent();
	}

	@Override
	public void setToValue(IntegerComponent component, Integer value) {
		
	}

	@Override
	public Integer getValueFrom(IntegerComponent component) {
		return null;
	}

}
