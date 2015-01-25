package com.clearlyspam23.GLE.basic.layers.tile.gui;

import org.piccolo2d.PNode;

import com.clearlyspam23.GLE.basic.layers.tile.TileLayer;
import com.clearlyspam23.GLE.basic.layers.tile.TileLayerTemplate;
import com.clearlyspam23.GLE.basic.layers.tile.TilesetManager;
import com.clearlyspam23.GLE.basic.layers.tile.edit.TileLayerEditManager;
import com.clearlyspam23.GLE.basic.layers.tile.edit.actions.AnchorSelectionAction;
import com.clearlyspam23.GLE.basic.layers.tile.edit.actions.CreateSelectionAction;

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
	
	public TileSelection clearSelectionWithoutAnchor(){
		TileSelection oldSelection = selection;
		if(selection!=null){
			selection.onRemove();
			if(selectionNode!=null)
				removeChild(selectionNode);
			if(overlayNode!=null)
				layer.getOverlayGUI().removeChild(overlayNode);
			selection = null;
			layer.selectionChanged(oldSelection, selection);
		}
		return oldSelection;
	}
	
	public TileSelection clearSelection(){
		TileSelection oldSelection = selection;
		if(selection!=null){
			selection.onAnchor();
			selection.onRemove();
			if(selectionNode!=null)
				removeChild(selectionNode);
			if(overlayNode!=null)
				layer.getOverlayGUI().removeChild(overlayNode);
			selection = null;
			layer.selectionChanged(oldSelection, selection);
		}
		return oldSelection;
	}
	
	public void setSelection(TileSelection selection){
		TileSelection oldSelection = this.selection;
		this.selection = selection;
		if(selection!=null){
			selectionNode = selection.getSelectionNode();
			if(selectionNode!=null)
				addChild(selectionNode);
			overlayNode = selection.getOverlayNode();
			if(overlayNode!=null)
				layer.getOverlayGUI().addChild(overlayNode);
			
		}
		layer.selectionChanged(oldSelection, selection);
	}

	public void setSelectionWithAction(TileSelection selection, TileLayerEditManager editor) {
//		if(this.selection!=null){
//			this.selection.onAnchor();
//			this.selection.onRemove();
//			if(selectionNode!=null)
//				removeChild(selectionNode);
//			if(overlayNode!=null)
//				layer.getOverlayGUI().removeChild(overlayNode);
//		}
		TileSelection oldSelection = clearSelection();
		if(oldSelection!=null){
			editor.registerEditAction(new AnchorSelectionAction(oldSelection, this));
		}
		setSelection(selection);
		if(selection!=null){
			editor.registerEditAction(new CreateSelectionAction(selection, this));
		}
	}
	
	public boolean canCopy(){
		return hasSelection();
	}
	
	public boolean canCut(){
		return hasSelection();
	}
	
	public void clearSelectionWithAction(TileLayerEditManager editor){
		setSelectionWithAction(null, editor);
		//for everyone not comfortable with passing in nulls
	}
	
	public void anchorSelection(){
		if(selection!=null){
			selection.onAnchor();
			selection.onRemove();
		}
	}
	
	public boolean hasSelection(){
		return selection!=null;
	}

}
