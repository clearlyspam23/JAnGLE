package com.clearlyspam23.GLE.basic.layers.tile.commands.EditActions;

import java.util.List;

import com.clearlyspam23.GLE.EditAction;
import com.clearlyspam23.GLE.basic.layers.tile.Tile;
import com.clearlyspam23.GLE.basic.layers.tile.gui.TilePNode;
import com.clearlyspam23.GLE.util.Pair;

public class EraseTileAction implements EditAction {
	
	public List<Pair<TilePNode, Tile>> nodes;

	public EraseTileAction(List<Pair<TilePNode, Tile>> tiles) {
		this.nodes = tiles;
	}

	@Override
	public void undoAction() {
		for(Pair<TilePNode, Tile> p : nodes){
			p.first.setTileset(p.second.tileset, p.second.tileX, p.second.tileY);
		}
	}

	@Override
	public void doAction() {
		for(Pair<TilePNode, Tile> p : nodes){
			p.first.resetTileset();
		}
	}

	@Override
	public String getDescription() {
		return "Erased " + nodes.size() + " Tiles";
	}

}
