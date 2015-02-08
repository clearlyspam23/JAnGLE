package com.clearlyspam23.GLE.basic.layers.tile;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import com.clearlyspam23.GLE.level.Layer;
import com.clearlyspam23.GLE.level.LayerTemplate;
import com.clearlyspam23.GLE.util.Vector2;

public class TileLayerTemplate extends LayerTemplate{
	
	private Vector2 defaultGridDimensions = new Vector2();
	private List<TileConstraint> constraints = new ArrayList<TileConstraint>();
	private boolean allowGridResizing;
	
	public TileLayerTemplate() {
	}
	
	public TileLayerTemplate(TileLayerDefinition def) {
		super(def);
	}

	@Override
	public Layer<?> createLayer() {
		return new TileLayer(this);
	}

	public double getDefaultGridHeight() {
		return defaultGridDimensions.y;
	}
	
	public Vector2 getDefaultGridDimensions(){
		return defaultGridDimensions;
	}

	public double getDefaultGridWidth() {
		return defaultGridDimensions.x;
	}

	public void setDefaultGridDimensions(double gridWidth, double gridHeight) {
		defaultGridDimensions.set(gridWidth, gridHeight);
	}
	
	public void setDefaultGridDimensions(Vector2 dimensions){
		defaultGridDimensions.set(dimensions);
	}

	public boolean allowsGridResizing() {
		return allowGridResizing;
	}

	public void allowGridResizing(boolean allowGridResizing) {
		this.allowGridResizing = allowGridResizing;
	}
	
	public List<TileConstraint> getConstraints(){
		return Collections.unmodifiableList(constraints);
	}
	
	public void addConstraint(TileConstraint constraint){
		constraints.add(constraint);
	}
	
	public void clearConstraints(){
		constraints.clear();
	}
	
	public void addAllConstraints(Collection<? extends TileConstraint> cons){
		constraints.addAll(cons);
	}

}
