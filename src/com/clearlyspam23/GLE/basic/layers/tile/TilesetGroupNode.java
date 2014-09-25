package com.clearlyspam23.GLE.basic.layers.tile;

import java.util.ArrayList;
import java.util.List;

import com.clearlyspam23.GLE.basic.layers.tile.resources.TilesetHandle;

public class TilesetGroupNode extends TilesetTreeNode{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<TilesetTreeNode> children = new ArrayList<TilesetTreeNode>();

	public TilesetGroupNode(String name) {
		super(name);
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
	}
	
	public List<TilesetTreeNode> getChildren(){
		return children;
	}

	@Override
	public Type getType() {
		return Type.GROUP;
	}

}
