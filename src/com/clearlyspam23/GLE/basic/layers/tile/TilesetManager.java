package com.clearlyspam23.GLE.basic.layers.tile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.clearlyspam23.GLE.basic.layers.tile.resources.TilesetFileHandle;
import com.clearlyspam23.GLE.basic.layers.tile.resources.TilesetHandle;

public class TilesetManager {
	
	private List<TilesetHandle> allTilesets = new ArrayList<TilesetHandle>();
	private Map<String, TilesetHandle> nameTable = new HashMap<String, TilesetHandle>();
	
	public boolean addTileset(TilesetFileHandle t){
		if(nameTable.containsKey(t.getName()))
			return false;
		allTilesets.add(t);
		nameTable.put(t.getName(), t);
		return true;
	}
	
	public List<TilesetHandle> getAllTilesets(){
		return allTilesets;
	}
	
	public TilesetHandle getTilesetByName(String name){
		return nameTable.get(name);
	}

	public Map<String, TilesetHandle> getNameTable() {
		return nameTable;
	}

	public void setNameTable(Map<String, TilesetHandle> nameTable) {
		this.nameTable = nameTable;
	}

	public void setAllTilesets(List<TilesetHandle> allTilesets) {
		this.allTilesets = allTilesets;
	}

}
