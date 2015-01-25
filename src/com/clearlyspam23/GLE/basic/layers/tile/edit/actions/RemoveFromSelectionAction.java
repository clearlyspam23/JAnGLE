package com.clearlyspam23.GLE.basic.layers.tile.edit.actions;

import java.util.List;

import com.clearlyspam23.GLE.basic.layers.tile.TileLocation;
import com.clearlyspam23.GLE.basic.layers.tile.gui.ImmovableTileSelection;

public class RemoveFromSelectionAction extends AddToSelectionAction {

	public RemoveFromSelectionAction(ImmovableTileSelection selection,
			List<TileLocation> locations) {
		super(selection, locations);
	}

	@Override
	public void undoAction() {
		super.doAction();
	}

	@Override
	public void doAction() {
		super.undoAction();
	}

	@Override
	public String getDescription() {
		return "Removed " + tilesAdded.size() + " Tiles from the Current Selection";
	}

}
