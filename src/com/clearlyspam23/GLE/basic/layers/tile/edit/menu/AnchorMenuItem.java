package com.clearlyspam23.GLE.basic.layers.tile.edit.menu;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import com.clearlyspam23.GLE.basic.layers.tile.TileLayer;
import com.clearlyspam23.GLE.basic.layers.tile.edit.TileLayerEditManager;
import com.clearlyspam23.GLE.basic.layers.tile.gui.TileLayerSelectionListener;
import com.clearlyspam23.GLE.basic.layers.tile.gui.TileSelection;
import com.clearlyspam23.GLE.edit.LayerMenuItem;

public class AnchorMenuItem extends LayerMenuItem<TileLayer, JMenuItem> implements TileLayerSelectionListener{
	
	private TileLayerEditManager editor;

	public AnchorMenuItem(TileLayerEditManager editor) {
		super(new JMenuItem("Anchor Selection"));
		this.editor = editor;
		JMenuItem item = getMenuItem();
		item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, InputEvent.CTRL_MASK));
		item.setEnabled(false);
	}

	@Override
	public void selectionChange(TileLayer layer, TileSelection oldSelection,
			TileSelection newSelection) {
		getMenuItem().setEnabled(newSelection!=null);
	}	
	
	public void onHide(TileLayer activeLayer){
		activeLayer.removeSelectionListener(this);
	}
	
	public void onShow(TileLayer activeLayer){
		getMenuItem().setEnabled(activeLayer.getBase().hasSelection());
		activeLayer.addSelectionListener(this);
	}

	@Override
	public void performAction(TileLayer layer) {
		layer.getBase().anchorSelectionWithAction(editor);
	}

}
