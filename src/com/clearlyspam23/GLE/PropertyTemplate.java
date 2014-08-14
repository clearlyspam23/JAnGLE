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
	
	public final void setDefinition(PropertyDefinition def){
		definition = def;
	}
	
//	public final void setToProperty(T component, Property<E> value){
//		setToValue(component, value.getValue());
//	}
//	
//	public final Property<E> getPropertyFrom(T component){
//		return new Property<E>(definition.getName(), getValueFrom(component));
//	}
//	
//	public final Property<E> getDefaultProperty(){
//		return new Property<E>(definition.getName(), getDefaultValue());
//	}
	
	public abstract T getEditorComponent();
	
	public abstract void setToValue(T component, E value);
	
	public abstract E getValueFrom(T component);
	
	public abstract E getDefaultValue();
	
	public List<String> checkValidity(){
		return null;
	}

}
