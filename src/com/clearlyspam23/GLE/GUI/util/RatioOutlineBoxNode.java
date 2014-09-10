package com.clearlyspam23.GLE.GUI.util;


public class RatioOutlineBoxNode extends OutlineBoxNode {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public RatioOutlineBoxNode(double width, double height){
		this(width, height, 120);
	}
	
	public RatioOutlineBoxNode(double width, double height, double outlineRatio){
		super(width, height, (float) (Math.min(width, height)/outlineRatio));
	}
	
	public void ratioResize(double width, double height, double outlineRatio){
		resize(width, height, (float) (Math.min(width, height)/outlineRatio));
	}

}
