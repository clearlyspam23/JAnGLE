package com.clearlyspam23.GLE.basic.coordinates;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;

import com.clearlyspam23.GLE.CoordinateSystem;

public class CenteredDown extends CoordinateSystem {

	private Icon img;
	
	public CenteredDown()
	{
		img = null;
		try {
		    img = new ImageIcon(ImageIO.read(new File("images/CenteredDown.png")));
		} catch (IOException e) {
		}
	}

	@Override
	public String getName() {
		return "Centered (positive Y down)";
	}

	@Override
	public Icon getHelperIcon() {
		return img;
	}

}
