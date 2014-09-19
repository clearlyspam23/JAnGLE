package com.clearlyspam23.GLE.basic.layers.tile;

import java.util.ArrayList;
import java.util.List;

import com.clearlyspam23.GLE.basic.layers.tile.resources.TilesetHandle;

public class TilesetTileNode extends TilesetTreeNode {
	
	private List<TilesetHandle> tilesets = new ArrayList<TilesetHandle>();

	public TilesetTileNode(String name) {
		super(name);
	}
	
	public void addTileset(TilesetHandle tileset){
		tilesets.add(tileset);
	}
	
	public void removeTileset(TilesetHandle tileset){
		tilesets.remove(tileset);
	}

	@Override
	public List<TilesetHandle> getTilesets() {
		return tilesets;
	}
	
	@Override
	public Type getType() {
		return Type.TILE;
	}

}
