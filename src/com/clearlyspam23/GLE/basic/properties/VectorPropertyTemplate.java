package com.clearlyspam23.GLE.basic.properties;

import com.clearlyspam23.GLE.Property;
import com.clearlyspam23.GLE.PropertyTemplate;
import com.clearlyspam23.GLE.GUI.util.VectorComponent;
import com.clearlyspam23.GLE.util.Vector2;

public class VectorPropertyTemplate extends PropertyTemplate<VectorComponent, Vector2> {
	
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setToValue(VectorComponent component, Property<Vector2> value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Property<Vector2> getValueFrom(VectorComponent component) {
		// TODO Auto-generated method stub
		return null;
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
	public Property<Vector2> getDefaultValue() {
		return new Property<Vector2>(getName(), def);
	}

}
