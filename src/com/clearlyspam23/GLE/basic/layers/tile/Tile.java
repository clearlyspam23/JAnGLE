package com.clearlyspam23.GLE.basic.layers.tile;

public class Tile {
	
	public TilesetHandle tileset;
	public int tileX;
	public int tileY;
	
	public Tile(){
		
	}
	
	public Tile(TilesetHandle tileset, int tileX, int tileY){
		this.tileset = tileset;
		this.tileX = tileX;
		this.tileY = tileY;
	}

}
