package com.clearlyspam23.GLE.GUI.level;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.InputEvent;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JPanel;

import org.piccolo2d.PNode;
import org.piccolo2d.event.PInputEventFilter;
import org.piccolo2d.event.PMouseWheelZoomEventHandler;
import org.piccolo2d.extras.pswing.PSwingCanvas;
import org.piccolo2d.util.PBounds;

import com.clearlyspam23.GLE.Layer;
import com.clearlyspam23.GLE.Level;
import com.clearlyspam23.GLE.GUI.LayerEditManager;

public class LevelPanel extends JPanel implements ComponentListener, LayerContainer{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4240456098926254073L;
	private final PSwingCanvas canvas;
	private Level level;
	private Frame myFrame;
	private boolean initialResize = true;
	
	private LayerSelectionDialog dialog;
	
	private double currWidth;
	private double currHeight;
	
	private int selectedIndex = -1;
	
	private PNode base;
	
	private List<PNode> layers = new ArrayList<PNode>();
	private List<LayerEditManager> editors = new ArrayList<LayerEditManager>();
	
	private HashMap<LayerEditManager, JDialog> editDialogs = new HashMap<LayerEditManager, JDialog>();
	
	public LevelPanel(Level level, Frame frame)
	{
		myFrame = frame;
		canvas = new PSwingCanvas();
		dialog = new LayerSelectionDialog(frame, level.getLayers(), this);
		dialog.setSize(150, 600);
		
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
			PNode node = l.getLayerGUI();
			layers.add(node);
			editors.add(l.getEditManager());
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
	
	private double calculateStartingScale(){
		Dimension d = getSize();
		double width = level.getWidth();
		double height = level.getHeight();
		System.out.println(d);
		System.out.println(width + ", " + height);
		if(width/d.width>height/d.height){
			//the bigger ratio is horizontal
			double w = d.width*3/4;
			return w/width;
		}
		double h = d.height*3/4;
		return h/height;
	}
	
	public String getLevelName(){
		return level.getName();
	}
	
	public void changeLayer(int index){
		if(selectedIndex>=0){
			LayerEditManager editor = editors.get(selectedIndex);
			canvas.removeInputEventListener(editor);
			editDialogs.get(editor).setVisible(false);
		}
		base.removeAllChildren();
		//setup currentLayer
		selectedIndex = index;
		for(int i = 0; i < index+1; i++){
			base.addChild(layers.get(i));
		}
		LayerEditManager editor = editors.get(selectedIndex);
//		for(PInputEventListener l : currentLayer.getListeners()){
		canvas.addInputEventListener(editor);
//			currentListeners.add(l);
//			canvas.addInputEventListener(l);
//		}
		if(!editDialogs.containsKey(editor)){
			LayerDockingDialog dialog = new LayerDockingDialog(myFrame, editor.getName(), editor);
			editDialogs.put(editor, dialog);
		}
		if(isShowing())
			editDialogs.get(editor).setVisible(true);
//			for(LayerEditorDialog d : currentLayer.getEditors(getFrame())){
//				d.setVisible(true);
	}

	@Override
	public void componentHidden(ComponentEvent arg0) {
		LayerEditManager manager = editors.get(selectedIndex);
		if(editDialogs.containsKey(manager)){
			editDialogs.get(manager).setVisible(false);
		}
		dialog.setVisible(false);
	}
	
	//super hacker way to get this thing's frame, might be temporary
	//it was
//	private Frame getFrame(){
//		return myFrame;
//	}

	@Override
	public void componentMoved(ComponentEvent arg0) {
		
	}

	@Override
	public void componentResized(ComponentEvent arg0) {
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
//		double scale = canvas.getCamera().getViewScale();
//		canvas.getCamera().setViewBounds(new Rectangle2D.Double(x, y, canvas.getWidth(), canvas.getHeight()));
//		canvas.getCamera().scaleAboutPoint(scale, x, y);
//		canvas.getCamera().centerBoundsOnPoint(x, y);
		System.out.println(canvas.getCamera().getViewScale());
		currWidth = canvas.getWidth();
		currHeight = canvas.getHeight();
		if(initialResize){
			double scaling = calculateStartingScale();
			canvas.getCamera().scaleView(calculateStartingScale());
			initialResize = false;
		}
	}

	@Override
	public void componentShown(ComponentEvent arg0) {
		if(!isShowing())
			//lol wut?
			return;
		LayerEditManager manager = editors.get(selectedIndex);
		if(editDialogs.containsKey(manager)){
			editDialogs.get(manager).setVisible(true);
		}
		dialog.setVisible(true);
	}

	@Override
	public List<Layer> getLayers() {
		return level.getLayers();
	}
	
	public void dispose(){
		dialog.dispose();
		for(JDialog d : editDialogs.values()){
			d.dispose();
		}
	}

}
