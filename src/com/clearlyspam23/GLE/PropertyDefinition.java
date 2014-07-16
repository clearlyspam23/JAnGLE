package com.clearlyspam23.GLE;

import com.clearlyspam23.GLE.GUI.SubPanel;

public abstract class PropertyDefinition<T extends SubPanel, E extends PropertyTemplate<?, ?>> implements Nameable{
	
	public abstract T getLayerComponent();
	
	public abstract E buildFromGUI(T gui, String name);
	
	public abstract String setGUITo(T gui, E template);

}
