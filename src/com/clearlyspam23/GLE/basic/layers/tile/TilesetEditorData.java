package com.clearlyspam23.GLE.basic.layers.tile;

import java.awt.Image;

public class TilesetEditorData {
	
	private Tileset currentTileset;
	
	private int selectedX = -1;
	private int selectedY = -1;
	
	
	
	
	public Tileset getCurrentTileset() {
		return currentTileset;
	}
	public void setCurrentTileset(Tileset currentTileset) {
		this.currentTileset = currentTileset;
	}
	public int getSelectedX() {
		return selectedX;
	}
	public void setSelectedX(int selectedX) {
		this.selectedX = selectedX;
	}
	public int getSelectedY() {
		return selectedY;
	}
	public void setSelectedY(int selectedY) {
		this.selectedY = selectedY;
	}
	
	public Image getSelectedTile(){
		if(currentTileset==null||!currentTileset.isValidLocation(selectedX, selectedY))
			return null;
		return currentTileset.getTileAt(selectedX, selectedY);
	}

}