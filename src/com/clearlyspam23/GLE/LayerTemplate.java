package com.clearlyspam23.GLE;


public abstract class LayerTemplate implements Nameable{
	
	private String name;
	
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

}
