package com.clearlyspam23.GLE.basic.layers.tile.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.clearlyspam23.GLE.GUI.util.AspectRatioLayout;
import com.clearlyspam23.GLE.GUI.util.StretchIcon;
import com.clearlyspam23.GLE.basic.layers.tile.TilesetHandle;
import com.clearlyspam23.GLE.basic.layers.tile.resources.TilesetFileHandle;

public class TilesetSelectionPanel extends JPanel implements ComponentListener{
	
	private static final long serialVersionUID = 1L;
	
	private int selectedX;
	private int selectedY;
	private TilesetHandle currentTileset;
	
	private List<TilesetHandle> tilesets = new ArrayList<TilesetHandle>();
	
	private JScrollPane scrollPane;
	private JPanel panel;
	private JPanel panel_1;
	private JLabel lblTileset;
	private JComboBox<String> comboBox;
	private List<JButton> buttons = new ArrayList<JButton>();

	/**
	 * Create the panel.
	 */
	public TilesetSelectionPanel() {
		setLayout(new BorderLayout(5, 5));
		
		scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
		
		panel = new JPanel();
		JPanel parentPanel = new JPanel(new AspectRatioLayout());
		parentPanel.add(panel);
		scrollPane.setViewportView(parentPanel);
//		panel.setLayout(new GridLayout(1, 0, 0, 0));
		
		panel_1 = new JPanel();
		add(panel_1, BorderLayout.NORTH);
		panel_1.setLayout(new BorderLayout(5, 5));
		
		lblTileset = new JLabel("Tileset");
		panel_1.add(lblTileset, BorderLayout.WEST);
		
		comboBox = new JComboBox<String>();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int i = comboBox.getSelectedIndex();
				setToTileset(i);
			}
		});
		panel_1.add(comboBox, BorderLayout.CENTER);
		panel.addComponentListener(this);
		this.setPreferredSize(new Dimension(160, 200));

	}
	
	private void setToTileset(int index){
		if(index>=0&&index<tilesets.size()){
			TilesetHandle t = tilesets.get(index);
			if(!t.equals(currentTileset)){
				selectedX = -1;
				selectedY = -1;
			}
			setToTileset(tilesets.get(index));
		}
		else{
			currentTileset = null;
			selectedX = -1;
			selectedY = -1;
		}
	}
	
	private void setToTileset(TilesetHandle tileset){
		currentTileset = tileset;
		selectedX = -1;
		selectedY = -1;
		panel.removeAll();
		buttons.clear();
		panel.setLayout(new GridLayout(tileset.getWidth(), tileset.getHeight()));
		int width = 0;
		int height = 0;
		for(int j = 0; j < tileset.getHeight(); j++){
			int tempWidth = 0;
			int tempHeight = 0;
			for(int i = 0; i < tileset.getWidth(); i++){
				final int x = i;
				final int y = j;
				ImageIcon ico = new StretchIcon(tileset.getTileAt(i, j), true);
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
				button.addActionListener(new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent arg0) {
						for(JButton b : buttons)
							b.setSelected(false);
						((JButton)arg0.getSource()).setSelected(true);
						selectedX = x;
						selectedY = y;
					}
					
				});
				button.setLayout(null);
				button.setBorderPainted(false);
				button.setContentAreaFilled(false);
				panel.add(button);
				buttons.add(button);
				tempWidth+=tileset.getTileAt(i, j).getWidth(null);
				tempHeight = Math.max(tempHeight, tileset.getTileAt(i, j).getHeight(null));
			}
			width = Math.max(tempWidth, width);
			height+=tempHeight;
		}
		panel.setPreferredSize(new Dimension(width, height));
		if(tileset.getWidth()>0)
		{
			((JButton)panel.getComponent(0)).doClick();
		}
	}
	
	public TilesetHandle getCurrentTileset(){
		return currentTileset;
	}
	
	public int getSelectedX(){
		return selectedX;
	}
	
	public int getSelectedY(){
		return selectedY;
	}
	
	public void addTileset(TilesetHandle tileset){
		tilesets.add(tileset);
		updateComboBox();
	}
	
	public void addAllTilesets(List<TilesetHandle> tilesets){
		this.tilesets.addAll(tilesets);
		updateComboBox();
	}
	
	public void removeTileset(TilesetHandle tileset){
		tilesets.remove(tileset);
		updateComboBox();
	}
	
	public void removeTilesets(List<TilesetHandle> tilesets){
		this.tilesets.removeAll(tilesets);
		updateComboBox();
	}
	
	private void updateComboBox(){
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
		int foundIndex = -1;
		for(int i = 0; i < tilesets.size(); i++){
			TilesetHandle t = tilesets.get(i);
			model.addElement(t.getName());
			if(t.equals(currentTileset))
				foundIndex = i;
		}
		comboBox.setModel(model);
		if(foundIndex>=0){
			comboBox.setSelectedIndex(foundIndex);
		}
		else if(model.getSize()>0){
			setToTileset(0);
		}
		else{
			currentTileset = null;
			selectedX = -1;
			selectedY = -1;
		}
		this.revalidate();
		this.repaint();
	}
	
	public Image getSelectedTile(){
		if(currentTileset!=null&&selectedX>=0&&selectedX<currentTileset.getWidth()&&selectedY>=0&&selectedY<currentTileset.getHeight())
			return currentTileset.getTileAt(selectedX, selectedY);
		return null;
	}
	
	public JPanel getPanel(){
		return panel;
	}

	@Override
	public void componentHidden(ComponentEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentMoved(ComponentEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentResized(ComponentEvent arg0) {
		
	}

	@Override
	public void componentShown(ComponentEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void clearTilesets() {
		tilesets.clear();
		currentTileset = null;
		updateComboBox();
	}

}
