package com.clearlyspam23.GLE.basic.layers.tile;

import java.util.List;

import org.piccolo2d.PNode;

import com.clearlyspam23.GLE.Layer;
import com.clearlyspam23.GLE.Level;
import com.clearlyspam23.GLE.GUI.LayerDialog;
import com.clearlyspam23.GLE.GUI.util.GridNode;

public class TileLayer extends Layer<TileExportData> {
	
	private TileLayerTemplate template;
	
	private PNode base;
	private TileLayerPNode tiles;
	private GridNode grid;
	private double width;
	private double height;
	
	public TileLayer(TileLayerTemplate template, Level level)
	{
		this.template = template;
		base = new PNode();
		tiles = new TileLayerPNode(width = level.getWidth(), height = level.getHeight(), template.getGridWidth(), template.getGridHeight());
		base.addChild(tiles);
		grid = new GridNode(width, height, template.getGridWidth(), template.getGridHeight());
		base.addChild(grid);
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

}
