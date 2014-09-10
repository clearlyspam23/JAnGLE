package com.clearlyspam23.GLE.GUI.util;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

import org.piccolo2d.PNode;
import org.piccolo2d.util.PPaintContext;

public class AxisAlignedRectGridNode extends PNode {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Line2D gridLine = new Line2D.Double();
	private Rectangle2D rect = new Rectangle2D.Double();
	private Color gridPaint = Color.BLACK;
	private double gridWidth;
	private double gridHeight;
	private Stroke widthGridStroke;
	private Stroke heightGridStroke;
	
	/**
	 * hacker way to allow toggling grid to work across stuff
	 */
	public static boolean isVisible;
	
	public AxisAlignedRectGridNode(double gridWidth, double gridHeight){
		this(0, 0, gridWidth, gridHeight);
	}
	
	public AxisAlignedRectGridNode(double levelWidth, double levelHeight, double gridWidth, double gridHeight)
	{
		this.gridWidth = gridWidth;
		this.gridHeight = gridHeight;
		float widthLineSpacing = (float) (gridWidth/4);
		float heightLineSpacing = (float) (gridHeight/4);
		widthGridStroke = new BasicStroke(0, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER, 1.0f, new float[]{widthLineSpacing, widthLineSpacing}, widthLineSpacing/2);
		heightGridStroke = new BasicStroke(0, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER, 1.0f, new float[]{heightLineSpacing, heightLineSpacing}, heightLineSpacing/2);
		setPickable(false);
		resize(levelWidth, levelHeight);
	}
	
	public void resize(double width, double height){
		this.setBounds(0, 0, width, height);
		rect.setRect(0, 0, width, height);
	}
	
	protected void paint(PPaintContext paintContext) {
        double bx = (getX());
        double by = (getY());
        double rightBorder = getX() + getWidth();
        double bottomBorder = getY() + getHeight();

        Graphics2D g2 = paintContext.getGraphics();
        g2.setBackground(new Color(0, 0, 0, 0));
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setStroke(widthGridStroke);
        g2.setPaint(gridPaint);

        for (double x = bx+gridWidth; x < rightBorder; x += gridWidth) {
            gridLine.setLine(x, by, x, bottomBorder);
                g2.draw(gridLine);
        }
        
        g2.setStroke(heightGridStroke);

        for (double y = by+gridHeight; y < bottomBorder; y += gridHeight) {
            gridLine.setLine(bx, y, rightBorder, y);
                g2.draw(gridLine);
        }
        g2.setStroke(new BasicStroke(0));
        g2.draw(rect);
    }

}
