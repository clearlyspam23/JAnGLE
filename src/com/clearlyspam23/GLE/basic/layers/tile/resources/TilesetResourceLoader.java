package com.clearlyspam23.GLE.basic.layers.tile.resources;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import com.clearlyspam23.GLE.basic.layers.tile.TilesetHandle;
import com.clearlyspam23.GLE.resources.ResourceLoader;
import com.clearlyspam23.GLE.resources.ResourceManager;

public class TilesetResourceLoader implements ResourceLoader<TilesetHandle>{

	@Override
	public TilesetHandle loadResource(File file) throws IOException {
		BufferedImage image = ResourceManager.get().getImageResource(file.getPath());
		return null;
	}

}
