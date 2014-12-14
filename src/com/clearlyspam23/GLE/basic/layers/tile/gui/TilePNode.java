package com.clearlyspam23.GLE.basic.layers.tile.gui;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import org.piccolo2d.nodes.PImage;

import com.clearlyspam23.GLE.basic.layers.tile.Tile;
import com.clearlyspam23.GLE.basic.layers.tile.TilesetHandle;

public class TilePNode extends PImage {
	
	public static interface TileChangeListener{
		
		public void onChange(TilePNode changedNode, Tile previous, Tile next);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private TilesetHandle currentTileset;
	private int tilesetX;
	private int tilesetY;
	private int gridX;
	private int gridY;
	
	private boolean silentlyIgnoreInput;
	
	private List<TileChangeListener> listeners = new ArrayList<TileChangeListener>();
	
	public TilePNode(){
		tilesetX = -1;
		tilesetY = -1;
	}
	
	public TilePNode(TilesetHandle set, int x, int y){
		setTileset(set, x, y);
	}
	
	public void setTileset(TilesetHandle set, int x, int y)
	{
		if(silentlyIgnoreInput)
			return;
		Tile prev = getTile();
		currentTileset = set;
		tilesetX = x;
		tilesetY = y;
		if(currentTileset!=null&&currentTileset.isValidLocation(tilesetX, tilesetY))
			setImage(currentTileset.getTileAt(tilesetX, tilesetY));
		Tile curr = getTile();
		if(!prev.equals(curr))
			for(TileChangeListener l : listeners)
				l.onChange(this, prev, curr);
	}
	
	public void setTilesetHard(TilesetHandle set, int x, int y){
		boolean b = silentlyIgnoreInput;
		silentlyIgnoreInput  = false;
		setTileset(set, x, y);
		silentlyIgnoreInput = b;
	}
	
	public void setTileset(Tile tile){
		setTileset(tile.tileset, tile.tileX, tile.tileY);
	}
	
	public void resetTileset(){
		if(silentlyIgnoreInput)
			return;
		Tile prev = getTile();
		currentTileset = null;
		tilesetX = -1;
		tilesetY = -1;
		setImage((Image)null);
		invalidatePaint();
		Tile curr = getTile();
		if(!prev.equals(curr))
			for(TileChangeListener l : listeners)
				l.onChange(this, prev, curr);
	}
	
	public void resetTilesetHard(){
		boolean b = silentlyIgnoreInput;
		silentlyIgnoreInput  = false;
		resetTilesetHard();
		silentlyIgnoreInput = b;
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

	public boolean isSilentlyIgnoringInput() {
		return silentlyIgnoreInput;
	}

	public void silentlyIgnoreInput(boolean silentlyIgnoreInput) {
		this.silentlyIgnoreInput = silentlyIgnoreInput;
	}
	
	public void addChangeListener(TileChangeListener l){
		listeners.add(l);
	}
	
	public void removeChangeListener(TileChangeListener l){
		listeners.remove(l);
	}

}
