package com.clearlyspam23.GLE.basic.layers.tile.edit.actions;

import java.util.List;

import com.clearlyspam23.GLE.basic.layers.tile.Tile;
import com.clearlyspam23.GLE.basic.layers.tile.gui.BasePNode;
import com.clearlyspam23.GLE.basic.layers.tile.gui.TileSelection;
import com.clearlyspam23.GLE.edit.EditAction;

public class CutSelectionAction implements EditAction {
	
	private AnchorSelectionAction anchorAction;
	private ClearSelectionAction clearAction;
	
	private int affectedTiles;
	
	public CutSelectionAction(List<Tile> lastTiles, TileSelection selection, BasePNode base){
		clearAction = new ClearSelectionAction(lastTiles, selection);
		anchorAction = new AnchorSelectionAction(selection, base);
		affectedTiles = lastTiles.size();
	}

	@Override
	public void undoAction() {
		anchorAction.undoAction();
		clearAction.undoAction();
	}

	@Override
	public void doAction() {
		clearAction.doAction();
		anchorAction.doAction();
	}

	@Override
	public String getDescription() {
		return "Cut " + affectedTiles + " Tiles";
	}

}
