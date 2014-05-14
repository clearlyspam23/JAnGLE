package com.clearlyspam23.GLE.piccolo;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import org.piccolo2d.PCamera;
import org.piccolo2d.PCanvas;
import org.piccolo2d.PLayer;
import org.piccolo2d.PNode;
import org.piccolo2d.PRoot;
import org.piccolo2d.event.PDragSequenceEventHandler;
import org.piccolo2d.event.PInputEvent;
import org.piccolo2d.extras.PFrame;
import org.piccolo2d.util.PPaintContext;

/**
 * Example of drawing an infinite grid, and providing support for snap to grid.
 */
public class GridExample extends PFrame {

    static protected Line2D gridLine = new Line2D.Double();
    static protected Color gridPaint = Color.BLACK;
    static protected double gridSpacing = 20;
    static protected float lineSpacing = (float) (gridSpacing/4);
    static protected Stroke gridStroke = new BasicStroke(1, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER, 1.0f, new float[]{lineSpacing, lineSpacing}, lineSpacing/2);
    static protected Stroke startStroke = new BasicStroke(1, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER, 1.0f, new float[]{lineSpacing/2, lineSpacing}, 0);

    public GridExample() {
        this(null);
    }

    public GridExample(PCanvas aCanvas) {
        super("GridExample", false, aCanvas);
    }

    public void initialize() {
        PRoot root = getCanvas().getRoot();
        final PCamera camera = getCanvas().getCamera();
        final PLayer gridLayer = new PLayer() {
            protected void paint(PPaintContext paintContext) {
                // make sure grid gets drawn on snap to grid boundaries. And
                // expand a little to make sure that entire view is filled.
                double bx = (getX());
                double by = (getY());
                double rightBorder = getX() + getWidth();
                double bottomBorder = getY() + getHeight();

                Graphics2D g2 = paintContext.getGraphics();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                g2.setStroke(gridStroke);
                g2.setPaint(gridPaint);

                for (double x = bx; x < rightBorder; x += gridSpacing) {
                    gridLine.setLine(x, by, x, bottomBorder);
                        g2.draw(gridLine);
                }

                for (double y = by; y < bottomBorder; y += gridSpacing) {
                    gridLine.setLine(bx, y, rightBorder, y);
                        g2.draw(gridLine);
                }
            }
        };

        // replace standard layer with grid layer.
        root.removeChild(camera.getLayer(0));
        camera.removeLayer(0);
        root.addChild(gridLayer);
        camera.addLayer(gridLayer);

        gridLayer.setBounds(camera.getViewBounds());

        PNode n = new PNode();
        n.setPaint(Color.BLUE);
        n.setBounds(0, 0, 100, 80);

        getCanvas().getLayer().addChild(n);
        //getCanvas().removeInputEventListener(getCanvas().getPanEventHandler());

        // add a drag event handler that supports snap to grid.
        getCanvas().addInputEventListener(new PDragSequenceEventHandler() {

            protected PNode draggedNode;
            protected Point2D nodeStartPosition;

            protected boolean shouldStartDragInteraction(PInputEvent event) {
                if (super.shouldStartDragInteraction(event)) {
                    return event.getPickedNode() != event.getTopCamera() && !(event.getPickedNode() instanceof PLayer);
                }
                return false;
            }

            protected void startDrag(PInputEvent event) {
                super.startDrag(event);
                draggedNode = event.getPickedNode();
                //draggedNode.moveToFront();
                nodeStartPosition = draggedNode.getOffset();
            }

            protected void drag(PInputEvent event) {
                super.drag(event);

                Point2D start = getCanvas().getCamera().localToView((Point2D) getMousePressedCanvasPoint().clone());
                Point2D current = event.getPositionRelativeTo(getCanvas().getLayer());
                Point2D dest = new Point2D.Double();

                dest.setLocation(nodeStartPosition.getX() + (current.getX() - start.getX()), nodeStartPosition.getY()
                        + (current.getY() - start.getY()));

                dest.setLocation(dest.getX() - (dest.getX() % gridSpacing), dest.getY() - (dest.getY() % gridSpacing));

                draggedNode.setOffset(dest.getX(), dest.getY());
            }
        });
    }

    public static void main(String[] args) {
        new GridExample();
    }
}
