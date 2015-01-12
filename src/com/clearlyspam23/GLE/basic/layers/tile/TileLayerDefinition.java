package com.clearlyspam23.GLE.basic.layers.tile;

import com.clearlyspam23.GLE.Template;
import com.clearlyspam23.GLE.basic.layers.tile.gui.TileLayerGUIOptions;
import com.clearlyspam23.GLE.basic.layers.tile.gui.TileLayerEditManager;
import com.clearlyspam23.GLE.edit.EditorItems;
import com.clearlyspam23.GLE.level.LayerDefinition;

public class TileLayerDefinition extends LayerDefinition<TileLayerGUIOptions, TileLayerTemplate> {
	
	private TileLayerEditManager editorData;
	
	public TileLayerDefinition(){
		editorData = new TileLayerEditManager();
	}

	@Override
	public String getName() {
		return "Tile";
	}

	@Override
	public TileLayerTemplate buildFromEditorGUI(TileLayerGUIOptions gui) {
		TileLayerTemplate t = new TileLayerTemplate(this);
		t.setDefaultGridDimensions(gui.getGridDimensions());
		return t;
	}
	
	@Override
	public TileLayerTemplate buildDefault() {
		TileLayerTemplate t = new TileLayerTemplate(this);
		t.setDefaultGridDimensions(32, 32);
		t.allowGridResizing(true);
		return t;
	}

	@Override
	public TileLayerGUIOptions getLayerComponent() {
		return new TileLayerGUIOptions();
	}

	@Override
	public void setEditorGUITo(TileLayerGUIOptions gui, TileLayerTemplate template) {
		gui.setGridDimensions(template.getDefaultGridDimensions());
	}
	
	public void onTemplateCreation(Template template){
		TilesetManager manager = new TilesetManager();
		template.putTemplateData(this, "tilesets", manager);
	}
	
	@Override
	public EditorItems<TileLayer> onTemplateOpen(final Template template){
		TileEditorItems ans = new TileEditorItems(this);
		updateTilesets(template);
		return ans;
	}
	
	public void updateTilesets(Template template){
		editorData.clearTilesets();
		for(TilesetHandle t : ((TilesetManager) template.getTemplateData(this, "tilesets")).getAllTilesets()){
			editorData.addTileset(t);
		}
	}

	public TileLayerEditManager getEditorData() {
		return editorData;
	}

}
