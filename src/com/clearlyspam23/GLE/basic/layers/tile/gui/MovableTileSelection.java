package com.clearlyspam23.GLE.basic.layers.tile.gui;

import java.awt.BasicStroke;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;

import org.piccolo2d.PCamera;
import org.piccolo2d.PNode;
import org.piccolo2d.activities.PActivity;

import com.clearlyspam23.GLE.GUI.util.Animatable;
import com.clearlyspam23.GLE.GUI.util.AnimatedOutlineRectNode;
import com.clearlyspam23.GLE.GUI.util.FixedWidthOutlineRectNode;
import com.clearlyspam23.GLE.GUI.util.FixedWidthStroke;
import com.clearlyspam23.GLE.GUI.util.LineNode;
import com.clearlyspam23.GLE.basic.layers.tile.Tile;
import com.clearlyspam23.GLE.basic.layers.tile.TileLocation;

public class MovableTileSelection implements TileSelection, Animatable{
	
	/**
	 * this class needs to hold a reference to both a box above the current tile layer, and the nodes below that box
	 * in order to allow saving when regions are selected, this box will leave nodes below it unchanged, until they are moved by this box
	 * @author john
	 *
	 */
	public class SelectionPNode extends TileLayerPNode{
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		private TileLayerPNode lowerLayer;

		public SelectionPNode(TileLayerPNode lowerLayer) {
			super(lowerLayer.getGridWidth(), lowerLayer.getGridHeight(), lowerLayer.getLayer(), lowerLayer.getBase());
			this.lowerLayer = lowerLayer;
		}
		
		public TileLayerPNode getLowerLayer(){
			return lowerLayer;
		}
		
		public void setToTiles(List<Tile> tiles){
			if(tiles.isEmpty()){
				if(getBase().getSelection()==this){
					getBase().clearSelection();
				}
				return;
			}
			clearAllTiles();
			silentlyIgnoreInput(true);
			setTilesPickable(false);
			int lowestX = Integer.MAX_VALUE;
			int lowestY = Integer.MAX_VALUE;
			int highestX = 0;
			int highestY = 0;
			for(Tile t : tiles){
				lowestX = Math.min(lowestX, t.relativeLocation.gridX);
				lowestY = Math.min(lowestY, t.relativeLocation.gridY);
				highestX = Math.max(highestX, t.relativeLocation.gridX);
				highestY = Math.max(highestY, t.relativeLocation.gridY);
			}
			double startX = lowestX*getGridWidth();
			double startY = lowestY*getGridHeight();
			double width = Math.min(lowerLayer.getWidth()-startX, (highestX-lowestX+1)*getGridWidth());
			double height = Math.min(lowerLayer.getHeight()-startY, (highestY-lowestY+1)*getGridHeight());
			this.setBounds(getX(), getY(), width, height);
			for(Tile t : tiles){
				TilePNode node = getNodeAt(t.relativeLocation.gridX-lowestX, t.relativeLocation.gridY-lowestY);
				node.silentlyIgnoreInput(false);
				node.setPickable(true);
				node.setTileset(t);
			}
			calculateBoundingRect();
		}
		
		private void calculateBoundingRect(){
			List<FixedWidthOutlineRectNode> boundingRect = new ArrayList<FixedWidthOutlineRectNode>();
			boundingRect.clear();
			TilePNode[][] grid = getNodeGrid();
			int[][] sideGrid = new int[grid.length][(grid.length>0 ? grid[0].length : 0)];
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
			linesNode.setBounds(getX(), getY(), getWidth(),getHeight());
		}
		
		public void setToOffset(TileLocation location){
			if(location.gridX<0)
				location.gridX = 0;
			else if(location.gridX+getNodeGridWidth()>=lowerLayer.getNodeGridWidth())
				location.gridX = lowerLayer.getNodeGridWidth()-getNodeGridWidth();
			if(location.gridY<0)
				location.gridY = 0;
			else if(location.gridY+getNodeGridHeight()>=lowerLayer.getNodeGridHeight())
				location.gridY = lowerLayer.getNodeGridHeight()-getNodeGridHeight();
			setGridOffset(location);
			double x = location.gridX*getGridWidth();
			double y = location.gridY*getGridHeight();
			double width = Math.min(getNodeGridWidth()*getGridWidth(), lowerLayer.getWidth()-x);
			double height = Math.min(getNodeGridHeight()*getGridHeight(), lowerLayer.getHeight()-y);
			this.setBounds(x, y, width, height);
			linesNode.setBounds(x, y, width, height);
		}
		
	}
	
	public class AnimateLines extends PActivity{

		public AnimateLines() {
			super(-1, 100);
		}
		
		protected void activityStep(long elapsedTime) {
            super.activityStep(elapsedTime);
                            
            currentStroke = (currentStroke+1)%strokes.length;
            linesNode.setStroke(strokes[currentStroke]);
            linesNode.repaint();
		}
		
	}
	
	private AnimateLines animationActivity = new AnimateLines();
	
	private LineNode linesNode;
	private FixedWidthStroke[] strokes = new FixedWidthStroke[12];
	private int currentStroke;
	
	private SelectionPNode selectionNode;
	private PNode overlayNode = new PNode();
	
	public MovableTileSelection(PCamera camera, TileLayerPNode lowerLayer, List<Tile> tiles, TileLocation location){
		for(int i =0 ; i < strokes.length; i++){
			strokes[i] = new FixedWidthStroke(2, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER, 1.0f, new float[]{6, 6}, i, camera);
		}
		linesNode = new LineNode(strokes[currentStroke]);
		overlayNode.addChild(linesNode);
		selectionNode = new SelectionPNode(lowerLayer);
		selectionNode.setToTiles(tiles);
		selectionNode.setToOffset(location);
		Animatable.registerAnimation(selectionNode, this);
	}
	
	public void setToOffset(TileLocation offset){
		selectionNode.setToOffset(offset);
	}

	@Override
	public List<Tile> onCopy() {
		List<Tile> out = new ArrayList<Tile>();
		for(TilePNode[] ap : selectionNode.getNodeGrid()){
			for(TilePNode p : ap){
				if(!p.isSilentlyIgnoringInput())
					out.add(p.getTile());
			}
		}
		return out;
	}

	@Override
	public List<Tile> onCut() {
		List<Tile> tiles = onCopy();
		for(TilePNode[] ap : selectionNode.getNodeGrid()){
			for(TilePNode p : ap){
				p.resetTileset();
			}
		}
		return tiles;
	}

	@Override
	public int getTileWidth() {
		return selectionNode.getNodeGrid().length;
	}

	@Override
	public int getTileHeight() {
		return selectionNode.getNodeGrid()[0].length;
	}

	@Override
	public void onRemove() {
		
	}

	@Override
	public boolean isNodeInSelection(TilePNode node) {
		return selectionNode.isValidLocation(node.getTileLocation());
	}

	@Override
	public void onAnchor() {
		for(TilePNode[] ap : selectionNode.getNodeGrid()){
			for(TilePNode p : ap){
				if(!p.isSilentlyIgnoringInput())
					selectionNode.getLowerLayer().getNodeAt(p.getTileLocation()).setTileset(p.getTileData());
			}
		}
	}

	@Override
	public PNode getSelectionNode() {
		return selectionNode;
	}

	@Override
	public PNode getOverlayNode() {
		return overlayNode;
	}

	@Override
	public void onClear() {
		
	}

	@Override
	public void removeFromSelection(List<TileLocation> toRemove) {
		//intentionally left blank
		
	}

	@Override
	public void addToSelection(List<TileLocation> toAdd) {
		//intentionally left blank
	}

	@Override
	public PActivity getAnimationActivity() {
		return animationActivity;
	}

}
