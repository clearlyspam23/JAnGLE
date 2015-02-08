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
	public IntPropertyTemplate buildFromGUI(IntegerPanel gui) {
		return new IntPropertyTemplate(this, gui.getDefault(), gui.getMin(), gui.getMax(), gui.useSlider());
	}

	@Override
	public void setGUITo(IntegerPanel gui, IntPropertyTemplate template) {
		gui.setDefault(template.getDefault());
		gui.setMin(template.getMin());
		gui.setMax(template.getMax());
	}

	@Override
	public Class<IntPropertyTemplate> getPropertyClass() {
		return IntPropertyTemplate.class;
	}

}
