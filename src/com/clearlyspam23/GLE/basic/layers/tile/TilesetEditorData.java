package com.clearlyspam23.GLE.basic.layers.tile;

import java.awt.Frame;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.piccolo2d.event.PInputEvent;
import org.piccolo2d.event.PInputEventListener;

import com.clearlyspam23.GLE.GUI.BasicEditorButton;
import com.clearlyspam23.GLE.GUI.BasicEditorDialog;
import com.clearlyspam23.GLE.GUI.LayerEditorDialog;
import com.clearlyspam23.GLE.basic.layers.tile.commands.EraseTileCommand;
import com.clearlyspam23.GLE.basic.layers.tile.commands.PlaceTileCommand;

public class TilesetEditorData implements ChangeListener, PInputEventListener{
	
	private Tileset currentTileset;
	
	private int selectedX = -1;
	private int selectedY = -1;
	
	private PInputEventListener currentEvent;
	
	private List<PInputEventListener> allEvents = new ArrayList<PInputEventListener>();
	
	private List<LayerEditorDialog> dialogs = new ArrayList<LayerEditorDialog>();
	
	public TilesetEditorData()
	{
		allEvents.add(new PlaceTileCommand(this));
		allEvents.add(new EraseTileCommand(this));
	}
	
	private void buildDialogs(Frame frame){
		BufferedImage pencilIcon = null;
		BufferedImage eraserIcon = null;
		try {
			File f = new File("images/Pencil.png");
			pencilIcon  = ImageIO.read(f);
			f = new File("images/Eraser.png");
			eraserIcon  = ImageIO.read(f);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(pencilIcon==null||eraserIcon==null)
		{
			System.err.println("unable to load the image");
			return;
		}
		dialogs.clear();
		BasicEditorDialog d = new BasicEditorDialog(frame, "Tiles", 
				new BasicEditorButton(new ImageIcon(pencilIcon), "Pencil", "Places Tiles"), 
				new BasicEditorButton(new ImageIcon(eraserIcon), "Eraser", "Removes Tiles"));
		d.addChangeListener(this);
		dialogs.add(d);
	}
	
	public List<LayerEditorDialog> getEditorDialogs(Frame f){
		if(dialogs.isEmpty()||!f.equals(dialogs.get(0).getParent()))
			buildDialogs(f);
		return dialogs;
	}
	
	public Tileset getCurrentTileset() {
		return currentTileset;
	}
	public void setCurrentTileset(Tileset currentTileset) {
		this.currentTileset = currentTileset;
	}
	public int getSelectedX() {
		return selectedX;
	}
	public int getSelectedY(){
		return selectedY;
	}
	public void setSelectedIndex(int selectedX, int selectedY){
		this.selectedX = selectedX;
		this.selectedY = selectedY;
	}
	
	public Image getSelectedTile(){
		if(currentTileset==null||!currentTileset.isValidLocation(selectedX, selectedY))
			return null;
		return currentTileset.getTileAt(selectedX, selectedY);
	}
	@Override
	public void stateChanged(ChangeEvent e) {
		int index = ((BasicEditorDialog)e.getSource()).getSelectedButtonIndex();
		if(index>=0&&index<allEvents.size())
			currentEvent = allEvents.get(index);
		else
			currentEvent = null;
		System.out.println(currentEvent);
	}

	@Override
	public void processEvent(PInputEvent arg0, int arg1) {
		if(currentEvent!=null)
			currentEvent.processEvent(arg0, arg1);
	}

}
