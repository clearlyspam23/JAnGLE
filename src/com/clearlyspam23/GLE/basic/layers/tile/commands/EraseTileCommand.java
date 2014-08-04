package com.clearlyspam23.GLE.basic.layers.tile.commands;

import org.piccolo2d.PCamera;

import com.clearlyspam23.GLE.basic.layers.tile.TilesetEditorData;
import com.clearlyspam23.GLE.basic.layers.tile.gui.TilePNode;

public class EraseTileCommand extends PlaceTileCommand {

	public EraseTileCommand(TilesetEditorData data) {
		super(data);
	}
	
	protected void setTile(TilePNode tile, PCamera cam){
//		tile.setImage((Image)null);
		tile.resetTileset();
//		tile.invalidatePaint();
	}

}
