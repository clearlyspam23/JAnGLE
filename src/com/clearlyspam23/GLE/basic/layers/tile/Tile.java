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
	
	public boolean equals(Object o){
		if(!(o instanceof Tile))
			return false;
		return equals((Tile)o);
	}
	
	public boolean equals(Tile tile){
		return equals(tile.tileset, tile.tileX, tile.tileY);
	}
	
	public boolean equals(TilesetHandle tileset, int tileX, int tileY){
		if(this.tileset==null){
			return tileset==null;
		}
		return this.tileset.equals(tileset)&&this.tileX==tileX&&this.tileY==tileY;
	}

}
