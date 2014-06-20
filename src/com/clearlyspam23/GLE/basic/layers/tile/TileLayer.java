package com.clearlyspam23.GLE.basic.layers.tile;

import java.util.List;

import org.piccolo2d.PNode;

import com.clearlyspam23.GLE.Layer;
import com.clearlyspam23.GLE.GUI.LayerDialog;
import com.clearlyspam23.GLE.GUI.util.GridNode;

public class TileLayer extends Layer<TileExportData> {
	
	private TileLayerTemplate template;
	
	private PNode base;
	private TileLayerPNode tiles;
	private GridNode grid;
	
	public TileLayer(TileLayerTemplate template)
	{
		this.template = template;
		base = new PNode();
//		tiles = new TileLayerPNode();
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
