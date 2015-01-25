package com.clearlyspam23.GLE.basic.layers.tile.gui;

import java.awt.Color;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;

import org.piccolo2d.PCamera;
import org.piccolo2d.PNode;

import com.clearlyspam23.GLE.GUI.util.AnimatedOutlineRectNode;
import com.clearlyspam23.GLE.GUI.util.FixedWidthOutlineRectNode;
import com.clearlyspam23.GLE.GUI.util.FixedWidthStroke;
import com.clearlyspam23.GLE.GUI.util.LineNode;
import com.clearlyspam23.GLE.basic.layers.tile.Tile;
import com.clearlyspam23.GLE.basic.layers.tile.TileLocation;

public class ImmovableTileSelection implements TileSelection{
	
	private PNode overlayNode = new PNode();
	private LineNode linesNode;
	
	private List<TileLocation> selectedLocations = new ArrayList<TileLocation>();
//	private List<FixedWidthOutlineRectNode> outlineRect;
	private int width;
	private int height;
	private TileLocation offset;
	private TileLayerPNode tileLayer;
	
	public ImmovableTileSelection(List<TileLocation> locations, TileLayerPNode layer, PCamera camera){
		tileLayer = layer;
		linesNode = new LineNode(new FixedWidthStroke(2, camera));
		linesNode.setPaint(Color.YELLOW);
		linesNode.setPickable(false);
		overlayNode.addChild(linesNode);
		recalculateTiles(locations);
	}
	
	private void recalculateTiles(List<TileLocation> locations){
		selectedLocations.clear();
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
		if(selectedLocations.isEmpty()){
			if(tileLayer.getBase().getSelection()==this){
				tileLayer.getBase().clearSelection();
			}
			return;
		}
		calculateBoundingRect();
	}

	public List<Tile> onCopy() {
		List<Tile> answer = new ArrayList<Tile>();
		for(TileLocation loc : selectedLocations){
			if(tileLayer.isValidLocation(loc.gridX+offset.gridX, loc.gridY+offset.gridY)){
				answer.add(new Tile(tileLayer.getNodeAt(loc.gridX+offset.gridX, loc.gridY+offset.gridY).getTileData(),
						loc, offset));
			}
		}
		return answer;
	}
	
//	public List<Tile> onCut() {
//		List<Tile> answer = onCopy();
//		onClear();
//		return answer;
//	}
	
	@Override
	public void onClear() {
		for(TileLocation t : selectedLocations){
			if(tileLayer.isValidLocation(t.gridX+offset.gridX, t.gridY+offset.gridY)){
				tileLayer.getNodeAt(t.gridX+offset.gridX, t.gridY+offset.gridY).resetTileset();
			}
		}
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
	
	public int getTileCount(){
		return selectedLocations.size();
	}
	
	private void calculateBoundingRect(){
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
		//try to build the largest lines possible
		List<Line2D> lines = new ArrayList<Line2D>();
		for(int i = 0; i < sideGrid.length; i++){
			for(int j = 0; j < sideGrid[i].length; j++){
				if((sideGrid[i][j]&AnimatedOutlineRectNode.LEFT)==AnimatedOutlineRectNode.LEFT){
					int k = 0;
					for(; k + j < sideGrid[i].length&&(sideGrid[i][k+j]&AnimatedOutlineRectNode.LEFT)==AnimatedOutlineRectNode.LEFT; k++){
						sideGrid[i][j+k] &=~AnimatedOutlineRectNode.LEFT;
					}
					lines.add(new Line2D.Double(i, j, i, j+k));
				}
				if((sideGrid[i][j]&AnimatedOutlineRectNode.RIGHT)==AnimatedOutlineRectNode.RIGHT){
					int k = 0;
					for(; k + j < sideGrid[i].length&&(sideGrid[i][k+j]&AnimatedOutlineRectNode.RIGHT)==AnimatedOutlineRectNode.RIGHT; k++){
						sideGrid[i][j+k] &=~AnimatedOutlineRectNode.RIGHT;
					}
					lines.add(new Line2D.Double(i+1, j, i+1, j+k));
				}
				if((sideGrid[i][j]&AnimatedOutlineRectNode.TOP)==AnimatedOutlineRectNode.TOP){
					int k = 0;
					for(; k + i < sideGrid.length&&(sideGrid[i+k][j]&AnimatedOutlineRectNode.TOP)==AnimatedOutlineRectNode.TOP; k++){
						sideGrid[i+k][j] &=~AnimatedOutlineRectNode.TOP;
					}
					lines.add(new Line2D.Double(i, j, i+k, j));
				}
				if((sideGrid[i][j]&AnimatedOutlineRectNode.BOTTOM)==AnimatedOutlineRectNode.BOTTOM){
					int k = 0;
					for(; k + i < sideGrid.length&&(sideGrid[i+k][j]&AnimatedOutlineRectNode.BOTTOM)==AnimatedOutlineRectNode.BOTTOM; k++){
						sideGrid[i+k][j] &=~AnimatedOutlineRectNode.BOTTOM;
					}
					lines.add(new Line2D.Double(i, j+1, i+k, j+1));
				}
			}
		}
		linesNode.setLines(lines);
		linesNode.setBounds(tileLayer.getGridWidth()*offset.gridX, tileLayer.getGridHeight()*offset.gridY, 
				Math.min(tileLayer.getGridWidth()*width, tileLayer.getWidth()-tileLayer.getGridWidth()*offset.gridX),
				Math.min(tileLayer.getGridHeight()*height, tileLayer.getHeight()-tileLayer.getGridHeight()*offset.gridY));
		linesNode.repaint();
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
		return null;
	}

	public void removeFromSelection(List<TileLocation> toRemove) {
		for(TileLocation l : toRemove){
			selectedLocations.remove(new TileLocation(l.gridX - offset.gridX, l.gridY - offset.gridY));
		}
		for(TileLocation l : selectedLocations){
			l.set(l.gridX+offset.gridX, l.gridY+offset.gridY);
		}
		recalculateTiles(new ArrayList<TileLocation>(selectedLocations));
	}

	public void addToSelection(List<TileLocation> toAdd) {
		for(TileLocation l : selectedLocations){
			l.set(l.gridX+offset.gridX, l.gridY+offset.gridY);
		}
		selectedLocations.addAll(toAdd);
		recalculateTiles(new ArrayList<TileLocation>(selectedLocations));
	}

	@Override
	public void setToTiles(List<Tile> tiles) {
		selectedLocations.clear();
		tileLayer.silentlyIgnoreInput(true);
		if(!tiles.isEmpty())
			offset = tiles.get(0).offset.copy();
		for(Tile t : tiles){
			tileLayer.getNodeAt(t.getLocation()).silentlyIgnoreInput(false);
			tileLayer.setTile(t);
			selectedLocations.add(t.relativeLocation.copy());
		}
		calculateBoundingRect();
	}

	@Override
	public void onLift() {
		// TODO Auto-generated method stub
		
	}

}
