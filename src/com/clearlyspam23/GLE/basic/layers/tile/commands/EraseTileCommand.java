package com.clearlyspam23.GLE.basic.layers.tile.commands;

import java.util.ArrayList;
import java.util.List;

import org.piccolo2d.PCamera;
import org.piccolo2d.event.PInputEvent;

import com.clearlyspam23.GLE.basic.layers.tile.Tile;
import com.clearlyspam23.GLE.basic.layers.tile.commands.EditActions.EraseTileAction;
import com.clearlyspam23.GLE.basic.layers.tile.gui.TilePNode;
import com.clearlyspam23.GLE.basic.layers.tile.gui.TilesetEditorData;
import com.clearlyspam23.GLE.util.Pair;

public class EraseTileCommand extends TileDragCommand {
	
	protected List<Pair<TilePNode, Tile>> replacedList;

	public EraseTileCommand(TilesetEditorData data) {
		super(data);
	}
	
	protected void setTile(TilePNode tile, PCamera cam){
		replacedList.add(new Pair<TilePNode, Tile>(tile, tile.getTile()));
		tile.resetTileset();
	}
	
	private boolean aChangeExists(){
		for(Pair<TilePNode, Tile> p : replacedList){
			if(p.second.tileset!=null)
				return true;
		}
		return false;
	}

	@Override
	protected void onFinish(PInputEvent event) {
		if(aChangeExists()){
			EraseTileAction action = new EraseTileAction(replacedList);
			data.registerEditAction(action);
		}
	}

	@Override
	protected void onStart(PInputEvent event) {
		replacedList = new ArrayList<Pair<TilePNode, Tile>>();
	}

}
