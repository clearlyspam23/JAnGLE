package com.clearlyspam23.GLE;

import java.awt.Component;

import com.clearlyspam23.GLE.templates.Layer;

public abstract class LayerDefinition<T extends Component> {
	
	public abstract T getLayerComponent();
	
	public abstract Layer buildFromGUI(T gui);
	
	public abstract String getTypeName();

}
