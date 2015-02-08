package com.clearlyspam23.GLE.basic.layers.tile.edit.actions;

import com.clearlyspam23.GLE.basic.layers.tile.gui.BasePNode;
import com.clearlyspam23.GLE.basic.layers.tile.gui.TileSelection;
import com.clearlyspam23.GLE.edit.EditAction;

public class AnchorSelectionAction implements EditAction {
	
	private TileSelection selection;
	private BasePNode base;
	
	public AnchorSelectionAction(TileSelection selection, BasePNode base){
		this.selection = selection;
		this.base = base;
	}

	@Override
	public void undoAction() {
		base.setSelection(selection);
		selection.onLift();
	}

	@Override
	public void doAction() {
		base.anchorSelection();
	}

	@Override
	public String getDescription() {
		return "Anchored the Current Selection";
	}

}
