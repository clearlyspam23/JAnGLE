package com.clearlyspam23.GLE.definitions;

import javax.swing.JComponent;
import javax.swing.JMenu;

import com.clearlyspam23.GLE.level.Layer;

public abstract class LayerDefinition<T extends JComponent> {
	
	public abstract T createLayerOptions();
	public abstract Layer createLayer(T component);
	public abstract String getLayerTemplateName();
	public abstract void addLayerMenuOptions(JMenu menu);
	//probably also need some method that adds appropriate level buttons and properties to the window or something

}
