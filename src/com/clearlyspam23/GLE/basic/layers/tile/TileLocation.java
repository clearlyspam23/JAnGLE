package com.clearlyspam23.GLE.basic.layers.tile;

public class TileLocation {
	
	public int gridX;
	public int gridY;
	
	public TileLocation(){
		
	}
	
	public TileLocation(int gridX, int gridY){
		this.gridX = gridX;
		this.gridY = gridY;
	}
	
	public TileLocation copy(){
		return new TileLocation(gridX, gridY);
	}
	
	public void set(int x, int y){
		gridX = x;
		gridY = y;
	}

}
