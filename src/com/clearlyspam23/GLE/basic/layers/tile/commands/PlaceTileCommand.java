package com.clearlyspam23.GLE.basic.layers.tile.commands;

import java.awt.geom.Point2D;

import org.piccolo2d.PCamera;
import org.piccolo2d.PNode;
import org.piccolo2d.event.PDragSequenceEventHandler;
import org.piccolo2d.event.PInputEvent;
import org.piccolo2d.util.PPickPath;

import com.clearlyspam23.GLE.basic.layers.tile.TilesetEditorData;
import com.clearlyspam23.GLE.basic.layers.tile.gui.TileLayerPNode;
import com.clearlyspam23.GLE.basic.layers.tile.gui.TilePNode;

public class PlaceTileCommand extends PDragSequenceEventHandler {
	
	private TilesetEditorData data;
	
	
	public PlaceTileCommand(TilesetEditorData data){
		this.data = data;
	}
	
	protected void setTile(TilePNode tile, PCamera cam){
		tile.setImage(data.getSelectedTile());
		tile.setTileset(data.getCurrentTileset(), data.getSelectedX(), data.getSelectedY());
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
			tryPlaceImage(event.getCanvasPosition(), event.getCamera());
	}
	
	protected void tryPlaceImage(Point2D pos, PCamera cam)
	{
		PNode p = getPickedNode(pos, cam);
        if(p instanceof TilePNode){
        	TilePNode tile = (TilePNode)p;
        	if(data.getSelectedTile()!=null){
        		setTile(tile, cam);
        	}
        }
	}
	
//	public void endDrag(PInputEvent event){
//		super.endDrag(event);
//		PNode p = getPickedNode(event.getCanvasPosition(), event.getCamera());
//		if(p.getParent() instanceof TileLayerPNode){
//			((TileLayerPNode)p.getParent()).registerChange();
//		}
//	}
	
	protected void drag(PInputEvent event) {
        super.drag(event);
        tryPlaceImage(event.getCanvasPosition(), event.getCamera());
    }
	
	private PNode getPickedNode(Point2D pos, PCamera cam){
		final PPickPath p = cam.pick(pos.getX(), pos.getY(), 1);
		if(p==null)
			return null;
		return p.getPickedNode();
	}

}
