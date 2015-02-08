package com.clearlyspam23.GLE.basic.layers.tile.gui;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;

import com.clearlyspam23.GLE.GUI.SubPanel;
import com.clearlyspam23.GLE.GUI.util.VectorComponent;
import com.clearlyspam23.GLE.util.Vector2;
import javax.swing.JComboBox;

public class TileLayerGUIOptions extends SubPanel {
	
	private VectorComponent vectorPanel;
	public TileLayerGUIOptions() {
		
		JLabel lblGridWidth = new JLabel("Grid Dimensions");
		lblGridWidth.setToolTipText("The width of the grid for this Tile Layer, in pixel coordinates (or similar)");
		lblGridWidth.setBounds(10, 15, 75, 14);
		
		JList list = new JList();
		list.setBounds(0, 0, 0, 0);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.setBounds(189, 228, 51, 23);
		
		JLabel lblConstraints = new JLabel("Constraints");
		lblConstraints.setBounds(10, 77, 55, 14);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(107, 75, 214, 142);
		
		JButton btnRemove = new JButton("Remove");
		btnRemove.setBounds(250, 228, 71, 23);
		
		JList list_1 = new JList();
		scrollPane.setViewportView(list_1);
		setLayout(null);
		add(lblGridWidth);
		add(list);
		add(btnAdd);
		add(lblConstraints);
		add(scrollPane);
		add(btnRemove);
		
		vectorPanel = new VectorComponent();
		vectorPanel.setBounds(106, 15, 215, 20);
		vectorPanel.setXField(16);
		vectorPanel.setYField(16);
		add(vectorPanel);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(107, 46, 214, 20);
		add(comboBox);
		
		JLabel lblExportFormat = new JLabel("Export Format");
		lblExportFormat.setBounds(10, 46, 87, 14);
		add(lblExportFormat);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void reset() {
		vectorPanel.setXField(0);
		vectorPanel.setYField(0);
	}
	
	public Vector2 getGridDimensions(){
		return vectorPanel.getVector();
	}
	
	public void setGridDimensions(Vector2 vec){
		vectorPanel.setToVector(vec);
	}
}
