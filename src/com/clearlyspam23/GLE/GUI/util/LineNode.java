package com.clearlyspam23.GLE.GUI.util;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.SwingUtilities;

import org.piccolo2d.PNode;
import org.piccolo2d.extras.PFrame;
import org.piccolo2d.util.PPaintContext;

public class LineNode extends PNode {
	
	public static void main(String[] args){
		SwingUtilities.invokeLater(new Runnable() {
			 
            @Override
            public void run() {
                PFrame frame = new PFrame();
                LineNode test = new LineNode(new BasicStroke(1));
                test.setLines(Arrays.asList(new Line2D.Double(0, 0, 0, 50)));
                LineNode test2 = new LineNode(new BasicStroke(1));
                test2.setLines(Arrays.asList(new Line2D.Double(0, 0, 50, 0)));
                frame.getCanvas().getLayer().addChild(test);
                frame.getCanvas().getLayer().addChild(test2);
            }
        });
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Line2D> lines = new ArrayList<Line2D>();
	private Line2D drawnLine = new Line2D.Double();
	private Stroke stroke;
	
	public LineNode(Stroke stroke){
		this.stroke = stroke;
		setPaint(Color.BLACK);
	}
	
	public LineNode(Stroke stroke, List<Line2D> lines){
		this.stroke = stroke;
		setPaint(Color.BLACK);
		setLines(lines);
	}
	
	public void setLines(List<Line2D> lines){
		this.lines.clear();
		if(lines==null||lines.isEmpty())
			return;
//		double minX = Double.MAX_VALUE;
//		double minY = Double.MAX_VALUE;
		double maxX = Double.MIN_VALUE;
		double maxY = Double.MIN_VALUE;
		for(Line2D l : lines){
//			minX = Math.min(minX, Math.min(l.getX1(), l.getX2()));
//			minY = Math.min(minY, Math.min(l.getY1(), l.getY2()));
			maxX = Math.max(maxX, Math.max(l.getX1(), l.getX2()));
			maxY = Math.max(maxY, Math.max(l.getY1(), l.getY2()));
		}
		for(Line2D l : lines){
			this.lines.add(new Line2D.Double((l.getX1())/maxX, (l.getY1())/maxY, (l.getX2())/maxX, (l.getY2())/maxY));
		}
		this.repaint();
	}
	
	protected void paint(PPaintContext paintContext) {
		//super.paint(paintContext);
		
		Graphics2D g2 = paintContext.getGraphics();
		g2.setPaint(this.getPaint());
        
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setStroke(stroke);
        
        for(Line2D l : lines){
        	drawnLine.setLine(l.getX1()*getWidth()+getX(), l.getY1()*getHeight()+getY(), l.getX2()*getWidth()+getX(), l.getY2()*getHeight()+getY());
        	g2.draw(drawnLine);
        }
    }

}
