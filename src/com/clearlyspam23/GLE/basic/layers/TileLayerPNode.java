package com.clearlyspam23.GLE.basic.layers;

import org.piccolo2d.PNode;

public class TileLayerPNode extends PNode{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private TileLayerData data;
	
	public TileLayerPNode(TileLayerData data)
	{
		setToData(data);
	}
	
	public void setToData(TileLayerData nData)
	{
		data = nData;
	}

}
