package com.clearlyspam23.GLE.basic.layers.tile;

import java.awt.Image;

public class TileData {
	
	public TilesetHandle tileset;
	public int tileX;
	public int tileY;
	
	public TileData(){
		
	}
	
	public TileData(TilesetHandle tileset, int tileX, int tileY){
		this.tileset = tileset;
		this.tileX = tileX;
		this.tileY = tileY;
	}
	
	public boolean equals(Object o){
		if(!(o instanceof TileData))
			return false;
		return equals((TileData)o);
	}
	
	public boolean equals(TileData tile){
		return equals(tile.tileset, tile.tileX, tile.tileY);
	}
	
	public boolean equals(TilesetHandle tileset, int tileX, int tileY){
		if(this.tileset==null){
			return tileset==null;
		}
		return this.tileset.equals(tileset)&&this.tileX==tileX&&this.tileY==tileY;
	}
	
	public void setTileset(TilesetHandle tileset, int tileX, int tileY){
		this.tileset = tileset;
		this.tileX = tileX;
		this.tileY = tileY;
	}
	
	public void setTileset(TileData data){
		setTileset(data.tileset, data.tileX, data.tileY);
	}
	
	public void resetTileset(){
		setTileset(null, -1, -1);
	}
	
	public boolean isValid(){
		return tileset!=null&&tileset.isValidLocation(tileX, tileY);
	}
	
	public Image getTileImage(){
		return tileset.getTileAt(tileX, tileY);
	}
	
	public TileData copyTileData(){
		return new TileData(tileset, tileX, tileY);
	}

}
