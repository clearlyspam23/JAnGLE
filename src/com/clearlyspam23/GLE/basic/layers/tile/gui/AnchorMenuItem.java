package com.clearlyspam23.GLE.basic.layers.tile.gui;

import javax.swing.JMenuItem;

import com.clearlyspam23.GLE.GUI.LayerMenuItem;
import com.clearlyspam23.GLE.basic.layers.tile.TileLayer;

public class AnchorMenuItem extends LayerMenuItem<TileLayer, JMenuItem> {

	public AnchorMenuItem() {
		super(new JMenuItem("Anchor Selection"));
		JMenuItem item = getMenuItem();
		item.setEnabled(false);
	}

	@Override
	public void performAction(TileLayer layer) {
		// TODO Auto-generated method stub
		
	}
	
	public void onShow(TileLayer activeLayer){
		getMenuItem().setEnabled(activeLayer.getBase().hasSelection());
	}

}
