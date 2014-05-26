package com.clearlyspam23.GLE;

import com.clearlyspam23.GLE.GUI.SubPanel;
import com.clearlyspam23.GLE.templates.Layer;

public abstract class LayerDefinition<T extends SubPanel> {
	
	public abstract T getLayerComponent();
	
	public abstract Layer buildFromGUI(T gui);
	
	public abstract String getTypeName();

}
