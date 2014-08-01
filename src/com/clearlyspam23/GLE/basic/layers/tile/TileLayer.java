package com.clearlyspam23.GLE.basic.layers.tile;

import java.awt.Frame;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.imageio.ImageIO;

import org.piccolo2d.PNode;
import org.piccolo2d.event.PInputEventListener;

import com.clearlyspam23.GLE.Layer;
import com.clearlyspam23.GLE.LayerData;
import com.clearlyspam23.GLE.GUI.LayerDialog;
import com.clearlyspam23.GLE.GUI.LayerEditorDialog;
import com.clearlyspam23.GLE.GUI.util.GridNode;
import com.clearlyspam23.GLE.basic.layers.tile.gui.TileLayerPNode;

public class TileLayer extends Layer<LayerData> {
	
	private TileLayerTemplate template;
	
	private PNode base;
	private TileLayerPNode tiles;
	private GridNode grid;
	private double width;
	private double height;
	private TilesetEditorData data;
	
	public TileLayer(TileLayerTemplate template)
	{
		this.template = template;
		base = new PNode();
		data = new TilesetEditorData();
		
		//the below code should be removed as soon as a better solution is found
		BufferedImage tile = null;
		Image[][] tiles = null;
		try {
			File f = new File("images/Pipes.png");
			BufferedImage temp  = ImageIO.read(f);
			tile = temp.getSubimage(0, 0, 64, 64);
			tiles = new Image[8][8];
			for(int i = 0; i < tiles.length; i++)
			{
				for(int j = 0; j < tiles[i].length; j++)
				{
					tiles[i][j] = temp.getSubimage(64*j, 64*i, 64, 64);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(tile==null)
		{
			System.err.println("unable to load the image");
			return;
		}
		data.addTileset(new Tileset("test1", tiles));
//		data.setCurrentTileset(new Tileset("test1", new Image[][]{{tile}}));
//		data.setSelectedIndex(0, 0);
	}

	@Override
	public LayerData getExportData() {
		return null;
	}

	@Override
	public List<LayerDialog> getLayerDialogs() { 
		return null;
	}

	@Override
	public PNode getLayerGUI() {
		return base;
	}

	@Override
	public void buildFromData(LayerData data) {
		
	}

	@Override
	public List<LayerEditorDialog> getEditors(Frame frame) {
		return data.getEditorDialogs(frame);
	}

	@Override
	public List<PInputEventListener> getListeners() {
		return Arrays.asList((PInputEventListener)data);
	}

	@Override
	public void onResize(double x, double y) {
		width = x;
		height = y;
		tiles = new TileLayerPNode(width, height, template.getGridWidth(), template.getGridHeight());
		base.addChild(tiles);
		grid = new GridNode(width, height, template.getGridWidth(), template.getGridHeight());
		base.addChild(grid);
	}

	@Override
	public String getName() {
		return template.getName();
	}

}
