package com.clearlyspam23.jangle.entity.gui.handler;

import javafx.geometry.Point2D;
import javafx.scene.Cursor;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.transform.Affine;
import javafx.scene.transform.NonInvertibleTransformException;

import com.clearlyspam23.jangle.entity.gui.EntityLayerNode;
import com.clearlyspam23.jangle.entity.gui.EntityNode;

public class EntityMoveHandler extends BasicEntityHandler<MouseEvent> {

    private double relativeX;
    private double relativeY;

    private Affine layerAffine;

    protected void registerLayer(EntityLayerNode layer) {
        layerAffine = layer.getAffine();
    }

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
        Point2D out;
        try {
            out =
                    layerAffine.inverseDeltaTransform(new Point2D(event.getScreenX(), event
                            .getScreenY()));

        } catch (NonInvertibleTransformException e) {
            // log this at some point?
            return;
        }

        if (MouseEvent.MOUSE_PRESSED.equals(event.getEventType())) {
            relativeX = source.getEntityX() - out.getX();
            relativeY = source.getEntityY() - out.getY();
            source.setCursor(Cursor.CLOSED_HAND);


        } else if (MouseEvent.MOUSE_DRAGGED.equals(event.getEventType())) {
            source.setEntityX(out.getX() + relativeX);
            source.setEntityY(out.getY() + relativeY);
        } else if (MouseEvent.MOUSE_RELEASED.equals(event.getEventType())) {
            source.setCursor(Cursor.OPEN_HAND);
        }
    }



}
