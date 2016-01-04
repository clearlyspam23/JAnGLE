package com.clearlyspam23.jangle.handler;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

import com.clearlyspam23.jangle.gui.EntityNode;

public class EntityMoveHandler implements EventHandler<MouseEvent>{
	
	private double relativeX;
	private double relativeY;

	public void handle(MouseEvent event) {
		if(!event.isPrimaryButtonDown()){
			return;
		}
		if(!(event.getSource() instanceof EntityNode)){
			return;
		}
		EntityNode source = (EntityNode) event.getSource();
		if(MouseEvent.MOUSE_PRESSED.equals(event.getEventType())){
			relativeX = source.getX() - event.getSceneX();
			relativeY = source.getY() - event.getSceneY();
		}
		else if(MouseEvent.MOUSE_DRAGGED.equals(event.getEventType())){
			source.setX(event.getSceneX() + relativeX);
			source.setY(event.getSceneY() + relativeY);
		}
	}
	
	

}
