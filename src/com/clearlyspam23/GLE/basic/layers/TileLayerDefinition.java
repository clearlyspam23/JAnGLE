package com.clearlyspam23.GLE.basic.layers;

import com.clearlyspam23.GLE.LayerDefinition;
import com.clearlyspam23.GLE.basic.gui.TileLayerGUIOptions;
import com.clearlyspam23.GLE.LayerTemplate;

public class TileLayerDefinition extends LayerDefinition<TileLayerGUIOptions> {

	@Override
	public String getTypeName() {
		return "Tile";
	}

	@Override
	public LayerTemplate buildFromGUI(TileLayerGUIOptions gui) {
		return null;
	}

	@Override
	public TileLayerGUIOptions getLayerComponent() {
		return new TileLayerGUIOptions();
	}

}
