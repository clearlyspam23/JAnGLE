package com.clearlyspam23.GLE.basic.layers.tile.edit.actions;

import java.util.List;

import com.clearlyspam23.GLE.basic.layers.tile.TileLocation;
import com.clearlyspam23.GLE.basic.layers.tile.gui.ImmovableTileSelection;
import com.clearlyspam23.GLE.edit.EditAction;

public class AddToSelectionAction implements EditAction {
	
	protected List<TileLocation> tilesAdded;
	private ImmovableTileSelection selection;
	
	public AddToSelectionAction(ImmovableTileSelection selection, List<TileLocation> locations){
		tilesAdded = locations;
		this.selection = selection;
	}

	@Override
	public void undoAction() {
		selection.removeFromSelection(tilesAdded);
	}

	@Override
	public void doAction() {
		selection.addToSelection(tilesAdded);
	}

	@Override
	public String getDescription() {
		return "Added " + tilesAdded.size() + " Tiles to the Current Selection";
	}

}
