package com.clearlyspam23.GLE.resources;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class ImageResourceLoader implements ResourceLoader<BufferedImage> {

	@Override
	public BufferedImage loadResource(File file) throws IOException {
		return ImageIO.read(file);
	}

}
