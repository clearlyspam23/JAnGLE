package com.clearlyspam23.GLE.level;

import java.util.List;

import javax.swing.JMenuItem;

import org.piccolo2d.PNode;

import com.clearlyspam23.GLE.ActionData;
import com.clearlyspam23.GLE.Nameable;
import com.clearlyspam23.GLE.GUI.LayerEditManager;

public abstract class Layer<T> implements Nameable, LevelChangeListener{
	
	public static int SUCCESS = 0;
	public static int PARTIAL_SUCCESS = 1;
	public static int FAILURE = 2;
	
	private final LayerDefinition def;
	
	public Layer(LayerDefinition def){
		this.def = def;
	}
	
	/**
	 * get an object representing the data necessary to recreate this layer in game. This data should not include anything necessary
	 * to render in JAnGLE (only stuff useful for games), and ideally shouldnt be dependant on a specific form of serialization
	 * this method will only be called before serialization of the Layer, and so it is fine to generate this on the fly, if need be.
	 * @return the necessary data for this layer
	 */
	public abstract T getExportData();
	
	/**
	 * get the actual main UI part of this Layer. This should be the display for whatever would be seen "in game" on this layer
	 * @return the Piccolo2D Node representing this layer
	 */
	public abstract PNode getLayerGUI();
	
	public PNode getOverlayGUI(){
		return null;
	}
	
	/**
	 * the inverse operation of getExportData. Should set this grid to represent the given data.
	 * @param data
	 */
	public abstract int buildFromData(T data);
	
	public abstract LayerEditManager getEditManager();
	
	public Class<?> getExportDataClass(){
		return getExportData().getClass();
	}
	
	public List<JMenuItem> getMenuItems(){
		return null;
	}

	public LayerDefinition getDefinition() {
		return def;
	}
	
	/**
	 * kind of hacker way to allow the borders to be the right size, might change this later
	 * @return the minimum border width of this level, or -1 if this layer does not care
	 */
	public float minBorderWidth(){
		return -1f;
	}
	

}
