package com.clearlyspam23.GLE;

import java.awt.Component;

import com.clearlyspam23.GLE.templates.LayerTemplate;

public abstract class LayerDefinition<T extends Component> {
	
	public abstract T getLayerComponent();
	
	public abstract LayerTemplate buildFromGUI(T gui);
	
	public abstract String getTypeName();
	
	public abstract String getToolTipInfo();

}
