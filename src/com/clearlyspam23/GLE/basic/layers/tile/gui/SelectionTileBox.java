package com.clearlyspam23.GLE.basic.layers.tile.gui;

import java.util.List;

import org.piccolo2d.PCamera;
import org.piccolo2d.PNode;

import com.clearlyspam23.GLE.basic.layers.tile.Tile;
import com.clearlyspam23.GLE.basic.layers.tile.TileData;
import com.clearlyspam23.GLE.basic.layers.tile.TileLocation;

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
		
		public void setToTiles(List<Tile> tiles){
			if(tiles.isEmpty())
				return;
			clearAllTiles();
			silentlyIgnoreInput(true);
			setTilesPickable(false);
			int lowestX = Integer.MAX_VALUE;
			int lowestY = Integer.MAX_VALUE;
			int highestX = 0;
			int highestY = 0;
			for(Tile t : tiles){
				TileLocation l = t.getLocation();
				lowestX = Math.min(lowestX, l.gridX);
				lowestY = Math.min(lowestY, l.gridY);
				highestX = Math.max(highestX, l.gridX);
				highestY = Math.max(highestY, l.gridY);
			}
			double startX = lowestX*getGridWidth();
			double startY = lowestY*getGridHeight();
			double width = Math.min(lowerLayer.getWidth()-startX, (highestX-lowestX+1)*getGridWidth());
			double height = Math.min(lowerLayer.getHeight()-startY, (highestY-lowestY+1)*getGridHeight());
			this.setBounds(startX, startY, width, height);
			this.setGridOffset(lowestX, lowestY);
			for(Tile t : tiles){
				TilePNode node = getNodeAt(t.getLocation());
				node.silentlyIgnoreInput(false);
				node.setPickable(true);
				node.setTileset(t);
			}
		}
		
		protected void onNodeAdd(TilePNode node){
			
		}
		
		@Override
		public void onChange(TilePNode changedNode, TileData previous, TileData next) {
			
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
	public TileData[][] onCopy() {
		return null;

	}

	@Override
	public TileData[][] onCut() {
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
