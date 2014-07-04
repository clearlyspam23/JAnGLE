package com.clearlyspam23.GLE.GUI.util;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.LayoutManager2;

public class AspectRatioLayout implements LayoutManager2 {
	
	private Component component;

	@Override
	public void addLayoutComponent(String arg0, Component arg1) {
		component = arg1;
	}

	@Override
	public void layoutContainer(Container target) {
		if(component==null)
			return;
		Insets insets = target.getInsets();
		double top = insets.top;
		double bottom = target.getHeight() - insets.bottom;
		double left = insets.left;
		double right = target.getWidth() - insets.right;
		Dimension pref = component.getPreferredSize();
		double ratioPref = pref.getWidth()/pref.getHeight();
		double ratioCurr = (right-left)/(bottom-top);
		int width = 0;
		int height = 0;
		if(ratioCurr>ratioPref){
			//this means that y is the smaller dimension, expand as far as we can vertically
			height = (int) Math.round(bottom-top);
			width = (int) Math.round(height*ratioPref);
		}
		else {
			//this means that x is the smaller dimension, expand as far as we can horizontally
			width = (int) Math.round(right-left);
			height = (int) Math.round(width*pref.getHeight()/pref.getWidth());
		}
		component.setBounds((int)((right-left)/2-width/2), (int)((bottom-top)/2-height/2), width, height);
	}

	@Override
	public Dimension minimumLayoutSize(Container arg0) {
		return component.getPreferredSize();
	}

	@Override
	public Dimension preferredLayoutSize(Container arg0) {
		return component.getPreferredSize();
	}

	@Override
	public void removeLayoutComponent(Component arg0) {
		if(arg0==component)
			component = null;

	}

	@Override
	public void addLayoutComponent(Component arg0, Object arg1) {
		component = arg0;
	}

	@Override
	public float getLayoutAlignmentX(Container arg0) {
		return 0.5f;
	}

	@Override
	public float getLayoutAlignmentY(Container arg0) {
		return 0.5f;
	}

	@Override
	public void invalidateLayout(Container arg0) {
		
	}

	@Override
	public Dimension maximumLayoutSize(Container arg0) {
		return new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE);
	}

}
