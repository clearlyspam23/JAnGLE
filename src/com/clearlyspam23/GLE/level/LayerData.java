package com.clearlyspam23.GLE.level;

import com.clearlyspam23.GLE.Nameable;

public class LayerData implements Nameable{
	
	public String name;
	public Object data;
	
	public LayerData(){
		
	}
	
	public LayerData(String name, Object data){
		this.name = name;
		this.data = data;
	}
	
	//TODO, split this up
	
	public final String getName(){
		return name;
	}
	
	public final Object getData(){
		return data;
	}

}
