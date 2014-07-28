package com.clearlyspam23.GLE.basic.layers.tile;

import java.util.List;

import com.clearlyspam23.GLE.Layer;
import com.clearlyspam23.GLE.LayerDefinition;
import com.clearlyspam23.GLE.LayerTemplate;
import com.clearlyspam23.GLE.Level;
import com.clearlyspam23.GLE.util.Vector2;

public class TileLayerTemplate extends LayerTemplate{
	
	private Vector2 gridDimensions = new Vector2();
	private List<TileConstraint> constraints;
	
	public TileLayerTemplate() {
	}
	
	public TileLayerTemplate(TileLayerDefinition def) {
		super(def);
	}

	@Override
	public Layer<?> createLayer() {
		return new TileLayer(this);
	}

	public double getGridHeight() {
		return gridDimensions.y;
	}
	
	public Vector2 getGridDimensions(){
		return gridDimensions;
	}

	public double getGridWidth() {
		return gridDimensions.x;
	}

	public void setGridDimensions(double gridWidth, double gridHeight) {
		gridDimensions.set(gridWidth, gridHeight);
	}
	
	public void setGridDimensions(Vector2 dimensions){
		gridDimensions.set(dimensions);
	}

}
