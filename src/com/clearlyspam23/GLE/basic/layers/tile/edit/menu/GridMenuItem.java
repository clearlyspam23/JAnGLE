package com.clearlyspam23.GLE.basic.layers.tile.edit.menu;

import javax.swing.JCheckBoxMenuItem;

import com.clearlyspam23.GLE.GUI.util.AxisAlignedRectGridNode;
import com.clearlyspam23.GLE.basic.layers.tile.TileLayer;
import com.clearlyspam23.GLE.edit.LayerMenuItem;

public class GridMenuItem extends LayerMenuItem<TileLayer, JCheckBoxMenuItem> {

	public GridMenuItem() {
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
