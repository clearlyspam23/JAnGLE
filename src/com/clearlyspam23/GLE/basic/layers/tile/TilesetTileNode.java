package com.clearlyspam23.GLE.basic.layers.tile;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TilesetTileNode extends TilesetTreeNode{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<TilesetHandle> tilesetArray;
	
	public TilesetTileNode(TilesetHandle tileset) {
		setTileset(tileset);
	}

	public TilesetTileNode(String name, TilesetHandle tileset) {
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
	
	public TilesetHandle getTileset(){
		if(tilesetArray.isEmpty())
			return null;
		return tilesetArray.get(0);
	}
	
	@Override
	public Type getType() {
		return Type.TILE;
	}
	
	public String getName(){
		return getTileset().getName();
	}
	
	public void setName(String name){
		getTileset().setName(name);
	}

}
