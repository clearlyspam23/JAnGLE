package com.clearlyspam23.GLE.GUI.template;

import java.awt.Font;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.clearlyspam23.GLE.CoordinateSystem;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GeneralPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	
	private CoordinateSystem[] possibleCoordinates;
	
	public GeneralPanel(CoordinateSystem[] possibleCoords) {
		setLayout(null);
		
		this.possibleCoordinates = possibleCoords;
		
		String[] model = new String[possibleCoordinates.length];
		for(int i = 0; i < possibleCoordinates.length; i++)
			model[i] = possibleCoordinates[i].getName();
		
		JLabel label = new JLabel("Engine Properties");
		label.setFont(new Font("Tahoma", Font.BOLD, 14));
		label.setBounds(10, 11, 128, 20);
		add(label);
		
		JLabel label_1 = new JLabel("Level Properties");
		label_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_1.setBounds(10, 194, 128, 14);
		add(label_1);
		
		JLabel label_2 = new JLabel("Coordinate System");
		label_2.setBounds(10, 50, 117, 14);
		add(label_2);
		
		final JLabel imgLabel = new JLabel("");
		imgLabel.setBounds(312, 50, 128, 128);
		add(imgLabel);
		
		final JComboBox comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(comboBox.getSelectedIndex()>=0&&comboBox.getSelectedIndex()<possibleCoordinates.length)
					imgLabel.setIcon(possibleCoordinates[comboBox.getSelectedIndex()].getHelperIcon());
			}
		});
		comboBox.setModel(new DefaultComboBoxModel(model));
		comboBox.setSelectedIndex(0);
		comboBox.setBounds(125, 47, 162, 20);
		add(comboBox);

	}
}
