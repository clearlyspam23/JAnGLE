package com.clearlyspam23.GLE.basic.layers.tile;

import com.clearlyspam23.GLE.LayerDefinition;
import com.clearlyspam23.GLE.basic.gui.TileLayerGUIOptions;

public class TileLayerDefinition extends LayerDefinition<TileLayerGUIOptions, TileLayerTemplate> {

	@Override
	public String getName() {
		return "Tile";
	}

	@Override
	public TileLayerTemplate buildFromGUI(TileLayerGUIOptions gui) {
		TileLayerTemplate t = new TileLayerTemplate(this);
		t.setGridDimensions(gui.getGridDimensions());
		return t;
	}

	@Override
	public TileLayerGUIOptions getLayerComponent() {
		return new TileLayerGUIOptions();
	}

	@Override
	public void setGUITo(TileLayerGUIOptions gui, TileLayerTemplate template) {
		gui.setGridDimensions(template.getGridDimensions());
	}

	@Override
	public Class<TileLayerTemplate> getLayerClass() {
		return TileLayerTemplate.class;
	}

}
