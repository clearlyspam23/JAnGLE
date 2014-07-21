package com.clearlyspam23.GLE.basic.properties;

import com.clearlyspam23.GLE.PropertyDefinition;
import com.clearlyspam23.GLE.PropertyTemplate;
import com.clearlyspam23.GLE.GUI.util.VectorComponent;
import com.clearlyspam23.GLE.util.Vector2f;

public class VectorPropertyTemplate extends PropertyTemplate<VectorComponent, Vector2f> {

	public VectorPropertyTemplate(String name, VectorPropertyDefinition def) {
		super(name, def);
	}

	@Override
	public VectorComponent getEditorComponent() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setToValue(VectorComponent component, Vector2f value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Vector2f getValueFrom(VectorComponent component) {
		// TODO Auto-generated method stub
		return null;
	}

}
