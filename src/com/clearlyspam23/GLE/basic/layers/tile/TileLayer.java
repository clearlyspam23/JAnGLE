package com.clearlyspam23.GLE.basic.layers.tile;

import java.awt.Frame;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.piccolo2d.PNode;
import org.piccolo2d.event.PInputEventListener;

import com.clearlyspam23.GLE.Layer;
import com.clearlyspam23.GLE.GUI.LayerDialog;
import com.clearlyspam23.GLE.GUI.LayerEditorDialog;
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
		for(Tileset t : getTilesetManager().getAllTilesets())
			data.addTileset(t);
	}

	@Override
	public Object getExportData() {
		if(tiles==null)
			return new CompactExportData[0];
		Map<Tileset, CompactExportData> dataMap = new HashMap<Tileset, CompactExportData>();
		TilePNode[][] grid = tiles.getNodeGrid();
		for(int i = 0; i < grid.length; i++){
			for(int j = 0; j < grid[i].length; j++){
				if(grid[i][j]==null||grid[i][j].getTileset()==null)
					continue;
				if(!dataMap.containsKey(grid[i][j].getTileset())){
					CompactExportData ced = new CompactExportData();
					ced.tileset = grid[i][j].getTileset().getName();
					fillWithMinus1(ced.tiles);
					dataMap.put(grid[i][j].getTileset(), ced);
				}
				dataMap.get(grid[i][j].getTileset()).tiles[i][j] = grid[i][j].getTilesetX() + grid[i][j].getTileset().getWidth()*grid[i][j].getTilesetY();
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
	public List<LayerDialog> getLayerDialogs() { 
		return null;
	}

	@Override
	public PNode getLayerGUI() {
		return base;
	}

	@Override
	public void buildFromData(Object data) {
		CompactExportData[] ceds = (CompactExportData[])data;
		for(CompactExportData d : ceds){
			Tileset ts = getTilesetManager().getTilesetByName(d.tileset);
			for(int i = 0; i < d.tiles.length; i++){
				for(int j = 0; j < d.tiles.length; j++){
					if(d.tiles[i][j]<0){
						tiles.getNodeGrid()[i][j].resetTileset();
					}
					else{
						tiles.getNodeGrid()[i][j].setTileset(ts, i, j);
					}
				}
			}
		}
	}

	@Override
	public List<LayerEditorDialog> getEditors(Frame frame) {
		return data.getEditorDialogs(frame);
	}

	@Override
	public List<PInputEventListener> getListeners() {
		return Arrays.asList((PInputEventListener)data);
	}

	@Override
	public void onResize(double x, double y) {
		System.out.println(x);
		System.out.println(y);
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

}
