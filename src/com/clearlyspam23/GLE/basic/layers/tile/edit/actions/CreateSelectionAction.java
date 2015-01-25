package com.clearlyspam23.GLE.basic.layers.tile.edit.actions;

import com.clearlyspam23.GLE.basic.layers.tile.gui.BasePNode;
import com.clearlyspam23.GLE.basic.layers.tile.gui.TileSelection;
import com.clearlyspam23.GLE.edit.EditAction;

public class CreateSelectionAction implements EditAction {
	
	private TileSelection selection;
	private BasePNode base;
	
	public CreateSelectionAction(TileSelection selection, BasePNode base){
		this.selection = selection;
		this.base = base;
	}

	@Override
	public void undoAction() {
		base.clearSelectionWithoutAnchor();
	}

	@Override
	public void doAction() {
		base.setSelection(selection);
	}

	@Override
	public String getDescription() {
		return "Selected " + selection.getTileCount() + " Tiles";
	}

}
