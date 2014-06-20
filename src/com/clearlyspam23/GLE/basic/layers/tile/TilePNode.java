package com.clearlyspam23.GLE.basic.layers.tile;

import org.piccolo2d.nodes.PImage;

public class TilePNode extends PImage {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Tileset currentTileset;
	private int tilesetX = -1;
	private int tilesetY = -1;
	
	public void setTileset(Tileset set, int x, int y)
	{
		currentTileset = set;
		tilesetX = x;
		tilesetY = y;
	}
	
	public void resetTileset(){
		currentTileset = null;
		tilesetX = -1;
		tilesetY = -1;
	}

	public Tileset getTileset() {
		return currentTileset;
	}

	public int getTilesetX() {
		return tilesetX;
	}

	public int getTilesetY() {
		return tilesetY;
	}

}
