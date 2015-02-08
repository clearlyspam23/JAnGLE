package com.clearlyspam23.GLE.basic.layers.tile.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.clearlyspam23.GLE.basic.layers.tile.TilesetHandle;
import com.clearlyspam23.GLE.basic.layers.tile.gui.tileset.TilesetViewPanel;
import com.clearlyspam23.GLE.basic.layers.tile.gui.tileset.TilesetViewSelectionListener;

public class TilesetSelectionPanel extends JPanel implements TilesetViewSelectionListener{
	
	private static final long serialVersionUID = 1L;
	
	private int selectedX;
	private int selectedY;
	private TilesetHandle currentTileset;
	
	private List<TilesetHandle> tilesets = new ArrayList<TilesetHandle>();
	
	private JScrollPane scrollPane;
	private TilesetViewPanel tilesetView;
//	private JPanel panel;
	private JPanel panel_1;
	private JLabel lblTileset;
	private JComboBox<String> comboBox;

	/**
	 * Create the panel.
	 */
	public TilesetSelectionPanel() {
		setLayout(new BorderLayout(5, 5));
		
		scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
		
		tilesetView = new TilesetViewPanel();
		tilesetView.toggleButtonSelection(true);
		tilesetView.toggleBorders(true);
		tilesetView.addListener(this);
		
//		panel = new JPanel();
//		JPanel parentPanel = new JPanel(new AspectRatioLayout());
//		parentPanel.add(panel);
		scrollPane.setViewportView(tilesetView);
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
		this.setPreferredSize(new Dimension(160, 200));

	}
	
	private void setToTileset(int index){
		if(index>=0&&index<tilesets.size()){
			TilesetHandle t = tilesets.get(index);
			if(!t.equals(currentTileset)){
				selectedX = -1;
				selectedY = -1;
			}
			currentTileset = t;
			tilesetView.setToTileset(tilesets.get(index));
			if(currentTileset.getWidth()>0)
				tilesetView.selectTile(0, 0);
		}
		else{
			currentTileset = null;
			selectedX = -1;
			selectedY = -1;
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

	public void clearTilesets() {
		tilesets.clear();
		currentTileset = null;
		updateComboBox();
	}

	@Override
	public void onSelection(int x, int y, ActionEvent e) {
		selectedX = x;
		selectedY = y;
	}

}
