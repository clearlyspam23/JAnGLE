package com.clearlyspam23.GLE.GUI;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.InputEvent;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import org.piccolo2d.PNode;
import org.piccolo2d.event.PInputEventFilter;
import org.piccolo2d.event.PInputEventListener;
import org.piccolo2d.event.PMouseWheelZoomEventHandler;
import org.piccolo2d.extras.pswing.PSwingCanvas;
import org.piccolo2d.util.PBounds;

import com.clearlyspam23.GLE.Layer;
import com.clearlyspam23.GLE.LayerNode;
import com.clearlyspam23.GLE.LayerNodeListener;
import com.clearlyspam23.GLE.Level;

public class LevelPanel extends JPanel implements ComponentListener, LayerNodeListener, LayerContainer{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4240456098926254073L;
	private final PSwingCanvas canvas;
	private Level level;
	
	private double currWidth;
	private double currHeight;
	
	private int selectedIndex = -1;
	
	private PNode base;
	
	private List<LayerNode> layers = new ArrayList<LayerNode>();
	private List<PInputEventListener> currentListeners = new ArrayList<PInputEventListener>();
	
	public LevelPanel(Level level)
	{
		canvas = new PSwingCanvas();
		
		this.level = level;
		
		setLayout(new BorderLayout());
		
//		level = new Level(t);
//		level.setDimensions(320, 320);
//		Layer layer = template.createLayer();
//		layer.onResize(level.getWidth(), level.getHeight());
//		level.addLayer(layer);
		
		add(canvas, BorderLayout.CENTER);
		
		base = new PNode();
		canvas.getLayer().addChild(base);
		
		for(Layer l : level.getLayers())
		{
			LayerNode node = l.getLayerGUI();
			layers.add(node);
			base.addChild(node);
		}
		//need some way to determine currentLayer, for now this will have to do
		changeLayer(level.getLayers().size()-1);
		
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
	
	public void changeLayer(int index){
		if(selectedIndex>=0){
			Layer<?> currentLayer = level.getLayers().get(selectedIndex);
			for(PInputEventListener l : currentListeners){
				canvas.removeInputEventListener(l);
			}
			currentListeners.clear();
			for(LayerEditorDialog d : currentLayer.getEditors((Frame)this.getParent())){
				d.setVisible(false);
			}
		}
		base.removeAllChildren();
		//setup currentLayer
		selectedIndex = index;
		for(int i = 0; i < index+1; i++){
			base.addChild(layers.get(i));
		}
		Layer<?> currentLayer = level.getLayers().get(selectedIndex);
		for(PInputEventListener l : currentLayer.getListeners()){
			currentListeners.add(l);
			canvas.addInputEventListener(l);
		}
		for(LayerEditorDialog d : currentLayer.getEditors((Frame)this.getParent())){
			d.setVisible(true);
		}
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
	}

	@Override
	public void componentShown(ComponentEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onChange(LayerNode<?> node) {
		// TODO Auto-generated method stub
		
	}

}
