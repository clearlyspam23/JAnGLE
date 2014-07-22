package com.clearlyspam23.GLE.basic.layers.tile;

import java.util.List;

import com.clearlyspam23.GLE.LayerDefinition;
import com.clearlyspam23.GLE.LayerTemplate;
import com.clearlyspam23.GLE.basic.gui.TileLayerGUIOptions;
import com.clearlyspam23.GLE.util.Vector2;

public class TileLayerDefinition extends LayerDefinition<TileLayerGUIOptions, TileLayerTemplate> {
	
	private Vector2 gridDimensions;
	private List<TileConstraint> constraints;

	@Override
	public String getName() {
		return "Tile";
	}

	@Override
	public TileLayerTemplate buildFromGUI(TileLayerGUIOptions gui) {
		return null;
	}

	@Override
	public TileLayerGUIOptions getLayerComponent() {
		return new TileLayerGUIOptions();
	}

	@Override
	public void setGUITo(TileLayerGUIOptions gui, TileLayerTemplate template) {
		
	}

}
