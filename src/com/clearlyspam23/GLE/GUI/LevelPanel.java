package com.clearlyspam23.GLE.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.InputEvent;
import java.awt.geom.Point2D;

import javax.swing.JPanel;

import org.piccolo2d.PLayer;
import org.piccolo2d.PNode;
import org.piccolo2d.event.PDragSequenceEventHandler;
import org.piccolo2d.event.PInputEvent;
import org.piccolo2d.event.PInputEventFilter;
import org.piccolo2d.event.PMouseWheelZoomEventHandler;
import org.piccolo2d.extras.pswing.PSwingCanvas;

import com.clearlyspam23.GLE.GUI.util.GridNode;

public class LevelPanel extends JPanel {

//	static protected Line2D gridLine = new Line2D.Double();
//    static protected Rectangle2D rect = new Rectangle2D.Double();
//    static protected Color gridPaint = Color.BLACK;
//    static protected double gridSpacing = 20;
//    static protected float lineSpacing = (float) (gridSpacing/4);
//    static protected Stroke gridStroke = new BasicStroke(1, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER, 1.0f, new float[]{lineSpacing, lineSpacing}, lineSpacing/2);
//    static protected Stroke startStroke = new BasicStroke(1, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER, 1.0f, new float[]{lineSpacing/2, lineSpacing}, 0);
	
	public LevelPanel()
	{
		final double gridSpacing = 10;
		setLayout(new BorderLayout());
		final PSwingCanvas canvas = new PSwingCanvas();
		add(canvas, BorderLayout.CENTER);
        final PNode gridNode = new GridNode(gridSpacing, gridSpacing, 800, 600);
        
        gridNode.setPickable(false);
        
        PNode base = new PNode();
        
        canvas.getLayer().addChild(base);
        canvas.getLayer().addChild(gridNode);

        PNode n = new PNode();
        n.setPaint(Color.BLUE);
        n.setBounds(0, 0, 100, 80);
        
        PNode n2 = new PNode();
        n2.setPaint(Color.red);
        n2.setBounds(100, 0, 100, 80);
        
        base.addChild(n);
        base.addChild(n2);
        
        canvas.getPanEventHandler().setEventFilter(new PInputEventFilter(InputEvent.BUTTON3_MASK));
        canvas.removeInputEventListener(canvas.getZoomEventHandler());
        PMouseWheelZoomEventHandler eh = new PMouseWheelZoomEventHandler();
        eh.zoomAboutMouse();
        eh.setScaleFactor(-0.1);
        canvas.addInputEventListener(eh); 

        // add a drag event handler that supports snap to grid.
        canvas.addInputEventListener(new PDragSequenceEventHandler() {

            protected PNode draggedNode;
            protected Point2D nodeStartPosition;

            protected boolean shouldStartDragInteraction(PInputEvent event) {
                if (super.shouldStartDragInteraction(event)) {
                    return event.getPickedNode() != event.getTopCamera() && !(event.getPickedNode() instanceof PLayer) && event.isLeftMouseButton();
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

                Point2D start = canvas.getCamera().localToView((Point2D) getMousePressedCanvasPoint().clone());
                Point2D current = event.getPositionRelativeTo(canvas.getLayer());
                Point2D dest = new Point2D.Double();

                dest.setLocation(nodeStartPosition.getX() + (current.getX() - start.getX()), nodeStartPosition.getY()
                        + (current.getY() - start.getY()));

                dest.setLocation(dest.getX() - (dest.getX() % gridSpacing), dest.getY() - (dest.getY() % gridSpacing));

                draggedNode.setOffset(dest.getX(), dest.getY());
            }
        });
        this.setVisible(true);
        this.validate();
	}

}
