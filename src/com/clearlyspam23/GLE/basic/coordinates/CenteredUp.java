package com.clearlyspam23.GLE.basic.coordinates;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;

import com.clearlyspam23.GLE.template.CoordinateSystem;

public class CenteredUp extends CoordinateSystem{

	private Icon img;
	
	public CenteredUp()
	{
		img = null;
		try {
		    img = new ImageIcon(ImageIO.read(new File("images/CenteredUp.png")));
		} catch (IOException e) {
		}
	}

	@Override
	public String getName() {
		return "Centered (positive Y up)";
	}

	@Override
	public Icon getHelperIcon() {
		return img;
	}

}
