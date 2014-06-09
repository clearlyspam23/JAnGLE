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
import javax.swing.JTextField;

public class GeneralPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Create the panel.
	 */
	
	private List<CoordinateSystem> possibleCoordinates;
	private JTextField textField;
	
	public GeneralPanel(List<CoordinateSystem> possibleCoords) {
		setLayout(null);
		
		this.possibleCoordinates = possibleCoords;
		
		String[] model = new String[possibleCoordinates.size()];
		for(int i = 0; i < possibleCoordinates.size(); i++)
			model[i] = possibleCoordinates.get(i).getName();
		
		JLabel label = new JLabel("Engine Properties");
		label.setFont(new Font("Tahoma", Font.BOLD, 14));
		label.setBounds(10, 158, 128, 20);
		add(label);
		
		JLabel label_2 = new JLabel("Coordinate System");
		label_2.setBounds(20, 189, 117, 14);
		add(label_2);
		
		final JLabel imgLabel = new JLabel("");
		imgLabel.setBounds(313, 186, 128, 128);
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
		comboBox.setBounds(125, 186, 162, 20);
		add(comboBox);
		
		JLabel lblTemplateProperties = new JLabel("Template Properties");
		lblTemplateProperties.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTemplateProperties.setBounds(10, 11, 190, 20);
		add(lblTemplateProperties);
		
		JLabel lblTemplateName = new JLabel("Template Name");
		lblTemplateName.setBounds(20, 42, 84, 14);
		add(lblTemplateName);
		
		textField = new JTextField();
		textField.setBounds(114, 42, 312, 20);
		add(textField);
		textField.setColumns(10);

	}
}
