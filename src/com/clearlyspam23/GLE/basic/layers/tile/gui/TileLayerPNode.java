package com.clearlyspam23.GLE.basic.layers.tile.gui;

import java.util.HashMap;
import java.util.Map;

import org.piccolo2d.PNode;

import com.clearlyspam23.GLE.basic.layers.tile.TileData;
import com.clearlyspam23.GLE.basic.layers.tile.TileLocation;
import com.clearlyspam23.GLE.basic.layers.tile.TilesetHandle;
import com.clearlyspam23.GLE.basic.layers.tile.TilesetManager;

public class TileLayerPNode extends PNode implements TilePNode.TileChangeListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private TilePNode[][] nodeGrid;
	
	private static final double epsilon = 1e-4;
	
	private double gridWidth;
	private double gridHeight;
	
	private TileLocation gridOffset = new TileLocation();
	
	public TileLayerPNode(double gridWidth, double gridHeight)
	{
		this.gridWidth = gridWidth;
		this.gridHeight = gridHeight;
		nodeGrid = new TilePNode[0][0];
	}
	
	public double getGridWidth(){
		return gridWidth;
	}
	
	public double getGridHeight(){
		return gridHeight;
	}
	
	public void setGridDimensions(double width, double height){
		gridWidth = width;
		gridHeight = height;
		refreshNodes();
	}
	
	@Override
	public boolean setBounds(double x, double y, double width, double height){
		this.removeAllChildren();
		if(!super.setBounds(x, y, width, height))
			return false;
		refreshNodes();
		return true;
	}
	
	private void refreshNodes(){
		double rowsd = getWidth()/gridWidth;
		double columnsd = getHeight()/gridHeight;
		int rows = (int) rowsd;
		int columns = (int) columnsd;
		TilePNode[][] oldNodeGrid = nodeGrid;
		nodeGrid = new TilePNode[rows + (rowsd-epsilon < rows ? 0 : 1)][columns + (columnsd-epsilon < columns ? 0 : 1)];
		for(int i = 0; i < rows; i++){
			for(int j = 0; j < columns; j++){
				if(oldNodeGrid!=null&&i<oldNodeGrid.length&&j<oldNodeGrid[i].length)
					addPNode(i, j, gridWidth, gridHeight, oldNodeGrid[i][j]);
				else
					addNewPNode(i, j, gridWidth, gridHeight);
			}
		}
		if(rows<nodeGrid.length){
			double remainder = (rowsd-rows)*gridWidth;
			for(int j = 0; j < nodeGrid[rows].length; j++){
				if(oldNodeGrid!=null&&rows<oldNodeGrid.length&&j<oldNodeGrid[rows].length)
					addPNode(rows, j, remainder, gridHeight, gridWidth, gridHeight, oldNodeGrid[rows][j]);
				else
					addNewPNode(rows, j, remainder, gridHeight, gridWidth, gridHeight);
			}
		}
		if(nodeGrid.length>0&&columns<nodeGrid[0].length){
			double remainder = (columnsd-columns)*gridHeight;
			for(int i = 0; i < nodeGrid.length; i++){
				if(oldNodeGrid!=null&&i<oldNodeGrid.length&&columns<oldNodeGrid[i].length)
					addPNode(i, columns, gridWidth, remainder, gridWidth, gridHeight, oldNodeGrid[i][columns]);
				else
					addNewPNode(i, columns, gridWidth, remainder, gridWidth, gridHeight);
			}
		}
	}
	
	public void resize(double width, double height){
		setBounds(getX(), getY(), width, height);
	}
	
	private void addNewPNode(int i, int j, double width, double height){
		addNewPNode(i, j, width, height, width, height);
	}
	
	private void addNewPNode(int i, int j, double width, double height, double gridWidth, double gridHeight){
		addPNode(i, j, width, height, gridWidth, gridHeight, new TilePNode());
	}
	
	private void addPNode(int i, int j, double width, double height, TilePNode pnode){
		addPNode(i, j, width, height, width, height, pnode);
	}
	
	private void addPNode(int i, int j, double width, double height, double gridWidth, double gridHeight, TilePNode pnode){
		pnode.addChangeListener(this);
		nodeGrid[i][j] = pnode;
		pnode.setGridLocation(i, j);
		addChild(nodeGrid[i][j]);
		nodeGrid[i][j].setBounds(gridWidth*i, gridHeight*j, width, height);
		onNodeAdd(pnode);
	}
	
	public TilePNode[][] getNodeGrid(){
		return nodeGrid;
	}
	
	public TilePNode getNodeAt(int x, int y){
		return nodeGrid[x-gridOffset.gridX][y-gridOffset.gridY];
	}
	
	public TilePNode getNodeAt(TileLocation location){
		return getNodeAt(location.gridX, location.gridY);
	}
	
	public TileData[][] getTiles(){
		TileData[][] ans = new TileData[nodeGrid.length][];
		for(int i = 0; i < nodeGrid.length; i++){
			ans[i] = new TileData[nodeGrid[i].length];
			for(int j = 0; j < nodeGrid[i].length; j++){
				ans[i][j] = nodeGrid[i][j].getTileData();
			}
		}
		return ans;
	}
	
	/**
	 * calculates a mapping of TilePNodes to their appropriate Tilesets, returning a full grid (width x height of this layer) per each Tileset
	 * @return a mapping of Tilesets to all the tiles in this grid, in their appropriate spot
	 */
	public Map<TilesetHandle, TilePNode[][]> getMappedNodeGrid(){
		Map<TilesetHandle, TilePNode[][]> ans = new HashMap<TilesetHandle, TilePNode[][]>();
		for(int i = 0; i < nodeGrid.length; i++)
		{
			for(int j = 0; j < nodeGrid[i].length; j++)
			{
				TilePNode node = nodeGrid[i][j];
				TilesetHandle t = node.getTileset();
				if(t==null)
					continue;
				if(!ans.containsKey(t))
					ans.put(t, new TilePNode[nodeGrid.length][nodeGrid[i].length]);
				ans.get(t)[i][j] = node;
			}
		}
		return ans;
	}
	
	/**
	 * try and refresh all of the nodes in this TileLayer. if there happens to be a problem, clear out that node and report it here
	 * this should be called whenever new tilesets are added, or old tilesets are modified.
	 * if an existing tileset in use has been changed such that existing tiles are no longer valid, this method will zero out those tiles and return false
	 * @param manager the tileset manager to use for updates
	 * @return whether or not this refresh completed without incident
	 */
	public boolean refreshNodes(TilesetManager manager){
		boolean output = true;
		for(int i = 0; i < nodeGrid.length; i++){
			for(int j = 0; j < nodeGrid[i].length; j++){
				TilePNode node = nodeGrid[i][j];
				try{
					if(node.getTileset()==null)
						continue;
					TilesetHandle h = manager.getTilesetByName(node.getTileset().getName());
					if(h==null){
						throw new NullPointerException();
					}
					node.setTileset(h, node.getTilesetX(), node.getTilesetY());
				}
				catch(Exception e){
					e.printStackTrace();
					node.resetTileset();
					output = false;
				}
			}
		}
		return output;
	}
	
	public void setGridOffset(int x, int y){
		gridOffset.set(x, y);
	}
	
	public void setGridOffset(TileLocation loc){
		setGridOffset(loc.gridX, loc.gridY);
	}
	
	public void setGridOffsetX(int x){
		setGridOffset(x, gridOffset.gridY);
	}
	
	public void setGridOffsetY(int y){
		setGridOffset(gridOffset.gridX, y);
	}
	
	public int getGridOffsetX(){
		return gridOffset.gridX;
	}
	
	public int getGridOffsetY(){
		return gridOffset.gridY;
	}
	
	public TileLocation getGridOffset(){
		return gridOffset.copy();
	}
	
	public void clearAllTiles(){
		for(TilePNode[] p : nodeGrid){
			for(TilePNode t : p){
				t.resetTilesetHard();
			}
		}
	}
	
	public void silentlyIgnoreInput(boolean flag){
		for(TilePNode[] p : nodeGrid){
			for(TilePNode t : p){
				t.silentlyIgnoreInput(flag);
			}
		}
	}
	
	public void setTilesPickable(boolean flag){
		for(TilePNode[] p : nodeGrid){
			for(TilePNode t : p){
				t.setPickable(flag);
			}
		}
	}

	@Override
	public void onChange(TilePNode changedNode, TileData previous, TileData next) {
		
	}
	
	protected void onNodeAdd(TilePNode node) {
		
	}
}
