package com.clearlyspam23.GLE.basic.layers.tile.edit.actions;

import java.util.List;

import com.clearlyspam23.GLE.basic.layers.tile.TileData;
import com.clearlyspam23.GLE.basic.layers.tile.gui.TilePNode;
import com.clearlyspam23.GLE.edit.EditAction;
import com.clearlyspam23.GLE.util.Pair;

public class PlaceTileAction implements EditAction{
	
	public List<Pair<TilePNode, TileData>> nodes;
	public TileData endTile;

	public PlaceTileAction(List<Pair<TilePNode, TileData>> tiles, TileData changeTile) {
		this.nodes = tiles;
		this.endTile = changeTile;
	}

	@Override
	public void undoAction() {
		for(Pair<TilePNode, TileData> p : nodes){
			if(p.second.tileset==null||p.second.tileX<0||p.second.tileY<0)
				p.first.resetTileset();
			else
				p.first.setTileset(p.second.tileset, p.second.tileX, p.second.tileY);
		}
	}

	@Override
	public void doAction() {
		for(Pair<TilePNode, TileData> p : nodes){
			p.first.setTileset(endTile.tileset, endTile.tileX, endTile.tileY);
		}
	}

	@Override
	public String getDescription() {
		return "Placed " + nodes.size() + " Tiles";
	}

}
