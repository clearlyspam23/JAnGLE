package com.clearlyspam23.GLE.basic.layers.tile.edit.actions;

import java.util.List;

import com.clearlyspam23.GLE.basic.layers.tile.Tile;
import com.clearlyspam23.GLE.basic.layers.tile.gui.TileSelection;
import com.clearlyspam23.GLE.edit.EditAction;

public class ClearSelectionAction implements EditAction {
	
	private TileSelection selection;
	private List<Tile> lastTiles;
	
	public ClearSelectionAction(List<Tile> lastTiles, TileSelection selection){
		this.selection = selection;
		this.lastTiles = lastTiles;
	}

	@Override
	public void undoAction() {
		selection.setToTiles(lastTiles);
	}

	@Override
	public void doAction() {
		selection.onClear();
	}

	@Override
	public String getDescription() {
		return "Cleared All Tiles in the Current Selection";
	}

}
