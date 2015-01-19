package com.clearlyspam23.GLE.basic.layers.tile.gui;

import java.util.List;

import org.piccolo2d.PNode;

import com.clearlyspam23.GLE.basic.layers.tile.Tile;
import com.clearlyspam23.GLE.basic.layers.tile.TileLocation;

public interface TileSelection {
	
	public List<Tile> onCopy();
	
	public List<Tile> onCut();
	
	public void onClear();
	
	public int getTileWidth();
	
	public int getTileHeight();
	
	public void onRemove();
	
	public void onAnchor();
	
	public PNode getSelectionNode();
	
	public PNode getOverlayNode();
	
	public boolean isNodeInSelection(TilePNode node);
	
	public void removeFromSelection(List<TileLocation> toRemove);
	
	public void addToSelection(List<TileLocation> toAdd);

}
