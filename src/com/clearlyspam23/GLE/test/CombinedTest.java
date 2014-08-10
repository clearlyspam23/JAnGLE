package com.clearlyspam23.GLE.test;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.event.InputEvent;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import org.piccolo2d.PLayer;
import org.piccolo2d.PNode;
import org.piccolo2d.event.PDragSequenceEventHandler;
import org.piccolo2d.event.PInputEvent;
import org.piccolo2d.event.PInputEventFilter;
import org.piccolo2d.event.PMouseWheelZoomEventHandler;
import org.piccolo2d.extras.pswing.PSwingCanvas;
import org.piccolo2d.util.PPaintContext;

public class CombinedTest extends JFrame{
	
	static protected Line2D gridLine = new Line2D.Double();
    static protected Rectangle2D rect = new Rectangle2D.Double();
    static protected Color gridPaint = Color.BLACK;
    static protected double gridSpacing = 20;
    static protected float lineSpacing = (float) (gridSpacing/4);
    static protected Stroke gridStroke = new BasicStroke(1, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER, 1.0f, new float[]{lineSpacing, lineSpacing}, lineSpacing/2);
    static protected Stroke startStroke = new BasicStroke(1, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER, 1.0f, new float[]{lineSpacing/2, lineSpacing}, 0);
	
	public CombinedTest()
	{
		setSize(800, 600);
		final PSwingCanvas canvas = new PSwingCanvas();
		add(canvas, BorderLayout.CENTER);
        final PNode gridNode = new PNode() {
        	 protected void paint(PPaintContext paintContext) {
                 double bx = (getX());
                 double by = (getY());
                 double rightBorder = getX() + getWidth();
                 double bottomBorder = getY() + getHeight();

                 Graphics2D g2 = paintContext.getGraphics();
                 g2.setBackground(new Color(0, 0, 0, 0));
                 g2.draw(rect);
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
        
        gridNode.setBounds(new Rectangle2D.Double(0, 0, 800, 600));
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
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   

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
	}
	
	 public static void main(String[] args) {
	        SwingUtilities.invokeLater(new Runnable() {
	 
	            @Override
	            public void run() {
	                JFrame frame = new JFrame();
	                frame.add(new CombinedPanel());
	                frame.revalidate();
	                frame.setVisible(true);
	            }
	        });
	    }

}
