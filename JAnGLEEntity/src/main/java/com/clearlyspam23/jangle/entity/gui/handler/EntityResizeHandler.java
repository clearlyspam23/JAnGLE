package com.clearlyspam23.jangle.entity.gui.handler;

import javafx.beans.binding.DoubleExpression;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;

import com.clearlyspam23.jangle.entity.gui.EntityHandler;
import com.clearlyspam23.jangle.entity.gui.EntityNode;

public class EntityResizeHandler implements EntityHandler {

    private interface MouseHandler extends EventHandler<MouseEvent> {

    }

    private class XHandler implements MouseHandler {

        private Rectangle entityRectangle;

        private double startX;
        private double originalX;
        private double originalWidth;

        public XHandler(Rectangle entityRectangle) {
            this.entityRectangle = entityRectangle;
        }

        @Override
        public void handle(MouseEvent event) {
            if (!event.isPrimaryButtonDown()) {
                return;
            }
            if (MouseEvent.MOUSE_PRESSED.equals(event.getEventType())) {
                startX = event.getSceneX();
                originalX = entityRectangle.getX();
                originalWidth = entityRectangle.getWidth();
            } else if (MouseEvent.MOUSE_DRAGGED.equals(event.getEventType())) {
                double delta = startX - event.getSceneX();
                double newWidth = originalWidth + delta;
                if (newWidth < 0) {
                    entityRectangle.setX(originalX + originalWidth);
                    entityRectangle.setWidth(0);
                } else {
                    entityRectangle.setX(originalX - delta);
                    entityRectangle.setWidth(newWidth);
                }
            }
        }
    }

    private class YHandler implements MouseHandler {

        private Rectangle entityRectangle;

        private double startY;
        private double originalY;
        private double originalHeight;

        public YHandler(Rectangle entityRectangle) {
            this.entityRectangle = entityRectangle;
        }

        @Override
        public void handle(MouseEvent event) {
            if (!event.isPrimaryButtonDown()) {
                return;
            }
            if (MouseEvent.MOUSE_PRESSED.equals(event.getEventType())) {
                startY = event.getSceneY();
                originalY = entityRectangle.getY();
                originalHeight = entityRectangle.getHeight();
            } else if (MouseEvent.MOUSE_DRAGGED.equals(event.getEventType())) {
                double delta = startY - event.getSceneY();
                double newHeight = originalHeight + delta;
                if (newHeight < 0) {
                    entityRectangle.setY(originalY + originalHeight);
                    entityRectangle.setHeight(0);
                } else {
                    entityRectangle.setY(originalY - delta);
                    entityRectangle.setHeight(newHeight);
                }
            }
        }
    }

    private class WidthHandler implements MouseHandler {

        private Rectangle entityRectangle;

        private double startX;
        private double originalWidth;

        public WidthHandler(Rectangle entityRectangle) {
            this.entityRectangle = entityRectangle;
        }

        @Override
        public void handle(MouseEvent event) {
            if (!event.isPrimaryButtonDown()) {
                return;
            }
            if (MouseEvent.MOUSE_PRESSED.equals(event.getEventType())) {
                startX = event.getSceneX();
                originalWidth = entityRectangle.getWidth();
            } else if (MouseEvent.MOUSE_DRAGGED.equals(event.getEventType())) {
                double delta = startX - event.getSceneX();
                double newWidth = originalWidth - delta;
                if (newWidth < 0) {
                    entityRectangle.setWidth(0);
                } else {
                    entityRectangle.setWidth(newWidth);
                }
            }
        }
    }

    private class HeightHandler implements MouseHandler {

        private Rectangle entityRectangle;

        private double startY;
        private double originalHeight;

        public HeightHandler(Rectangle entityRectangle) {
            this.entityRectangle = entityRectangle;
        }

        @Override
        public void handle(MouseEvent event) {
            if (!event.isPrimaryButtonDown()) {
                return;
            }
            if (MouseEvent.MOUSE_PRESSED.equals(event.getEventType())) {
                startY = event.getSceneY();
                originalHeight = entityRectangle.getHeight();
            } else if (MouseEvent.MOUSE_DRAGGED.equals(event.getEventType())) {
                double delta = startY - event.getSceneY();
                double newHeight = originalHeight - delta;
                if (newHeight < 0) {
                    entityRectangle.setHeight(0);
                } else {
                    entityRectangle.setHeight(newHeight);
                }
            }
        }
    }

    private Rectangle topRect;
    private Rectangle rightRect;
    private Rectangle bottomRect;
    private Rectangle leftRect;
    private Rectangle topLeftRect;
    private Rectangle topRightRect;
    private Rectangle bottomRightRect;
    private Rectangle bottomLeftRect;

    @Override
    public void registerToEntityNode(EntityNode node) {
        Rectangle entityRect = node.getEntityRectangle();
        Group group = new Group();
        topRect =
                generateBaseRect("top", Cursor.N_RESIZE,
                        entityRect.xProperty().add(entityRect.widthProperty().divide(2)),
                        entityRect.yProperty(), new YHandler(entityRect));
        rightRect =
                generateBaseRect("right", Cursor.E_RESIZE,
                        entityRect.xProperty().add(entityRect.widthProperty()), entityRect
                                .yProperty().add(entityRect.heightProperty().divide(2)),
                        new WidthHandler(entityRect));
        bottomRect =
                generateBaseRect("bottom", Cursor.S_RESIZE,
                        entityRect.xProperty().add(entityRect.widthProperty().divide(2)),
                        entityRect.yProperty().add(entityRect.heightProperty()), new HeightHandler(
                                entityRect));
        leftRect =
                generateBaseRect("left", Cursor.W_RESIZE, entityRect.xProperty(), entityRect
                        .yProperty().add(entityRect.heightProperty().divide(2)), new XHandler(
                        entityRect));
        topLeftRect =
                generateBaseRect("top-left", Cursor.NW_RESIZE, entityRect.xProperty(),
                        entityRect.yProperty(), new XHandler(entityRect), new YHandler(entityRect));
        topRightRect =
                generateBaseRect("top-right", Cursor.NE_RESIZE,
                        entityRect.xProperty().add(entityRect.widthProperty()),
                        entityRect.yProperty(), new WidthHandler(entityRect), new YHandler(
                                entityRect));
        bottomRightRect =
                generateBaseRect("bottom-right", Cursor.SE_RESIZE,
                        entityRect.xProperty().add(entityRect.widthProperty()), entityRect
                                .yProperty().add(entityRect.heightProperty()), new WidthHandler(
                                entityRect), new HeightHandler(entityRect));
        bottomLeftRect =
                generateBaseRect("bottom-left", Cursor.SW_RESIZE, entityRect.xProperty(),
                        entityRect.yProperty().add(entityRect.heightProperty()), new XHandler(
                                entityRect), new HeightHandler(entityRect));
        group.getChildren().add(topRect);
        group.getChildren().add(rightRect);
        group.getChildren().add(bottomRect);
        group.getChildren().add(leftRect);
        group.getChildren().add(topLeftRect);
        group.getChildren().add(topRightRect);
        group.getChildren().add(bottomRightRect);
        group.getChildren().add(bottomLeftRect);
        node.getChildren().add(group);
    }

    private Rectangle generateBaseRect(String id, Cursor cursor, DoubleExpression xBinding,
            DoubleExpression yBinding, MouseHandler... handlers) {
        final Rectangle output = new Rectangle(8, 8);
        output.getStyleClass().add("resize-rect");
        output.setId(id);
        output.xProperty().bind(xBinding.subtract(output.widthProperty().divide(2)));
        output.yProperty().bind(yBinding.subtract(output.heightProperty().divide(2)));
        output.setCursor(cursor);
        for (MouseHandler h : handlers) {
            output.addEventHandler(MouseEvent.MOUSE_PRESSED, h);
            output.addEventHandler(MouseEvent.MOUSE_DRAGGED, h);
        }
        return output;
    }

}
