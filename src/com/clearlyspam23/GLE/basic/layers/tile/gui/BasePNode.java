package com.clearlyspam23.GLE.basic.layers.tile.gui;

import org.piccolo2d.PNode;

import com.clearlyspam23.GLE.basic.layers.tile.TileLayerTemplate;
import com.clearlyspam23.GLE.basic.layers.tile.TilesetManager;

public class BasePNode extends PNode {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TileLayerPNode tiles;
	
	public BasePNode(TileLayerTemplate template){
		tiles = new TileLayerPNode(template.getGridWidth(), template.getGridHeight());
		addChild(tiles);
	}

	public TileLayerPNode getTiles() {
		return tiles;
	}
	
	public boolean refreshTilesets(TilesetManager manager){
		return tiles.refreshNodes(manager);
	}
	
	public void resize(double x, double y){
		tiles.resize(x, y);
	}

}
