package com.clearlyspam23.GLE.GUI.util;

import org.piccolo2d.PNode;

public abstract class AnimatedPNode extends PNode implements Animatable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public AnimatedPNode() {
		super();
		Animatable.registerAnimation(this, this);
	}
	

	public AnimatedPNode(String newName) {
		super(newName);
		Animatable.registerAnimation(this, this);
	}

}
