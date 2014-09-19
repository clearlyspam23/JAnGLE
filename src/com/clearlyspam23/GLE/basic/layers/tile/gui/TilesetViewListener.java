package com.clearlyspam23.GLE.basic.layers.tile.gui;

import javax.swing.tree.DefaultMutableTreeNode;

import com.clearlyspam23.GLE.basic.layers.tile.TilesetGroupNode;
import com.clearlyspam23.GLE.basic.layers.tile.TilesetTileNode;

public interface TilesetViewListener {
	
	public void onTilesetDoubleClick(TilesetTileNode tileNode, DefaultMutableTreeNode treeNode);
	
	public void onGroupDoubleClick(TilesetGroupNode tileNode, DefaultMutableTreeNode treeNode);
	
	public void onTilesetRightClick(TilesetTileNode tileNode, DefaultMutableTreeNode treeNode);
	
	public void onGroupRightClick(TilesetGroupNode tileNode, DefaultMutableTreeNode treeNode);

}
