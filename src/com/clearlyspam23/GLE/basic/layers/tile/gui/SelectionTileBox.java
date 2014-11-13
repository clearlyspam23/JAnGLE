package com.clearlyspam23.GLE.basic.layers.tile.gui;

import org.piccolo2d.PNode;

import com.clearlyspam23.GLE.basic.layers.tile.Tile;

public class SelectionTileBox implements TileBox {

	@Override
	public void lostSelection() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean canCopy() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canCut() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Tile[][] onCopy() {
		return null;
		// TODO Auto-generated method stub

	}

	@Override
	public Tile[][] onCut() {
		return null;
		// TODO Auto-generated method stub

	}

	@Override
	public PNode getPNode() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onAdd(PNode node) {
		// TODO Auto-generated method stub

	}

}
