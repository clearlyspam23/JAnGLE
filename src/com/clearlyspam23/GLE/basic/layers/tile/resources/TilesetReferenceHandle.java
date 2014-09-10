package com.clearlyspam23.GLE.basic.layers.tile.resources;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Set;

import com.clearlyspam23.GLE.resources.ResourceLoader;
import com.clearlyspam23.GLE.resources.ResourceManager;

public class TilesetReferenceHandle implements ResourceLoader<TilesetFileHandle>, TilesetHandle{
	
	private String tilesetFile;

	@Override
	public TilesetFileHandle loadResource(File file) throws IOException {
		return null;
	}

	@Override
	public String getName() {
		return getTilesetFileHandle().getName();
	}
	
	@Override
	public String getFilename(){
		return getTilesetFileHandle().getFilename();
	}

	@Override
	public Image getTileAt(int x, int y) {
		return getTilesetFileHandle().getTileAt(x, y);
	}

	@Override
	public Image getTileByIndex(int index) {
		return getTilesetFileHandle().getTileByIndex(index);
	}

	@Override
	public int getXFromIndex(int index) {
		return getTilesetFileHandle().getXFromIndex(index);
	}

	@Override
	public int getYFromIndex(int index) {
		return getTilesetFileHandle().getYFromIndex(index);
	}

	@Override
	public boolean isValidLocation(int x, int y) {
		return getTilesetFileHandle().isValidLocation(x, y);
	}

	@Override
	public int getIndex(int x, int y) {
		return getTilesetFileHandle().getIndex(x, y);
	}

	@Override
	public int getTileWidth() {
		return getTilesetFileHandle().getTileWidth();
	}

	@Override
	public int getTileHeight() {
		return getTilesetFileHandle().getTileHeight();
	}

	@Override
	public int getWidth() {
		return getTilesetFileHandle().getWidth();
	}

	@Override
	public int getHeight() {
		return getTilesetFileHandle().getHeight();
	}

	@Override
	public int getTileXSpacing() {
		return getTilesetFileHandle().getTileXSpacing();
	}

	@Override
	public int getTileYSpacing() {
		return getTilesetFileHandle().getTileYSpacing();
	}
	
	@Override
	public Set<String> getTags() {
		return getTilesetFileHandle().getTags();
	}
	
	private TilesetFileHandle getTilesetFileHandle(){
		return ResourceManager.get().getResource(tilesetFile, TilesetFileHandle.class, this);
	}

}
