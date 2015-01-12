package com.clearlyspam23.GLE.GUI.level;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.InputEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.JDialog;
import javax.swing.JPanel;

import org.piccolo2d.PNode;
import org.piccolo2d.event.PInputEventFilter;
import org.piccolo2d.event.PMouseWheelZoomEventHandler;
import org.piccolo2d.extras.pswing.PSwingCanvas;
import org.piccolo2d.util.PPaintContext;

import com.clearlyspam23.GLE.GUI.util.FixedWidthOutlineRectNode;
import com.clearlyspam23.GLE.edit.LayerEditManager;
import com.clearlyspam23.GLE.level.Layer;
import com.clearlyspam23.GLE.level.Level;
import com.clearlyspam23.GLE.level.LevelChangeListener;

public class LevelPanel extends JPanel implements ComponentListener, LayerContainer, LevelChangeListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4240456098926254073L;
	private final PSwingCanvas canvas;
	private Level level;
	private Frame myFrame;
	private boolean initialResize = true;
	
	private LayerSelectionDialog dialog;
	
//	private double currWidth;
//	private double currHeight;
	
	private int selectedIndex = -1;
	
	private PNode base;
	private PNode background;
	private FixedWidthOutlineRectNode outline;
	
	private List<PNode> layers = new ArrayList<PNode>();
	private List<LayerEditManager<?>> editors = new ArrayList<LayerEditManager<?>>();
	private Map<LayerEditManager<?>, JDialog> editDialogs;
	
	private List<LayerChangeListener> listeners = new ArrayList<LayerChangeListener>();
	
	private static final Color backgroundColor = new Color(200, 200, 240);
	
	private PNode overlayNode;
	
	@SuppressWarnings("rawtypes")
	private Layer currentLayer;
	
	@SuppressWarnings("rawtypes")
	public LevelPanel(Level level, Frame frame, Map<LayerEditManager<?>, JDialog> editDialogs)
	{
		myFrame = frame;
		canvas = new PSwingCanvas();
		canvas.setDefaultRenderQuality(PPaintContext.LOW_QUALITY_RENDERING);
		canvas.setBackground(Color.white);
		
		this.level = level;
		level.addChangeListener(this);
		background = new PNode();
		background.setPaint(backgroundColor);
		background.setBounds(0, 0, level.getWidth(), level.getHeight());
		
		base = new PNode();
		
		outline = new FixedWidthOutlineRectNode(8, canvas.getCamera());
		outline.setBounds(0, 0, level.getWidth(), level.getHeight());
		outline.setPickable(false);
		outline.setChildrenPickable(false);
//		
		
		setLayout(new BorderLayout());
		
		this.editDialogs = editDialogs;
		
//		level = new Level(t);
//		level.setDimensions(320, 320);
//		Layer layer = template.createLayer();
//		layer.onResize(level.getWidth(), level.getHeight());
//		level.addLayer(layer);
		
		add(canvas, BorderLayout.CENTER);
		
		canvas.getLayer().addChild(base);
		base.addChild(background);
		
		for(Layer l : level.getLayers())
		{
			PNode node = l.getLayerGUI();
			layers.add(node);
			editors.add(l.getEditManager());
			base.addChild(node);
		}
		base.addChild(outline);
		//need some way to determine currentLayer, for now this will have to do
		changeLayer(level.getLayers().size()-1);
		
		canvas.getPanEventHandler().setEventFilter(new PInputEventFilter(InputEvent.BUTTON3_MASK));
        canvas.removeInputEventListener(canvas.getZoomEventHandler());
        PMouseWheelZoomEventHandler eh = new PMouseWheelZoomEventHandler();
        eh.zoomAboutMouse();
        eh.setScaleFactor(-0.1);
        canvas.addInputEventListener(eh); 
        addComponentListener(this);
        
        dialog = new LayerSelectionDialog(frame, level.getLayers(), layers, this);
		dialog.setSize(150, 600);
        
//        System.out.println("initial: " + canvas.getWidth() + ", " + canvas.getHeight());
		

        this.setVisible(true);
        this.validate();
	}
	
	private double calculateStartingScale(){
		Dimension d = getSize();
		double width = level.getWidth();
		double height = level.getHeight();
		if(width/d.width>height/d.height){
			//the bigger ratio is horizontal
			double w = d.width*3/4;
			System.out.println(w/width);
			return w/width;
		}
		double h = d.height*3/4;
		System.out.println(h/height);
		return h/height;
	}
	
	public String getLevelName(){
		return level.getName() + (level.needsSave() ? " *" : "");
	}
	
	@SuppressWarnings("rawtypes")
	public Layer getCurrentLayer(){
		if(selectedIndex>=0)
			return level.getLayers().get(selectedIndex);
		return null;
	}
	
	public LayerEditManager<?> getCurrentEditManager(){
		if(selectedIndex>=0)
			return editors.get(selectedIndex);
		return null;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void changeLayer(int index){
		Layer old = null;
		//if the old layer exists, inform it that it is no longer active
		if(selectedIndex>=0){
			old = level.getLayers().get(selectedIndex);
			LayerEditManager editor = editors.get(selectedIndex);
			LayerEditManager nextEditor = editors.get(index);
			editor.removeEditListener(level);
			canvas.removeInputEventListener(editor);
			if(isShowing()){
				if(!editor.equals(nextEditor)){
					editDialogs.get(editor).setVisible(false);
					editor.onInActive(old);
					nextEditor.onActive(level.getLayers().get(index));
				}
				else{
					editor.onLayerChange(old, level.getLayers().get(index));
				}
			}
		}
		//set the current index, and have the all of the other nodes become unpickable
		selectedIndex = index;
		for(int i = 0; i < layers.size(); i++){
			PNode node = layers.get(i);
			if(i==selectedIndex){
				node.setPickable(true);
				node.setChildrenPickable(true);
			}
			else{
				node.setPickable(false);
				node.setChildrenPickable(false);
			}
		}
		currentLayer = level.getLayers().get(selectedIndex);
		//if there was an overlay, remove it
		if(overlayNode!=null)
			base.removeChild(overlayNode);
		//if the new layer has an overlay, show it
		overlayNode = currentLayer.getOverlayGUI();
		if(overlayNode!=null)
			base.addChild(overlayNode);
		//get the edit manager for this layer
		LayerEditManager editor = editors.get(selectedIndex);
		canvas.addInputEventListener(editor);
		editor.addEditListener(level);
		if(!editDialogs.containsKey(editor)){
			LayerDockingDialog dialog = new LayerDockingDialog(myFrame, editor.getName(), editor);
			dialog.setSize(300, 800);
			editDialogs.put(editor, dialog);
		}
		if(isShowing())
			editDialogs.get(editor).setVisible(true);
		for(LayerChangeListener l : listeners){
			l.onLayerChange(old, level.getLayers().get(selectedIndex), this);
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void componentHidden(ComponentEvent arg0) {
		System.out.println("hidden");
		LayerEditManager manager = editors.get(selectedIndex);
		manager.onInActive(level.getLayers().get(selectedIndex));
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
//		PBounds bounds = canvas.getCamera().getViewBounds();
//		double x;
//		double y;
//		if(currWidth<=0)
//			x = level.getWidth()/2-canvas.getWidth()/2;
//		else
//			x = bounds.x - (canvas.getWidth()-currWidth)/2;
//		if(currHeight<=0)
//			y = level.getHeight()/2 - canvas.getHeight()/2;
//		else
//			y = bounds.y - (canvas.getHeight()-currHeight)/2;
//		double scale = canvas.getCamera().getViewScale();
//		canvas.getCamera().setViewBounds(new Rectangle2D.Double(x, y, canvas.getWidth(), canvas.getHeight()));
//		canvas.getCamera().scaleAboutPoint(scale, x, y);
//		canvas.getCamera().centerBoundsOnPoint(x, y);
//		currWidth = canvas.getWidth();
//		currHeight = canvas.getHeight();
		if(initialResize){
//			double scaling = calculateStartingScale();
			canvas.getCamera().scaleView(calculateStartingScale());
			initialResize = false;
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void componentShown(ComponentEvent arg0) {
		if(!isShowing())
			//lol wut?
			return;
		System.out.println("showing");
		LayerEditManager manager = editors.get(selectedIndex);
		manager.onActive(level.getLayers().get(selectedIndex));
		if(editDialogs.containsKey(manager)){
			editDialogs.get(manager).setVisible(true);
		}
		dialog.setVisible(true);
	}

	@SuppressWarnings("rawtypes")
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
	
	public void addChangeLayerListener(LayerChangeListener l){
		listeners.add(l);
	}
	
//	@SuppressWarnings("rawtypes")
//	private float calculateRatio(double width, double height){
//		float min = Float.MAX_VALUE;
//		for(Layer l : level.getLayers()){
//			float f = l.minBorderWidth();
//			if(f>=0)
//				min = Math.min(min, f);
//		}
//		return Math.min(min, (float) Math.min(width, height)/120f);
//	}

	@Override
	public void onResize(Level level, double width, double height) {
		background.setBounds(0, 0, level.getWidth(), level.getHeight());
		outline.resize(width, height);
	}

}
