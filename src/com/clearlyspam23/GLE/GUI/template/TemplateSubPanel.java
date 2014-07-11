package com.clearlyspam23.GLE.GUI.template;

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
	
	public abstract void setToTemplate(Template template);
	
	public abstract void generateTemplate(Template template);

}
