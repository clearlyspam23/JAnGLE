package com.clearlyspam23.GLE.basic.layers.tile.commands;

import java.awt.geom.Point2D;

import org.piccolo2d.PCanvas;
import org.piccolo2d.PNode;
import org.piccolo2d.event.PDragSequenceEventHandler;
import org.piccolo2d.event.PInputEvent;
import org.piccolo2d.util.PPickPath;

import com.clearlyspam23.GLE.basic.layers.tile.TilePNode;
import com.clearlyspam23.GLE.basic.layers.tile.TilesetEditorData;

public class PlaceTileCommand extends PDragSequenceEventHandler {
	
	private TilesetEditorData data;
	
	private PCanvas canvas;
	
	public PlaceTileCommand(PCanvas canvas, TilesetEditorData data){
		this.data = data;
		this.canvas = canvas;
	}
	
	protected void setTile(TilePNode node){
		
	}
	
	@Override
	protected boolean shouldStartDragInteraction(PInputEvent event) {
        if (super.shouldStartDragInteraction(event)) {
            return event.isLeftMouseButton();
        }
        return false;
    }
	
	public void mousePressed(PInputEvent event)
	{
		super.mousePressed(event);
		if(event.isLeftMouseButton())
			tryPlaceImage(event.getCanvasPosition());
	}
	
	protected void tryPlaceImage(Point2D pos)
	{
		PNode p = getPickedNode(pos);
        if(p instanceof TilePNode){
        	TilePNode tile = (TilePNode)p;
        	if(data.getSelectedTile()!=null){
        		tile.setImage(data.getSelectedTile());
        		tile.setTileset(data.getCurrentTileset(), data.getSelectedX(), data.getSelectedY());
        	}
        }
	}
	
	protected void drag(PInputEvent event) {
        super.drag(event);
        
        PNode p = getPickedNode(event.getCanvasPosition());
        if(p instanceof TilePNode){
        	TilePNode tile = (TilePNode)p;
        	if(data.getSelectedTile()!=null){
        		tile.setImage(data.getSelectedTile());
        		tile.setTileset(data.getCurrentTileset(), data.getSelectedX(), data.getSelectedY());
        	}
        }
    }
	
	private PNode getPickedNode(Point2D pos){
		final PPickPath p = canvas.getCamera().pick(pos.getX(), pos.getY(), 1);
		if(p==null)
			return null;
		return p.getPickedNode();
	}

}
