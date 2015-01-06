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
	
	private PNode selectionNode;
	private TileSelection selection;
	private PNode overlayNode;
	private TileLayer layer;
	
	public BasePNode(TileLayerTemplate template, TileLayer layer){
		this.layer = layer;
		tiles = new TileLayerPNode(template.getDefaultGridWidth(), template.getDefaultGridHeight(), layer, this);
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
			this.selection.onAnchor();
			this.selection.onRemove();
			if(selectionNode!=null)
				removeChild(selectionNode);
			if(overlayNode!=null)
				layer.getOverlayGUI().removeChild(overlayNode);
		}
		this.selection = selection;
		if(selection!=null){
			selectionNode = selection.getSelectionNode();
			if(selectionNode!=null)
				addChild(selectionNode);
			overlayNode = selection.getOverlayNode();
			if(overlayNode!=null)
				layer.getOverlayGUI().addChild(overlayNode);
		}
	}
	
	public void clearSelection(){
		setSelection(null);
		//for everyone not comfortable with passing in nulls
	}
	
	public boolean canCut(){
		return selection!=null;
	}
	
	public boolean canCopy(){
		return selection!=null;
	}

}
