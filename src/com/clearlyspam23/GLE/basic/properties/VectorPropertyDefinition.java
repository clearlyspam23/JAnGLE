package com.clearlyspam23.GLE.basic.properties;

import com.clearlyspam23.GLE.PropertyDefinition;
import com.clearlyspam23.GLE.basic.gui.properties.VectorPanel;

public class VectorPropertyDefinition extends PropertyDefinition<VectorPanel, VectorPropertyTemplate> {

	@Override
	public String getName() {
		return "Vector";
	}

	@Override
	public VectorPanel getLayerComponent() {
		return new VectorPanel();
	}

	@Override
	public VectorPropertyTemplate buildFromGUI(VectorPanel gui, String name) {
		return new VectorPropertyTemplate(name, this, gui.getMinVector(), gui.getMaxVector(), gui.getDefaultVector());
	}

	@Override
	public void setGUITo(VectorPanel gui, VectorPropertyTemplate template) {
		gui.setMinVector(template.getMin());
		gui.setMaxVector(template.getMax());
		gui.setDefaultVector(template.getDefault());
	}

	@Override
	public Class<VectorPropertyTemplate> getLayerClass() {
		return VectorPropertyTemplate.class;
	}

}
