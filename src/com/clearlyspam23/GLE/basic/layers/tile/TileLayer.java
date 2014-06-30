package com.clearlyspam23.GLE.basic.layers.tile;

import java.awt.Frame;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.imageio.ImageIO;

import org.piccolo2d.PNode;
import org.piccolo2d.event.PInputEventListener;

import com.clearlyspam23.GLE.Layer;
import com.clearlyspam23.GLE.Level;
import com.clearlyspam23.GLE.GUI.LayerDialog;
import com.clearlyspam23.GLE.GUI.LayerEditorDialog;
import com.clearlyspam23.GLE.GUI.util.GridNode;
import com.clearlyspam23.GLE.basic.layers.tile.gui.TileLayerPNode;

public class TileLayer extends Layer<TileExportData> {
	
	private TileLayerTemplate template;
	
	private PNode base;
	private TileLayerPNode tiles;
	private GridNode grid;
	private double width;
	private double height;
	private TilesetEditorData data;
	
	public TileLayer(TileLayerTemplate template, Level level)
	{
		this.template = template;
		base = new PNode();
		tiles = new TileLayerPNode(width = level.getWidth(), height = level.getHeight(), template.getGridWidth(), template.getGridHeight());
		base.addChild(tiles);
		grid = new GridNode(width, height, template.getGridWidth(), template.getGridHeight());
		base.addChild(grid);
		data = new TilesetEditorData();
		
		//the below code should be removed as soon as a better solution is found
		BufferedImage tile = null;
		BufferedImage[] tiles = null;
		try {
			File f = new File("images/testboxes.png");
			BufferedImage temp  = ImageIO.read(f);
			tile = temp.getSubimage(0, 0, 64, 64);
			tiles = new BufferedImage[4];
			for(int i = 0; i < tiles.length; i++)
			{
				tiles[i] = temp.getSubimage(64*i, 0, 64, 64);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(tile==null)
		{
			System.err.println("unable to load the image");
			return;
		}
		data.addTileset(new Tileset("test1", new Image[][]{{tiles[0], tiles[1], tiles[2], tiles[3]}}));
//		data.setCurrentTileset(new Tileset("test1", new Image[][]{{tile}}));
//		data.setSelectedIndex(0, 0);
	}

	@Override
	public TileExportData getExportData() {
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
	public void buildFromData(TileExportData data) {
		
	}

	@Override
	public List<LayerEditorDialog> getEditors(Frame frame) {
		return data.getEditorDialogs(frame);
	}

	@Override
	public List<PInputEventListener> getListeners() {
		return Arrays.asList((PInputEventListener)data);
	}

}
