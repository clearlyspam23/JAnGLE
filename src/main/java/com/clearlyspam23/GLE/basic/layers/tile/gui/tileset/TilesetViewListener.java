package com.clearlyspam23.GLE.basic.layers.tile.gui.tileset;

import java.awt.event.MouseEvent;

import com.clearlyspam23.GLE.basic.layers.tile.TilesetGroupNode;
import com.clearlyspam23.GLE.basic.layers.tile.TilesetTileNode;

public interface TilesetViewListener {
	
	public void onTilesetDoubleClick(TilesetTreeViewPanel panel, TilesetTileNode tileNode, MouseEvent e);
	
	public void onGroupDoubleClick(TilesetTreeViewPanel panel, TilesetGroupNode tileNode, MouseEvent e);
	
	public void onTilesetRightClick(TilesetTreeViewPanel panel, TilesetTileNode tileNode, MouseEvent e);
	
	public void onGroupRightClick(TilesetTreeViewPanel panel, TilesetGroupNode tileNode, MouseEvent e);
	
	public void onTilesetRenamed(TilesetTreeViewPanel panel, TilesetTileNode node);
	
	public void onGroupRenamed(TilesetTreeViewPanel panel, TilesetGroupNode node);

}
