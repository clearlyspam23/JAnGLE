package com.clearlyspam23.GLE.basic.layers.tile.gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Line2D;

import javax.swing.SwingUtilities;

import org.piccolo2d.PCamera;
import org.piccolo2d.activities.PActivity;
import org.piccolo2d.extras.PFrame;
import org.piccolo2d.util.PPaintContext;

import com.clearlyspam23.GLE.GUI.util.AnimatedPNode;
import com.clearlyspam23.GLE.GUI.util.FixedWidthStroke;

public class AnimatedOutlineRect extends AnimatedPNode {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final int RIGHT = 1;
	public static final int LEFT = 2;
	public static final int TOP = 4;
	public static final int BOTTOM = 8;
	public static final int ALL = RIGHT + LEFT + TOP + BOTTOM;
	public static final int LEFT_AND_RIGHT = LEFT | RIGHT;
	public static final int TOP_AND_BOTTOM = TOP | BOTTOM;
	public static final int NONE = 0;
	
	private int drawMask;
	
	private FixedWidthStroke[] strokes = new FixedWidthStroke[12];
	private int state = 0;
	private Line2D gridLine = new Line2D.Double();
	private AnimationActivity activity = new AnimationActivity();
	
	public static void main(String[] args){
		SwingUtilities.invokeLater(new Runnable() {
			 
            @Override
            public void run() {
                PFrame frame = new PFrame();
                AnimatedOutlineRect rect = new AnimatedOutlineRect(frame.getCanvas().getCamera(), LEFT+RIGHT+TOP);
                AnimatedOutlineRect botRect = new AnimatedOutlineRect(frame.getCanvas().getCamera(), LEFT+RIGHT+BOTTOM);
                rect.setBounds(0, 0, 200, 200);
                botRect.setBounds(0, 200, 200, 200);
                frame.getCanvas().getLayer().addChild(rect);
                frame.getCanvas().getLayer().addChild(botRect);
            }
        });
	}
	
	public class AnimationActivity extends PActivity{

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
		this(camera, ALL);
	}
	
	public AnimatedOutlineRect(PCamera camera, int drawOptions){
		for(int i =0 ; i < strokes.length; i++){
			strokes[i] = new FixedWidthStroke(2, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER, 1.0f, new float[]{6, 6}, i, camera);
		}
		drawMask = drawOptions;
	}
	
	public void setDrawRight(boolean flag){
		drawMask&=~RIGHT;
		if(flag)
			drawMask|=RIGHT;
	}
	
	public boolean isDrawingRight(){
		return (drawMask&RIGHT)==RIGHT;
	}
	
	public void setDrawLeft(boolean flag){
		drawMask&=~LEFT;
		if(flag)
			drawMask|=LEFT;
	}
	
	public boolean isDrawingLeft(){
		return (drawMask&LEFT)==LEFT;
	}
	
	public void setDrawTop(boolean flag){
		drawMask&=~TOP;
		if(flag)
			drawMask|=TOP;
	}
	
	public boolean isDrawingTop(){
		return (drawMask&TOP)==TOP;
	}
	
	public void setDrawBottom(boolean flag){
		drawMask&=~BOTTOM;
		if(flag)
			drawMask|=BOTTOM;
	}
	
	public boolean isDrawingBottom(){
		return (drawMask&BOTTOM)==BOTTOM;
	}
	
	public void setDrawMask(int mask){
		drawMask = mask;
	}
	
	public void incrementState(){
		state = (state+1)%strokes.length;
	}
	
	protected void paint(PPaintContext paintContext) {

        Graphics2D g2 = paintContext.getGraphics();
        g2.setBackground(new Color(0, 0, 0, 0));
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setStroke(strokes[state]);
        g2.setPaint(Color.BLACK);
        
        if(isDrawingTop()){
        	gridLine.setLine(getX(), getY(), getX()+getWidth(), getY());
            g2.draw(gridLine);
        }
        if(isDrawingRight()){
        	gridLine.setLine(getX()+getWidth(), getY(), getX()+getWidth(), getY()+getHeight());
            g2.draw(gridLine);
        }
        if(isDrawingBottom()){
        	gridLine.setLine(getX()+getWidth(), getY()+getHeight(), getX(), getY()+getHeight());
            g2.draw(gridLine);
        }
        if(isDrawingLeft()){
        	 gridLine.setLine(getX(), getY()+getHeight(), getX(), getY());
        	 g2.draw(gridLine);
        }
        g2.setStroke(new BasicStroke(0));
    }

	@Override
	public PActivity getActivity() {
		return activity;
	}

}
