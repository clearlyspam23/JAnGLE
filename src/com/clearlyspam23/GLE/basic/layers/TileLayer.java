package com.clearlyspam23.GLE.basic.layers;

import java.util.List;

import com.clearlyspam23.GLE.Layer;
import com.clearlyspam23.GLE.GUI.LayerDialog;

public class TileLayer extends Layer {
	
	private TileLayerTemplate template;
	
	public TileLayer(TileLayerTemplate template)
	{
		this.template = template;
	}

	@Override
	public Object getExportData() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<LayerDialog> getLayerDialogs() {
		// TODO Auto-generated method stub
		return null;
	}

}
