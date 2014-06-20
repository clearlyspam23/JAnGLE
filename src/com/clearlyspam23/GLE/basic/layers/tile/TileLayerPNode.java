package com.clearlyspam23.GLE.basic.layers.tile;

import java.awt.geom.Rectangle2D;
import java.util.HashMap;
import java.util.Map;

import org.piccolo2d.PNode;

public class TileLayerPNode extends PNode{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//need some way to map PNode to Tilesets
	
	private TilePNode[][] nodeGrid;
	
	public TileLayerPNode(int width, int height, int gridWidth, int gridHeight)
	{
		int rows = width/gridWidth;
		int columns = height/gridHeight;
		nodeGrid = new TilePNode[rows + (width%gridWidth == 0 ? 0 : 1)][columns + (height%gridHeight == 0 ? 0 : 1)];
		for(int i = 0; i < rows; i++){
			for(int j = 0; j < columns; j++){
				addNewPNode(i, j, gridWidth, gridHeight);
			}
		}
		if(rows<nodeGrid.length){
			int remainder = width%gridWidth;
			for(int j = 0; j < nodeGrid[rows].length; j++){
				addNewPNode(rows, j, remainder, gridHeight, gridWidth, gridHeight);
			}
		}
		if(nodeGrid.length>0&&columns<nodeGrid[0].length){
			int remainder = height%gridHeight;
			for(int i = 0; i < nodeGrid.length; i++){
				addNewPNode(i, columns, gridWidth, remainder, gridWidth, gridHeight);
			}
		}
	}
	
	private void addNewPNode(int i, int j, int width, int height){
		addNewPNode(i, j, width, height, width, height);
	}
	
	private void addNewPNode(int i, int j, int width, int height, int gridWidth, int gridHeight){
		nodeGrid[i][j] = new TilePNode();
		nodeGrid[i][j].setBounds(new Rectangle2D.Double(gridWidth*i, gridHeight*j, width, height));
		addChild(nodeGrid[i][j]);
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

}
