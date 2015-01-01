package com.clearlyspam23.GLE.basic.layers.tile.gui;

import java.util.ArrayList;
import java.util.List;

import org.piccolo2d.PNode;

import com.clearlyspam23.GLE.basic.layers.tile.Tile;
import com.clearlyspam23.GLE.basic.layers.tile.TileLocation;

public class ImmovableTileBox implements TileBox{
	
	private PNode overlayNode = new PNode();
	
	private List<TileLocation> selectedLocations = new ArrayList<TileLocation>();
	private int width;
	private int height;
	private TileLocation offset;
	private TileLayerPNode tileLayer;
	
	public ImmovableTileBox(List<Tile> locations, TileLayerPNode layer){
		tileLayer = layer;
		tileLayer.silentlyIgnoreInput(true);
		int minX = Integer.MAX_VALUE;
		int maxX = 0;
		int minY = Integer.MAX_VALUE;
		int maxY = 0;
		for(Tile t : locations){
			TileLocation loc = t.getLocation();
			tileLayer.getNodeAt(loc).silentlyIgnoreInput(false);
			minX = Math.min(minX, loc.gridX);
			maxX = Math.max(maxX, loc.gridX);
			minY = Math.min(minY, loc.gridY);
			maxY = Math.max(maxY, loc.gridY);
		}
		offset = new TileLocation(minX, minY);
		for(Tile t : locations){
			TileLocation loc = t.getLocation();
			selectedLocations.add(new TileLocation(loc.gridX - offset.gridX, loc.gridY - offset.gridY));
		}
		width = maxX-minX+1;
		height = maxY-minY+1;
	}

	public List<Tile> onCopy() {
		List<Tile> answer = new ArrayList<Tile>();
		for(TileLocation loc : selectedLocations){
			if(tileLayer.isValidLocation(loc.gridX+offset.gridX, loc.gridY+offset.gridY)){
				answer.add(new Tile(tileLayer.getNodeAt(loc.gridX+offset.gridX, loc.gridY+offset.gridY).getTileData(),
						loc.gridX, loc.gridY));
			}
		}
		return answer;
	}
	
	public List<Tile> onCut() {
		List<Tile> answer = onCopy();
		for(TileLocation t : selectedLocations){
			if(tileLayer.isValidLocation(t.gridX+offset.gridX, t.gridY+offset.gridY)){
				tileLayer.getNodeAt(t.gridX+offset.gridX, t.gridY+offset.gridY).resetTileset();
			}
		}
		selectedLocations.clear();
		return answer;
	}
	
	public int getTileWidth(){
		return width;
	}
	
	public int getTileHeight(){
		return height;
	}
	
	public void onRemove(){
		tileLayer.silentlyIgnoreInput(false);
	}
	
//	public void setOffset(int newX, int newY){
//		List<TileLocation> list = new ArrayList<TileLocation>();
//		for(Tile t : selectedLocations){
//			TileLocation loc = t.getLocation();
//			if(tileLayer.isValidLocation(loc)){
//				TilePNode node = tileLayer.getNodeAt(loc);
//				t.setTileset(node.getTileData());
//				node.resetTileset();
//				node.silentlyIgnoreInput(true);
//				list.add(loc);
//			}
//		}
//		offset.gridX=newX;
//		offset.gridY=newY;
//		for(Tile t : selectedLocations){
//			TileLocation loc = t.getLocation();
//			if(tileLayer.isValidLocation(loc)){
//				TilePNode node = tileLayer.getNodeAt(loc);
//				node.silentlyIgnoreInput(false);
//				node.setTileset(t);
//			}
//		}
//	}
//	
//	public void move(int deltaX, int deltaY){
//		
//	}
//	
//	public void move(TileLocation offset){
//		move(offset.gridX, offset.gridY);
//	}
//	
//	public boolean pruneInvalidTiles(){
//		for(Iterator<Tile> it = selectedLocations.iterator(); it.hasNext();){
//			Tile t = it.next();
//			if(!tileLayer.isValidLocation(t.getLocation()))
//				it.remove();
//		}
//		return !selectedLocations.isEmpty();
//	}
	
//	public void moveTo(TileLocation location){
//		
//	}

	public PNode getOverlayNode() {
		// TODO Auto-generated method stub
		return null;
	}

}
