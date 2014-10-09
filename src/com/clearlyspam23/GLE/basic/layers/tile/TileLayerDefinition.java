package com.clearlyspam23.GLE.basic.layers.tile;

import com.clearlyspam23.GLE.Template;
import com.clearlyspam23.GLE.GUI.EditorItems;
import com.clearlyspam23.GLE.basic.layers.tile.gui.LayerGridMenuItem;
import com.clearlyspam23.GLE.basic.layers.tile.gui.TileLayerGUIOptions;
import com.clearlyspam23.GLE.basic.layers.tile.gui.TilesetEditorData;
import com.clearlyspam23.GLE.level.LayerDefinition;

public class TileLayerDefinition extends LayerDefinition<TileLayerGUIOptions, TileLayerTemplate> {
	
	private TilesetEditorData editorData;
	
	public TileLayerDefinition(){
		editorData = new TilesetEditorData();
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
		//the below code will be moved soon
//		BufferedImage tile = null;
//		Image[][] tiles = null;
//		try {
//			File f = new File("images/Pipes.png");
//			BufferedImage temp  = ImageIO.read(f);
//			tile = temp.getSubimage(0, 0, 64, 64);
//			tiles = new Image[8][8];
//			for(int i = 0; i < tiles.length; i++)
//			{
//				for(int j = 0; j < tiles[i].length; j++)
//				{
//					tiles[i][j] = temp.getSubimage(64*i, 64*j, 64, 64);
//				}
//			}
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		if(tile==null)
//		{
//			System.err.println("unable to load the image");
//			return;
//		}
		//manager.addTilesetToRoot(new BasicTilesetHandle("test1", "images/Pipes.png", 64, 64));
		//except for this line below
		template.putTemplateData(this, "tilesets", manager);
	}
	
	@Override
	public EditorItems onTemplateOpen(Template template){
		System.out.println("here");
		EditorItems ans = new EditorItems(this);
		ans.addLevelItem(new LayerGridMenuItem());
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
