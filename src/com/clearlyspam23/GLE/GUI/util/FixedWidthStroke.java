package com.clearlyspam23.GLE.GUI.util;

import org.piccolo2d.PCamera;
import org.piccolo2d.extras.util.PFixedWidthStroke;

public class FixedWidthStroke extends PFixedWidthStroke {
	
	private PCamera camera;

	public FixedWidthStroke(PCamera cam) {
		camera = cam;
	}

	public FixedWidthStroke(float width, int cap, int join, float miterlimit,
			float[] dash, float dashPhase, PCamera cam) {
		super(width, cap, join, miterlimit, dash, dashPhase);
		camera = cam;
	}

	public FixedWidthStroke(float width, int cap, int join, float miterlimit, PCamera cam) {
		super(width, cap, join, miterlimit);
		camera = cam;
	}

	public FixedWidthStroke(float width, int cap, int join, PCamera cam) {
		super(width, cap, join);
		camera = cam;
	}

	public FixedWidthStroke(float width, PCamera cam) {
		super(width);
		camera = cam;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected float getActiveScale(){
		return (float) camera.getViewScale();
	}

}
