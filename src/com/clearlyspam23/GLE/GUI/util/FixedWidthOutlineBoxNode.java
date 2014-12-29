package com.clearlyspam23.GLE.GUI.util;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.geom.Rectangle2D;

import org.piccolo2d.PCamera;
import org.piccolo2d.PNode;
import org.piccolo2d.util.PPaintContext;

public class FixedWidthOutlineBoxNode extends PNode {
	
	private static final long serialVersionUID = 1L;
	private Stroke stroke;
	private Rectangle2D rect;
	private Color color;
	
	public FixedWidthOutlineBoxNode(float strokeWidth, PCamera camera){
		this(0, 0, strokeWidth, camera, Color.BLACK);
	}
	
	public FixedWidthOutlineBoxNode(float strokeWidth, PCamera camera, Color strokeColor){
		this(0, 0, strokeWidth, camera, strokeColor);
	}
	
	public FixedWidthOutlineBoxNode(double width, double height, float strokeWidth, PCamera camera){
		this(width, height, strokeWidth, camera, Color.BLACK);
	}
	
	public FixedWidthOutlineBoxNode(double width, double height, float strokeWidth, PCamera camera, Color color){
		this.color = color;
		stroke = new FixedWidthStroke(strokeWidth, camera);
		rect = new Rectangle2D.Double(0, 0, width, height);
		setBounds(0, 0, width, height);
	}
	
	protected void paint(PPaintContext paintContext) {

        Graphics2D g2 = paintContext.getGraphics();
        g2.setPaint(color);
        g2.setBackground(new Color(0, 0, 0, 0));
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setStroke(stroke);
        g2.draw(rect);
    }
	
	public void setColor(Color color){
		this.color = color;
	}
	
	public Color getColor(){
		return color;
	}
	
	public boolean setBounds(final double x, final double y, final double width, final double height){
		if(super.setBounds(x, y, width, height)){
			rect.setRect(x, y, width, height);
			return true;
		}
		return false;
	}
	
	public void resize(double width, double height){
		this.setBounds(0, 0, width, height);
	}

}
