package com.clearlyspam23.GLE.basic.layers.tile;

import java.io.Serializable;
import java.util.List;

import com.clearlyspam23.GLE.Nameable;

public abstract class TilesetTreeNode implements Nameable, Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static enum Type{
		TILE, GROUP
	}
	private TilesetGroupNode parent;
	
	public TilesetTreeNode(){
		
	}
	public TilesetTreeNode(TilesetGroupNode parent){
		this.parent = parent;
	}
	
	public abstract String getName();
	
	public abstract void setName(String name);
	
	public abstract Type getType();
	
	public TilesetTileNode getAsTiles(){
		return (TilesetTileNode) this;
	}
	
	public TilesetGroupNode getAsGroup(){
		return (TilesetGroupNode) this;
	}
	
	public final TilesetGroupNode getParent(){
		return parent;
	}
	
	public final void setParent(TilesetGroupNode parent){
		if(this.parent!=null&&this.parent!=parent){
			this.parent.getChildren().remove(this);
		}
		this.parent = parent;
		if(parent!=null&&!parent.getChildren().contains(this))
			parent.addNode(this);
	}
	
	public abstract List<TilesetHandle> getTilesets();
	
	public abstract TilesetTreeNode cloneAsBasic();

}
