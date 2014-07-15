package com.clearlyspam23.GLE;

import com.clearlyspam23.GLE.GUI.SubPanel;

public abstract class PropertyDefinition<T extends SubPanel, E extends PropertyTemplate> {
	
	public abstract String getName();
	
	public abstract T getLayerComponent();
	
	public abstract E buildFromGUI(T gui);
	
	public abstract void setGUITo(T gui, E template);

}
