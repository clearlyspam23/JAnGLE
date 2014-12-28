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
	
	private TileBox selection;
	private PNode selectionNode;
	
	public BasePNode(TileLayerTemplate template){
		tiles = new TileLayerPNode(template.getDefaultGridWidth(), template.getDefaultGridHeight());
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

	public TileBox getSelection() {
		return selection;
	}

	public void setSelection(TileBox selection) {
		if(selectionNode!=null)
			selectionNode.removeFromParent();
		if(this.selection!=null){
			this.selection.lostSelection();
		}
		
		this.selection = selection;
		if(selection!=null){
			selectionNode = selection.getPNode();
			addChild(selectionNode);
		}
		else
			selectionNode = null;
	}
	
	public boolean canCut(){
		return selection!=null&&selection.canCut();
	}
	
	public boolean canCopy(){
		return selection!=null&&selection.canCopy();
	}

}
