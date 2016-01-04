package com.clearlyspam23.jangle.gui;

import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import com.clearlyspam23.jangle.handler.EntityMoveHandler;

public class EntityNode extends Rectangle{

	public EntityNode() {
	
	}
	
	public EntityNode(double x, double y, double width, double height, Image image){
		this.setX(x);
		this.setY(y);
		this.setWidth(width);
		this.setHeight(height);
		this.setFill(new ImagePattern(image));
		this.getStyleClass().add("entity-node");
		EntityMoveHandler moveHandler = new EntityMoveHandler();
		this.addEventHandler(MouseEvent.MOUSE_PRESSED, moveHandler);
		this.addEventHandler(MouseEvent.MOUSE_DRAGGED, moveHandler);
	}
	
	public void setLocation(double x, double y){
		this.setX(x);
		this.setY(y);
	}

}
