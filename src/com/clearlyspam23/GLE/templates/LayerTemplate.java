package com.clearlyspam23.GLE.templates;

import com.clearlyspam23.GLE.level.Layer;

public abstract class LayerTemplate {
	
	public abstract Layer createLayer();
	public abstract String getLayerTemplateName();
	//probably also need some method that adds appropriate level buttons and properties to the window or something

}
