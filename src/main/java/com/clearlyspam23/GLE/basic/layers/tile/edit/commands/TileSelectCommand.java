package com.clearlyspam23.GLE.basic.layers.tile.edit.commands;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.piccolo2d.PCamera;
import org.piccolo2d.PNode;
import org.piccolo2d.event.PDragSequenceEventHandler;
import org.piccolo2d.event.PInputEvent;
import org.piccolo2d.util.PPickPath;

import com.clearlyspam23.GLE.GUI.util.FixedWidthOutlineRectNode;
import com.clearlyspam23.GLE.basic.layers.tile.TileData;
import com.clearlyspam23.GLE.basic.layers.tile.TileLocation;
import com.clearlyspam23.GLE.basic.layers.tile.edit.TileLayerEditManager;
import com.clearlyspam23.GLE.basic.layers.tile.edit.actions.AddToSelectionAction;
import com.clearlyspam23.GLE.basic.layers.tile.edit.actions.MoveSelectionAction;
import com.clearlyspam23.GLE.basic.layers.tile.edit.actions.RemoveFromSelectionAction;
import com.clearlyspam23.GLE.basic.layers.tile.gui.BasePNode;
import com.clearlyspam23.GLE.basic.layers.tile.gui.ImmovableTileSelection;
import com.clearlyspam23.GLE.basic.layers.tile.gui.MovableTileSelection;
import com.clearlyspam23.GLE.basic.layers.tile.gui.TileLayerPNode;
import com.clearlyspam23.GLE.basic.layers.tile.gui.TilePNode;

public class TileSelectCommand extends PDragSequenceEventHandler {
	
	protected TileLayerEditManager data;
	protected TilePNode startNode;
	protected TilePNode lastNode;
	
	protected FixedWidthOutlineRectNode outlineBoxNode;
	
	private TilePNode grabbed;
	
	private static final int SELECTING = 1;
	private static final int MOVING = 2;
	
	private int state;
	
	private MovableTileSelection movingSelection;
	private TileLocation startLocation;
	private TileLocation relativeLocation = new TileLocation();
	
	public TileSelectCommand(TileLayerEditManager data){
		this.data = data;
	}
	
	private void floodSelect(TilePNode node, TileData target, TilePNode[][] grid, List<TileLocation> out){
		if(!target.equals(node.getTileData())||out.contains(node.getTileLocation()))
			return;
		if(node.isSilentlyIgnoringInput())
			return;
		out.add(node.getTileLocation());
		int x = node.getGridX();
		int y = node.getGridY();
		if(x-1>=0)
			floodSelect(grid[x-1][y], target, grid, out);
		if(x+1<grid.length)
			floodSelect(grid[x+1][y], target, grid, out);
		if(y-1>=0)
			floodSelect(grid[x][y-1], target, grid, out);
		if(y+1<grid[x].length)
			floodSelect(grid[x][y+1], target, grid, out);
	}
	
	private void createSelection(List<TileLocation> selectedLocations, TileLayerPNode parent, PCamera camera){
		ImmovableTileSelection selection = new ImmovableTileSelection(selectedLocations, parent, camera);
		if(parent.getBase().hasSelection()){
			anchorSelection(parent.getBase());
		}
		parent.getBase().setSelectionWithAction(selection, data);
	}
	
	private void anchorSelection(BasePNode base){
		base.anchorSelectionWithAction(data);
	}
	
	private void selectSimilar(PInputEvent event, TilePNode node){
		if(!node.getTileData().equals(new TileData())){
			TileLayerPNode parent = node.getTilePNodeLayer();
			List<TileLocation> selectedLocations = new ArrayList<TileLocation>();
			floodSelect(node, node.getTileData(), node.getTilePNodeLayer().getNodeGrid(), selectedLocations);
			if(!selectedLocations.isEmpty()){
				createSelection(selectedLocations, parent, event.getCamera());
			}
		}
	}
	
	public void mouseClicked(PInputEvent event){
		super.mouseClicked(event);
		//tile flood fill selection
		if(event.isLeftMouseButton()&&!event.isAltDown()&&!event.isControlDown()&&event.getClickCount()==2){
			TilePNode node = tryGrabNode(event.getCanvasPosition(), event.getCamera());
			if(node!=null){
				selectSimilar(event, node);
			}
		}
		//deselect selection
		else if(event.isRightMouseButton()){
			TilePNode node = tryGrabNode(event.getCanvasPosition(), event.getCamera());
			if(node!=null){
				BasePNode base = ((TileLayerPNode) node.getParent()).getBase();
				if(base.getSelection()!=null&&!base.getSelection().isNodeInSelection(node)){
					base.anchorSelectionWithAction(data);
				}
				
			}
		}
	}
	
	public void mousePressed(PInputEvent event){
		super.mousePressed(event);
		//control clicking
		grabbed = tryGrabNode(event.getCanvasPosition(), event.getCamera());
	}
	
	public void mouseReleased(PInputEvent event){
		super.mouseReleased(event);
		//double checking the control click
		if(grabbed==tryGrabNode(event.getCanvasPosition(), event.getCamera())){
			if(event.isLeftMouseButton()&&event.isControlDown()){
				TilePNode node = tryGrabNode(event.getCanvasPosition(), event.getCamera());
				if(node!=null){
					BasePNode base = ((TileLayerPNode) node.getParent()).getBase();
					if(base.getSelection() instanceof ImmovableTileSelection){
						ImmovableTileSelection selection = ((ImmovableTileSelection) base.getSelection());
						if(base.getSelection().isNodeInSelection(node)){
							List<TileLocation> locations = Arrays.asList(node.getTileLocation());
							selection.removeFromSelection(locations);
							data.registerEditAction(new RemoveFromSelectionAction(selection, locations));
						}
						else{
							List<TileLocation> locations = Arrays.asList(node.getTileLocation());
							selection.addToSelection(locations);
							data.registerEditAction(new AddToSelectionAction(selection, locations));
						}
					}
					
				}
			}
		}
	}
	
	@Override
	protected boolean shouldStartDragInteraction(PInputEvent event) {
        if (super.shouldStartDragInteraction(event)) {
            return event.isLeftMouseButton()&&!event.isAltDown()&&!event.isControlDown()&&event.getClickCount()==1;
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
		lastNode = startNode = tryGrabNode(event.getCanvasPosition(), event.getCamera());
		if(startNode!=null){
			BasePNode base = startNode.getTilePNodeLayer().getBase();
			if(base.getSelection() instanceof MovableTileSelection&&base.getSelection().isNodeInSelection(startNode)){
				movingSelection = (MovableTileSelection) base.getSelection();
				startLocation = movingSelection.getOffset();
				relativeLocation.set(startNode.getGridX()-startLocation.gridX, startNode.getGridY()-startLocation.gridY);
				state = MOVING;
			}
			else{
				base.anchorSelectionWithAction(data);
				state = SELECTING;
			}
		}
		
	}
	
	
	@Override
	protected void endDrag(PInputEvent event){
		super.endDrag(event);
		if(state==SELECTING){
			if(startNode!=null&&lastNode!=null&&outlineBoxNode!=null){
				int startX = Math.min(startNode.getGridX(), lastNode.getGridX());
				int startY = Math.min(startNode.getGridY(), lastNode.getGridY());
				int endX = Math.max(startNode.getGridX(), lastNode.getGridX());
				int endY = Math.max(startNode.getGridY(), lastNode.getGridY());
				int width = endX - startX + 1;
				int height = endY - startY + 1;
				TileLayerPNode parent = startNode.getTilePNodeLayer();
				List<TileLocation> selectedLocations = new ArrayList<TileLocation>();
				for(int i = 0; i < width; i++){
					for(int j = 0; j < height; j++){
						System.out.println(i+startX + ", " + (j+startY));
						selectedLocations.add(new TileLocation(i+startX, j+startY));
					}
				}
				createSelection(selectedLocations, parent, event.getCamera());
			}
			else{
				TilePNode node = tryGrabNode(event.getCanvasPosition(), event.getCamera());
				if(node!=null){
					BasePNode base = ((TileLayerPNode) node.getParent()).getBase();
					if(base.getSelection()!=null&&!base.getSelection().isNodeInSelection(node)){
						base.anchorSelectionWithAction(data);
					}
					
				}
			}
		}
		else if(state==MOVING){
			if(movingSelection!=null)
				data.registerEditAction(new MoveSelectionAction(startLocation, movingSelection.getOffset(), movingSelection));
		}
		startNode = null;
		lastNode = null;
		movingSelection = null;
		state = 0;
		if(outlineBoxNode!=null){
			outlineBoxNode.removeFromParent();
			outlineBoxNode = null;
		}
	}
	
	protected void drag(PInputEvent event) {
        super.drag(event);
        if(startNode!=null){
        	TilePNode currentNode = tryGrabNode(event.getCanvasPosition(), event.getCamera());
        	if(state==SELECTING){
        		if(currentNode!=null&&currentNode.getParent()==startNode.getParent()){
        			lastNode = currentNode;
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
            		}
        		}
        	}
        	else if(state==MOVING){
        		if(currentNode!=null){
        			movingSelection.setToOffset(new TileLocation(currentNode.getGridX()-relativeLocation.gridX, currentNode.getGridY()-relativeLocation.gridY));
        			currentNode.getTilePNodeLayer().getBase().repaint();
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
//        else{
//        	if(p instanceof TileLayerPNode){
//        		System.out.println(p.getWidth() + ", " + p.getHeight());
//        	}
//        	System.out.println(p.getClass());
//        }
		return null;
	}

}
