package com.clearlyspam23.GLE.basic.layers.tile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.clearlyspam23.GLE.basic.layers.tile.resources.TilesetHandle;

public class TilesetTileNode extends TilesetTreeNode {
	
	private List<TilesetHandle> tilesetArray;

	public TilesetTileNode(String name, TilesetHandle tileset) {
		super(name);
		setTileset(tileset);
	}
	
	public void setTileset(TilesetHandle tileset){
		ArrayList<TilesetHandle> l = new ArrayList<TilesetHandle>();
		l.add(tileset);
		tilesetArray = Collections.unmodifiableList(l);
	}

	@Override
	public List<TilesetHandle> getTilesets() {
		return tilesetArray;
	}
	
	@Override
	public Type getType() {
		return Type.TILE;
	}

}
