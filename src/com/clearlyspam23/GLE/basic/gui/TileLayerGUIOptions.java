package com.clearlyspam23.GLE.basic.gui;

import java.awt.Container;
import javax.swing.JLabel;
import com.clearlyspam23.GLE.GUI.util.VectorPanel;
import javax.swing.JList;
import javax.swing.JButton;

public class TileLayerGUIOptions extends Container {
	public TileLayerGUIOptions() {
		
		JLabel lblGridWidth = new JLabel("Grid Dimensions");
		lblGridWidth.setToolTipText("The width of the grid for this Tile Layer, in pixel coordinates (or similar)");
		lblGridWidth.setBounds(10, 13, 85, 14);
		add(lblGridWidth);
		
		VectorPanel vectorPanel = new VectorPanel();
		vectorPanel.setBounds(95, 13, 198, 20);
		add(vectorPanel);
		
		JLabel lblConstraints = new JLabel("Constraints");
		lblConstraints.setBounds(10, 60, 69, 14);
		add(lblConstraints);
		
		JList list = new JList();
		list.setBounds(94, 59, 199, 182);
		add(list);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.setBounds(95, 252, 89, 23);
		add(btnAdd);
		
		JButton btnRemove = new JButton("Remove");
		btnRemove.setBounds(204, 252, 89, 23);
		add(btnRemove);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
}
