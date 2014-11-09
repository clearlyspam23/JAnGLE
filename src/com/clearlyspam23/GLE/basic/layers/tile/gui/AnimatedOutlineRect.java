package com.clearlyspam23.GLE.basic.layers.tile.gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Line2D;

import org.piccolo2d.PCamera;
import org.piccolo2d.PNode;
import org.piccolo2d.util.PPaintContext;

import com.clearlyspam23.GLE.GUI.util.FixedWidthStroke;

public class AnimatedOutlineRect extends PNode {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private FixedWidthStroke[] strokes = new FixedWidthStroke[6];
	private int state;
	private Line2D gridLine = new Line2D.Double();
	
	public AnimatedOutlineRect(PCamera camera){
		for(int i =0 ; i > strokes.length; i++){
			strokes[i] = new FixedWidthStroke(2, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER, 1.0f, new float[]{6, 6}, i, camera);
		}
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
        
        gridLine.setLine(getX(), getY(), getX()+getWidth(), getY());
        g2.draw(gridLine);
        gridLine.setLine(getX(), getY(), getX(), getY()+getHeight());
        g2.draw(gridLine);
        gridLine.setLine(getX()+getWidth(), getY(), getX()+getWidth(), getY()+getHeight());
        g2.draw(gridLine);
        gridLine.setLine(getX(), getY()+getHeight(), getX()+getWidth(), getY()+getHeight());
        g2.draw(gridLine);

        g2.setStroke(new BasicStroke(0));
    }

}
