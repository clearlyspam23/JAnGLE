package com.clearlyspam23.GLE.test;

import javax.swing.SwingUtilities;

import org.piccolo2d.PNode;
import org.piccolo2d.event.PMouseWheelZoomEventHandler;
import org.piccolo2d.extras.PFrame;
import org.piccolo2d.nodes.PText;
 
/**
 * This program demonstrates how to create zoomable text component
 * using Piccolo2D framework.
 * @author www.codejava.net
 *
 */
public class ZoomableTextDemo extends PFrame {
 
    public ZoomableTextDemo() {
        super("Zoomable Text Demo", false, null);
        setSize(480, 320);
        setLocationRelativeTo(null);
        getCanvas().removeInputEventListener(getCanvas().getZoomEventHandler());
        
        // disable panning
        //getCanvas().removeInputEventListener(getCanvas().getPanEventHandler());
        getCanvas().addInputEventListener(new PMouseWheelZoomEventHandler());
        //getCanvas().addInputEventListener(new ScrollZoom());
    }
     
    public void initialize() {
        PNode textNode = new PText("Hello CodeJava!");     
        textNode.setX(getCanvas().getWidth()/2-textNode.getWidth()/2);
        textNode.setY(getCanvas().getHeight()/2-textNode.getHeight()/2);
        textNode.addInputEventListener(new ScrollZoom());
        getCanvas().getLayer().addChild(textNode);
    }
 
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
 
            @Override
            public void run() {
                new ZoomableTextDemo().setVisible(true);
            }
        });
    }
}
