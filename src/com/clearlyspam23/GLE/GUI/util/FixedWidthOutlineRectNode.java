package com.clearlyspam23.GLE.GUI.util;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.RenderingHints;
import java.awt.geom.Line2D;

import javax.swing.SwingUtilities;

import org.piccolo2d.PCamera;
import org.piccolo2d.PNode;
import org.piccolo2d.extras.PFrame;
import org.piccolo2d.util.PPaintContext;

public class FixedWidthOutlineRectNode extends PNode {
	
	public static void main(String[] args){
		SwingUtilities.invokeLater(new Runnable() {
			 
            @Override
            public void run() {
                PFrame frame = new PFrame();
                FixedWidthOutlineRectNode rect = new FixedWidthOutlineRectNode(1, frame.getCanvas().getCamera(), LEFT+RIGHT+TOP);
                FixedWidthOutlineRectNode botRect = new FixedWidthOutlineRectNode(1, frame.getCanvas().getCamera(), LEFT+RIGHT+BOTTOM);
                rect.setBounds(0, 0, 200, 200);
                botRect.setBounds(0, 200, 200, 200);
                frame.getCanvas().getLayer().addChild(rect);
                frame.getCanvas().getLayer().addChild(botRect);
            }
        });
	}
	
	public static final int RIGHT = 1;
	public static final int LEFT = 2;
	public static final int TOP = 4;
	public static final int BOTTOM = 8;
	public static final int ALL = RIGHT + LEFT + TOP + BOTTOM;
	public static final int LEFT_AND_RIGHT = LEFT | RIGHT;
	public static final int TOP_AND_BOTTOM = TOP | BOTTOM;
	public static final int NONE = 0;
	
	private static final long serialVersionUID = 1L;
	private FixedWidthStroke stroke;
	private Line2D gridLine = new Line2D.Double();
	private int drawMask;
//	private Rectangle2D rect;
	private Color color;
	
	public FixedWidthOutlineRectNode(float strokeWidth, PCamera camera){
		this(strokeWidth, camera, Color.BLACK, ALL);
	}
	
	public FixedWidthOutlineRectNode(float strokeWidth, PCamera camera, int drawMask){
		this(strokeWidth, camera, Color.BLACK, drawMask);
	}
	
	public FixedWidthOutlineRectNode(float strokeWidth, PCamera camera, Color strokeColor){
		this(strokeWidth, camera, strokeColor, ALL);
	}
	
	public FixedWidthOutlineRectNode(float strokeWidth, PCamera camera, Color strokeColor, int drawMask){
		this(new FixedWidthStroke(strokeWidth, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, camera), strokeColor, drawMask);
	}
	
	public FixedWidthOutlineRectNode(FixedWidthStroke stroke, Color strokeColor, int drawMask){
		this.drawMask = drawMask;
		this.color = strokeColor;
		this.stroke = stroke;
	}
	
	protected void paint(PPaintContext paintContext) {
		
		Graphics2D g2 = paintContext.getGraphics();
        
        g2.setBackground(new Color(0, 0, 0, 0));
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setStroke(stroke);
        Paint p = g2.getPaint();
        g2.setPaint(color);
        
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
        g2.setPaint(p);
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
	
	public void setColor(Color color){
		this.color = color;
	}
	
	public Color getColor(){
		return color;
	}
	
	public void resize(double width, double height){
		this.setBounds(0, 0, width, height);
	}
	
	public FixedWidthStroke getStroke(){
		return stroke;
	}
	
	public void setStroke(FixedWidthStroke stroke){
		this.stroke = stroke;
	}

}
