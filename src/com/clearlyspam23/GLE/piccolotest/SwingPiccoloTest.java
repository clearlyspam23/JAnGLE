package com.clearlyspam23.GLE.piccolotest;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import org.piccolo2d.event.PMouseWheelZoomEventHandler;
import org.piccolo2d.extras.pswing.PSwing;
import org.piccolo2d.extras.pswing.PSwingCanvas;
import org.piccolo2d.nodes.PImage;
 
/**
 * This program demonstrates how to wrap a Swing component
 * to provide zoomable user interfaces using Piccolo2D framework.
 * @author www.codejava.net
 *
 */
public class SwingPiccoloTest extends JFrame {
	
	PImage[][] nodes;
 
    public SwingPiccoloTest() {
        super("Zoomable Swing GUI Demo - Swing Wrapper");
         
//        JPanel panel = new TileLayer();
//        panel.setLayout(new GridLayout(200, 200, 0, 0));
//        panel.enableInputMethods(false);
//        PSwing swingWrapper = new PSwing(panel);
        
        //JButton button = new JButton();
        
        //PSwing wrapper = new PSwing(button);
         
        PSwingCanvas canvas = new PSwingCanvas();
        canvas.removeInputEventListener(canvas.getZoomEventHandler());
        PMouseWheelZoomEventHandler eh = new PMouseWheelZoomEventHandler();
        eh.zoomAboutMouse();
        canvas.addInputEventListener(eh);
        canvas.setBackground(Color.blue);
        nodes = new PImage[20][20];
        for(int i = 0; i < nodes.length; i++)
        {
        	for(int j = 0; j < nodes[i].length; j++)
        	{
        		nodes[i][j] = new PImage();
        		nodes[i][j].setPaint(Color.BLUE);
        		canvas.getLayer().addChild(nodes[i][j]);
        		nodes[i][j].setBounds(i*16, j*16, 16, 16);
        		nodes[i][j].addInputEventListener(new ClickListener(i, j));
        	}
        }
        //canvas.getLayer().addChild(wrapper);
        //canvas.getLayer().addChild(swingWrapper);
         
        add(canvas, BorderLayout.CENTER);
         
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    
        setLocationRelativeTo(null);
    }
 
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
 
            @Override
            public void run() {
                new SwingPiccoloTest().setVisible(true);
            }
        });
    }
}
