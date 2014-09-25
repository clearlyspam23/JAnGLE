package com.clearlyspam23.GLE.basic.layers.tile;

import java.io.Serializable;
import java.util.List;

import com.clearlyspam23.GLE.Nameable;
import com.clearlyspam23.GLE.basic.layers.tile.resources.TilesetHandle;

public abstract class TilesetTreeNode implements Nameable, Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static enum Type{
		TILE, GROUP
	}
	
	private String name;
	private TilesetGroupNode parent;
	
	public TilesetTreeNode(String name){
		this(name, null);
	}
	public TilesetTreeNode(String name, TilesetGroupNode parent){
		this.name = name;
		this.parent = parent;
	}
	
	public final String getName(){
		return name;
	}
	
	public final void setName(String name){
		this.name = name;
	}
	
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
		if(this.parent!=null){
			this.parent.getChildren().remove(this);
		}
		this.parent = parent;
		if(parent!=null&&!parent.getChildren().contains(this))
			parent.addNode(this);
	}
	
	public abstract List<TilesetHandle> getTilesets();

}
