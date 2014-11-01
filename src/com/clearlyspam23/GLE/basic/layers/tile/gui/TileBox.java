package com.clearlyspam23.GLE.basic.layers.tile.gui;

import org.piccolo2d.PNode;

public interface TileBox {
	
	public void lostSelection();
	
	public boolean canCopy();
	
	public boolean canCut();
	
	public void onCopy();
	
	public void onCut();
	
	public PNode getPNode();

}
