package com.clearlyspam23.jangle.layer.gui.handler;

import javafx.scene.input.ScrollEvent;

import com.clearlyspam23.jangle.layer.gui.LayerNode;

public class MouseZoomHandler extends BasicLayerHandler<ScrollEvent> {

    private double scrollFactor;

    public MouseZoomHandler(double scrollFactor) {
        super(ScrollEvent.SCROLL);
        this.scrollFactor = scrollFactor;
    }

    @Override
    public void handle(ScrollEvent event) {
        if (!(event.getSource() instanceof LayerNode)) {
            return;
        }
        LayerNode source = (LayerNode) event.getSource();
        double zoomAmount = (event.getDeltaY() > 0 ? scrollFactor : 1 / scrollFactor);
        source.getAffine().appendTranslation(event.getX(), event.getY());
        source.getAffine().appendScale(zoomAmount, zoomAmount);
        source.getAffine().appendTranslation(-event.getX(), -event.getY());
    }

}
