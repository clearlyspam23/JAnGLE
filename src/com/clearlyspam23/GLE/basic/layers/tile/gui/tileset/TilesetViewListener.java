package com.clearlyspam23.GLE.basic.layers.tile.gui.tileset;

import java.awt.event.MouseEvent;

import com.clearlyspam23.GLE.basic.layers.tile.TilesetGroupNode;
import com.clearlyspam23.GLE.basic.layers.tile.TilesetTileNode;

public interface TilesetViewListener {
	
	public void onTilesetDoubleClick(TilesetViewPanel panel, TilesetTileNode tileNode, MouseEvent e);
	
	public void onGroupDoubleClick(TilesetViewPanel panel, TilesetGroupNode tileNode, MouseEvent e);
	
	public void onTilesetRightClick(TilesetViewPanel panel, TilesetTileNode tileNode, MouseEvent e);
	
	public void onGroupRightClick(TilesetViewPanel panel, TilesetGroupNode tileNode, MouseEvent e);
	
	public void onTilesetRenamed(TilesetViewPanel panel, TilesetTileNode node);
	
	public void onGroupRenamed(TilesetViewPanel panel, TilesetGroupNode node);

}
