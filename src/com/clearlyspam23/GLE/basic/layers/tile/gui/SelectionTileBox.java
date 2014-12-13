package com.clearlyspam23.GLE.basic.layers.tile.gui;

import org.piccolo2d.PCamera;
import org.piccolo2d.PNode;

import com.clearlyspam23.GLE.basic.layers.tile.Tile;

public class SelectionTileBox implements TileBox {
	
	/**
	 * this class needs to hold a reference to both a box above the current tile layer, and the nodes below that box
	 * in order to allow saving when regions are selected, this box will leave nodes below it unchanged, until they are moved by this box
	 * @author john
	 *
	 */
	public class SelectionPNode extends TileLayerPNode{
		
		private TileLayerPNode lowerLayer;
		
		private PCamera camera;

		public SelectionPNode(PCamera camera, TileLayerPNode lowerLayer) {
			super(lowerLayer.getGridWidth(), lowerLayer.getGridHeight());
			this.camera = camera;
			this.lowerLayer = lowerLayer;
		}
		
	}
	
	private SelectionPNode selectionNode;
	
	public SelectionTileBox(PCamera camera, TileLayerPNode node){
		selectionNode = new SelectionPNode(camera, node);
		
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
