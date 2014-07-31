package com.clearlyspam23.GLE;

public class ActionData {
	
	public final Object data;
	public final String name;
	public final Layer layer;
	
	public ActionData(String name, Layer layer, Object data){
		this.name = name;
		this.layer = layer;
		this.data = data;
	}

}
