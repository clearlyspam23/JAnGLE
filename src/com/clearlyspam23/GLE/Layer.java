package com.clearlyspam23.GLE;

import java.util.List;

import javax.swing.JMenuItem;

import org.piccolo2d.PNode;

import com.clearlyspam23.GLE.GUI.LayerEditManager;

public abstract class Layer<T> implements Nameable{
	
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
	
	/**
	 * the inverse operation of getExportData. Should set this grid to represent the given data.
	 * @param data
	 */
	public abstract void buildFromData(T data);
	
	public abstract LayerEditManager getEditManager();
	
	public abstract void onResize(double x, double y);
	
	public ActionData getActionData(){
		return new ActionData(getName(), this, getExportData());
	}
	
	public Class<?> getExportDataClass(){
		return getExportData().getClass();
	}
	
	@SuppressWarnings("unchecked")
	public void setToActionData(ActionData data){
		this.buildFromData((T) data.data);
	}
	
	public List<JMenuItem> getMenuItems(){
		return null;
	}
	

}
