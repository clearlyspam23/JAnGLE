package com.clearlyspam23.GLE.defaultcoordinates;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.clearlyspam23.GLE.CoordinateSystem;

public class BottomLeft extends CoordinateSystem {

	private Image img;
	
	public BottomLeft()
	{
		img = null;
		try {
		    img = ImageIO.read(new File("images/BotLeft.png"));
		} catch (IOException e) {
		}
	}

	@Override
	public String getName() {
		return "Bottom Left";
	}

	@Override
	public Image getHelperImage() {
		return img;
	}

}
