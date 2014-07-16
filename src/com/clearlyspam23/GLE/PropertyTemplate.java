package com.clearlyspam23.GLE;

import java.awt.Component;

public abstract class PropertyTemplate<T extends Component, E> implements Nameable{
	
	public abstract T getEditorComponent();
	
	public abstract void setToValue(T component, E value);
	
	public abstract E getValueFrom(T component);

}
