package com.clearlyspam23.jangle.layer.gui.handler;

import javafx.scene.input.ScrollEvent;

import com.clearlyspam23.jangle.layer.gui.LayerNode;

public class MouseZoomHandler extends BasicLayerHandler<ScrollEvent> {

    private static final double DEFAULT_SCROLL_FACTOR = 1.1;
    private static final double DEFAULT_MAX_ZOOM = 5;
    private static final double DEFAULT_MIN_ZOOM = 1 / DEFAULT_MAX_ZOOM;

    private double scrollFactor;
    private double minZoom;
    private double maxZoom;

    private double currentZoom = 1;

    public MouseZoomHandler() {
        this(DEFAULT_SCROLL_FACTOR, DEFAULT_MIN_ZOOM, DEFAULT_MAX_ZOOM);
    }

    public MouseZoomHandler(double scrollFactor, double minScroll, double maxScroll) {
        super(ScrollEvent.SCROLL);
        this.scrollFactor = scrollFactor;
        this.minZoom = minScroll;
        this.maxZoom = maxScroll;
    }

    @Override
    public void handle(ScrollEvent event) {
        if (!(event.getSource() instanceof LayerNode)) {
            return;
        }
        LayerNode source = (LayerNode) event.getSource();
        double zoomAmount = (event.getDeltaY() > 0 ? scrollFactor : 1 / scrollFactor);
        double tempZoom = currentZoom * zoomAmount;
        if (tempZoom > maxZoom || tempZoom < minZoom) {
            return;
        }
        currentZoom *= zoomAmount;
        source.getAffine().appendTranslation(event.getX(), event.getY());
        source.getAffine().appendScale(zoomAmount, zoomAmount);
        source.getAffine().appendTranslation(-event.getX(), -event.getY());
    }

}
