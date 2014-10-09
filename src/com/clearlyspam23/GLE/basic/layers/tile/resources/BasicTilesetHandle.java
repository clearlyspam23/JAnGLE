package com.clearlyspam23.GLE.basic.layers.tile.resources;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.clearlyspam23.GLE.basic.layers.tile.TilesetHandle;

public class BasicTilesetHandle extends TilesetHandle{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int tileWidth;
	private int tileHeight;
	private int tileXSpacing;
	private int tileYSpacing;
	private String name;
	private String imageFile;
	private transient Image[][] tileset;
	
	public BasicTilesetHandle(){
		
	}
	
	public BasicTilesetHandle(String name){
		this.name = name;
	}
	
	public BasicTilesetHandle(String name, String filename, int tileWidth, int tileHeight){
		this(name, filename, tileWidth, tileHeight, 0, 0);
	}
	
	public BasicTilesetHandle(String name, String filename, int tileWidth, int tileHeight, int xSpacing, int ySpacing){
		this.name = name;
		this.imageFile = filename;
		this.tileWidth = tileWidth;
		this.tileHeight = tileHeight;
		this.tileXSpacing = xSpacing;
		this.tileYSpacing = ySpacing;
		checkLoad();
	}
	
	private void checkLoad(){
		if(imageFile!=null&&tileset==null){
			reloadTileset();
		}
	}
	
	private void reloadTileset(){
		Image[][] tiles = null;
		try {
			BufferedImage temp  = ImageIO.read(new File(imageFile));
			tiles = new Image[(temp.getWidth()+getTileXSpacing())/(getTileWidth()+getTileXSpacing())]
					[(temp.getHeight()+getTileYSpacing())/(getTileHeight()+getTileYSpacing())];
			for(int i = 0; i < tiles.length; i++)
			{
				for(int j = 0; j < tiles[i].length; j++)
				{
					tiles[i][j] = temp.getSubimage((getTileWidth()+getTileXSpacing())*i, (getTileHeight()+getTileYSpacing())*j, getTileWidth(), getTileHeight());
				}
			}
		} catch (IOException e) {
			
		}
		tileset = tiles;
	}
	
	public Image[][] getTileset() {
		checkLoad();
		return tileset;
	}
	
	public Image getTileAt(int x, int y){
		checkLoad();
		return tileset[x][y];
	}
	
	public Image getTileByIndex(int index){
		checkLoad();
		return getTileAt(index%tileset.length, index/tileset.length);
	}
	
	public int getXFromIndex(int index){
		checkLoad();
		return index%tileset.length;
	}
	
	public int getYFromIndex(int index){
		checkLoad();
		return index/tileset.length;
	}
	
	public boolean isValidLocation(int x, int y){
		checkLoad();
		return x>=0&&x<tileset.length&&y>=0&&y<tileset[x].length;
	}
	
	public int getIndex(int x, int y){
		checkLoad();
		return y*tileset.length+x;
	}
	
	public int getWidth() {
		checkLoad();
		return tileset.length;
	}

	public int getHeight() {
		checkLoad();
		return (tileset.length>0 ? tileset[0].length : 0);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFilename() {
		return imageFile;
	}

	public void setFilename(String filename) {
		boolean b = imageFile!=filename;
		this.imageFile = filename;
		if(b)
			reloadTileset();
	}

	public int getTileWidth() {
		return tileWidth;
	}

	public void setTileWidth(int tileWidth) {
		boolean b = tileWidth!=this.tileWidth;
		this.tileWidth = tileWidth;
		if(b)
			reloadTileset();
	}

	public int getTileHeight() {
		return tileHeight;
	}

	public void setTileHeight(int tileHeight) {
		boolean b = tileHeight!=this.tileHeight;
		this.tileHeight = tileHeight;
		if(b)
			reloadTileset();
	}

	public int getTileXSpacing() {
		return tileXSpacing;
	}

	public void setTileXSpacing(int tileXSpacing) {
		boolean b = tileXSpacing!=this.tileXSpacing;
		this.tileXSpacing = tileXSpacing;
		if(b)
			reloadTileset();
	}

	public int getTileYSpacing() {
		return tileYSpacing;
	}

	public void setTileYSpacing(int tileYSpacing) {
		boolean b = tileYSpacing!=this.tileYSpacing;
		this.tileYSpacing = tileYSpacing;
		if(b)
			reloadTileset();
	}
	
	public String toString(){
		return name;
	}

	@Override
	public BasicTilesetHandle cloneAsBasic() {
		BasicTilesetHandle out = new BasicTilesetHandle();
		out.tileWidth = tileWidth;
		out.tileHeight = tileHeight;
		out.tileXSpacing = tileXSpacing;
		out.tileYSpacing = tileYSpacing;
		out.name = name;
		out.imageFile = imageFile;
		out.checkLoad();
		return out;
	}
	
	public boolean isValid(){
		return tileset!=null;
	}

}
