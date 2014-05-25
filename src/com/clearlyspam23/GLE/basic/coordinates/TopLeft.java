package com.clearlyspam23.GLE.basic.coordinates;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;

import com.clearlyspam23.GLE.CoordinateSystem;

public class TopLeft extends CoordinateSystem {
	
	private Icon img;
	
	public TopLeft()
	{
		img = null;
		try {
		    img = new ImageIcon(ImageIO.read(new File("images/TopLeft.png")));
		} catch (IOException e) {
		}
	}

	@Override
	public String getName() {
		return "Top Left";
	}

	@Override
	public Icon getHelperIcon() {
		return img;
	}

}
