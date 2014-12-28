package com.clearlyspam23.GLE.GUI.util;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

import org.piccolo2d.PNode;
import org.piccolo2d.PRoot;
import org.piccolo2d.activities.PActivity;

public abstract class AnimatedPNode extends PNode implements PropertyChangeListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<PNode> parentsList = new ArrayList<PNode>();
	private PRoot lastRoot;
	
	public AnimatedPNode() {
		super();
		this.addPropertyChangeListener(PROPERTY_PARENT, this);
	}
	

	public AnimatedPNode(String newName) {
		super(newName);
		this.addPropertyChangeListener(PROPERTY_PARENT, this);
	}

	@Override
	public void propertyChange(PropertyChangeEvent arg0) {
		if(lastRoot!=getRoot()){
			PActivity act = getActivity();
			if(lastRoot!=null&&lastRoot.getActivityScheduler().getActivitiesReference().contains(act))
				lastRoot.getActivityScheduler().removeActivity(act);
			lastRoot = getRoot();
			if(lastRoot!=null)
				lastRoot.addActivity(act);
		}
		for(PNode parent : parentsList)
			parent.removePropertyChangeListener(PROPERTY_PARENT, this);
		parentsList.clear();
		for(PNode node = getParent(); node != null; node = node.getParent()){
			node.addPropertyChangeListener(PROPERTY_PARENT, this);
		}
	}
	
	public abstract PActivity getActivity();

}
