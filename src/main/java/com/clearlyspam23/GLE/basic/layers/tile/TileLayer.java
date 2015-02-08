package com.clearlyspam23.GLE.basic.layers.tile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.piccolo2d.PNode;

import com.clearlyspam23.GLE.GUI.util.AxisAlignedRectGridNode;
import com.clearlyspam23.GLE.basic.layers.tile.edit.TileLayerEditManager;
import com.clearlyspam23.GLE.basic.layers.tile.export.CompactExportData;
import com.clearlyspam23.GLE.basic.layers.tile.gui.BasePNode;
import com.clearlyspam23.GLE.basic.layers.tile.gui.TileLayerSelectionListener;
import com.clearlyspam23.GLE.basic.layers.tile.gui.TilePNode;
import com.clearlyspam23.GLE.basic.layers.tile.gui.TileSelection;
import com.clearlyspam23.GLE.edit.LayerEditManager;
import com.clearlyspam23.GLE.level.Layer;
import com.clearlyspam23.GLE.level.Level;

public class TileLayer extends Layer<Object>{
	
	private TileLayerTemplate template;
	
	private BasePNode base;
//	private TileLayerPNode tiles;
	private PNode overlay = new PNode();
	private AxisAlignedRectGridNode grid;
	private TileLayerEditManager data;
	
	private List<TileLayerSelectionListener> selectionListeners = new ArrayList<TileLayerSelectionListener>();
	
	public TileLayer(TileLayerTemplate template)
	{
		super(template.getDefinition());
		this.template = template;
		data = ((TileLayerDefinition)template.getDefinition()).getEditorData();
		base = new BasePNode(template, this);
		grid = new AxisAlignedRectGridNode(template.getDefaultGridWidth(), template.getDefaultGridHeight());
		grid.setTransparency(0);
		overlay.addChild(grid);
	}

	@Override
	public Object getExportData() {
		if(base==null||base.getTiles()==null)
			return new CompactExportData[0];
		Map<TilesetHandle, CompactExportData> dataMap = new HashMap<TilesetHandle, CompactExportData>();
		TilePNode[][] grid = base.getTiles().getNodeGrid();
		for(int i = 0; i < grid.length; i++){
			for(int j = 0; j < grid[i].length; j++){
				if(grid[i][j]==null||grid[i][j].getTileset()==null)
					continue;
				if(!dataMap.containsKey(grid[i][j].getTileset())){
					CompactExportData ced = new CompactExportData();
					ced.tiles = new int[grid[0].length][grid.length];
					ced.tileset = grid[i][j].getTileset().getName();
					ced.tilesetId = grid[i][j].getTileset().getID();
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
	public int buildFromData(Object data) {
		int failureNum = 0;
		CompactExportData[] ceds = (CompactExportData[])data;
		for(CompactExportData d : ceds){
			TilesetHandle ts = getTilesetManager().getTilesetByID(d.tilesetId);
			if(ts==null)
				ts = getTilesetManager().getTilesetByName(d.tileset);
			if(ts==null){
				failureNum++;
				continue;
			}
			for(int i = 0; i < d.tiles.length; i++){
				for(int j = 0; j < d.tiles[i].length; j++){
					if(d.tiles[i][j]<0){
						base.getTiles().getNodeGrid()[j][i].resetTileset();
					}
					else{
						base.getTiles().getNodeGrid()[j][i].setTileset(ts, ts.getXFromIndex(d.tiles[i][j]), ts.getYFromIndex(d.tiles[i][j]));
					}
				}
			}
		}
		if(failureNum<=0)
			return Layer.SUCCESS;
		if(failureNum==ceds.length)
			return Layer.FAILURE;
		return Layer.PARTIAL_SUCCESS;
	}

	@Override
	public void onResize(Level level, double x, double y) {
		base.resize(x, y);
		grid.resize(x, y);
	}
	
	public TilesetManager getTilesetManager(){
		return (TilesetManager) template.getTemplate().getTemplateData(template.getDefinition(), "tilesets");
	}

	@Override
	public String getName() {
		return template.getName();
	}
	
	public BasePNode getBase(){
		return base;
	}

	@Override
	public LayerEditManager<TileLayer> getEditManager() {
		return data;
	}
	
	public void toggleShowGrid(boolean flag){
		if(flag)
			grid.setTransparency(1);
		else
			grid.setTransparency(0);
	}
	
	public boolean isGridShowing(){
		return grid.getTransparency()>0.5f;
	}
	
	public float minBorderWidth(){
		return (float) (Math.min(template.getDefaultGridWidth(), template.getDefaultGridHeight())/10);
	}
	
	public PNode getOverlayGUI(){
		return overlay;
	}
	
	public boolean refreshTilesets(){
		return base.refreshTilesets(getTilesetManager());
	}
	
	public void selectionChanged(TileSelection old, TileSelection current){
		for(TileLayerSelectionListener l : selectionListeners){
			l.selectionChange(this, old, current);
		}
	}
	
	public void addSelectionListener(TileLayerSelectionListener listener){
		selectionListeners.add(listener);
	}
	
	public void removeSelectionListener(TileLayerSelectionListener listener){
		selectionListeners.remove(listener);
	}
	
	public List<TileLayerSelectionListener> getSelectionListeners(){
		return selectionListeners;
	}
	
	public boolean canCopy(){
		return base.canCopy();
	}
	
	public boolean canCut(){
		return base.canCut();
	}

}
