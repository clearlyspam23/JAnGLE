package com.clearlyspam23.GLE.basic.layers.tile.edit;

import java.util.ArrayList;
import java.util.List;

import org.piccolo2d.PCamera;
import org.piccolo2d.event.PInputEvent;

import com.clearlyspam23.GLE.basic.layers.tile.TileData;
import com.clearlyspam23.GLE.basic.layers.tile.edit.commands.PlaceTileAction;
import com.clearlyspam23.GLE.basic.layers.tile.gui.TileLayerPNode;
import com.clearlyspam23.GLE.basic.layers.tile.gui.TilePNode;
import com.clearlyspam23.GLE.util.Pair;

public class FloodFillTileCommand extends TileDragCommand {
	
	protected List<Pair<TilePNode, TileData>> replacedList;
	
	
	public FloodFillTileCommand(TileLayerEditManager data){
		super(data);
	}
	
	protected void setTile(TilePNode tile, PCamera cam){
//		tile.setImage((Image)null);
		floodFill(tile, tile.getTileData(), ((TileLayerPNode)tile.getParent()).getNodeGrid());
//		tile.invalidatePaint();
	}
	
	private boolean aChangeExists(){
		for(Pair<TilePNode, TileData> p : replacedList){
			if(!p.second.equals(data.getCurrentTileset(), data.getSelectedX(), data.getSelectedY()))
				return true;
		}
		return false;
	}
	
	
	private void floodFill(TilePNode node, TileData target, TilePNode[][] grid){
		if(!target.equals(node.getTileData()))
			return;
		Pair<TilePNode, TileData> pair = new Pair<TilePNode, TileData>(node, node.getTileData());
		if(!node.setTileset(data.getCurrentTileset(), data.getSelectedX(), data.getSelectedY()))
			return;
		replacedList.add(pair);
		int x = node.getGridX();
		int y = node.getGridY();
		if(x-1>=0)
			floodFill(grid[x-1][y], target, grid);
		if(x+1<grid.length)
			floodFill(grid[x+1][y], target, grid);
		if(y-1>=0)
			floodFill(grid[x][y-1], target, grid);
		if(y+1<grid[x].length)
			floodFill(grid[x][y+1], target, grid);
	}

	@Override
	protected void onFinish(PInputEvent event) {
		if(aChangeExists()){
			PlaceTileAction action = new PlaceTileAction(replacedList, new TileData(data.getCurrentTileset(), data.getSelectedX(), data.getSelectedY()));
			data.registerEditAction(action);
		}
	}

	@Override
	protected boolean onStart(PInputEvent event) {
		replacedList = new ArrayList<Pair<TilePNode, TileData>>();
		return true;
	}

}
