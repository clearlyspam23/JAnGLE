package com.clearlyspam23.GLE.basic.layers.tile.commands;

import java.awt.geom.Point2D;

import org.piccolo2d.PCamera;
import org.piccolo2d.PNode;
import org.piccolo2d.event.PDragSequenceEventHandler;
import org.piccolo2d.event.PInputEvent;
import org.piccolo2d.util.PPickPath;

import com.clearlyspam23.GLE.GUI.util.FixedWidthOutlineRectNode;
import com.clearlyspam23.GLE.basic.layers.tile.gui.TileLayerPNode;
import com.clearlyspam23.GLE.basic.layers.tile.gui.TilePNode;
import com.clearlyspam23.GLE.basic.layers.tile.gui.TileLayerEditManager;

public class TileSelectCommand extends PDragSequenceEventHandler {
	
	protected TileLayerEditManager data;
	protected TilePNode startNode;
	
	protected FixedWidthOutlineRectNode outlineBoxNode;
	
	public TileSelectCommand(TileLayerEditManager data){
		this.data = data;
	}
	
	public void mouseClicked(PInputEvent event){
		super.mouseClicked(event);
		if(event.isLeftMouseButton()&&!event.isAltDown()&&!event.isControlDown()&&event.getClickCount()==2){
			System.out.println("should select a bunch");
		}
		else if(event.isRightMouseButton()){
			outlineBoxNode.removeFromParent();
			outlineBoxNode = null;
		}
	}
	
	@Override
	protected boolean shouldStartDragInteraction(PInputEvent event) {
        if (super.shouldStartDragInteraction(event)) {
            return event.isLeftMouseButton()&&event.getClickCount()==1;
        }
        return false;
    }
	
	private PNode getPickedNode(Point2D pos, PCamera cam){
		final PPickPath p = cam.pick(pos.getX(), pos.getY(), 1);
		if(p==null)
			return null;
		return p.getPickedNode();
	}
	
	protected void startDrag(PInputEvent event){
		super.startDrag(event);
		System.out.println("start drag");
		startNode = tryGrabNode(event.getCanvasPosition(), event.getCamera());
		if(outlineBoxNode!=null){
			outlineBoxNode.removeFromParent();
			outlineBoxNode = null;
		}
	}
	
	
	@Override
	protected void endDrag(PInputEvent event){
		super.endDrag(event);
		System.out.println("end drag");
		startNode = null;
	}
	
	protected void drag(PInputEvent event) {
        super.drag(event);
        if(startNode!=null){
        	TilePNode currentNode = tryGrabNode(event.getCanvasPosition(), event.getCamera());
        	if(currentNode!=null){
        		TileLayerPNode parent = (TileLayerPNode) currentNode.getParent();
        		if(outlineBoxNode == null){
                	outlineBoxNode = new FixedWidthOutlineRectNode(1, event.getCamera());
                	outlineBoxNode.setBounds(0, 0, startNode.getWidth(), startNode.getHeight());
                	outlineBoxNode.setPickable(false);
                	parent.getLayer().getOverlayGUI().addChild(outlineBoxNode);
                }
        		int startX = Math.min(currentNode.getGridX(), startNode.getGridX());
        		int endX = Math.max(currentNode.getGridX(), startNode.getGridX());
        		int startY = Math.min(currentNode.getGridY(), startNode.getGridY());
        		int endY = Math.max(currentNode.getGridY(), startNode.getGridY());
        		double x = startX*parent.getGridWidth();
        		double y = startY * parent.getGridHeight();
        		double width = Math.min((endX-startX+1)*parent.getGridWidth(), parent.getWidth()-x);
        		double height = Math.min((endY-startY+1)*parent.getGridHeight(), parent.getHeight()-y);
        		if(outlineBoxNode.setBounds(x, y, width, height)){
        			outlineBoxNode.repaint();
            		parent.repaint();
            		System.out.println("Repaint");
        		}
        	}
        }
    }
	
	protected TilePNode tryGrabNode(Point2D pos, PCamera cam)
	{
		PNode p = getPickedNode(pos, cam);
        if(p instanceof TilePNode){
        	TilePNode tile = (TilePNode)p;
        	return tile;
        }
        else{
        	if(p instanceof TileLayerPNode){
        		System.out.println(p.getWidth() + ", " + p.getHeight());
        	}
        	System.out.println(p.getClass());
        }
		return null;
	}

}
