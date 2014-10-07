package com.clearlyspam23.GLE.basic.layers.tile.gui.tileset;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import com.clearlyspam23.GLE.GUI.util.AspectRatioLayout;
import com.clearlyspam23.GLE.GUI.util.StretchIcon;
import com.clearlyspam23.GLE.basic.layers.tile.TilesetHandle;

public class TilesetViewPanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel panel;
	
	private List<JButton> buttons = new ArrayList<JButton>();
	private List<TilesetViewSelectionListener> listeners = new ArrayList<TilesetViewSelectionListener>();
	private TilesetHandle currentTileset;
	
	private boolean enableSelection = false;
	private boolean enableBorder = false;

	/**
	 * Create the panel.
	 */
	public TilesetViewPanel() {
		
		super(new AspectRatioLayout());
		
		panel = new JPanel();
		add(panel);
		//setMinimumSize(new Dimension(0, 0));

	}
	
	public void toggleButtonSelection(boolean flag){
		enableSelection = flag;
	}
	
	public boolean allowsButtonSelection(){
		return enableSelection;
	}
	
	public void toggleBorders(boolean flag){
		enableBorder = flag;
		if(flag)
			for(JButton button : buttons)
				button.setBorder(BorderFactory.createLineBorder(Color.BLACK, 0, false));
		else
			for(JButton button : buttons)
				button.setBorder(BorderFactory.createEmptyBorder());
	}
	
	public boolean usesBorders(){
		return enableBorder;
	}
	
	public void setToTileset(TilesetHandle handle){
		currentTileset = handle;
		panel.removeAll();
		buttons.clear();
		if(handle==null){
			return;
		}
		panel.setLayout(new GridLayout(handle.getHeight(), handle.getWidth()));
		int width = 0;
		int height = 0;
		for(int j = 0; j < handle.getHeight(); j++){
			int tempWidth = 0;
			int tempHeight = 0;
			for(int i = 0; i < handle.getWidth(); i++){
				final int x = i;
				final int y = j;
				ImageIcon ico = new StretchIcon(handle.getTileAt(i, j), true);
				JButton button = new JButton(ico){
					private static final long serialVersionUID = 1L;
					public void setSelected(boolean flag){
						super.setSelected(flag);
						if(flag){
							setBorderPainted(true);
						}
						else {
							setBorderPainted(false);
						}
					}
					
				};
				if(enableBorder)
					button.setBorder(BorderFactory.createLineBorder(Color.black, 0));
				button.addActionListener(new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent arg0) {
						if(enableSelection)
							selectTile(x, y, arg0);
//						selectedX = x;
//						selectedY = y;
					}
					
				});
				button.setLayout(null);
				button.setBorderPainted(false);
				button.setContentAreaFilled(false);
				panel.add(button);
				buttons.add(button);
				tempWidth+=handle.getTileAt(i, j).getWidth(null);
				tempHeight = Math.max(tempHeight, handle.getTileAt(i, j).getHeight(null));
			}
			width = Math.max(tempWidth, width);
			height+=tempHeight;
		}
		panel.setPreferredSize(new Dimension(width, height));
	}
	
//	private void setToTileset(TilesetHandle tileset){
//	currentTileset = tileset;
//	selectedX = -1;
//	selectedY = -1;
//	panel.removeAll();
//	buttons.clear();
//	panel.setLayout(new GridLayout(tileset.getWidth(), tileset.getHeight()));
//	int width = 0;
//	int height = 0;
//	for(int j = 0; j < tileset.getHeight(); j++){
//		int tempWidth = 0;
//		int tempHeight = 0;
//		for(int i = 0; i < tileset.getWidth(); i++){
//			final int x = i;
//			final int y = j;
//			ImageIcon ico = new StretchIcon(tileset.getTileAt(i, j), true);
//			JButton button = new JButton(ico){
//				private static final long serialVersionUID = 1L;
//				public void setSelected(boolean flag){
//					super.setSelected(flag);
//					if(flag){
//						setBorderPainted(true);
//					}
//					else {
//						setBorderPainted(false);
//					}
//				}
//				
//			};
//			button.addActionListener(new ActionListener(){
//
//				@Override
//				public void actionPerformed(ActionEvent arg0) {
//					for(JButton b : buttons)
//						b.setSelected(false);
//					((JButton)arg0.getSource()).setSelected(true);
//					selectedX = x;
//					selectedY = y;
//				}
//				
//			});
//			button.setLayout(null);
//			button.setBorderPainted(false);
//			button.setContentAreaFilled(false);
//			panel.add(button);
//			buttons.add(button);
//			tempWidth+=tileset.getTileAt(i, j).getWidth(null);
//			tempHeight = Math.max(tempHeight, tileset.getTileAt(i, j).getHeight(null));
//		}
//		width = Math.max(tempWidth, width);
//		height+=tempHeight;
//	}
//	panel.setPreferredSize(new Dimension(width, height));
//	if(tileset.getWidth()>0)
//	{
//		((JButton)panel.getComponent(0)).doClick();
//	}
//}
	
	public void selectTile(int x, int y){
		selectTile(x, y, null);
	}
	
	public void selectTile(int x, int y, ActionEvent e){
		int index = y*currentTileset.getWidth() + x;
		for(JButton b : buttons)
			b.setSelected(false);
		buttons.get(index).setSelected(true);
		for(TilesetViewSelectionListener l : listeners){
			l.onSelection(x, y, e);
		}
	}
	
	public void addListener(TilesetViewSelectionListener listener){
		listeners.add(listener);
	}
	
	public void removeListener(TilesetViewSelectionListener listener){
		listeners.remove(listener);
	}

}
