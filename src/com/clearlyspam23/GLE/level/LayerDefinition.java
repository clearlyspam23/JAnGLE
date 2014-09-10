package com.clearlyspam23.GLE.level;

import com.clearlyspam23.GLE.GUI.EditorItems;
import com.clearlyspam23.GLE.GUI.SubPanel;
import com.clearlyspam23.GLE.level.LayerTemplate;
import com.clearlyspam23.GLE.Nameable;
import com.clearlyspam23.GLE.Template;

public abstract class LayerDefinition<T extends SubPanel, E extends LayerTemplate> implements Nameable{
	
	public abstract T getLayerComponent();
	
	/**
	 * method to build a LayerTemplate from this Definition's supplied GUI
	 * @param gui the current gui for this LayerDefinition
	 * @return a LayerTemplate representing the given gui
	 */
	public abstract E buildFromEditorGUI(T gui);
	
	/**
	 * essentially the inverse method to buildFromEditorGUI, this method should take a gui and a template,
	 * and set the gui to reflect the template
	 * @param gui
	 * @param template
	 */
	public abstract void setEditorGUITo(T gui, E template);
	
	public void onTemplateCreation(Template template){
		
	}
	
	/**
	 * called when a template is opened that contains atleast one LayerTemplate defined by this definition
	 * @param template the template being opened
	 * @return an EditorItems object describing the various GUIs employed by this type of layer, or null if there are no GUI elements
	 */
	public EditorItems onTemplateOpen(Template template){
		return null;
	}
	
	public void onTemplateClose(Template template){
		
	}

}
