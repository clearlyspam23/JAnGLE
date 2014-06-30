package com.clearlyspam23.GLE.basic.layers.tile.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.clearlyspam23.GLE.GUI.util.DockablePanel;
import com.clearlyspam23.GLE.basic.layers.tile.Tileset;

public class TilesetSelectionPanel extends DockablePanel {
	
	private static final long serialVersionUID = 1L;
	
	private int selectedX;
	private int selectedY;
	private Tileset currentTileset;
	
	private List<Tileset> tilesets = new ArrayList<Tileset>();
	
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
		setLayout(new BorderLayout(0, 0));
		
		scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
		
		panel = new JPanel();
		scrollPane.setViewportView(panel);
//		panel.setLayout(new GridLayout(1, 0, 0, 0));
		
		panel_1 = new JPanel();
		add(panel_1, BorderLayout.NORTH);
		panel_1.setLayout(new BorderLayout(0, 0));
		
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

	}
	
	private void setToTileset(int index){
		if(index>=0&&index<tilesets.size()){
			Tileset t = tilesets.get(index);
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
	
	private void setToTileset(Tileset tileset){
		currentTileset = tileset;
		selectedX = -1;
		selectedY = -1;
		panel.removeAll();
		buttons.clear();
		System.out.println(tileset.getWidth() + ", " +  tileset.getHeight());
		panel.setLayout(new GridLayout(tileset.getWidth(), tileset.getHeight()));
		for(int i = 0; i < tileset.getWidth(); i++){
			for(int j = 0; j < tileset.getHeight(); j++){
				final int x = i;
				final int y = j;
				JButton button = new JButton(new ImageIcon(tileset.getTileAt(i, j))){
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
			}
		}
	}
	
	public Tileset getCurrentTileset(){
		return currentTileset;
	}
	
	public int getSelectedX(){
		return selectedX;
	}
	
	public int getSelectedY(){
		return selectedY;
	}
	
	public void addTileset(Tileset tileset){
		tilesets.add(tileset);
		updateComboBox();
	}
	
	public void addAllTilesets(List<Tileset> tilesets){
		this.tilesets.addAll(tilesets);
		updateComboBox();
	}
	
	public void removeTileset(Tileset tileset){
		tilesets.remove(tileset);
		updateComboBox();
	}
	
	public void removeTilesets(List<Tileset> tilesets){
		this.tilesets.removeAll(tilesets);
		updateComboBox();
	}
	
	private void updateComboBox(){
		System.out.println("here");
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
		int foundIndex = -1;
		for(int i = 0; i < tilesets.size(); i++){
			Tileset t = tilesets.get(i);
			model.addElement(t.getName());
			if(t.equals(currentTileset))
				foundIndex = i;
		}
		comboBox.setModel(model);
		if(foundIndex>=0){
			comboBox.setSelectedIndex(foundIndex);
		}
		else if(model.getSize()>0){
			System.out.println("probably here");
			setToTileset(0);
		}
		else{
			System.out.println("shouldnt be here");
			currentTileset = null;
			selectedX = -1;
			selectedY = -1;
		}
		this.revalidate();
		this.repaint();
	}

	@Override
	public String getName() {
		return "Tileset";
	}
	
	public Image getSelectedTile(){
		if(currentTileset!=null&&selectedX>=0&&selectedX<currentTileset.getWidth()&&selectedY>=0&&selectedY<currentTileset.getHeight())
			return currentTileset.getTileAt(selectedX, selectedY);
		return null;
	}

}
