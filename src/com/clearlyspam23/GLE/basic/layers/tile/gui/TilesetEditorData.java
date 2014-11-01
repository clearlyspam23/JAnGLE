package com.clearlyspam23.GLE.basic.layers.tile.gui;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.piccolo2d.event.PInputEvent;
import org.piccolo2d.event.PInputEventListener;

import com.clearlyspam23.GLE.GUI.LayerEditManager;
import com.clearlyspam23.GLE.GUI.util.BasicEditorButton;
import com.clearlyspam23.GLE.GUI.util.BasicEditorPanel;
import com.clearlyspam23.GLE.basic.layers.tile.Tile;
import com.clearlyspam23.GLE.basic.layers.tile.TileLayer;
import com.clearlyspam23.GLE.basic.layers.tile.TilesetHandle;
import com.clearlyspam23.GLE.basic.layers.tile.commands.EraseTileCommand;
import com.clearlyspam23.GLE.basic.layers.tile.commands.FloodFillTileCommand;
import com.clearlyspam23.GLE.basic.layers.tile.commands.PlaceTileCommand;

public class TilesetEditorData extends LayerEditManager<TileLayer> implements ChangeListener, PInputEventListener{
	
//	private Tileset currentTileset;
//	
//	private int selectedX = -1;
//	private int selectedY = -1;
	
	private PInputEventListener currentEvent;
	
	private List<PInputEventListener> allEvents = new ArrayList<PInputEventListener>();
	
	private TilesetSelectionPanel selectionPanel;
	
	private Tile[][] cutSelection;
	
	
	
//	private List<LayerEditorDialog> dialogs = new ArrayList<LayerEditorDialog>();
	
	public TilesetEditorData()
	{
		allEvents.add(new PlaceTileCommand(this));
		allEvents.add(new EraseTileCommand(this));
		allEvents.add(new FloodFillTileCommand(this));
		selectionPanel = new TilesetSelectionPanel();
		BasicEditorPanel panel = new BasicEditorPanel
				(new BasicEditorButton("images/Pencil.png", "Pencil", "Places Tiles"), 
				new BasicEditorButton("images/Eraser.png", "Eraser", "Removes Tiles"),
				new BasicEditorButton("images/Bucket.png", "Bucket", "Fill Tiles"));
		panel.addChangeListener(this);
		panel.selectButton(0);
		setMainComponent(panel, "Tile Editor");
		addSubComponents(selectionPanel, "Tileset");
	}
	
//	public void buildDialogs(Frame frame){
//		getDialogs().clear();
//		dialog = new TileEditorDialog(frame);
//		dialog.getPanel().addChangeListener(this);
//		dialog.getPanel().selectButton(0);
//		dialog.getTilesetSelectionPanel().addAllTilesets(tempTilesetStorage);
//		getDialogs().add(dialog);
//		tempTilesetStorage.clear();
//	}
	
//	public List<LayerEditorDialog> getEditorDialogs(Frame f){
//		if(getDialogs().isEmpty()||!f.equals(getDialogs().get(0).getParent()))
//			buildDialogs(f);
//		return dialogs;
//	}
	
	public TilesetHandle getCurrentTileset() {
		return selectionPanel.getCurrentTileset();
	}
//	public void setCurrentTileset(Tileset currentTileset) {
//		dialog.getTilesetSelectionPanel().set
//	}
	public int getSelectedX() {
		return selectionPanel.getSelectedX();
	}
	public int getSelectedY(){
		return selectionPanel.getSelectedY();
	}
//	public void setSelectedIndex(int selectedX, int selectedY){
//		this.selectedX = selectedX;
//		this.selectedY = selectedY;
//	}
	
	public void addTileset(TilesetHandle t){
		selectionPanel.addTileset(t);
	}
	
	public void clearTilesets(){
		selectionPanel.clearTilesets();
	}
	
//	public void addAllTilesets(){
//		
//	}
	
	public Image getSelectedTile(){
		return selectionPanel.getSelectedTile();
	}
	@Override
	public void stateChanged(ChangeEvent e) {
		int index = ((BasicEditorPanel)e.getSource()).getSelectedButtonIndex();
		if(index>=0&&index<allEvents.size())
			currentEvent = allEvents.get(index);
		else
			currentEvent = null;
	}

	@Override
	public void processEvent(PInputEvent arg0, int arg1) {
		if(currentEvent!=null)
			currentEvent.processEvent(arg0, arg1);
	}

	@Override
	public String getName() {
		return "Tile Layer";
	}

	public Tile[][] getCutSelection() {
		return cutSelection;
	}

	public void setCutSelection(Tile[][] cutSelection) {
		this.cutSelection = cutSelection;
	}

}
