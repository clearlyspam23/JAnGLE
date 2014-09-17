package com.clearlyspam23.GLE.GUI.util;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;

import javax.swing.Icon;

public class ColorIcon implements Icon {
	
	private Color color;
	private int width;
	private int height;
	
	public ColorIcon(Color color, int width, int height){
		this.color = color;
		this.width = width;
		this.height = height;
	}

	@Override
	public void paintIcon(Component c, Graphics g, int x, int y) {
		g.setColor(color);
		g.drawRect(x, y, width, height);
	}

	@Override
	public int getIconWidth() {
		return width;
	}

	@Override
	public int getIconHeight() {
		return height;
	}

}
