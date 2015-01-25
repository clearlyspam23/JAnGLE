package com.clearlyspam23.GLE.basic.layers.tile.edit;

import java.awt.Image;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.piccolo2d.PInputManager;
import org.piccolo2d.event.PInputEvent;
import org.piccolo2d.event.PInputEventListener;
import org.piccolo2d.util.PBounds;

import com.clearlyspam23.GLE.GUI.util.BasicEditorButton;
import com.clearlyspam23.GLE.GUI.util.BasicEditorPanel;
import com.clearlyspam23.GLE.basic.layers.tile.Tile;
import com.clearlyspam23.GLE.basic.layers.tile.TileLayer;
import com.clearlyspam23.GLE.basic.layers.tile.TileLocation;
import com.clearlyspam23.GLE.basic.layers.tile.TilesetHandle;
import com.clearlyspam23.GLE.basic.layers.tile.edit.actions.CutSelectionAction;
import com.clearlyspam23.GLE.basic.layers.tile.edit.commands.EraseTileCommand;
import com.clearlyspam23.GLE.basic.layers.tile.edit.commands.FloodFillTileCommand;
import com.clearlyspam23.GLE.basic.layers.tile.edit.commands.PlaceTileCommand;
import com.clearlyspam23.GLE.basic.layers.tile.edit.commands.TileSelectCommand;
import com.clearlyspam23.GLE.basic.layers.tile.edit.menu.AnchorMenuItem;
import com.clearlyspam23.GLE.basic.layers.tile.edit.menu.GridMenuItem;
import com.clearlyspam23.GLE.basic.layers.tile.gui.MovableTileSelection;
import com.clearlyspam23.GLE.basic.layers.tile.gui.TileLayerSelectionListener;
import com.clearlyspam23.GLE.basic.layers.tile.gui.TileSelection;
import com.clearlyspam23.GLE.basic.layers.tile.gui.TilesetSelectionPanel;
import com.clearlyspam23.GLE.edit.LayerEditManager;
import com.clearlyspam23.GLE.edit.LayerMenuItem;

public class TileLayerEditManager extends LayerEditManager<TileLayer> implements ChangeListener, PInputEventListener, TileLayerSelectionListener{
	
//	private Tileset currentTileset;
//	
//	private int selectedX = -1;
//	private int selectedY = -1;
	
	private PInputEventListener currentEvent;
	
	private GridMenuItem gridItem;
	private AnchorMenuItem anchorItem;
	
	private List<PInputEventListener> allEvents = new ArrayList<PInputEventListener>();
	
	private TilesetSelectionPanel selectionPanel;
	
	private List<Tile> cutSelection;
	private int selectionWidth;
	private int selectionHeight;
	
	public TileLayerEditManager()
	{
		gridItem = new GridMenuItem();
		anchorItem = new AnchorMenuItem(this);
		allEvents.add(new PlaceTileCommand(this));
		allEvents.add(new EraseTileCommand(this));
		allEvents.add(new FloodFillTileCommand(this));
		allEvents.add(new TileSelectCommand(this));
		selectionPanel = new TilesetSelectionPanel();
		BasicEditorPanel panel = new BasicEditorPanel
				(new BasicEditorButton("images/Pencil.png", "Pencil", "Places Tiles"), 
				new BasicEditorButton("images/Eraser.png", "Eraser", "Removes Tiles"),
				new BasicEditorButton("images/Bucket.png", "Bucket", "Fill Tiles"),
				new BasicEditorButton("images/FingerPointer.png", "Pointer", "Select Tiles"));
		panel.addChangeListener(this);
		panel.selectButton(0);
		setMainComponent(panel, "Tile Editor");
		addSubComponents(selectionPanel, "Tileset");
	}
	
	public TilesetHandle getCurrentTileset() {
		return selectionPanel.getCurrentTileset();
	}

	public int getSelectedX() {
		return selectionPanel.getSelectedX();
	}
	public int getSelectedY(){
		return selectionPanel.getSelectedY();
	}
	
	public void addTileset(TilesetHandle t){
		selectionPanel.addTileset(t);
	}
	
	public void clearTilesets(){
		selectionPanel.clearTilesets();
	}
	
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
	
	public void onActive(TileLayer layer){
		layer.addSelectionListener(this);
		checkCopyCut(layer);
	}
	
	public void onLayerChange(TileLayer oldLayer, TileLayer newLayer){
		oldLayer.removeSelectionListener(this);
		newLayer.addSelectionListener(this);
		checkCopyCut(newLayer);
	}
	
	public void onInActive(TileLayer layer){
		layer.removeSelectionListener(this);
	}
	
	private void checkCopyCut(TileLayer layer){
		if(layer.canCopy()!=canCopy())
			toggleCanCopy(layer.canCopy());
		if(layer.canCut()!=canCut())
			toggleCanCut(layer.canCut());
	}
	
	public void onCopy(TileLayer currentLayer){
		selectionWidth = currentLayer.getBase().getSelection().getTileWidth();
		selectionHeight = currentLayer.getBase().getSelection().getTileHeight();
		cutSelection = currentLayer.getBase().getSelection().onCopy();
		if(canPaste()!=(cutSelection!=null))
			toggleCanPaste(cutSelection!=null);
	}
	
	public void onCut(TileLayer currentLayer){
		selectionWidth = currentLayer.getBase().getSelection().getTileWidth();
		selectionHeight = currentLayer.getBase().getSelection().getTileHeight();
		cutSelection = currentLayer.getBase().getSelection().onCopy();
		TileSelection selection = currentLayer.getBase().getSelection();
		selection.onClear();
		currentLayer.getBase().anchorSelection();
		this.registerEditAction(new CutSelectionAction(cutSelection, selection, currentLayer.getBase()));
		if(canPaste()!=(cutSelection!=null))
			toggleCanPaste(cutSelection!=null);
	}
	
	public void onPaste(TileLayer currentLayer){
		PInputManager inputManager = currentLayer.getBase().getRoot().getDefaultInputManager();
		Point2D p = (Point2D) inputManager.getCurrentCanvasPosition().clone();
		PBounds globalBounds = inputManager.getMouseOver().getBottomCamera().getGlobalBounds();
		if(!globalBounds.contains(p))
			p = new Point2D.Double(globalBounds.width/2+globalBounds.x, globalBounds.height/2+globalBounds.y);
		p = inputManager.getMouseOver().getBottomCamera().localToView(p);
		TileLocation l = new TileLocation((int) (p.getX()/currentLayer.getBase().getTiles().getGridWidth()), (int) (p.getY()/currentLayer.getBase().getTiles().getGridHeight()));
		l.gridX-=selectionWidth/2;
		l.gridY-=selectionHeight/2;
		MovableTileSelection selection = new MovableTileSelection(inputManager.getMouseOver().getBottomCamera(), currentLayer.getBase().getTiles(), cutSelection, l);
		currentLayer.getBase().setSelectionWithAction(selection, this);
	}

	@Override
	public void selectionChange(TileLayer layer, TileSelection oldSelection,
			TileSelection newSelection) {
		checkCopyCut(layer);
	}
	
	@Override
	public List<LayerMenuItem<TileLayer, ?>> getLayerItems(TileLayer layer) {
		List<LayerMenuItem<TileLayer, ?>> items = new ArrayList<LayerMenuItem<TileLayer, ?>>();
		items.add(gridItem);
		items.add(anchorItem);
		return items;
	}

}
