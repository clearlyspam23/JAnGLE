package com.clearlyspam23.GLE.debug;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.InputEvent;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;

import org.piccolo2d.PLayer;
import org.piccolo2d.PNode;
import org.piccolo2d.event.PInputEventFilter;
import org.piccolo2d.event.PInputEventListener;
import org.piccolo2d.event.PMouseWheelZoomEventHandler;
import org.piccolo2d.extras.pswing.PSwingCanvas;
import org.piccolo2d.util.PBounds;

import com.clearlyspam23.GLE.Template;
import com.clearlyspam23.GLE.basic.layers.tile.TileLayerDefinition;
import com.clearlyspam23.GLE.basic.layers.tile.TileLayerTemplate;
import com.clearlyspam23.GLE.basic.layers.tile.edit.TileLayerEditManager;
import com.clearlyspam23.GLE.level.Layer;
import com.clearlyspam23.GLE.level.Level;

public class TestLevelPanel extends JPanel implements ComponentListener{

//	static protected Line2D gridLine = new Line2D.Double();
//    static protected Rectangle2D rect = new Rectangle2D.Double();
//    static protected Color gridPaint = Color.BLACK;
//    static protected double gridSpacing = 20;
//    static protected float lineSpacing = (float) (gridSpacing/4);
//    static protected Stroke gridStroke = new BasicStroke(1, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER, 1.0f, new float[]{lineSpacing, lineSpacing}, lineSpacing/2);
//    static protected Stroke startStroke = new BasicStroke(1, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER, 1.0f, new float[]{lineSpacing/2, lineSpacing}, 0);
	
	private final PSwingCanvas canvas;
	private Level level;
	
	private double currWidth;
	private double currHeight;
	
	public TestLevelPanel()
	{
		canvas = new PSwingCanvas();
		setLayout(new BorderLayout());
		
		Template t = new Template();
		
		TileLayerDefinition def = new TileLayerDefinition();
		
		TileLayerTemplate template = new TileLayerTemplate(def);
		template.setDefaultGridDimensions(32, 32);
		
		level = new Level(t);
		level.setDimensions(320, 320);
		Layer layer = template.createLayer();
		layer.onResize(level, level.getWidth(), level.getHeight());
		level.addLayer(layer);
		
		add(canvas, BorderLayout.CENTER);
		
		PNode base = new PNode();
		canvas.getLayer().addChild(base);
		
		for(Layer l : level.getLayers())
		{
			base.addChild(l.getLayerGUI());
		}
		//need some way to determine currentLayer, for now this will have to do
		
		Layer<?> currentLayer = level.getLayers().get(level.getLayers().size()-1);
		
		TileLayerEditManager data = new TileLayerEditManager();
//		data.setCurrentTileset(new Tileset(new Image[][]{{tile}}));
//		data.setSelectedIndex(0, 0);
//		for(PInputEventListener l : currentLayer.getListeners())
//			canvas.addInputEventListener(l);
//		
//		for(LayerEditorDialog d : currentLayer.getEditors((Frame)this.getParent())){
//			d.setVisible(true);
//		}
		
		PLayer pLayer = canvas.getLayer();
		
		canvas.getPanEventHandler().setEventFilter(new PInputEventFilter(InputEvent.BUTTON3_MASK));
        canvas.removeInputEventListener(canvas.getZoomEventHandler());
        PMouseWheelZoomEventHandler eh = new PMouseWheelZoomEventHandler();
        eh.zoomAboutMouse();
        eh.setScaleFactor(-0.1);
        canvas.addInputEventListener(eh); 
        addComponentListener(this);
        
//        System.out.println("initial: " + canvas.getWidth() + ", " + canvas.getHeight());
		

        this.setVisible(true);
        this.validate();
	}

	@Override
	public void componentHidden(ComponentEvent arg0) {
		
	}

	@Override
	public void componentMoved(ComponentEvent arg0) {
		
	}

	@Override
	public void componentResized(ComponentEvent arg0) {
//		System.out.println("current: " + canvas.getWidth() + ", " + canvas.getHeight());
		PBounds bounds = canvas.getCamera().getViewBounds();
		double x;
		double y;
		if(currWidth<=0)
			x = level.getWidth()/2-canvas.getWidth()/2;
		else
			x = bounds.x - (canvas.getWidth()-currWidth)/2;
		if(currHeight<=0)
			y = level.getHeight()/2 - canvas.getHeight()/2;
		else
			y = bounds.y - (canvas.getHeight()-currHeight)/2;
		canvas.getCamera().setViewBounds(new Rectangle2D.Double(x, y, canvas.getWidth(), canvas.getHeight()));
		currWidth = canvas.getWidth();
		currHeight = canvas.getHeight();
//		canvas.getCamera().setViewBounds(new Rectangle2D.Double(level.getWidth()/2-canvas.getWidth()/2, level.getHeight()/2-canvas.getHeight()/2, canvas.getWidth(), canvas.getHeight()));
//		System.out.println("location :" + canvas.getCamera().getViewBounds().x + ", " + canvas.getCamera().getViewBounds().y);
	}

	@Override
	public void componentShown(ComponentEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
