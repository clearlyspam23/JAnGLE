package com.clearlyspam23.GLE.basic.layers.tile;

import com.clearlyspam23.GLE.Layer;
import com.clearlyspam23.GLE.LayerTemplate;
import com.clearlyspam23.GLE.Level;

public class TileLayerTemplate extends LayerTemplate{
	
	private double gridWidth;
	private double gridHeight;

	@Override
	public Layer<?> createLayer(Level level) {
		return new TileLayer(this);
	}

	public double getGridHeight() {
		return gridHeight;
	}

	public void setGridHeight(double gridHeight) {
		this.gridHeight = gridHeight;
	}

	public double getGridWidth() {
		return gridWidth;
	}

	public void setGridWidth(double gridWidth) {
		this.gridWidth = gridWidth;
	}

}
