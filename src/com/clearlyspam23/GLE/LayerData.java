package com.clearlyspam23.GLE;

public abstract class LayerData implements Nameable{
	
	public String name;
	
	public final String getName(){
		return name;
	}
	
	public final void setName(String name){
		this.name = name;
	}
	
	public abstract String getClassAlias();

}
