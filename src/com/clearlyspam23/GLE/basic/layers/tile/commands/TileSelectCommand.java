package com.clearlyspam23.GLE.basic.layers.tile.commands;

import java.awt.geom.Point2D;

import org.piccolo2d.PCamera;
import org.piccolo2d.PNode;
import org.piccolo2d.event.PDragSequenceEventHandler;
import org.piccolo2d.event.PInputEvent;
import org.piccolo2d.util.PPickPath;

import com.clearlyspam23.GLE.basic.layers.tile.gui.TilePNode;
import com.clearlyspam23.GLE.basic.layers.tile.gui.TilesetEditorData;

public class TileSelectCommand extends PDragSequenceEventHandler {
	
	protected TilesetEditorData data;
	protected TilePNode startNode;
	
	public TileSelectCommand(TilesetEditorData data){
		this.data = data;
	}
	
	protected void startDrag(PInputEvent event){
		super.startDrag(event);
		tryPlaceImage(event.getCanvasPosition(), event.getCamera());
	}
	
	@Override
	protected boolean shouldStartDragInteraction(PInputEvent event) {
        if (super.shouldStartDragInteraction(event)) {
            return event.isLeftMouseButton();
        }
        return false;
    }
	
	private PNode getPickedNode(Point2D pos, PCamera cam){
		final PPickPath p = cam.pick(pos.getX(), pos.getY(), 1);
		if(p==null)
			return null;
		return p.getPickedNode();
	}
	
	
	@Override
	protected void endDrag(PInputEvent event){
		super.endDrag(event);
		startNode = null;
	}
	protected void tryPlaceImage(Point2D pos, PCamera cam)
	{
		PNode p = getPickedNode(pos, cam);
        if(p instanceof TilePNode){
        	TilePNode tile = (TilePNode)p;
        }
	}

}
