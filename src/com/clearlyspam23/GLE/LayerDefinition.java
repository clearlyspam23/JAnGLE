package com.clearlyspam23.GLE;

import com.clearlyspam23.GLE.GUI.SubPanel;
import com.clearlyspam23.GLE.LayerTemplate;

public abstract class LayerDefinition<T extends SubPanel, E extends LayerTemplate> implements Nameable{
	
	public abstract T getLayerComponent();
	
	/**
	 * method to build a LayerTemplate from this Definition's supplied GUI
	 * @param gui the current gui for this LayerDefinition
	 * @return a LayerTemplate representing the given gui
	 */
	public abstract E buildFromGUI(T gui);
	
	/**
	 * essentially the inverse method to buildFromGUI, this method should take a gui and a template,
	 * and set the gui to reflect the template
	 * @param gui
	 * @param template
	 */
	public abstract void setGUITo(T gui, E template);
	
	public abstract Class<E> getLayerClass();

}
