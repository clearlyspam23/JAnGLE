package com.clearlyspam23.GLE.basic.layers.tile.gui;

import java.awt.Image;

import org.piccolo2d.nodes.PImage;

import com.clearlyspam23.GLE.basic.layers.tile.Tile;
import com.clearlyspam23.GLE.basic.layers.tile.TilesetHandle;

public class TilePNode extends PImage {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private TilesetHandle currentTileset;
	private int tilesetX;
	private int tilesetY;
	private int gridX;
	private int gridY;
	
	public TilePNode(){
		tilesetX = -1;
		tilesetY = -1;
	}
	
	public TilePNode(TilesetHandle set, int x, int y){
		setTileset(set, x, y);
	}
	
	public void setTileset(TilesetHandle set, int x, int y)
	{
		currentTileset = set;
		tilesetX = x;
		tilesetY = y;
		if(currentTileset!=null&&currentTileset.isValidLocation(tilesetX, tilesetY))
			setImage(currentTileset.getTileAt(tilesetX, tilesetY));
	}
	
	public void resetTileset(){
		currentTileset = null;
		tilesetX = -1;
		tilesetY = -1;
		setImage((Image)null);
		invalidatePaint();
	}

	public TilesetHandle getTileset() {
		return currentTileset;
	}

	public int getTilesetX() {
		return tilesetX;
	}

	public int getTilesetY() {
		return tilesetY;
	}
	
	public void setGridLocation(int x, int y){
		gridX = x;
		gridY = y;
	}
	
	public int getGridX(){
		return gridX;
	}
	
	public int getGridy(){
		return gridY;
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
		ans.setGridLocation(gridX, gridY);
		return ans;
	}
	
	public Tile getTile(){
		return new Tile(currentTileset, tilesetX, tilesetY);
	}

}
