package com.clearlyspam23.GLE;

import java.awt.Component;
import java.util.List;

public abstract class PropertyTemplate<T extends Component, E> implements Nameable{
	
	private final String name;
	@SuppressWarnings("rawtypes")
	private final PropertyDefinition def;
	
	@SuppressWarnings("rawtypes")
	public PropertyTemplate(String name, PropertyDefinition def){
		this.name = name;
		this.def = def;
	}
	
	public final String getName(){
		return name;
	}
	
	@SuppressWarnings("rawtypes")
	public final PropertyDefinition getDefinition(){
		return def;
	}
	
	public abstract T getEditorComponent();
	
	public abstract void setToValue(T component, E value);
	
	public abstract E getValueFrom(T component);
	
	public List<String> checkValidity(){
		return null;
	}

}
