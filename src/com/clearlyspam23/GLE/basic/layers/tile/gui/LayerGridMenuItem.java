package com.clearlyspam23.GLE.basic.layers.tile.gui;

import com.clearlyspam23.GLE.GUI.LayerMenuItem;
import com.clearlyspam23.GLE.basic.layers.tile.TileLayer;

public class LayerGridMenuItem extends LayerMenuItem<TileLayer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LayerGridMenuItem() {
		super("Show Grid");
	}

	@Override
	public void performAction(TileLayer layer) {
		layer.toggleShowGrid(!layer.isGridShowing());
		onShow(layer);
	}
	
	public void onShow(TileLayer layer){
		if(layer.isGridShowing())
			setText("Hide Grid");
		else
			setText("Show Grid");
	}

}
