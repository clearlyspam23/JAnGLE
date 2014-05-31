package com.clearlyspam23.GLE;

import com.clearlyspam23.GLE.GUI.SubPanel;
import com.clearlyspam23.GLE.LayerTemplate;

public abstract class LayerDefinition<T extends SubPanel> {
	
	public abstract T getLayerComponent();
	
	public abstract LayerTemplate buildFromGUI(T gui);
	
	public abstract String getTypeName();

}
