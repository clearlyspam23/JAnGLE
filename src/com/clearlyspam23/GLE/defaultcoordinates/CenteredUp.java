package com.clearlyspam23.GLE.defaultcoordinates;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.clearlyspam23.GLE.CoordinateSystem;

public class CenteredUp extends CoordinateSystem{

	private Image img;
	
	public CenteredUp()
	{
		img = null;
		try {
		    img = ImageIO.read(new File("images/CenteredUp.png"));
		} catch (IOException e) {
		}
	}

	@Override
	public String getName() {
		return "Centered (positive Y up)";
	}

	@Override
	public Image getHelperImage() {
		return img;
	}

}
