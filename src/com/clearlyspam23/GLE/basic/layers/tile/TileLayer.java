package com.clearlyspam23.GLE.basic.layers.tile;

import java.util.HashMap;
import java.util.Map;

import org.piccolo2d.PNode;

import com.clearlyspam23.GLE.Layer;
import com.clearlyspam23.GLE.GUI.LayerEditManager;
import com.clearlyspam23.GLE.GUI.util.GridNode;
import com.clearlyspam23.GLE.basic.layers.tile.export.CompactExportData;
import com.clearlyspam23.GLE.basic.layers.tile.gui.TileLayerPNode;
import com.clearlyspam23.GLE.basic.layers.tile.gui.TilePNode;

public class TileLayer extends Layer<Object> {
	
	private TileLayerTemplate template;
	
	private PNode base;
	private TileLayerPNode tiles;
	private GridNode grid;
	private double width;
	private double height;
	private TilesetEditorData data;
	
	public TileLayer(TileLayerTemplate template)
	{
		this.template = template;
		base = new PNode();
		data = new TilesetEditorData();
		//the below line should be removed as soon as a better solution is determined
		for(TilesetHandle t : getTilesetManager().getAllTilesets()){
			System.out.println(t);
			data.addTileset(t);
		}
	}

	@Override
	public Object getExportData() {
		if(tiles==null)
			return new CompactExportData[0];
		Map<TilesetHandle, CompactExportData> dataMap = new HashMap<TilesetHandle, CompactExportData>();
		TilePNode[][] grid = tiles.getNodeGrid();
		for(int i = 0; i < grid.length; i++){
			for(int j = 0; j < grid[i].length; j++){
				if(grid[i][j]==null||grid[i][j].getTileset()==null)
					continue;
				if(!dataMap.containsKey(grid[i][j].getTileset())){
					CompactExportData ced = new CompactExportData();
					ced.tiles = new int[grid[0].length][grid.length];
					ced.tileset = grid[i][j].getTileset().getName();
					fillWithMinus1(ced.tiles);
					dataMap.put(grid[i][j].getTileset(), ced);
				}
				dataMap.get(grid[i][j].getTileset()).tiles[j][i] = grid[i][j].getTilesetX() + grid[i][j].getTileset().getWidth()*grid[i][j].getTilesetY();
			}
		}
		CompactExportData[] ans = new CompactExportData[dataMap.size()];
		int i = 0;
		for(CompactExportData d : dataMap.values()){
			ans[i++] = d;
		}
		return ans;
	}
	
	private void fillWithMinus1(int[][] nodes){
		for(int i = 0; i < nodes.length; i++){
			for(int j = 0; j < nodes[i].length; j++){
				nodes[i][j] = -1;
			}
		}
	}

	@Override
	public PNode getLayerGUI() {
		return base;
	}

	@Override
	public void buildFromData(Object data) {
		CompactExportData[] ceds = (CompactExportData[])data;
		for(CompactExportData d : ceds){
			TilesetHandle ts = getTilesetManager().getTilesetByName(d.tileset);
			for(int i = 0; i < d.tiles.length; i++){
				for(int j = 0; j < d.tiles[i].length; j++){
					if(d.tiles[i][j]<0){
						tiles.getNodeGrid()[j][i].resetTileset();
					}
					else{
						tiles.getNodeGrid()[j][i].setTileset(ts, ts.getXFromIndex(d.tiles[i][j]), ts.getYFromIndex(d.tiles[i][j]));
					}
				}
			}
		}
	}

//	@Override
//	public List<LayerEditorDialog> getEditors(Frame frame) {
//		data.buildDialogs(frame);
//		return data.getDialogs();
//	}

	@Override
	public void onResize(double x, double y) {
		width = x;
		height = y;
		tiles = new TileLayerPNode(width, height, template.getGridWidth(), template.getGridHeight());
		base.addChild(tiles);
		grid = new GridNode(width, height, template.getGridWidth(), template.getGridHeight());
		base.addChild(grid);
	}
	
	public TilesetManager getTilesetManager(){
		return (TilesetManager) template.getTemplate().getTemplateData(template.getDefinition(), "tilesets");
	}

	@Override
	public String getName() {
		return template.getName();
	}

	@Override
	public LayerEditManager getEditManager() {
		return data;
	}

}
