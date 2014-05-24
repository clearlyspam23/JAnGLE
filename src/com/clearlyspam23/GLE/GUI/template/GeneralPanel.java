package com.clearlyspam23.GLE.GUI.template;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.clearlyspam23.GLE.CoordinateSystem;

public class GeneralPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Create the panel.
	 */
	
	private List<CoordinateSystem> possibleCoordinates;
	
	public GeneralPanel(List<CoordinateSystem> possibleCoords) {
		setLayout(null);
		
		this.possibleCoordinates = possibleCoords;
		
		String[] model = new String[possibleCoordinates.size()];
		for(int i = 0; i < possibleCoordinates.size(); i++)
			model[i] = possibleCoordinates.get(i).getName();
		
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
		
		final JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(comboBox.getSelectedIndex()>=0&&comboBox.getSelectedIndex()<possibleCoordinates.size())
					imgLabel.setIcon(possibleCoordinates.get(comboBox.getSelectedIndex()).getHelperIcon());
			}
		});
		comboBox.setModel(new DefaultComboBoxModel<String>(model));
		comboBox.setSelectedIndex(model.length>0 ? 0 : -1);
		comboBox.setBounds(125, 47, 162, 20);
		add(comboBox);

	}
}
