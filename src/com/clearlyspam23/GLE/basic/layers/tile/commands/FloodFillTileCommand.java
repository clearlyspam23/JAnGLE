package com.clearlyspam23.GLE.basic.layers.tile.commands;

import java.util.ArrayList;
import java.util.List;

import org.piccolo2d.PCamera;
import org.piccolo2d.event.PInputEvent;

import com.clearlyspam23.GLE.basic.layers.tile.TileData;
import com.clearlyspam23.GLE.basic.layers.tile.TilesetHandle;
import com.clearlyspam23.GLE.basic.layers.tile.commands.EditActions.PlaceTileAction;
import com.clearlyspam23.GLE.basic.layers.tile.gui.TileLayerPNode;
import com.clearlyspam23.GLE.basic.layers.tile.gui.TilePNode;
import com.clearlyspam23.GLE.basic.layers.tile.gui.TileLayerEditManager;
import com.clearlyspam23.GLE.util.Pair;

public class FloodFillTileCommand extends TileDragCommand {
	
	protected List<Pair<TilePNode, TileData>> replacedList;
	
	
	public FloodFillTileCommand(TileLayerEditManager data){
		super(data);
	}
	
	protected void setTile(TilePNode tile, PCamera cam){
//		tile.setImage((Image)null);
		floodFill(tile, tile.getTileset(), tile.getTilesetX(), tile.getTilesetY(), ((TileLayerPNode)tile.getParent()).getNodeGrid());
//		tile.invalidatePaint();
	}
	
	private boolean aChangeExists(){
		for(Pair<TilePNode, TileData> p : replacedList){
			if(!p.second.equals(data.getCurrentTileset(), data.getSelectedX(), data.getSelectedY()))
				return true;
		}
		return false;
	}
	
	private boolean isSameTile(TilesetHandle handle1, int x1, int y1, TilesetHandle handle2, int x2, int y2){
		return handle1==handle2&&x1==x2&&y1==y2;
	}
	
	private void floodFill(TilePNode node, TilesetHandle target, int targetX, int targetY, TilePNode[][] grid){
		if(isSameTile(target, targetX, targetY, data.getCurrentTileset(), data.getSelectedX(), data.getSelectedY()))
			return;
		if(!isSameTile(target, targetX, targetY, node.getTileset(), node.getTilesetX(), node.getTilesetY()))
			return;
		replacedList.add(new Pair<TilePNode, TileData>(node, node.getTileData()));
		node.setTileset(data.getCurrentTileset(), data.getSelectedX(), data.getSelectedY());
		int x = node.getGridX();
		int y = node.getGridY();
		if(x-1>=0)
			floodFill(grid[x-1][y], target, targetX, targetY, grid);
		if(x+1<grid.length)
			floodFill(grid[x+1][y], target, targetX, targetY, grid);
		if(y-1>=0)
			floodFill(grid[x][y-1], target, targetX, targetY, grid);
		if(y+1<grid[x].length)
			floodFill(grid[x][y+1], target, targetX, targetY, grid);
	}

	@Override
	protected void onFinish(PInputEvent event) {
		if(aChangeExists()){
			PlaceTileAction action = new PlaceTileAction(replacedList, new TileData(data.getCurrentTileset(), data.getSelectedX(), data.getSelectedY()));
			data.registerEditAction(action);
		}
	}

	@Override
	protected void onStart(PInputEvent event) {
		replacedList = new ArrayList<Pair<TilePNode, TileData>>();
	}

}
