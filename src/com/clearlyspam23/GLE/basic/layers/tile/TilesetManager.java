package com.clearlyspam23.GLE.basic.layers.tile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TilesetManager {
	
	private List<Tileset> allTilesets = new ArrayList<Tileset>();
	private Map<String, Tileset> nameTable = new HashMap<String, Tileset>();
	
	public boolean addTileset(Tileset t){
		if(nameTable.containsKey(t.getName()))
			return false;
		allTilesets.add(t);
		nameTable.put(t.getName(), t);
		return true;
	}
	
	public List<Tileset> getAllTilesets(){
		return allTilesets;
	}
	
	public Tileset getTilesetByName(String name){
		return nameTable.get(name);
	}

	public Map<String, Tileset> getNameTable() {
		return nameTable;
	}

	public void setNameTable(Map<String, Tileset> nameTable) {
		this.nameTable = nameTable;
	}

	public void setAllTilesets(List<Tileset> allTilesets) {
		this.allTilesets = allTilesets;
	}

}
