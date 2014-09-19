package com.clearlyspam23.GLE.basic.layers.tile;

import java.util.List;

import com.clearlyspam23.GLE.Nameable;
import com.clearlyspam23.GLE.basic.layers.tile.resources.TilesetHandle;

public abstract class TilesetTreeNode implements Nameable{
	
	public static enum Type{
		TILE, GROUP
	}
	
	private String name;
	private TilesetTreeNode parent;
	
	public TilesetTreeNode(String name){
		this(name, null);
	}
	public TilesetTreeNode(String name, TilesetTreeNode parent){
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
	
	public final TilesetTreeNode getParent(){
		return parent;
	}
	
	public final TilesetGroupNode getParentAsGroup(){
		return parent.getAsGroup();
	}
	
	public abstract List<TilesetHandle> getTilesets();

}
