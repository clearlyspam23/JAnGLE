package com.clearlyspam23.GLE;

import java.util.ArrayList;
import java.util.List;

import org.piccolo2d.PNode;

public abstract class LayerNode<T extends EditAction> extends PNode {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public abstract T getCopy();
	
	public abstract void setToCopy(T copy);
	
	private List<LayerNodeListener> listeners = new ArrayList<LayerNodeListener>();
	
	public void addListener(LayerNodeListener l){
		listeners.add(l);
	}
	
	public void registerChange(){
		for(LayerNodeListener l : listeners)
			l.onChange(this);
	}
	
	

}
