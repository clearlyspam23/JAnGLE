package com.clearlyspam23.jangle.layer.gui.handler;

import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import com.clearlyspam23.jangle.layer.gui.LayerNode;

public class MouseMoveHandler extends BasicLayerHandler<MouseEvent> {

    private double startX;
    private double startY;
    private double startTransX;
    private double startTransY;

    public MouseMoveHandler() {
        super(MouseEvent.MOUSE_PRESSED, MouseEvent.MOUSE_DRAGGED);
    }

    @Override
    public void handle(MouseEvent arg0) {
        if (!MouseButton.MIDDLE.equals(arg0.getButton())) {
            return;
        }
        if (!(arg0.getSource() instanceof LayerNode)) {
            return;
        }
        arg0.consume();

        LayerNode source = (LayerNode) arg0.getSource();
        if (MouseEvent.MOUSE_PRESSED.equals(arg0.getEventType())) {
            startX = arg0.getScreenX();
            startY = arg0.getScreenY();
            startTransX = source.getAffine().getTx();
            startTransY = source.getAffine().getTy();
        } else if (MouseEvent.MOUSE_DRAGGED.equals(arg0.getEventType())) {
            source.getAffine().setTx(startTransX + (arg0.getScreenX() - startX));
            source.getAffine().setTy(startTransY + (arg0.getScreenY() - startY));
        }
    }

}
