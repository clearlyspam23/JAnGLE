package com.clearlyspam23.GLE.basic.layers.tile;

import java.awt.Image;
import java.io.Serializable;

import com.clearlyspam23.GLE.Nameable;
import com.clearlyspam23.GLE.basic.layers.tile.resources.BasicTilesetHandle;

public abstract class TilesetHandle implements Nameable, Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public abstract String getFilename();
	
	public abstract void setName(String name);
	
	public abstract Image getTileAt(int x, int y);
	
	public abstract Image getTileByIndex(int index);
	
	public abstract int getXFromIndex(int index);
	
	public abstract int getYFromIndex(int index);
	
	public abstract boolean isValidLocation(int x, int y);
	
	public abstract int getIndex(int x, int y);

	public abstract int getTileWidth();

	public abstract int getTileHeight();
	
	public abstract int getWidth();
	
	public abstract int getHeight();

	public abstract int getTileXSpacing();

	public abstract int getTileYSpacing();
	
	public abstract int getID();
	
	public abstract BasicTilesetHandle cloneAsBasic();

}
