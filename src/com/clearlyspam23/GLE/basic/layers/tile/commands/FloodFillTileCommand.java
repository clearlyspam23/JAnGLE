package com.clearlyspam23.GLE.basic.layers.tile.commands;

import org.piccolo2d.PCamera;

import com.clearlyspam23.GLE.basic.layers.tile.TilesetHandle;
import com.clearlyspam23.GLE.basic.layers.tile.gui.TileLayerPNode;
import com.clearlyspam23.GLE.basic.layers.tile.gui.TilePNode;
import com.clearlyspam23.GLE.basic.layers.tile.gui.TilesetEditorData;

public class FloodFillTileCommand extends PlaceTileCommand {
	
	
	public FloodFillTileCommand(TilesetEditorData data){
		super(data);
	}
	
	protected void setTile(TilePNode tile, PCamera cam){
//		tile.setImage((Image)null);
		floodFill(tile, tile.getTileset(), tile.getTilesetX(), tile.getTilesetY(), ((TileLayerPNode)tile.getParent()).getNodeGrid());
//		tile.invalidatePaint();
	}
	
	private boolean isSameTile(TilesetHandle handle1, int x1, int y1, TilesetHandle handle2, int x2, int y2){
		return handle1==handle2&&x1==x2&&y1==y2;
	}
	
	private void floodFill(TilePNode node, TilesetHandle target, int targetX, int targetY, TilePNode[][] grid){
		System.out.println("node = " + node.getTileset());
		System.out.println("target = " + target);
		System.out.println("current = " + data.getCurrentTileset());
		if(isSameTile(target, targetX, targetY, data.getCurrentTileset(), data.getSelectedX(), data.getSelectedY()))
			return;
		System.out.println(!(target==null&&node.getTileset()==null));
		if(!isSameTile(target, targetX, targetY, node.getTileset(), node.getTilesetX(), node.getTilesetY()))
			return;
		System.out.println("should be filling");
		node.setTileset(data.getCurrentTileset(), data.getSelectedX(), data.getSelectedY());
		int x = node.getGridX();
		int y = node.getGridy();
		if(x-1>=0)
			floodFill(grid[x-1][y], target, targetX, targetY, grid);
		if(x+1<grid.length)
			floodFill(grid[x+1][y], target, targetX, targetY, grid);
		if(y-1>=0)
			floodFill(grid[x][y-1], target, targetX, targetY, grid);
		if(y+1<grid[x].length)
			floodFill(grid[x][y+1], target, targetX, targetY, grid);
	}

}
