package com.clearlyspam23.GLE.GUI;

import java.awt.Component;

import javax.swing.Icon;

public class ComponentData{
	public Component component;
	public String name;
	public Icon icon;
	
	public ComponentData(Component component, String name){
		this.component = component;
		this.name = name;
	}
	
	public ComponentData(Component component, String name, Icon icon){
		this.component = component;
		this.name = name;
		this.icon = icon;
	}
}
