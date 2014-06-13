package com.clearlyspam23.GLE.GUI.template;

import javax.swing.JPanel;

import com.clearlyspam23.GLE.PluginManager;
import com.clearlyspam23.GLE.Template;

public abstract class TemplateSubPanel extends JPanel{
	
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
	
	public abstract String getPanelName();

}
