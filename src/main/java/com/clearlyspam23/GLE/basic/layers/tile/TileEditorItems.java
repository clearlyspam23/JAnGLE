package com.clearlyspam23.GLE.basic.layers.tile;

import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import com.clearlyspam23.GLE.Template;
import com.clearlyspam23.GLE.basic.layers.tile.edit.menu.GridMenuItem;
import com.clearlyspam23.GLE.basic.layers.tile.gui.TilesetLoadDialog;
import com.clearlyspam23.GLE.edit.EditorItems;
import com.clearlyspam23.GLE.edit.LayerMenuItem;
import com.clearlyspam23.GLE.level.Layer;
import com.clearlyspam23.GLE.level.Level;
import com.clearlyspam23.GLE.util.Utility;

public class TileEditorItems extends EditorItems<TileLayer> {
	
	private TilesetLoadDialog loadDialog;
	
	

	public TileEditorItems(TileLayerDefinition def) {
		super(def);
		loadDialog = new TilesetLoadDialog();
	}

	@Override
	public List<JMenu> getMenuItems(final Template template) {JMenu menu = new JMenu("Tilesets");
		JMenuItem item = new JMenuItem("Tileset Manager");
		final TilesetManager manager = (TilesetManager) template.getTemplateData(this.getDef(), "tilesets");
		item.addActionListener(new ActionListener(){
	
			@Override
			public void actionPerformed(ActionEvent arg0) {
				loadDialog.showDialog(manager);
				((TileLayerDefinition)getDef()).updateTilesets(template);
				template.save();
				boolean b = true;
				for(Level l : template.getData().getOpenLevels()){
					for(Layer<?> layer : l.getLayers()){
						if(layer instanceof TileLayer){
							b = b && ((TileLayer)layer).refreshTilesets();
						}
					}
				}
				if(!b){
					JOptionPane.showMessageDialog(template.getData().getFrame(), "Unable to reload every tile" + Utility.NEWLINE + "Some tiles have been removed");
				}
			}
			
		});
		menu.add(item);
		ArrayList<JMenu> ans = new ArrayList<JMenu>();
		ans.add(menu);
		return ans;
	}

	@Override
	public List<Button> getButtonBarItems(Template template) {
		return new ArrayList<Button>();
	}

}
