package com.clearlyspam23.jangle.entity.gui;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class EntityNode extends Group {

    private Rectangle entityRect;

    public EntityNode() {

    }

    public EntityNode(double x, double y, double width, double height, Image image) {
        entityRect = new Rectangle(width, height);
        entityRect.setX(x);
        entityRect.setY(y);
        entityRect.setFill(new ImagePattern(image));
        entityRect.getStyleClass().add("entity-node");
        this.getChildren().add(entityRect);
    }

    public void setLocation(double x, double y) {
        setX(x);
        setY(y);
    }

    public void setX(double x) {
        entityRect.setX(x);
    }

    public void setY(double y) {
        entityRect.setY(y);
    }

    public double getX() {
        return entityRect.getX();
    }

    public double getY() {
        return entityRect.getY();
    }

    public Rectangle getEntityRectangle() {
        return entityRect;
    }

    public void addEntityHandler(EntityHandler handler) {
        handler.registerToEntityNode(this);
    }

}
