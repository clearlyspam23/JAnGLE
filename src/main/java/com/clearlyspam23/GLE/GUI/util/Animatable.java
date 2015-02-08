package com.clearlyspam23.GLE.GUI.util;

import org.piccolo2d.PNode;
import org.piccolo2d.activities.PActivity;

public interface Animatable {
	
	public PActivity getAnimationActivity();
	
	public static void registerAnimation(PNode node, Animatable animatable){
		node.addPropertyChangeListener(PNode.PROPERTY_PARENT, new PAnimation(node, animatable));
	}

}
