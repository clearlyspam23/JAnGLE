package com.clearlyspam23.GLE.defaultcoordinates;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.clearlyspam23.GLE.CoordinateSystem;

public class CenteredDown extends CoordinateSystem {

	private Image img;
	
	public CenteredDown()
	{
		img = null;
		try {
		    img = ImageIO.read(new File("images/CenteredDown.png"));
		} catch (IOException e) {
		}
	}

	@Override
	public String getName() {
		return "Centered (positive Y down)";
	}

	@Override
	public Image getHelperImage() {
		return img;
	}

}
