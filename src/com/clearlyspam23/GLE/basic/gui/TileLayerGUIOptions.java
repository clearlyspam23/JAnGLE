package com.clearlyspam23.GLE.basic.gui;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;

import com.clearlyspam23.GLE.GUI.SubPanel;
import com.clearlyspam23.GLE.GUI.util.VectorPanel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JScrollPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;

public class TileLayerGUIOptions extends SubPanel {
	public TileLayerGUIOptions() {
		
		JLabel lblGridWidth = new JLabel("Grid Dimensions");
		lblGridWidth.setToolTipText("The width of the grid for this Tile Layer, in pixel coordinates (or similar)");
		lblGridWidth.setBounds(10, 15, 75, 14);
		
		JList list = new JList();
		list.setBounds(0, 0, 0, 0);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.setBounds(229, 210, 51, 23);
		
		JLabel lblConstraints = new JLabel("Constraints");
		lblConstraints.setBounds(10, 59, 55, 14);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(107, 57, 254, 142);
		
		JButton btnRemove = new JButton("Remove");
		btnRemove.setBounds(290, 210, 71, 23);
		
		JList list_1 = new JList();
		scrollPane.setViewportView(list_1);
		setLayout(null);
		add(lblGridWidth);
		add(list);
		add(btnAdd);
		add(lblConstraints);
		add(scrollPane);
		add(btnRemove);
		
		VectorPanel vectorPanel = new VectorPanel();
		vectorPanel.setBounds(106, 15, 198, 20);
		add(vectorPanel);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}
}
