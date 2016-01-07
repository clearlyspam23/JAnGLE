package com.clearlyspam23.jangle.entity.gui.handler;

import javafx.scene.Cursor;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;

import com.clearlyspam23.jangle.entity.gui.BasicEntityHandler;

public class EntityMoveHandler extends BasicEntityHandler {

    private double relativeX;
    private double relativeY;

    public EntityMoveHandler() {
        this.registerForEvents(MouseEvent.MOUSE_PRESSED, MouseEvent.MOUSE_DRAGGED,
                MouseEvent.MOUSE_RELEASED);
    }

    public void handleMouseEvent(MouseEvent event) {
        if (!MouseButton.PRIMARY.equals(event.getButton())) {
            return;
        }
        if (!(event.getSource() instanceof Rectangle)) {
            return;
        }
        Rectangle source = (Rectangle) event.getSource();
        if (MouseEvent.MOUSE_PRESSED.equals(event.getEventType())) {
            relativeX = source.getX() - event.getSceneX();
            relativeY = source.getY() - event.getSceneY();
            source.setCursor(Cursor.CLOSED_HAND);
        } else if (MouseEvent.MOUSE_DRAGGED.equals(event.getEventType())) {
            source.setX(event.getSceneX() + relativeX);
            source.setY(event.getSceneY() + relativeY);
        } else if (MouseEvent.MOUSE_RELEASED.equals(event.getEventType())) {
            source.setCursor(Cursor.OPEN_HAND);
        }
    }



}
