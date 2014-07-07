package com.clearlyspam23.GLE.basic.layers.tile.gui;

import java.util.HashMap;
import java.util.Map;

import org.piccolo2d.PNode;

import com.clearlyspam23.GLE.EditAction;
import com.clearlyspam23.GLE.LayerNode;
import com.clearlyspam23.GLE.basic.layers.tile.Tileset;

public class TileLayerPNode extends LayerNode<TileLayerPNode> implements EditAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//need some way to map PNode to Tilesets
	
	private TilePNode[][] nodeGrid;
	
	private static final double epsilon = 1e-4;
	
	public TileLayerPNode(double width, double height, double gridWidth, double gridHeight)
	{
		setBounds(0, 0, width, height);
		double rowsd = width/gridWidth;
		double columnsd = height/gridHeight;
		int rows = (int) rowsd;
		int columns = (int) columnsd;
		nodeGrid = new TilePNode[rows + (rowsd-epsilon > rows ? 0 : 1)][columns + (columnsd-epsilon > rows ? 0 : 1)];
		for(int i = 0; i < rows; i++){
			for(int j = 0; j < columns; j++){
				addNewPNode(i, j, gridWidth, gridHeight);
			}
		}
		if(rows<nodeGrid.length){
			double remainder = (rowsd-rows)*gridWidth;
			for(int j = 0; j < nodeGrid[rows].length; j++){
				addNewPNode(rows, j, remainder, gridHeight, gridWidth, gridHeight);
			}
		}
		if(nodeGrid.length>0&&columns<nodeGrid[0].length){
			double remainder = (columnsd-columns)*gridHeight;
			for(int i = 0; i < nodeGrid.length; i++){
				addNewPNode(i, columns, gridWidth, remainder, gridWidth, gridHeight);
			}
		}
	}
	
	private TileLayerPNode(TileLayerPNode base){
		
		nodeGrid = new TilePNode[base.nodeGrid.length][base.nodeGrid.length>0 ? base.nodeGrid[0].length : 0];
		for(int i = 0; i < base.nodeGrid.length; i++){
			for(int j = 0; j < base.nodeGrid.length; j++){
				nodeGrid[i][j] = base.nodeGrid[i][j].getCopy();
				addChild(nodeGrid[i][j]);
			}
		}
	}
	
	private void addNewPNode(int i, int j, double width, double height){
		addNewPNode(i, j, width, height, width, height);
	}
	
	private void addNewPNode(int i, int j, double width, double height, double gridWidth, double gridHeight){
		nodeGrid[i][j] = new TilePNode();
		addChild(nodeGrid[i][j]);
		nodeGrid[i][j].setBounds(gridWidth*i, gridHeight*j, width, height);
	}
	
	public TilePNode[][] getNodeGrid(){
		return nodeGrid;
	}
	
	/**
	 * calculates a mapping of TilePNodes to their appropriate Tilesets, returning a full grid (width x height of this layer) per each Tileset
	 * @return a mapping of Tilesets to all the tiles in this grid, in their appropriate spot
	 */
	public Map<Tileset, TilePNode[][]> getMappedNodeGrid(){
		Map<Tileset, TilePNode[][]> ans = new HashMap<Tileset, TilePNode[][]>();
		for(int i = 0; i < nodeGrid.length; i++)
		{
			for(int j = 0; j < nodeGrid[i].length; j++)
			{
				TilePNode node = nodeGrid[i][j];
				Tileset t = node.getTileset();
				if(t==null)
					continue;
				if(!ans.containsKey(t))
					ans.put(t, new TilePNode[nodeGrid.length][nodeGrid[i].length]);
				ans.get(t)[i][j] = node;
			}
		}
		return ans;
	}

	@Override
	public TileLayerPNode getCopy() {
		return new TileLayerPNode(this);
	}

	@Override
	public void setToCopy(TileLayerPNode copy) {
		nodeGrid = copy.nodeGrid;
	}

	@Override
	public String getDescription() {
		return "Tile Layer";
	}

}
