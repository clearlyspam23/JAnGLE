package com.clearlyspam23.GLE.basic.layers.tile.gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Line2D;

import javax.swing.SwingUtilities;

import org.piccolo2d.PCamera;
import org.piccolo2d.PNode;
import org.piccolo2d.activities.PActivity;
import org.piccolo2d.extras.PFrame;
import org.piccolo2d.util.PPaintContext;

import com.clearlyspam23.GLE.GUI.util.FixedWidthStroke;

public class AnimatedOutlineRect extends PNode {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private FixedWidthStroke[] strokes = new FixedWidthStroke[12];
	private int state = 0;
	private Line2D gridLine = new Line2D.Double();
	private AnimationActivity activity = new AnimationActivity();
	
	public static void main(String[] args){
		SwingUtilities.invokeLater(new Runnable() {
			 
            @Override
            public void run() {
                PFrame frame = new PFrame();
                AnimatedOutlineRect rect = new AnimatedOutlineRect(frame.getCanvas().getCamera());
                rect.setBounds(0, 0, 200, 200);
                frame.getCanvas().getLayer().addChild(rect);
                frame.getCanvas().getRoot().addActivity(rect.getAnimationActivity());
            }
        });
	}
	
	private class AnimationActivity extends PActivity{

		public AnimationActivity() {
			super(-1, 100);
		}
		
		protected void activityStep(long elapsedTime) {
            super.activityStep(elapsedTime);
                            
            incrementState();
            repaint();
    }
		
	}
	
	public AnimatedOutlineRect(PCamera camera){
		for(int i =0 ; i < strokes.length; i++){
			strokes[i] = new FixedWidthStroke(2, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER, 1.0f, new float[]{6, 6}, i, camera);
		}
	}
	
	public void incrementState(){
		state = (state+1)%strokes.length;
	}
	
	public PActivity getAnimationActivity(){
		return activity;
	}
	
	protected void paint(PPaintContext paintContext) {

        Graphics2D g2 = paintContext.getGraphics();
        g2.setBackground(new Color(0, 0, 0, 0));
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setStroke(strokes[state]);
        g2.setPaint(Color.BLACK);
        
        gridLine.setLine(getX(), getY(), getX()+getWidth(), getY());
        g2.draw(gridLine);
        gridLine.setLine(getX()+getWidth(), getY(), getX()+getWidth(), getY()+getHeight());
        g2.draw(gridLine);
        gridLine.setLine(getX()+getWidth(), getY()+getHeight(), getX(), getY()+getHeight());
        g2.draw(gridLine);
        gridLine.setLine(getX(), getY()+getHeight(), getX(), getY());
        g2.draw(gridLine);

        g2.setStroke(new BasicStroke(0));
    }

}
