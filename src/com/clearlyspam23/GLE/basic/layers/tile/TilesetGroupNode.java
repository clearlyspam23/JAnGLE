package com.clearlyspam23.GLE.basic.layers.tile;

import java.util.ArrayList;
import java.util.List;

public class TilesetGroupNode extends TilesetTreeNode{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<TilesetTreeNode> children = new ArrayList<TilesetTreeNode>();
	
	private String name;

	public TilesetGroupNode(String name) {
		this.name = name;
	}

	@Override
	public List<TilesetHandle> getTilesets() {
		List<TilesetHandle> ans = new ArrayList<TilesetHandle>();
		for(TilesetTreeNode t : children){
			ans.addAll(t.getTilesets());
		}
		return ans;
	}
	
	public void addNode(TilesetTreeNode node){
		children.add(node);
		node.setParent(this);
	}
	
	public List<TilesetTreeNode> getChildren(){
		return children;
	}

	@Override
	public Type getType() {
		return Type.GROUP;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

}
