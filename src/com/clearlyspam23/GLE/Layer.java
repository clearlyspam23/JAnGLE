package com.clearlyspam23.GLE;

import java.util.List;

import org.piccolo2d.PNode;

import com.clearlyspam23.GLE.GUI.LayerDialog;

public abstract class Layer<T> {
	
	/**
	 * get an object representing the data necessary to recreate this layer in game. This data should not include anything necessary
	 * to render in JAnGLE (only stuff useful for games), and ideally shouldnt be dependant on a specific form of serialization
	 * this method will only be called before serialization of the Layer, and so it is fine to generate this on the fly, if need be.
	 * @return the necessary data for this layer
	 */
	public abstract T getExportData();
	
	/**
	 * gets a list of LayerDialogs associated with this layer. This list can be null, if the layer doesn't need any options
	 * these LayerDialogs can be unique per Layer, or shared across similar types of Layers
	 * @return this Layer's LayerDialogs
	 */
	public abstract List<LayerDialog> getLayerDialogs();
	
	/**
	 * get the actual main UI part of this Layer. This should be the display for whatever would be seen "in game" on this layer
	 * @return the Piccolo2D Node representing this layer
	 */
	public abstract PNode getLayerGUI();
	
	/**
	 * the inverse operation of getExportData. This operation should set this grid to represent the given data.
	 * @param data
	 */
	public abstract void buildFromData(T data);
	

}
