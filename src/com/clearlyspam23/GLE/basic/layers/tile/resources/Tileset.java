package com.clearlyspam23.GLE.basic.layers.tile.resources;

import java.awt.Image;

public class Tileset{
	
	private Image[][] tileset;
	private int width;
	private int height;
	
	public Tileset(Image[][] tileset){
		this.setTileset(tileset);
	}

	public Image[][] getTileset() {
		return tileset;
	}

	public void setTileset(Image[][] tileset) {
		this.tileset = tileset;
		width = tileset.length;
		height = (tileset.length>0 ? tileset[0].length : 0);
	}
	
	public Image getTileAt(int x, int y){
		return tileset[x][y];
	}
	
	public Image getTileByIndex(int index){
		return getTileAt(index%width, index/width);
	}
	
	public int getXFromIndex(int index){
		return index%width;
	}
	
	public int getYFromIndex(int index){
		return index/width;
	}
	
	public boolean isValidLocation(int x, int y){
		return x>=0&&x<tileset.length&&y>=0&&y<tileset[x].length;
	}
	
	public int getIndex(int x, int y){
		return y*width+x;
	}
	
	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

}
