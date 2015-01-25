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
	
	public void set(TileLocation other){
		set(other.gridX, other.gridY);
	}
	
	public boolean equals(Object o){
		if(o instanceof TileLocation)
			return equals((TileLocation) o );
		return false;
	}
	
	public boolean equals(TileLocation location){
		return gridX==location.gridX&&gridY==location.gridY;
	}
	
	/**
	 * convenience method for checking if this object equals new TileLocation (location.gridX + offset.gridX, location.gridY + offset.gridY)
	 * @param location the location to check against
	 * @param offset the location's offset
	 * @return if this object is equal to new TileLocation (location.gridX + offset.gridX, location.gridY + offset.gridY)
	 */
	public boolean equals(TileLocation location, TileLocation offset){
		return gridX==location.gridX+offset.gridX&&gridY==location.gridY+offset.gridY;
	}
	
	public int hashCode(){
		return gridX+23*gridY;
		
	}
	
	public String toString(){
		return "< " + gridX + ", " + gridY + " >";
	}

}
