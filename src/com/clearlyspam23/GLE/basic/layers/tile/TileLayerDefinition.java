package com.clearlyspam23.GLE.basic.layers.tile;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import com.clearlyspam23.GLE.Template;
import com.clearlyspam23.GLE.GUI.EditorItems;
import com.clearlyspam23.GLE.basic.layers.tile.gui.LayerGridMenuItem;
import com.clearlyspam23.GLE.basic.layers.tile.gui.TileLayerGUIOptions;
import com.clearlyspam23.GLE.basic.layers.tile.gui.TilesetEditorData;
import com.clearlyspam23.GLE.basic.layers.tile.gui.TilesetLoadDialog;
import com.clearlyspam23.GLE.level.LayerDefinition;

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
