package com.clearlyspam23.GLE.basic.layers.tile.commands.EditActions;

import java.util.List;

import com.clearlyspam23.GLE.EditAction;
import com.clearlyspam23.GLE.basic.layers.tile.Tile;
import com.clearlyspam23.GLE.basic.layers.tile.gui.TilePNode;
import com.clearlyspam23.GLE.util.Pair;

public class PlaceTileAction implements EditAction{
	
	public List<Pair<TilePNode, Tile>> nodes;
	public Tile endTile;

	public PlaceTileAction(List<Pair<TilePNode, Tile>> tiles, Tile changeTile) {
		this.nodes = tiles;
		this.endTile = changeTile;
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
			p.first.setTileset(endTile.tileset, endTile.tileX, endTile.tileY);
		}
	}

	@Override
	public String getDescription() {
		return "Placed " + nodes.size() + " Tiles";
	}

}
