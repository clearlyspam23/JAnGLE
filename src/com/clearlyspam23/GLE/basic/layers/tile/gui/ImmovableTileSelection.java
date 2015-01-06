package com.clearlyspam23.GLE.basic.layers.tile.gui;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import org.piccolo2d.PCamera;
import org.piccolo2d.PNode;

import com.clearlyspam23.GLE.GUI.util.AnimatedOutlineRectNode;
import com.clearlyspam23.GLE.GUI.util.FixedWidthOutlineRectNode;
import com.clearlyspam23.GLE.basic.layers.tile.Tile;
import com.clearlyspam23.GLE.basic.layers.tile.TileLocation;

public class ImmovableTileSelection implements TileSelection{
	
	private PNode overlayNode = new PNode();
	
	private List<TileLocation> selectedLocations = new ArrayList<TileLocation>();
	private List<FixedWidthOutlineRectNode> outlineRect;
	private int width;
	private int height;
	private TileLocation offset;
	private TileLayerPNode tileLayer;
	private PCamera camera;
	
	public ImmovableTileSelection(List<TileLocation> locations, TileLayerPNode layer, PCamera camera){
		this.camera = camera;
		tileLayer = layer;
		tileLayer.silentlyIgnoreInput(true);
		int minX = Integer.MAX_VALUE;
		int maxX = 0;
		int minY = Integer.MAX_VALUE;
		int maxY = 0;
		for(TileLocation loc : locations){
			tileLayer.getNodeAt(loc).silentlyIgnoreInput(false);
			minX = Math.min(minX, loc.gridX);
			maxX = Math.max(maxX, loc.gridX);
			minY = Math.min(minY, loc.gridY);
			maxY = Math.max(maxY, loc.gridY);
		}
		offset = new TileLocation(minX, minY);
		for(TileLocation loc : locations){
			selectedLocations.add(new TileLocation(loc.gridX - offset.gridX, loc.gridY - offset.gridY));
		}
		width = maxX-minX+1;
		height = maxY-minY+1;
		outlineRect = calculateBoundingRect();
		for(FixedWidthOutlineRectNode n : outlineRect)
			overlayNode.addChild(n);
	}

	public List<Tile> onCopy() {
		List<Tile> answer = new ArrayList<Tile>();
		for(TileLocation loc : selectedLocations){
			if(tileLayer.isValidLocation(loc.gridX+offset.gridX, loc.gridY+offset.gridY)){
				answer.add(new Tile(tileLayer.getNodeAt(loc.gridX+offset.gridX, loc.gridY+offset.gridY).getTileData(),
						loc.gridX, loc.gridY));
			}
		}
		return answer;
	}
	
	public List<Tile> onCut() {
		List<Tile> answer = onCopy();
		onClear();
		return answer;
	}
	
	@Override
	public void onClear() {
		for(TileLocation t : selectedLocations){
			if(tileLayer.isValidLocation(t.gridX+offset.gridX, t.gridY+offset.gridY)){
				tileLayer.getNodeAt(t.gridX+offset.gridX, t.gridY+offset.gridY).resetTileset();
			}
		}
		selectedLocations.clear();
	}
	
	public int getTileWidth(){
		return width;
	}
	
	public int getTileHeight(){
		return height;
	}
	
	public void onRemove(){
		tileLayer.silentlyIgnoreInput(false);
	}
	
	private List<FixedWidthOutlineRectNode> calculateBoundingRect(){
		List<FixedWidthOutlineRectNode> boundingRect = new ArrayList<FixedWidthOutlineRectNode>();
		boundingRect.clear();
		int[][] sideGrid = new int[width][height];
		TileLocation[][] grid = new TileLocation[width][height];
		for(TileLocation l : selectedLocations){
			grid[l.gridX][l.gridY] = l;
		}
		for(int i = 0; i < grid.length; i++){
			sideGrid[i] = new int[grid[i].length];
			for(int j = 0; j < grid[i].length; j++){
				sideGrid[i][j] = AnimatedOutlineRectNode.NONE;
				if(grid[i][j]==null)
					continue;
				//perform edge detection, if any edges are detected, create a node
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
					FixedWidthOutlineRectNode rect = new FixedWidthOutlineRectNode(1, camera, Color.YELLOW, value);
					rect.setBounds(tileLayer.getGridWidth()*offset.gridX, tileLayer.getGridHeight()*offset.gridY, 
							Math.min(tileLayer.getGridWidth()*width, tileLayer.getWidth()-tileLayer.getGridWidth()*offset.gridX),
							Math.min(tileLayer.getGridHeight()*height, tileLayer.getHeight()-tileLayer.getGridHeight()*offset.gridY));
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

	public PNode getOverlayNode() {
		return overlayNode;
	}

	@Override
	public boolean isNodeInSelection(TilePNode node) {
		TileLocation loc = node.getTile().getLocation();
		for(TileLocation l : selectedLocations){
			if(loc.equals(l, offset))
				return true;
		}
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

}
