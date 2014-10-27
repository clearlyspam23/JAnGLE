package com.clearlyspam23.GLE.basic.layers.tile.gui;

public interface TileBox {
	
	public void lostSelection();
	
	public boolean canCopy();
	
	public boolean canCut();
	
	public void onCopy();
	
	public void onCut();

}
