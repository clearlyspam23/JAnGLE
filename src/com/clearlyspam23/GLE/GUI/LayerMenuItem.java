package com.clearlyspam23.GLE.GUI;

import javax.swing.JMenuItem;

import com.clearlyspam23.GLE.level.Layer;

@SuppressWarnings("rawtypes")
public abstract class LayerMenuItem <T extends Layer, E extends JMenuItem>{
	
	private final E menuItem;
	
	public LayerMenuItem(E item){
		menuItem = item;
	}
	
	public final E getMenuItem(){
		return menuItem;
	}
	
//	public LayerMenuItem(String name){
//		super(name);
//	}
//	
//	public LayerMenuItem(String name, Icon icon){
//		super(name, icon);
//	}

	public abstract void performAction(T layer);
	
	public void onShow(T activeLayer){
		
	}
	
	public void onHide(T activeLayer){
		
	}

}
