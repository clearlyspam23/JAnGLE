package com.clearlyspam23.GLE.basic.properties;

import java.util.HashMap;
import java.util.Map;

import com.clearlyspam23.GLE.PropertyTemplate;
import com.clearlyspam23.GLE.GUI.util.VectorComponent;
import com.clearlyspam23.GLE.util.Vector2;

public class VectorPropertyTemplate extends PropertyTemplate<VectorComponent, Map<String, Number>> {
	
	private Vector2 min;
	private Vector2 max;
	private Vector2 def;
	
	public VectorPropertyTemplate(){}

	public VectorPropertyTemplate(VectorPropertyDefinition def, Vector2 min, Vector2 max, Vector2 defa) {
		super(def);
		this.min = min;
		this.max = max;
		this.def = defa;
	}

	@Override
	public VectorComponent getEditorComponent() {
		return new VectorComponent();
	}

	@Override
	public void setToValue(VectorComponent component, Map<String, Number> value) {
		component.setXField(value.get("x").doubleValue());
		component.setYField(value.get("y").doubleValue());
	}

	@Override
	public Map<String, Number> getValueFrom(VectorComponent component) {
		HashMap<String, Number> output = new HashMap<String, Number>();
		output.put("x", component.getXField());
		output.put("y", component.getYField());
		return output;
	}

	public Vector2 getMin() {
		return min;
	}

	public void setMin(Vector2 min) {
		this.min = min;
	}

	public Vector2 getMax() {
		return max;
	}

	public void setMax(Vector2 max) {
		this.max = max;
	}

	public Vector2 getDefault() {
		return def;
	}

	public void setDefault(Vector2 def) {
		this.def = def;
	}

	@Override
	public Map<String, Number> getDefaultValue() {
		HashMap<String, Number> output = new HashMap<String, Number>();
		output.put("x", def.x);
		output.put("y", def.y);
		return output;
	}

}
