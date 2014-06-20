package com.clearlyspam23.GLE.basic.layers.tile;

import java.awt.Image;

public class Tileset {
	
	private Image[][] tileset;
	
	public Tileset(int width, int height){
		setTileset(new Image[width][height]);
	}
	
	public Tileset(Image[][] tileset){
		this.setTileset(tileset);
	}

	public Image[][] getTileset() {
		return tileset;
	}

	public void setTileset(Image[][] tileset) {
		this.tileset = tileset;
	}
	
	public Image getTileAt(int x, int y){
		return tileset[x][y];
	}
	
	public boolean isValidLocation(int x, int y){
		return x>=0&&x<tileset.length&&y>=0&&y<tileset[x].length;
	}
	
	public int getIndex(int x, int y){
		return y*tileset.length+x;
	}

}
