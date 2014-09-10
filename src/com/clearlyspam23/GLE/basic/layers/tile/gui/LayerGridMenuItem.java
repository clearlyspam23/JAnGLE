package com.clearlyspam23.GLE.basic.layers.tile.gui;

import javax.swing.JCheckBoxMenuItem;

import com.clearlyspam23.GLE.GUI.LayerMenuItem;
import com.clearlyspam23.GLE.GUI.util.AxisAlignedRectGridNode;
import com.clearlyspam23.GLE.basic.layers.tile.TileLayer;

public class LayerGridMenuItem extends LayerMenuItem<TileLayer, JCheckBoxMenuItem> {

	public LayerGridMenuItem() {
		super(new JCheckBoxMenuItem("Show Grid"));
	}

	@Override
	public void performAction(TileLayer layer) {
		AxisAlignedRectGridNode.isVisible = !AxisAlignedRectGridNode.isVisible;
		onShow(layer);
	}
	
	public void onShow(TileLayer layer){
		layer.toggleShowGrid(AxisAlignedRectGridNode.isVisible);
		getMenuItem().setSelected(AxisAlignedRectGridNode.isVisible);
	}

}
