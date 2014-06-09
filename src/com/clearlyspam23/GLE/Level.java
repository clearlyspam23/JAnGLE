package com.clearlyspam23.GLE;

import java.util.ArrayList;
import java.util.List;

public class Level {
	
	private Template template;
	private List<Layer> layers = new ArrayList<Layer>();
	
	public Level(Template template)
	{
		this.template = template;
	}

	public Template getTemplate() {
		return template;
	}

	public void setTemplate(Template template) {
		this.template = template;
	}
	
	public void addLayer(Layer l)
	{
		layers.add(l);
	}

}
