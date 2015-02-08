package com.clearlyspam23.GLE.GUI.util;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.geom.Rectangle2D;

import org.piccolo2d.PNode;
import org.piccolo2d.util.PPaintContext;

public class OutlineBoxNode extends PNode{
	
	private static final long serialVersionUID = 1L;
	private Stroke stroke;
	private Rectangle2D rect;
	private float strokeWidth;
	
	public OutlineBoxNode(double width, double height, float strokeWidth){
		this.strokeWidth = strokeWidth;
		stroke = new BasicStroke(strokeWidth);
		rect = new Rectangle2D.Double(0, 0, width, height);
		setBounds(0, 0, width, height);
	}
	
	protected void paint(PPaintContext paintContext) {

        Graphics2D g2 = paintContext.getGraphics();
        g2.setPaint(Color.BLACK);
        g2.setBackground(new Color(0, 0, 0, 0));
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setStroke(stroke);
        g2.draw(rect);
    }
	
	public void resize(double width, double height){
		resize(width, height, strokeWidth);
	}
	
	public boolean setBounds(double x, double y, double width, double height){
		if(super.setBounds(x, y, width, height)){
			rect.setRect(0, 0, width, height);
			return true;
		}
		return false;
	}
	
	public void resize(double width, double height, float strokeWidth){
		this.strokeWidth = strokeWidth;
		this.setBounds(0, 0, width, height);
		stroke = new BasicStroke(strokeWidth);
	}

}
