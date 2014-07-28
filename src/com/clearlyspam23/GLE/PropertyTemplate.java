package com.clearlyspam23.GLE;

import java.awt.Component;
import java.util.List;

public abstract class PropertyTemplate<T extends Component, E> implements Nameable{
	
	@SuppressWarnings("rawtypes")
	private PropertyDefinition definition;
	
	private String name;
	
	protected PropertyTemplate(){
		
	}
	
	@SuppressWarnings("rawtypes")
	public PropertyTemplate(PropertyDefinition def){
		this.definition = def;
	}
	
	public final String getName(){
		return name;
	}
	
	public final void setName(String name){
		this.name = name;
	}
	
	@SuppressWarnings("rawtypes")
	public final PropertyDefinition getDefinition(){
		return definition;
	}
	
	public abstract T getEditorComponent();
	
	public abstract void setToValue(T component, Property<E> value);
	
	public abstract Property<E> getValueFrom(T component);
	
	public abstract Property<E> getDefaultValue();
	
	public List<String> checkValidity(){
		return null;
	}

}
