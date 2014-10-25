package com.clearlyspam23.GLE.basic.layers.tile.commands;

import org.piccolo2d.event.PDragSequenceEventHandler;
import org.piccolo2d.event.PInputEvent;

public class TileSelectCommand extends PDragSequenceEventHandler {
	
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
