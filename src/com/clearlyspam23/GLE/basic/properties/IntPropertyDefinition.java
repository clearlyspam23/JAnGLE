package com.clearlyspam23.GLE.basic.properties;

import com.clearlyspam23.GLE.PropertyDefinition;
import com.clearlyspam23.GLE.basic.gui.properties.IntegerPanel;

public class IntPropertyDefinition extends PropertyDefinition<IntegerPanel, IntPropertyTemplate> {

	@Override
	public String getName() {
		return "Integer";
	}

	@Override
	public IntegerPanel getLayerComponent() {
		return new IntegerPanel();
	}

	@Override
	public IntPropertyTemplate buildFromGUI(IntegerPanel gui, String name) {
		return null;
	}

	@Override
	public String setGUITo(IntegerPanel gui, IntPropertyTemplate template) {
		return null;
	}

}
