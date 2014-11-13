package com.clearlyspam23.GLE.basic.layers.tile.gui;

import org.piccolo2d.PNode;

import com.clearlyspam23.GLE.basic.layers.tile.Tile;

public interface TileBox {
	
	public void lostSelection();
	
	public boolean canCopy();
	
	public boolean canCut();
	
	public Tile[][] onCopy();
	
	public Tile[][] onCut();
	
	public PNode getPNode();
	
	public void onAdd(PNode node);

}
