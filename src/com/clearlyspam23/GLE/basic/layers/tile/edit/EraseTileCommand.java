package com.clearlyspam23.GLE.basic.layers.tile.edit;

import java.util.ArrayList;
import java.util.List;

import org.piccolo2d.PCamera;
import org.piccolo2d.event.PInputEvent;

import com.clearlyspam23.GLE.basic.layers.tile.TileData;
import com.clearlyspam23.GLE.basic.layers.tile.edit.commands.EraseTileAction;
import com.clearlyspam23.GLE.basic.layers.tile.gui.TilePNode;
import com.clearlyspam23.GLE.util.Pair;

public class EraseTileCommand extends TileDragCommand {
	
	protected List<Pair<TilePNode, TileData>> replacedList;

	public EraseTileCommand(TileLayerEditManager data) {
		super(data);
	}
	
	protected void setTile(TilePNode tile, PCamera cam){
		replacedList.add(new Pair<TilePNode, TileData>(tile, tile.getTileData()));
		tile.resetTileset();
	}
	
	private boolean aChangeExists(){
		for(Pair<TilePNode, TileData> p : replacedList){
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
	protected boolean onStart(PInputEvent event) {
		replacedList = new ArrayList<Pair<TilePNode, TileData>>();
		return true;
	}

}
