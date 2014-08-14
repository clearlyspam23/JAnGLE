package com.clearlyspam23.GLE.basic.properties;

import java.awt.Component;

import javax.swing.JSlider;

import com.clearlyspam23.GLE.PropertyTemplate;
import com.clearlyspam23.GLE.GUI.util.IntegerComponent;

public class IntPropertyTemplate extends PropertyTemplate<Component, Number> {
	
	private int def;
	private int min;
	private int max;
	private boolean useSlider;
	
	public IntPropertyTemplate(){}
	
	public IntPropertyTemplate(IntPropertyDefinition def){
		super(def);
	}
	
	public IntPropertyTemplate(IntPropertyDefinition def, int defa, int min, int max, boolean useSlider){
		super(def);
		this.setDefault(defa);
		this.setMin(min);
		this.setMax(max);
		this.setUseSlider(useSlider);
	}

	@Override
	public Component getEditorComponent() {
		if(useSlider){
			JSlider slider = new JSlider(min, max);
			slider.setPaintTicks(true);
			slider.setPaintLabels(true);
			int major = (max-min)/5;
			slider.setMajorTickSpacing(Math.max(major, 1));
			int minor = (max-min)/50;
			slider.setMinorTickSpacing(Math.max(minor, 1));
			return slider;
		}
		return new IntegerComponent();
	}

	@Override
	public void setToValue(Component component, Number value) {
		if(component instanceof JSlider)
			((JSlider)component).setValue(value.intValue());
		else
			((IntegerComponent)component).setValue(value.intValue());
	}

	@Override
	public Number getValueFrom(Component component) {
		if(component instanceof JSlider){
			return ((JSlider)component).getValue();
		}
		return ((IntegerComponent)component).getValue();
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
	public Number getDefaultValue() {
		return def;
	}

	public boolean isUseSlider() {
		return useSlider;
	}

	public void setUseSlider(boolean useSlider) {
		this.useSlider = useSlider;
	}

}
