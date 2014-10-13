package com.clearlyspam23.GLE.basic.layers.tile;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import com.clearlyspam23.GLE.Template;
import com.clearlyspam23.GLE.GUI.EditorItems;
import com.clearlyspam23.GLE.basic.layers.tile.gui.LayerGridMenuItem;
import com.clearlyspam23.GLE.basic.layers.tile.gui.TileLayerGUIOptions;
import com.clearlyspam23.GLE.basic.layers.tile.gui.TilesetEditorData;
import com.clearlyspam23.GLE.basic.layers.tile.gui.TilesetLoadDialog;
import com.clearlyspam23.GLE.level.Layer;
import com.clearlyspam23.GLE.level.LayerDefinition;
import com.clearlyspam23.GLE.level.Level;
import com.clearlyspam23.GLE.util.Utility;

public class TileLayerDefinition extends LayerDefinition<TileLayerGUIOptions, TileLayerTemplate> {
	
	private TilesetEditorData editorData;
	private TilesetLoadDialog loadDialog;
	
	public TileLayerDefinition(){
		editorData = new TilesetEditorData();
		loadDialog = new TilesetLoadDialog();
	}

	@Override
	public String getName() {
		return "Tile";
	}

	@Override
	public TileLayerTemplate buildFromEditorGUI(TileLayerGUIOptions gui) {
		TileLayerTemplate t = new TileLayerTemplate(this);
		t.setGridDimensions(gui.getGridDimensions());
		return t;
	}

	@Override
	public TileLayerGUIOptions getLayerComponent() {
		return new TileLayerGUIOptions();
	}

	@Override
	public void setEditorGUITo(TileLayerGUIOptions gui, TileLayerTemplate template) {
		gui.setGridDimensions(template.getGridDimensions());
	}
	
	public void onTemplateCreation(Template template){
		TilesetManager manager = new TilesetManager();
		template.putTemplateData(this, "tilesets", manager);
	}
	
	@Override
	public EditorItems onTemplateOpen(final Template template){
		EditorItems ans = new EditorItems(this);
		final TilesetManager manager = (TilesetManager) template.getTemplateData(this, "tilesets");
		ans.addLevelItem(new LayerGridMenuItem());
		JMenu menu = new JMenu("Tilesets");
		JMenuItem item = new JMenuItem("Tileset Manager");
		item.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				loadDialog.showDialog(manager);
				updateTilesets(template);
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
		ans.addMenuItem(menu);
		updateTilesets(template);
		return ans;
	}
	
	public void updateTilesets(Template template){
		editorData.clearTilesets();
		for(TilesetHandle t : ((TilesetManager) template.getTemplateData(this, "tilesets")).getAllTilesets()){
			System.out.println(t);
			editorData.addTileset(t);
		}
	}

	public TilesetEditorData getEditorData() {
		return editorData;
	}

}
