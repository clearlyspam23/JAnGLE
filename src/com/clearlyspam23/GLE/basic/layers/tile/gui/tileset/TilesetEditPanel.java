package com.clearlyspam23.GLE.basic.layers.tile.gui.tileset;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import com.clearlyspam23.GLE.GUI.util.VectorComponent;
import com.clearlyspam23.GLE.basic.layers.tile.resources.TilesetFileHandle;
import com.clearlyspam23.GLE.basic.layers.tile.resources.TilesetHandle;

public class TilesetEditPanel extends JPanel {
	
	private JTextField nameField;
	private JTextField fileNameField;
	
	private VectorComponent tileSizeComponent;
	private VectorComponent tileSpaceComponent;
	
	private JPanel tilesetGridPanel;

	/**
	 * Create the panel.
	 */
	public TilesetEditPanel() {
		setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		add(scrollPane_1, BorderLayout.CENTER);
		
		tilesetGridPanel = new JPanel();
		scrollPane_1.setViewportView(tilesetGridPanel);
		tilesetGridPanel.setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel panel_2 = new JPanel();
		add(panel_2, BorderLayout.SOUTH);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[]{0, 235, 0, 0, 0};
		gbl_panel_2.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_panel_2.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panel_2.rowWeights = new double[]{0.0, 0.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		panel_2.setLayout(gbl_panel_2);
		
		JLabel lblName = new JLabel("Name");
		GridBagConstraints gbc_lblName = new GridBagConstraints();
		gbc_lblName.insets = new Insets(0, 0, 5, 5);
		gbc_lblName.anchor = GridBagConstraints.EAST;
		gbc_lblName.gridx = 0;
		gbc_lblName.gridy = 0;
		panel_2.add(lblName, gbc_lblName);
		
		nameField = new JTextField();
		GridBagConstraints gbc_nameField = new GridBagConstraints();
		gbc_nameField.insets = new Insets(0, 0, 5, 0);
		gbc_nameField.fill = GridBagConstraints.HORIZONTAL;
		gbc_nameField.gridx = 1;
		gbc_nameField.gridy = 0;
		panel_2.add(nameField, gbc_nameField);
		nameField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Tile Size");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 2;
		panel_2.add(lblNewLabel, gbc_lblNewLabel);
		
		JLabel lblImageFile = new JLabel("Image File");
		GridBagConstraints gbc_lblImageFile = new GridBagConstraints();
		gbc_lblImageFile.anchor = GridBagConstraints.EAST;
		gbc_lblImageFile.insets = new Insets(0, 0, 5, 5);
		gbc_lblImageFile.gridx = 0;
		gbc_lblImageFile.gridy = 1;
		panel_2.add(lblImageFile, gbc_lblImageFile);
		
		fileNameField = new JTextField();
		GridBagConstraints gbc_fileNameField = new GridBagConstraints();
		gbc_fileNameField.gridwidth = 2;
		gbc_fileNameField.insets = new Insets(0, 0, 5, 5);
		gbc_fileNameField.fill = GridBagConstraints.HORIZONTAL;
		gbc_fileNameField.gridx = 1;
		gbc_fileNameField.gridy = 1;
		panel_2.add(fileNameField, gbc_fileNameField);
		fileNameField.setColumns(10);
		
		JButton btnNewButton = new JButton("Browse");
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton.gridx = 3;
		gbc_btnNewButton.gridy = 1;
		panel_2.add(btnNewButton, gbc_btnNewButton);
		
		tileSizeComponent = new VectorComponent();
		GridBagConstraints gbc_tileSizeComponent = new GridBagConstraints();
		gbc_tileSizeComponent.insets = new Insets(0, 0, 5, 5);
		gbc_tileSizeComponent.fill = GridBagConstraints.BOTH;
		gbc_tileSizeComponent.gridx = 1;
		gbc_tileSizeComponent.gridy = 2;
		panel_2.add(tileSizeComponent, gbc_tileSizeComponent);
		
		JLabel lblTileSpacing = new JLabel("Tile Spacing");
		GridBagConstraints gbc_lblTileSpacing = new GridBagConstraints();
		gbc_lblTileSpacing.anchor = GridBagConstraints.EAST;
		gbc_lblTileSpacing.insets = new Insets(0, 0, 5, 5);
		gbc_lblTileSpacing.gridx = 0;
		gbc_lblTileSpacing.gridy = 3;
		panel_2.add(lblTileSpacing, gbc_lblTileSpacing);
		
		tileSpaceComponent = new VectorComponent();
		GridBagConstraints gbc_tileSpaceComponent = new GridBagConstraints();
		gbc_tileSpaceComponent.insets = new Insets(0, 0, 5, 5);
		gbc_tileSpaceComponent.fill = GridBagConstraints.BOTH;
		gbc_tileSpaceComponent.gridx = 1;
		gbc_tileSpaceComponent.gridy = 3;
		panel_2.add(tileSpaceComponent, gbc_tileSpaceComponent);
	}
	
	private void toggleFields(boolean flag){
		nameField.setEnabled(flag);
		fileNameField.setEnabled(flag);
		tileSizeComponent.setEnabled(flag);
		tileSpaceComponent.setEnabled(flag);
		if(!flag){
			tilesetGridPanel.removeAll();
			nameField.setText("");
			fileNameField.setText("");
			tileSizeComponent.setXField(0);
			tileSizeComponent.setYField(0);
			tileSpaceComponent.setXField(0);
			tileSpaceComponent.setYField(0);
		}
	}
	
	public void setToTileset(TilesetHandle tileset){
		toggleFields(tileset!=null);
		if(tileset!=null){
			nameField.setText(tileset.getName());
			fileNameField.setText(tileset.getFilename());
			tileSizeComponent.setXField(tileset.getTileWidth());
			tileSizeComponent.setYField(tileset.getTileHeight());
			tileSpaceComponent.setXField(tileset.getTileXSpacing());
			tileSpaceComponent.setYField(tileset.getTileYSpacing());
		}
	}
	
	public TilesetHandle getTileset(){
		TilesetFileHandle tileset = new TilesetFileHandle(nameField.getText(), fileNameField.getText(), 
				(int) tileSizeComponent.getVector().x, (int) tileSizeComponent.getVector().y, 
				(int) tileSpaceComponent.getVector().x, (int) tileSpaceComponent.getVector().y);
		return tileset;
	}

}
