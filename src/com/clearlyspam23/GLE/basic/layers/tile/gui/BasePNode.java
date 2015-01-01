package com.clearlyspam23.GLE.basic.layers.tile.gui;

import org.piccolo2d.PNode;

import com.clearlyspam23.GLE.basic.layers.tile.TileLayer;
import com.clearlyspam23.GLE.basic.layers.tile.TileLayerTemplate;
import com.clearlyspam23.GLE.basic.layers.tile.TilesetManager;

public class BasePNode extends PNode {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TileLayerPNode tiles;
	
	private TileSelection selection;
	
	public BasePNode(TileLayerTemplate template, TileLayer layer){
		tiles = new TileLayerPNode(template.getDefaultGridWidth(), template.getDefaultGridHeight(), layer);
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

	public TileSelection getSelection() {
		return selection;
	}

	public void setSelection(TileSelection selection) {
		if(this.selection!=null){
			this.selection.onRemove();
		}
		this.selection = selection;
	}
	
	public boolean canCut(){
		return selection!=null;
	}
	
	public boolean canCopy(){
		return selection!=null;
	}

}
