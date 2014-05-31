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
		lblGridWidth.setBounds(10, 13, 85, 14);
		
		JList list = new JList();
		list.setBounds(94, 59, 199, 182);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.setBounds(95, 252, 89, 23);
		
		JLabel lblConstraints = new JLabel("Constraints");
		lblConstraints.setBounds(10, 60, 69, 14);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JButton btnRemove = new JButton("Remove");
		btnRemove.setBounds(204, 252, 89, 23);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(list)
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
							.addGroup(groupLayout.createSequentialGroup()
								.addContainerGap()
								.addComponent(btnAdd)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(btnRemove))
							.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
								.addContainerGap()
								.addComponent(lblConstraints)
								.addGap(42)
								.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 254, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblGridWidth)))
					.addContainerGap(79, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(list)
					.addGap(15)
					.addComponent(lblGridWidth)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 142, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnRemove)
								.addComponent(btnAdd)))
						.addComponent(lblConstraints))
					.addGap(178))
		);
		
		JList list_1 = new JList();
		scrollPane.setViewportView(list_1);
		setLayout(groupLayout);
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
