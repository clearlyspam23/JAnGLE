package com.clearlyspam23.GLE.basic.layers.tile.edit.commands;

import java.util.List;

import com.clearlyspam23.GLE.basic.layers.tile.TileData;
import com.clearlyspam23.GLE.basic.layers.tile.gui.TilePNode;
import com.clearlyspam23.GLE.edit.EditAction;
import com.clearlyspam23.GLE.util.Pair;

public class EraseTileAction implements EditAction {
	
	public List<Pair<TilePNode, TileData>> nodes;

	public EraseTileAction(List<Pair<TilePNode, TileData>> tiles) {
		this.nodes = tiles;
	}

	@Override
	public void undoAction() {
		for(Pair<TilePNode, TileData> p : nodes){
			p.first.setTileset(p.second.tileset, p.second.tileX, p.second.tileY);
		}
	}

	@Override
	public void doAction() {
		for(Pair<TilePNode, TileData> p : nodes){
			p.first.resetTileset();
		}
	}

	@Override
	public String getDescription() {
		return "Erased " + nodes.size() + " Tiles";
	}

}
