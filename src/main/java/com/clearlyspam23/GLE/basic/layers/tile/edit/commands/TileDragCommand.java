package com.clearlyspam23.GLE.basic.layers.tile.edit.commands;

import java.awt.geom.Point2D;
import java.util.HashSet;
import java.util.Set;

import org.piccolo2d.PCamera;
import org.piccolo2d.PNode;
import org.piccolo2d.event.PDragSequenceEventHandler;
import org.piccolo2d.event.PInputEvent;
import org.piccolo2d.util.PPickPath;

import com.clearlyspam23.GLE.basic.layers.tile.edit.TileLayerEditManager;
import com.clearlyspam23.GLE.basic.layers.tile.gui.TileLayerPNode;
import com.clearlyspam23.GLE.basic.layers.tile.gui.TilePNode;

public abstract class TileDragCommand extends PDragSequenceEventHandler {
	
	protected TileLayerEditManager data;
	
	private TileLayerPNode currentNode;
	
	protected Set<TilePNode> visited = new HashSet<TilePNode>();
	
	private boolean shouldRun;
	
	
	public TileDragCommand(TileLayerEditManager data){
		this.data = data;
	}
	
	@Override
	protected void startDrag(PInputEvent event){
		super.startDrag(event);
		if(event.isLeftMouseButton()){
			shouldRun = onStart(event);
			tryPlaceImage(event.getCanvasPosition(), event.getCamera());
		}
	}
	
	@Override
	protected void endDrag(PInputEvent event){
		super.endDrag(event);
		if(event.isLeftMouseButton()){
			currentNode = null;
			onFinish(event);
			visited.clear();
		}
	}
	
	protected abstract void setTile(TilePNode tile, PCamera cam);
	
	protected abstract void onFinish(PInputEvent event);
	
	protected abstract boolean onStart(PInputEvent event);
	
	@Override
	protected boolean shouldStartDragInteraction(PInputEvent event) {
        if (super.shouldStartDragInteraction(event)) {
            return event.isLeftMouseButton();
        }
        return false;
    }
	
	protected void tryPlaceImage(Point2D pos, PCamera cam)
	{
		PNode p = getPickedNode(pos, cam);
        if(shouldRun&&p instanceof TilePNode){
        	TilePNode tile = (TilePNode)p;
        	if(currentNode == null)
        		currentNode = (TileLayerPNode) tile.getParent();
        	if(!visited.contains(tile)&&currentNode.equals(tile.getParent())){
        		visited.add(tile);
        		setTile(tile, cam);
        	}
        }
	}
	
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
