package com.clearlyspam23.GLE.basic.layers.tile.gui;

import java.awt.Image;

import org.piccolo2d.nodes.PImage;

import com.clearlyspam23.GLE.basic.layers.tile.Tileset;

public class TilePNode extends PImage {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Tileset currentTileset;
	private int tilesetX;
	private int tilesetY;
	
	public TilePNode(){
		tilesetX = -1;
		tilesetY = -1;
	}
	
	public TilePNode(Tileset set, int x, int y){
		setTileset(set, x, y);
	}
	
	public void setTileset(Tileset set, int x, int y)
	{
		currentTileset = set;
		tilesetX = x;
		tilesetY = y;
	}
	
	public void resetTileset(){
		currentTileset = null;
		tilesetX = -1;
		tilesetY = -1;
	}

	public Tileset getTileset() {
		return currentTileset;
	}

	public int getTilesetX() {
		return tilesetX;
	}

	public int getTilesetY() {
		return tilesetY;
	}
	
	@Override
	public void setImage(final Image newImage)
	{
		double x = getX();
		double y = getY();
		double width = getWidth();
		double height = getHeight();
		super.setImage(newImage);
		setBounds(x, y, width, height);
	}
	
	public TilePNode getCopy(){
		TilePNode ans = new TilePNode(currentTileset, tilesetX, tilesetY);
		ans.setBounds(getX(), getY(), getWidth(), getHeight());
		ans.setImage(getImage());
		return ans;
	}

}
