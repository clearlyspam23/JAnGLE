package com.clearlyspam23.GLE.basic.layers.tile.commands;

import java.awt.Image;

import com.clearlyspam23.GLE.basic.layers.tile.TilePNode;
import com.clearlyspam23.GLE.basic.layers.tile.TilesetEditorData;

public class EraseTileCommand extends PlaceTileCommand {

	public EraseTileCommand(TilesetEditorData data) {
		super(data);
	}
	
	protected void setTile(TilePNode tile){
		System.out.println("erase");
		tile.setImage((Image)null);
		tile.resetTileset();
	}

}
