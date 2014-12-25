package com.clearlyspam23.GLE.GUI.util;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.piccolo2d.PNode;
import org.piccolo2d.activities.PActivity;

public abstract class AnimatedPNode extends PNode implements PropertyChangeListener{
	
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
		if(arg0.getOldValue() instanceof PNode){
			PNode node = ((PNode)arg0.getOldValue());
			if(node.getRoot()!=null)
				node.getRoot().getActivityScheduler().removeActivity(getActivity());
		}
		if(arg0.getNewValue() instanceof PNode){
			PNode node = ((PNode)arg0.getNewValue());
			if(node.getRoot()!=null){
				node.getRoot().addActivity(getActivity());
				if(arg0.getSource()!=this&&arg0.getSource() instanceof PNode)
					((PNode)arg0.getSource()).removePropertyChangeListener(PROPERTY_PARENT, this);
			}
			else
				node.addPropertyChangeListener(PROPERTY_PARENT, this);
		}
	}
	
	public abstract PActivity getActivity();

}
