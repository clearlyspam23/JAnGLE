package com.clearlyspam23.GLE.basic.properties;

import com.clearlyspam23.GLE.Property;
import com.clearlyspam23.GLE.PropertyTemplate;
import com.clearlyspam23.GLE.GUI.util.IntegerComponent;

public class IntPropertyTemplate extends PropertyTemplate<IntegerComponent, Number> {
	
	private int def;
	private int min;
	private int max;
	
	public IntPropertyTemplate(){}
	
	public IntPropertyTemplate(IntPropertyDefinition def){
		super(def);
	}
	
	public IntPropertyTemplate(IntPropertyDefinition def, int defa, int min, int max){
		super(def);
		this.setDefault(defa);
		this.setMin(min);
		this.setMax(max);
	}

	@Override
	public IntegerComponent getEditorComponent() {
		return new IntegerComponent();
	}

	@Override
	public void setToValue(IntegerComponent component, Property<Number> value) {
		
	}

	@Override
	public Property<Number> getValueFrom(IntegerComponent component) {
		return null;
	}

	public int getDefault() {
		return def;
	}

	public void setDefault(int def) {
		this.def = def;
	}

	public int getMin() {
		return min;
	}

	public void setMin(int min) {
		this.min = min;
	}

	public int getMax() {
		return max;
	}

	public void setMax(int max) {
		this.max = max;
	}

	@Override
	public Property<Number> getDefaultValue() {
		return new Property<Number>(getName(), def);
	}

}
