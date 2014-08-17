package com.clearlyspam23.GLE.GUI;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JMenuItem;

import com.clearlyspam23.GLE.Layer;
import com.clearlyspam23.GLE.Nameable;

public abstract class LayerMenuItem <T extends Layer> extends JMenuItem{
	
	public LayerMenuItem() {
		super();
	}

	public LayerMenuItem(Action a) {
		super(a);
	}

	public LayerMenuItem(Icon icon) {
		super(icon);
	}

	public LayerMenuItem(String text, int mnemonic) {
		super(text, mnemonic);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public LayerMenuItem(String name){
		super(name);
	}
	
	public LayerMenuItem(String name, Icon icon){
		super(name, icon);
	}

	public abstract void performAction(T layer);
	
	public void onShow(T activeLayer){
		
	}
	
	public void onHide(T activeLayer){
		
	}

}
