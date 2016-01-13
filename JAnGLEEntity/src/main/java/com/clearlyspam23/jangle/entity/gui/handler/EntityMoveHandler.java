package com.clearlyspam23.jangle.entity.gui.handler;

import javafx.scene.Cursor;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import com.clearlyspam23.jangle.entity.gui.EntityNode;

public class EntityMoveHandler extends BasicEntityHandler<MouseEvent> {

    private double relativeX;
    private double relativeY;

    public EntityMoveHandler() {
        super(MouseEvent.MOUSE_PRESSED, MouseEvent.MOUSE_DRAGGED, MouseEvent.MOUSE_RELEASED);
    }

    public void handle(MouseEvent event) {
        if (!MouseButton.PRIMARY.equals(event.getButton())) {
            return;
        }
        if (!(event.getSource() instanceof EntityNode)) {
            return;
        }
        EntityNode source = (EntityNode) event.getSource();
        if (MouseEvent.MOUSE_PRESSED.equals(event.getEventType())) {
            relativeX = source.getX() - event.getX();
            relativeY = source.getY() - event.getY();
            source.setCursor(Cursor.CLOSED_HAND);
        } else if (MouseEvent.MOUSE_DRAGGED.equals(event.getEventType())) {
            source.setX(event.getX() + relativeX);
            source.setY(event.getY() + relativeY);
        } else if (MouseEvent.MOUSE_RELEASED.equals(event.getEventType())) {
            source.setCursor(Cursor.OPEN_HAND);
        }
    }



}
