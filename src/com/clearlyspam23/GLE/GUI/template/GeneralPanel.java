package com.clearlyspam23.GLE.GUI.template;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.clearlyspam23.GLE.CoordinateSystem;
import com.clearlyspam23.GLE.PluginManager;
import com.clearlyspam23.GLE.Template;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JList;

public class GeneralPanel extends TemplateSubPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Create the panel.
	 */
	
	private List<CoordinateSystem> possibleCoordinates;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	
	public GeneralPanel(PluginManager pluginManager) {
		super(pluginManager);
		setLayout(null);
		
		this.possibleCoordinates = pluginManager.getRecognizedCoordinateSystems();
		
		String[] model = new String[possibleCoordinates.size()];
		for(int i = 0; i < possibleCoordinates.size(); i++)
			model[i] = possibleCoordinates.get(i).getName();
		
		JLabel label_2 = new JLabel("Coordinate System");
		label_2.setBounds(20, 140, 117, 14);
		add(label_2);
		
		final JLabel imgLabel = new JLabel("");
		imgLabel.setBounds(369, 140, 128, 128);
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
		comboBox.setBounds(114, 140, 238, 20);
		add(comboBox);
		
		JLabel lblTemplateProperties = new JLabel("Template Properties");
		lblTemplateProperties.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTemplateProperties.setBounds(10, 11, 190, 20);
		add(lblTemplateProperties);
		
		JLabel lblTemplateName = new JLabel("Template Name");
		lblTemplateName.setBounds(20, 42, 84, 14);
		add(lblTemplateName);
		
		textField = new JTextField();
		textField.setBounds(114, 42, 383, 20);
		add(textField);
		textField.setColumns(10);
		
		JLabel lblFileLocation = new JLabel("File Location");
		lblFileLocation.setBounds(20, 77, 84, 14);
		add(lblFileLocation);
		
		textField_1 = new JTextField();
		textField_1.setBounds(114, 74, 305, 20);
		add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnBrowse = new JButton("Browse");
		btnBrowse.setBounds(430, 73, 67, 23);
		add(btnBrowse);
		
		JCheckBox chckbxUseDefaultLocation = new JCheckBox("Use Default Location");
		chckbxUseDefaultLocation.setBounds(114, 101, 128, 23);
		add(chckbxUseDefaultLocation);
		
		JLabel lblProperties = new JLabel("Properties");
		lblProperties.setBounds(20, 297, 67, 14);
		add(lblProperties);
		
		JList list = new JList();
		list.setBounds(86, 296, 190, 209);
		add(list);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.setBounds(114, 516, 76, 23);
		add(btnAdd);
		
		JButton btnRemove = new JButton("Remove");
		btnRemove.setBounds(200, 516, 76, 23);
		add(btnRemove);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(286, 297, 46, 14);
		add(lblName);
		
		textField_2 = new JTextField();
		textField_2.setBounds(325, 294, 172, 20);
		add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblType = new JLabel("Type");
		lblType.setBounds(286, 322, 46, 14);
		add(lblType);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(325, 319, 172, 20);
		add(comboBox_1);
	}
	
	public void setToTemplate(Template template)
	{
		
	}

	@Override
	public String getPanelName() {
		return "Properties";
	}

	@Override
	public void generateTemplate(Template template) {
		// TODO Auto-generated method stub
		
	}
}
