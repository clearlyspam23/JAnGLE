package com.clearlyspam23.GLE.basic.layers.tile.gui;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import org.piccolo2d.nodes.PImage;

import com.clearlyspam23.GLE.basic.layers.tile.Tile;
import com.clearlyspam23.GLE.basic.layers.tile.TileData;
import com.clearlyspam23.GLE.basic.layers.tile.TileLocation;
import com.clearlyspam23.GLE.basic.layers.tile.TilesetHandle;

public class TilePNode extends PImage {
	
	public static interface TileChangeListener{
		
		public void onChange(TilePNode changedNode, TileData previous, TileData next);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Tile tile;
	
//	private TilesetHandle currentTileset;
//	private int tilesetX;
//	private int tilesetY;
//	private int gridX;
//	private int gridY;
//	
//	private TileLocation offset;
	
	private boolean silentlyIgnoreInput;
	
	private List<TileChangeListener> listeners = new ArrayList<TileChangeListener>();
	
	public TilePNode(){
		this(null, -1, -1, new TileLocation());
	}
	
	public TilePNode(TilesetHandle set, int x, int y){
		this(set, x, y, new TileLocation());
	}
	
	public TilePNode(TilesetHandle set, int x, int y, TileLocation offset){
		this(new Tile(set, x, y, new TileLocation(), offset));
	}
	
	public TilePNode(Tile t){
		tile = t.copyTile();
		if(tile.isValid())
			setImage(tile.getTileImage());
	}
	
	public boolean setTileset(TilesetHandle set, int x, int y)
	{
		if(silentlyIgnoreInput)
			return false;
		TileData prev = getTileData();
		TileData curr = new TileData(set, x, y);
		if(!prev.equals(curr)){
			tile.setTileset(set, x, y);
			if(tile.isValid())
				setImage(tile.getTileImage());
			for(TileChangeListener l : listeners)
				l.onChange(this, prev, curr);
			return true;
		}
		return false;
	}
	
	public boolean setTilesetHard(TilesetHandle set, int x, int y){
		boolean b = silentlyIgnoreInput;
		silentlyIgnoreInput  = false;
		boolean out = setTileset(set, x, y);
		silentlyIgnoreInput = b;
		return out;
	}
	
	public boolean setTileset(TileData tile){
		return setTileset(tile.tileset, tile.tileX, tile.tileY);
	}
	
	public boolean resetTileset(){
		if(silentlyIgnoreInput)
			return false;
		TileData prev = getTileData();
		if(prev!=new TileData()){
			tile.resetTileset();
			setImage((Image)null);
			invalidatePaint();
			for(TileChangeListener l : listeners)
				l.onChange(this, prev, getTileData());
			return true;
		}
		return false;
	}
	
	public boolean resetTilesetHard(){
		boolean b = silentlyIgnoreInput;
		silentlyIgnoreInput  = false;
		boolean out = resetTilesetHard();
		silentlyIgnoreInput = b;
		return out;
	}

	public TilesetHandle getTileset() {
		return tile.tileset;
	}

	public int getTilesetX() {
		return tile.tileX;
	}

	public int getTilesetY() {
		return tile.tileY;
	}
	
	public void setGridLocation(int x, int y){
		tile.relativeLocation.set(x, y);
	}
	
	public int getGridX(){
		return tile.getLocation().gridX;
	}
	
	public int getGridY(){
		return tile.getLocation().gridY;
	}
	
	public int getRawGridX(){
		return tile.relativeLocation.gridX;
	}
	
	public int getRawGridy(){
		return tile.relativeLocation.gridY;
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
		TilePNode ans = new TilePNode(tile.copyTile());
		ans.setBounds(getX(), getY(), getWidth(), getHeight());
		return ans;
	}
	
	public TileData getTileData(){
		return tile.copyTileData();
	}
	
	public Tile getTile(){
		return tile.copyTile();
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
	
	public TileLayerPNode getTilePNodeLayer(){
		return (TileLayerPNode) getParent();
	}

}
