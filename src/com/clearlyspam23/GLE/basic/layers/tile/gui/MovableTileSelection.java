package com.clearlyspam23.GLE.basic.layers.tile.gui;

import java.util.ArrayList;
import java.util.List;

import org.piccolo2d.PCamera;
import org.piccolo2d.PNode;

import com.clearlyspam23.GLE.GUI.util.AnimatedOutlineRectNode;
import com.clearlyspam23.GLE.basic.layers.tile.Tile;
import com.clearlyspam23.GLE.basic.layers.tile.TileData;
import com.clearlyspam23.GLE.basic.layers.tile.TileLocation;

public class MovableTileSelection implements TileSelection{
	
	/**
	 * this class needs to hold a reference to both a box above the current tile layer, and the nodes below that box
	 * in order to allow saving when regions are selected, this box will leave nodes below it unchanged, until they are moved by this box
	 * @author john
	 *
	 */
	public class SelectionPNode extends TileLayerPNode{
		
		private TileLayerPNode lowerLayer;
		
		private PCamera camera;
		private List<AnimatedOutlineRectNode> boundingRect = new ArrayList<AnimatedOutlineRectNode>();

		public SelectionPNode(PCamera camera, TileLayerPNode lowerLayer) {
			super(lowerLayer.getGridWidth(), lowerLayer.getGridHeight(), lowerLayer.getLayer(), lowerLayer.getBase());
			this.camera = camera;
			this.lowerLayer = lowerLayer;
		}
		
		public void setToTiles(List<TileLocation> locations){
			if(locations.isEmpty())
				return;
			clearAllTiles();
			silentlyIgnoreInput(true);
			setTilesPickable(false);
			int lowestX = Integer.MAX_VALUE;
			int lowestY = Integer.MAX_VALUE;
			int highestX = 0;
			int highestY = 0;
			for(TileLocation l : locations){
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
			for(TileLocation l : locations){
				TilePNode p = lowerLayer.getNodeAt(l);
				Tile t = p.getTile();
				TilePNode node = getNodeAt(t.getLocation());
				node.silentlyIgnoreInput(false);
				node.setPickable(true);
				node.setTileset(t);
			}
		}
		
		public void setToBox(TileLocation bottomLeft, TileLocation topRight){
			setToBox(bottomLeft.gridX, bottomLeft.gridY, topRight.gridX-bottomLeft.gridX + 1, topRight.gridY-bottomLeft.gridY + 1);
		}
		
		public void setToBox(int x, int y, int width, int height){
			if(width<=0||height<=0)
				return;
			clearAllTiles();
			setTilesPickable(true);
			silentlyIgnoreInput(false);
			double startX = x*getGridWidth();
			double startY = y*getGridHeight();
			double w = Math.min(lowerLayer.getWidth()-x, (width)*getGridWidth());
			double h = Math.min(lowerLayer.getHeight()-y, (height)*getGridHeight());
			this.setBounds(startX, startY, w, h);
			this.setGridOffset(x, y);
			for(int i = 0; i < width; i++){
				for(int j = 0; j < height; j++){
					TilePNode p = lowerLayer.getNodeAt(i+x, j+y);
					Tile t = p.getTile();
					TilePNode node = getNodeAt(t.getLocation());
					node.setTileset(t);
				}
			}
		}
		
		protected void onNodeAdd(TilePNode node){
			
		}
		
		@Override
		public void onChange(TilePNode changedNode, TileData previous, TileData next) {
			lowerLayer.getNodeAt(changedNode.getGridX(), changedNode.getGridY()).setTileset(next);
		}
		
		public List<AnimatedOutlineRectNode> calculateBoundingRect(){
			boundingRect.clear();
			TilePNode[][] grid = getNodeGrid();
			int[][] sideGrid = new int[grid.length][];
			for(int i = 0; i < grid.length; i++){
				sideGrid[i] = new int[grid[i].length];
				for(int j = 0; j < grid[i].length; j++){
					sideGrid[i][j] = AnimatedOutlineRectNode.NONE;
					if(grid[i][j]==null)
						continue;
					//perform edge detection, if any edges are detected, create an animated node
					if(i-1<0||grid[i-1][j]==null)
						sideGrid[i][j]|=AnimatedOutlineRectNode.LEFT;
					if(i+1>=grid.length||grid[i+1][j]==null)
						sideGrid[i][j]|=AnimatedOutlineRectNode.RIGHT;
					if(j-1<0||grid[i][j-1]==null)
						sideGrid[i][j]|=AnimatedOutlineRectNode.TOP;
					if(j+1>=grid[i].length||grid[i][j+1]==null)
						sideGrid[i][j]|=AnimatedOutlineRectNode.BOTTOM;
				}
			}
			//try to build the largest Rectangles possible
			//this is an optimization, and can be removed if buggy
			for(int i = 0; i < sideGrid.length; i++){
				for(int j = 0; j < sideGrid[i].length; j++){
					if(sideGrid[i][j]!=AnimatedOutlineRectNode.NONE){
						//grab as much of the matching sides as we can
						//this should go down and grab nodes with the same left and right as us
						//after that, grab as much of the top and bottom as we can
						//go around in a circle to try and ensure that we're grabbing what we actually can
						int leftHeight = 1;
						int rightHeight = 1;
						int topWidth = 1;
						int bottomWidth = 1;
						for(; leftHeight + j < sideGrid[i].length && 
								(sideGrid[i][leftHeight+j]&AnimatedOutlineRectNode.LEFT_AND_RIGHT)==(sideGrid[i][j]&AnimatedOutlineRectNode.LEFT_AND_RIGHT)
								&&sideGrid[i][leftHeight+j]!=AnimatedOutlineRectNode.NONE; leftHeight++)
						{}
						for(; topWidth + i < sideGrid.length && 
								(sideGrid[i + topWidth][j]&AnimatedOutlineRectNode.TOP_AND_BOTTOM)==(sideGrid[i][j]&AnimatedOutlineRectNode.TOP_AND_BOTTOM)
										&&sideGrid[i+topWidth][j]!=AnimatedOutlineRectNode.NONE; topWidth++)
						{}
						for(; rightHeight + j < sideGrid[i].length && 
								(sideGrid[i+topWidth-1][rightHeight+j]&AnimatedOutlineRectNode.LEFT_AND_RIGHT)==(sideGrid[i+topWidth-1][j]&AnimatedOutlineRectNode.LEFT_AND_RIGHT)
										&&sideGrid[i+topWidth-1][rightHeight+j]!=AnimatedOutlineRectNode.NONE; rightHeight++)
						{}
						for(; bottomWidth + i < sideGrid.length && 
								(sideGrid[i + bottomWidth][j+leftHeight-1]&AnimatedOutlineRectNode.TOP_AND_BOTTOM)==(sideGrid[i][j+leftHeight-1]&AnimatedOutlineRectNode.TOP_AND_BOTTOM)
										&&sideGrid[i + bottomWidth][j+leftHeight-1]!=AnimatedOutlineRectNode.NONE; bottomWidth++)
						{}
						int width = Math.min(topWidth, bottomWidth);
						int height = Math.min(leftHeight, rightHeight);
						int value = sideGrid[i][j]&(AnimatedOutlineRectNode.LEFT|AnimatedOutlineRectNode.TOP);
						value|=(sideGrid[i+width-1][j]&AnimatedOutlineRectNode.RIGHT);
						value|=(sideGrid[i][j+height-1]&AnimatedOutlineRectNode.BOTTOM);
						AnimatedOutlineRectNode rect = new AnimatedOutlineRectNode(camera, value);
						rect.setBounds(grid[i][j].getBounds().getBounds2D());
						boundingRect.add(rect);
						for(int k = 0; k < width; k++){
							sideGrid[i+k][j] = 0;
							sideGrid[i+k][j+height-1] = 0;
						}
						for(int k = 0; k < height; k++){
							sideGrid[i][j+k] = 0;
							sideGrid[i+width-1][k] = 0;
						}
					}
				}
			}
			return boundingRect;
		}
		
	}
	
	private SelectionPNode selectionNode;
	private PNode overlayNode = new PNode();
	
	public MovableTileSelection(PCamera camera, TileLayerPNode node){
		selectionNode = new SelectionPNode(camera, node);
		node.addChild(selectionNode);
	}

	@Override
	public List<Tile> onCopy() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Tile> onCut() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getTileWidth() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getTileHeight() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void onRemove() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isNodeInSelection(TilePNode node) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onAnchor() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PNode getSelectionNode() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PNode getOverlayNode() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onClear() {
		// TODO Auto-generated method stub
		
	}

}
