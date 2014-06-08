package com.clearlyspam23.GLE;


public abstract class LayerTemplate {
	
	private String name;
	
	public abstract Layer createLayer();
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}

}
