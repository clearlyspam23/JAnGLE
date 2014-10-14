package com.clearlyspam23.GLE.GUI.template;

import java.util.List;

import com.clearlyspam23.GLE.PluginManager;
import com.clearlyspam23.GLE.Template;
import com.clearlyspam23.GLE.GUI.NamedPanel;

public abstract class TemplateSubPanel extends NamedPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public final PluginManager pluginManager;
	
	public TemplateSubPanel(PluginManager pluginManager)
	{
		this.pluginManager = pluginManager;
	}
	
	/**
	 * called before the template is finalized, in order to ensure that any preconditions are met
	 * this method should return a String for everything that is "wrong" with this panel
	 * if everything is ok, this method should return either an empty List, or null
	 * @return a List of Strings with 1 String for every error with this panel
	 */
	public abstract List<String> verify();
	
	/**
	 * optional method, called after verify. This method should be used to issue warnings prior to template finalization.
	 * Acceptable usage would be to pop up a JOptionDialog, verifying that it is ok for a file to be overwritten
	 * @return true if it is ok to continue with template creation, false if not.
	 */
	public boolean getWarnings(){
		return true;
	}
	
	public abstract void setToTemplate(Template template);
	
	public abstract void generateTemplate(Template template);
	
	public abstract void reset();

}
