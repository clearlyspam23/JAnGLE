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
	
	private static void recursivePrint(TilesetGroupNode node, int indent){
		for(TilesetTreeNode n : node.getChildren()){
			for(int i = 0; i < indent; i++)
				System.out.print('\t');
			System.out.println(n.getType() + " : " + n.getName());
			if(n.getType()==TilesetTreeNode.Type.GROUP)
				recursivePrint(n.getAsGroup(), indent+1);
		}
	}
	
	public static void recursivePrint(TilesetGroupNode node){
		recursivePrint(node, 0);
	}
	
	public TilesetGroupNode(){
		name = "";
	}

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
	
	public void setChildren(List<TilesetTreeNode> children){
		this.children = children;
	}

	@Override
	public Type getType() {
		return Type.GROUP;
	}

	@Override
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public List<TilesetHandle> getTilesetsByName(String name){
		List<TilesetHandle> out = new ArrayList<TilesetHandle>();
		for(TilesetHandle h : getTilesets()){
			if(h.getName().equals(name))
				out.add(h);
		}
		return out;
	}

	@Override
	public TilesetTreeNode cloneAsBasic() {
		TilesetGroupNode out = new TilesetGroupNode(name);
		for(TilesetTreeNode n : children)
			out.addNode(n.cloneAsBasic());
		return out;
	}

}
