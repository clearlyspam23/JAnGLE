package com.clearlyspam23.jangle.entity.gui.handler;

import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;

import com.clearlyspam23.jangle.entity.gui.BasicEntityHandler;

public class EntityMoveHandler extends BasicEntityHandler {

    private double relativeX;
    private double relativeY;

    public EntityMoveHandler() {
        this.registerForEvents(MouseEvent.MOUSE_PRESSED, MouseEvent.MOUSE_DRAGGED);
    }

    public void handleMouseEvent(MouseEvent event) {
        if (!event.isPrimaryButtonDown()) {
            return;
        }
        if (!(event.getSource() instanceof Rectangle)) {
            return;
        }
        Rectangle source = (Rectangle) event.getSource();
        if (MouseEvent.MOUSE_PRESSED.equals(event.getEventType())) {
            relativeX = source.getX() - event.getSceneX();
            relativeY = source.getY() - event.getSceneY();
        } else if (MouseEvent.MOUSE_DRAGGED.equals(event.getEventType())) {
            source.setX(event.getSceneX() + relativeX);
            source.setY(event.getSceneY() + relativeY);
        }
    }



}
