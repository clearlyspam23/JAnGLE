package com.clearlyspam23.GLE.basic.layers.tile;

public class Tile extends TileData{
	
	public TileLocation relativeLocation;
	public TileLocation offset;
	
	public Tile(){
		this(null, -1, -1, new TileLocation(), new TileLocation());
	}
	
	public Tile(TileData data, int gridX, int gridY){
		this(data.tileset, data.tileX, data.tileY, new TileLocation(gridX, gridY), new TileLocation());
	}
	
	public Tile(TilesetHandle tileset, int tileX, int tileY, int gridX, int gridY){
		this(tileset, tileX, tileY, new TileLocation(gridX, gridY), new TileLocation());
	}
	
	public Tile(TilesetHandle tileset, int tileX, int tileY, TileLocation gridLocation){
		this(tileset, tileX, tileY, gridLocation, new TileLocation());
	}
	
	public Tile(TilesetHandle tileset, int tileX, int tileY, int gridX, int gridY, TileLocation offset){
		this(tileset, tileX, tileY, new TileLocation(gridX, gridY), offset);
	}
	
	/**
	 * creates a new Tile, with position relative to the given offset
	 * @param tileset the tilesethandle to use for this tile's image
	 * @param tileX the X index of the tile in the TilesetHandle
	 * @param tileY the Y index of the tile in the TilesetHandle
	 * @param relativeLocation this tile's location relative to the given offset
	 * @param offset an offset from this position. note that this is stored as a reference, meaning that it can be updated from elsewhere
	 */
	public Tile(TilesetHandle tileset, int tileX, int tileY, TileLocation relativeLocation, TileLocation offset){
		super(tileset, tileX, tileY);
		this.relativeLocation = relativeLocation;
		this.offset = offset;
	}
	
	public TileLocation getLocation(){
		return new TileLocation(relativeLocation.gridX + offset.gridX, relativeLocation.gridY + offset.gridY);
	}
	
	public void setLocation(TileLocation location){
		relativeLocation = location;
	}
	
	public Tile copyTile(){
		return new Tile(tileset, tileX, tileY, relativeLocation.gridX, relativeLocation.gridY, offset);
	}

}
