package com.clearlyspam23.GLE.basic.layers.tile;

import java.awt.Frame;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.piccolo2d.event.PInputEvent;
import org.piccolo2d.event.PInputEventListener;

import com.clearlyspam23.GLE.GUI.LayerEditorDialog;
import com.clearlyspam23.GLE.GUI.util.BasicEditorPanel;
import com.clearlyspam23.GLE.basic.layers.tile.commands.EraseTileCommand;
import com.clearlyspam23.GLE.basic.layers.tile.commands.PlaceTileCommand;
import com.clearlyspam23.GLE.basic.layers.tile.gui.TileEditorDialog;

public class TilesetEditorData implements ChangeListener, PInputEventListener{
	
//	private Tileset currentTileset;
//	
//	private int selectedX = -1;
//	private int selectedY = -1;
	
	private PInputEventListener currentEvent;
	
	private TileEditorDialog dialog;
	
	private List<PInputEventListener> allEvents = new ArrayList<PInputEventListener>();
	
	private List<LayerEditorDialog> dialogs = new ArrayList<LayerEditorDialog>();
	
	private List<TilesetHandle> tempTilesetStorage = new ArrayList<TilesetHandle>();
	
	public TilesetEditorData()
	{
		allEvents.add(new PlaceTileCommand(this));
		allEvents.add(new EraseTileCommand(this));
	}
	
	private void buildDialogs(Frame frame){
		dialogs.clear();
		dialog = new TileEditorDialog(frame);
		dialog.getPanel().addChangeListener(this);
		dialog.getPanel().selectButton(0);
		dialog.getTilesetSelectionPanel().addAllTilesets(tempTilesetStorage);
		dialogs.add(dialog);
		tempTilesetStorage.clear();
	}
	
	public List<LayerEditorDialog> getEditorDialogs(Frame f){
		if(dialogs.isEmpty()||!f.equals(dialogs.get(0).getParent()))
			buildDialogs(f);
		return dialogs;
	}
	
	public TilesetHandle getCurrentTileset() {
		return dialog.getTilesetSelectionPanel().getCurrentTileset();
	}
//	public void setCurrentTileset(Tileset currentTileset) {
//		dialog.getTilesetSelectionPanel().set
//	}
	public int getSelectedX() {
		return dialog.getTilesetSelectionPanel().getSelectedX();
	}
	public int getSelectedY(){
		return dialog.getTilesetSelectionPanel().getSelectedY();
	}
//	public void setSelectedIndex(int selectedX, int selectedY){
//		this.selectedX = selectedX;
//		this.selectedY = selectedY;
//	}
	
	public void addTileset(TilesetHandle t){
		if(dialog==null)
			tempTilesetStorage.add(t);
		else
			dialog.getTilesetSelectionPanel().addTileset(t);
	}
	
	public Image getSelectedTile(){
		return dialog.getTilesetSelectionPanel().getSelectedTile();
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

}
