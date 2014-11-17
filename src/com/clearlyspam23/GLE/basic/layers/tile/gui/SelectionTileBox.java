package com.clearlyspam23.GLE.basic.layers.tile.gui;

import org.piccolo2d.PCamera;
import org.piccolo2d.PNode;

import com.clearlyspam23.GLE.basic.layers.tile.Tile;

public class SelectionTileBox implements TileBox {
	
	public class SelectionPNode extends AnimatedOutlineRect{
		
		private int startTileX;
		private int startTileY;
		
		private TileLayerPNode lowerLayer;
		private TileLayerPNode selectionLayer;

		public SelectionPNode(PCamera camera, TileLayerPNode node, int startTileX, int startTileY) {
			super(camera);
			this.startTileX = startTileX;
			this.startTileY = startTileY;
			lowerLayer = node;
			selectionLayer = new TileLayerPNode(node.getGridWidth(), node.getGridHeight());
			selectionLayer.resize(node.getGridWidth(), node.getGridHeight());
//			selectionLayer.getNodeGrid()[0][0].setTileset(set, x, y);
		}
		
	}
	
	private SelectionPNode selectionNode;
	
	public SelectionTileBox(PCamera camera, TileLayerPNode node, int startTileX, int startTileY){
		selectionNode = new SelectionPNode(camera, node, startTileX, startTileY);
		
	}

	@Override
	public void lostSelection() {
		
	}

	@Override
	public boolean canCopy() {
		return true;
	}

	@Override
	public boolean canCut() {
		return true;
	}

	@Override
	public Tile[][] onCopy() {
		return null;

	}

	@Override
	public Tile[][] onCut() {
		return null;
	}

	@Override
	public PNode getPNode() {
		return null;
	}

	@Override
	public void onAdd(PNode node) {
		
	}
	
	public void expandToTile(int x, int y){
		
	}

}
