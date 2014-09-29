package com.clearlyspam23.GLE.basic.layers.tile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.clearlyspam23.GLE.basic.layers.tile.TilesetTreeNode.Type;

public class TilesetManager {
	
	private TilesetGroupNode root = new TilesetGroupNode("Tilesets");
	private Map<String, TilesetHandle> nameTable = new HashMap<String, TilesetHandle>();
	
	/**
	 * this function is now only here for debugging, and will soon be removed entirely
	 * @param t
	 * @return
	 */
	public boolean addTilesetToRoot(TilesetHandle t){
		if(nameTable.containsKey(t.getName()))
			return false;
		root.addNode(new TilesetTileNode(t));
		nameTable.put(t.getName(), t);
		return true;
	}
	
	private boolean helperUpdate(TilesetTreeNode node, Set<String> entries, Map<String, TilesetHandle> tempMap){
		if(node.getType()==Type.TILE){
			if(entries.add(node.getName())){
				tempMap.put(node.getName(), node.getAsTiles().getTileset());
				return true;
			}
			return false;
		}
		else{
			for(TilesetTreeNode n : node.getAsGroup().getChildren())
				if(!helperUpdate(n, entries, tempMap))
					return false;
			return true;
		}
	}
	
	/**
	 * updates the map to reflect the current TilesetGroupNode
	 * @return
	 */
	public boolean updateMap(){
		Set<String> entries = new HashSet<String>();
		Map<String, TilesetHandle> tempMap = new HashMap<String, TilesetHandle>();
		if(!helperUpdate(root, entries, tempMap))
			return false;
		for(Entry<String, TilesetHandle> e : tempMap.entrySet()){
			nameTable.put(e.getKey(), e.getValue());
		}
		List<String> toRemove = new ArrayList<String>();
		for(String s : nameTable.keySet()){
			if(!entries.contains(s))
				toRemove.add(s);
		}
		for(String s : toRemove)
			nameTable.remove(s);
		return true;
	}
	
	public List<TilesetHandle> getAllTilesets(){
		return root.getTilesets();
	}
	
	private boolean willWork(TilesetHandle h, List<TilesetSelectionConstraint> constraints){
		for(TilesetSelectionConstraint c : constraints){
			if(!c.willTilesetWork(h))
				return false;
		}
		return true;
	}
	
	public List<TilesetHandle> getAllMatchingTilesets(List<TilesetSelectionConstraint> constraints){
		List<TilesetHandle> all = getAllTilesets();
		List<TilesetHandle> ans = new ArrayList<TilesetHandle>();
		for(TilesetHandle h : all){
			if(willWork(h, constraints))
				ans.add(h);
		}
		return ans;
	}
	
	public TilesetHandle getTilesetByName(String name){
		return nameTable.get(name);
	}

	public Map<String, TilesetHandle> getNameTable() {
		return nameTable;
	}

	public void setNameTable(Map<String, TilesetHandle> nameTable) {
		this.nameTable = nameTable;
	}
	
	public TilesetGroupNode getRoot(){
		return root;
	}
	
	private void discoverNodes(TilesetGroupNode node){
		for(TilesetTreeNode n : node.getChildren()){
			if(n.getType()==Type.TILE){
				nameTable.put(n.getName(), n.getAsTiles().getTileset());
			}
			else{
				discoverNodes(n.getAsGroup());
			}
		}
	}
	
	public void setRoot(TilesetGroupNode root){
		this.root = root;
		nameTable.clear();
		discoverNodes(root);
	}

//	public void setAllTilesets(List<TilesetHandle> allTilesets) {
//		this.allTilesets = allTilesets;
//	}

}
