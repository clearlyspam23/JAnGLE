package com.clearlyspam23.GLE;

import java.awt.Component;

public abstract class PropertyTemplate<T extends Component, E> implements Nameable{
	
	private String name;
	
	public final String getName(){
		return name;
	}
	
	public final void setName(String name){
		this.name = name;
	}
	
	public abstract T getEditorComponent();
	
	public abstract void setToValue(T component, E value);
	
	public abstract E getValueFrom(T component);

}
