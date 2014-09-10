package com.clearlyspam23.GLE.basic.layers.tile.resources;

import java.awt.Image;
import java.util.Set;

import com.clearlyspam23.GLE.Nameable;

public interface TilesetHandle extends Nameable{
	
	public String getFilename();
	
	public Image getTileAt(int x, int y);
	
	public Image getTileByIndex(int index);
	
	public int getXFromIndex(int index);
	
	public int getYFromIndex(int index);
	
	public boolean isValidLocation(int x, int y);
	
	public int getIndex(int x, int y);

	public int getTileWidth();

	public int getTileHeight();
	
	public int getWidth();
	
	public int getHeight();

	public int getTileXSpacing();

	public int getTileYSpacing();
	
	public Set<String> getTags();

}
