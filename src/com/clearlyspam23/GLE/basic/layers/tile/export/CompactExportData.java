package com.clearlyspam23.GLE.basic.layers.tile.export;


public class CompactExportData{
	
	public int[][] tiles;
	public String tileset;
	public int tilesetId;
	
	public boolean equals(Object other){
		if(!(other instanceof CompactExportData))
			return false;
		return equals((CompactExportData)other);
	}
	
	public boolean equals(CompactExportData data)
	{
		if(!data.tileset.equals(tileset))
			return false;
		if(data.tilesetId!=tilesetId)
			return false;
		if(data.tiles.length!=tiles.length)
			return false;
		for(int i = 0; i < tiles.length; i++){
			if(data.tiles[i].length!=tiles[i].length)
				return false;
			for(int j = 0; j < tiles[i].length; i++)
				if(data.tiles[i][j]!=tiles[i][j])
					return false;
		}
		return true;
	}
}
