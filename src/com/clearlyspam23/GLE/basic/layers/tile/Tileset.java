package com.clearlyspam23.GLE.basic.layers.tile;

import java.awt.Image;

public class Tileset {
	
	private Image[][] tileset;
	private int width;
	private int height;
	private String name;
	
	public Tileset(String name, int width, int height){
		setTileset(new Image[width][height]);
		this.name = name;
	}
	
	public Tileset(String name, Image[][] tileset){
		this.setTileset(tileset);
		this.name = name;
	}

	public Image[][] getTileset() {
		return tileset;
	}

	public void setTileset(Image[][] tileset) {
		this.tileset = tileset;
		width = tileset.length;
		if(width>0)
			height = tileset[0].length;
	}
	
	public Image getTileAt(int x, int y){
		return tileset[x][y];
	}
	
	public boolean isValidLocation(int x, int y){
		return x>=0&&x<tileset.length&&y>=0&&y<tileset[x].length;
	}
	
	public int getIndex(int x, int y){
		return y*tileset.length+x;
	}
	
	public int getWidth(){
		return width;
	}
	
	public int getHeight(){
		return height;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
