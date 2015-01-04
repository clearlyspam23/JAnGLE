package com.clearlyspam23.GLE.GUI.util;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

import org.piccolo2d.PNode;
import org.piccolo2d.PRoot;
import org.piccolo2d.activities.PActivity;

public class PAnimation implements PropertyChangeListener {
	
	private ArrayList<PNode> parentsList = new ArrayList<PNode>();
	private PRoot lastRoot;
	
	private PNode watch;
	private Animatable animatable;
	
	public PAnimation(PNode node, Animatable animatable){
		this.watch = node;
	}

	@Override
	public void propertyChange(PropertyChangeEvent arg0) {
		if(lastRoot!=watch.getRoot()){
			PActivity act = animatable.getAnimationActivity();
			if(lastRoot!=null&&lastRoot.getActivityScheduler().getActivitiesReference().contains(act))
				lastRoot.getActivityScheduler().removeActivity(act);
			lastRoot = watch.getRoot();
			if(lastRoot!=null)
				lastRoot.addActivity(act);
		}
		for(PNode parent : parentsList)
			parent.removePropertyChangeListener(PNode.PROPERTY_PARENT, this);
		parentsList.clear();
		for(PNode node = watch.getParent(); node != null; node = node.getParent()){
			node.addPropertyChangeListener(PNode.PROPERTY_PARENT, this);
		}
	}

}
