package com.clearlyspam23.GLE.basic.layers.tile.edit.actions;

import com.clearlyspam23.GLE.basic.layers.tile.TileLocation;
import com.clearlyspam23.GLE.basic.layers.tile.gui.MovableTileSelection;
import com.clearlyspam23.GLE.edit.EditAction;

public class MoveSelectionAction implements EditAction {
	
	private TileLocation originalLocation;
	private TileLocation newLocation;
	private MovableTileSelection selection;
	
	public MoveSelectionAction(TileLocation originalLocation, TileLocation newLocation, MovableTileSelection selection){
		this.originalLocation = originalLocation;
		this.newLocation = newLocation;
		this.selection = selection;
	}

	@Override
	public void undoAction() {
		selection.setToOffset(originalLocation);
	}

	@Override
	public void doAction() {
		selection.setToOffset(newLocation);
	}

	@Override
	public String getDescription() {
		return "Moved Selection from " + originalLocation + " to " + newLocation;
	}

}
