package com.clearlyspam23.GLE.level;

import com.clearlyspam23.GLE.Layer;
import com.clearlyspam23.GLE.LayerTemplate;

public class Level {
	
	private Layer[] layers;
	
	private float boundx;
	private float boundy;
	
	public Level(LayerTemplate[] layerTemplates, float boundx, float boundy)
	{
		layers = new Layer[layerTemplates.length];
		for(int i = 0; i < layerTemplates.length; i++)
			layers[i] = layerTemplates[i].createLayer();
	}

}
