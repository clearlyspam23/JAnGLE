package com.clearlyspam23.GLE.basic.layers.tile.gui;

import com.clearlyspam23.GLE.basic.layers.tile.TileLayer;

public interface TileLayerSelectionListener {
	
	public void selectionChange(TileLayer layer, TileSelection oldSelection, TileSelection newSelection);

}
