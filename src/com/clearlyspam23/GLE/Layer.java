package com.clearlyspam23.GLE;

import java.awt.Frame;
import java.util.List;

import org.piccolo2d.PNode;
import org.piccolo2d.event.PInputEventListener;

import com.clearlyspam23.GLE.GUI.LayerDialog;
import com.clearlyspam23.GLE.GUI.LayerEditorDialog;

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
	 * the inverse operation of getExportData. Should set this grid to represent the given data.
	 * @param data
	 */
	public abstract void buildFromData(T data);
	
	/**
	 * grabs the necessary Dialogs to allow editing of this layer. These dialogs can be shared amongst similarly typed layers, or unique for each one.
	 * This method will only be called once when the level is created.
	 * @param frame the frame object each dialog should have as its "parent"
	 * @return a List of Dialogs necessary to edit this layer
	 */
	public abstract List<LayerEditorDialog> getEditors(Frame frame);
	
	/**
	 * grabs any PInputEventListeners that are relevant for this layer. 
	 * These EventListeners may be removed or re-added as this layer loses or gains focus.
	 * each time this focus changes, this method will be recalled.
	 * @return a list of relevant PInputEventListeners
	 */
	public abstract List<PInputEventListener> getListeners();
	

}
