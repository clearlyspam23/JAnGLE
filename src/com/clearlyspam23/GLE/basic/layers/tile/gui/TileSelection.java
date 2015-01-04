package com.clearlyspam23.GLE.basic.layers.tile.gui;

import java.util.List;

import com.clearlyspam23.GLE.basic.layers.tile.Tile;

public interface TileSelection {
	
	public List<Tile> onCopy();
	
	public List<Tile> onCut();
	
	public int getTileWidth();
	
	public int getTileHeight();
	
	public void onRemove();
	
	public boolean isNodeInSelection(TilePNode node);

}
