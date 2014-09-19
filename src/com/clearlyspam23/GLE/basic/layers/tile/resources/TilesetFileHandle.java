package com.clearlyspam23.GLE.basic.layers.tile.resources;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.clearlyspam23.GLE.resources.ResourceLoader;
import com.clearlyspam23.GLE.resources.ResourceManager;

public class TilesetFileHandle implements ResourceLoader<Tileset>, TilesetHandle{
	
	private int tileWidth;
	private int tileHeight;
	private int tileXSpacing;
	private int tileYSpacing;
	private String name;
	private String filename;
	
	public TilesetFileHandle(){
		
	}
	
	public Image[][] getTileset() {
		return ResourceManager.get().getResource(filename, Tileset.class, this).getTileset();
	}
	
	public TilesetFileHandle(String name, String filename, int tileWidth, int tileHeight){
		this(name, filename, tileWidth, tileHeight, 0, 0);
	}
	
	public TilesetFileHandle(String name, String filename, int tileWidth, int tileHeight, int xSpacing, int ySpacing){
		this.name = name;
		this.filename = filename;
		this.tileWidth = tileWidth;
		this.tileHeight = tileHeight;
		this.tileXSpacing = xSpacing;
		this.tileYSpacing = ySpacing;
	}

	@Override
	public Tileset loadResource(File file) throws IOException {
		Image[][] tiles = null;
		try {
			BufferedImage temp  = ImageIO.read(file);
			tiles = new Image[temp.getWidth()/getTileWidth()][temp.getHeight()/getTileHeight()];
			for(int i = 0; i < tiles.length; i++)
			{
				for(int j = 0; j < tiles[i].length; j++)
				{
					tiles[i][j] = temp.getSubimage(getTileWidth()*i, getTileHeight()*j, getTileWidth(), getTileHeight());
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new Tileset(tiles);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}
	
	public Image getTileAt(int x, int y){
		return ResourceManager.get().getResource(filename, Tileset.class, this).getTileAt(x, y);
	}
	
	public Image getTileByIndex(int index){
		return ResourceManager.get().getResource(filename, Tileset.class, this).getTileByIndex(index);
	}
	
	public int getXFromIndex(int index){
		return ResourceManager.get().getResource(filename, Tileset.class, this).getXFromIndex(index);
	}
	
	public int getYFromIndex(int index){
		return ResourceManager.get().getResource(filename, Tileset.class, this).getYFromIndex(index);
	}
	
	public boolean isValidLocation(int x, int y){
		return ResourceManager.get().getResource(filename, Tileset.class, this).isValidLocation(x, y);
	}
	
	public int getIndex(int x, int y){
		return ResourceManager.get().getResource(filename, Tileset.class, this).getIndex(x, y);
	}

	public int getTileWidth() {
		return tileWidth;
	}

	public void setTileWidth(int tileWidth) {
		this.tileWidth = tileWidth;
	}

	public int getTileHeight() {
		return tileHeight;
	}

	public void setTileHeight(int tileHeight) {
		this.tileHeight = tileHeight;
	}
	
	public int getWidth(){
		return ResourceManager.get().getResource(filename, Tileset.class, this).getWidth();
	}
	
	public int getHeight(){
		return ResourceManager.get().getResource(filename, Tileset.class, this).getHeight();
	}

	public int getTileXSpacing() {
		return tileXSpacing;
	}

	public void setTileXSpacing(int tileXSpacing) {
		this.tileXSpacing = tileXSpacing;
	}

	public int getTileYSpacing() {
		return tileYSpacing;
	}

	public void setTileYSpacing(int tileYSpacing) {
		this.tileYSpacing = tileYSpacing;
	}
	
	public String toString(){
		return name;
	}

}
