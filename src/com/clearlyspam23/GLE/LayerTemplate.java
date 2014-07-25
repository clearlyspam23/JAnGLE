package com.clearlyspam23.GLE;


public abstract class LayerTemplate implements Nameable{
	
	private String name;
	
	@SuppressWarnings("rawtypes")
	private final LayerDefinition definition;
	
	@SuppressWarnings("rawtypes")
	public LayerTemplate(LayerDefinition def){
		this.definition = def;
	}
	
	@SuppressWarnings("rawtypes")
	public abstract Layer createLayer(Level level);
	
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
