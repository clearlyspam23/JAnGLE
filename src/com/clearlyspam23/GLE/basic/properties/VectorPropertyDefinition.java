package com.clearlyspam23.GLE.basic.properties;

import com.clearlyspam23.GLE.PropertyDefinition;
import com.clearlyspam23.GLE.basic.gui.properties.VectorPanel;

public class VectorPropertyDefinition extends PropertyDefinition<VectorPanel, VectorPropertyTemplate> {

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VectorPanel getLayerComponent() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VectorPropertyTemplate buildFromGUI(VectorPanel gui, String name) {
		return new VectorPropertyTemplate(name, this);
	}

	@Override
	public void setGUITo(VectorPanel gui, VectorPropertyTemplate template) {
	}

}
