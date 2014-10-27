package com.clearlyspam23.GLE.basic.layers.tile.commands;

import org.piccolo2d.event.PDragSequenceEventHandler;
import org.piccolo2d.event.PInputEvent;

import com.clearlyspam23.GLE.basic.layers.tile.gui.TilesetEditorData;

public class TileSelectCommand extends PDragSequenceEventHandler {
	
	protected TilesetEditorData data;
	
	public TileSelectCommand(TilesetEditorData data){
		this.data = data;
	}
	
	protected void startDrag(PInputEvent event){
		super.startDrag(event);
	}
	
	@Override
	protected boolean shouldStartDragInteraction(PInputEvent event) {
        if (super.shouldStartDragInteraction(event)) {
            return event.isLeftMouseButton();
        }
        return false;
    }

}
