package com.clearlyspam23.GLE.basic.properties;

import com.clearlyspam23.GLE.PropertyDefinition;
import com.clearlyspam23.GLE.basic.gui.properties.BooleanPanel;

public class BooleanPropertyDefinition extends PropertyDefinition<BooleanPanel, BooleanPropertyTemplate> {

	@Override
	public String getName() {
		return "Boolean";
	}

	@Override
	public BooleanPanel getLayerComponent() {
		return new BooleanPanel();
	}

	@Override
	public BooleanPropertyTemplate buildFromGUI(BooleanPanel gui) {
		return new BooleanPropertyTemplate(gui.getFlag());
	}

	@Override
	public void setGUITo(BooleanPanel gui, BooleanPropertyTemplate template) {
		gui.setFlag(template.defaultsToTrue());
	}

	@Override
	public Class<BooleanPropertyTemplate> getPropertyClass() {
		return BooleanPropertyTemplate.class;
	}

}
