package com.clearlyspam23.GLE.level;

import com.clearlyspam23.GLE.Nameable;
import com.clearlyspam23.GLE.Template;


public abstract class LayerTemplate implements Nameable{
	
	private String name;
	private Template template;
	
	protected LayerTemplate(){
		
	}
	
	@SuppressWarnings("rawtypes")
	private LayerDefinition definition;
	
	@SuppressWarnings("rawtypes")
	public LayerTemplate(LayerDefinition def){
		this.definition = def;
	}
	
	public abstract Layer createLayer();
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}

	@SuppressWarnings("rawtypes")
	public LayerDefinition getDefinition() {
		return definition;
	}
	
	public void setDefinition(LayerDefinition def){
		this.definition = def;
	}

	public Template getTemplate() {
		return template;
	}

	public void setTemplate(Template template) {
		this.template = template;
	}

}
