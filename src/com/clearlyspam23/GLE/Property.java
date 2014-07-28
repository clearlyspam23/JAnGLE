package com.clearlyspam23.GLE;

public final class Property<T>{
	
	private String name;
	private T value;
	
	public Property(){
		
	}
	
	public Property(String name, T value){
		this.name = name;
		this.setValue(value);
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}

}
