package com.clearlyspam23.GLE.GUI.template;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.clearlyspam23.GLE.CoordinateSystem;
import com.clearlyspam23.GLE.PluginManager;
import com.clearlyspam23.GLE.Template;

public class GeneralPanel extends TemplateSubPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Create the panel.
	 */
	
	private List<CoordinateSystem> possibleCoordinates;
	private JTextField nameField;
	private JTextField locationField;
	private JTextField propsNameField;
	private JButton browseButton;
	private JTextField extensionField;
	
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
		
		final JComboBox<String> coordBox = new JComboBox<String>();
		coordBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(coordBox.getSelectedIndex()>=0&&coordBox.getSelectedIndex()<possibleCoordinates.size())
					imgLabel.setIcon(possibleCoordinates.get(coordBox.getSelectedIndex()).getHelperIcon());
			}
		});
		coordBox.setModel(new DefaultComboBoxModel<String>(model));
		coordBox.setSelectedIndex(model.length>0 ? 0 : -1);
		coordBox.setBounds(114, 140, 238, 20);
		add(coordBox);
		
		JLabel lblTemplateProperties = new JLabel("Template Properties");
		lblTemplateProperties.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTemplateProperties.setBounds(10, 11, 190, 20);
		add(lblTemplateProperties);
		
		JLabel lblTemplateName = new JLabel("Template Name");
		lblTemplateName.setBounds(20, 42, 84, 14);
		add(lblTemplateName);
		
		nameField = new JTextField();
		nameField.setBounds(114, 42, 383, 20);
		add(nameField);
		nameField.setColumns(10);
		
		JLabel lblFileLocation = new JLabel("File Location");
		lblFileLocation.setBounds(20, 77, 84, 14);
		add(lblFileLocation);
		
		locationField = new JTextField();
		locationField.setBounds(114, 74, 305, 20);
		add(locationField);
		locationField.setColumns(10);
		
		final JFileChooser fc = new JFileChooser();
		fc.setDialogType(JFileChooser.SAVE_DIALOG);
		FileNameExtensionFilter filter = new FileNameExtensionFilter("JAnGLE Templates (*.jant)", "jant");
		fc.setFileFilter(filter);
		browseButton = new JButton("Browse");
		browseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fc.setSelectedFile(new File(nameField.getText()));
				int ret = fc.showSaveDialog(GeneralPanel.this);
				if(ret==JFileChooser.APPROVE_OPTION){
					File f = fc.getSelectedFile();
					System.out.println(f);
				}
			}
		});
		browseButton.setBounds(430, 73, 67, 23);
		add(browseButton);
		
		final JCheckBox defaultLocCB = new JCheckBox("Use Default Location");
		defaultLocCB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				checkUseDefault((defaultLocCB.isSelected()));
			}
		});
		defaultLocCB.setBounds(114, 101, 128, 23);
		defaultLocCB.setSelected(true);
		add(defaultLocCB);
		checkUseDefault(defaultLocCB.isSelected());
		
		JLabel lblProperties = new JLabel("Properties");
		lblProperties.setBounds(20, 297, 67, 14);
		add(lblProperties);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.setBounds(114, 516, 76, 23);
		add(btnAdd);
		
		JButton btnRemove = new JButton("Remove");
		btnRemove.setBounds(200, 516, 76, 23);
		add(btnRemove);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(286, 297, 46, 14);
		add(lblName);
		
		propsNameField = new JTextField();
		propsNameField.setBounds(325, 294, 172, 20);
		add(propsNameField);
		propsNameField.setColumns(10);
		
		JLabel lblType = new JLabel("Type");
		lblType.setBounds(286, 322, 46, 14);
		add(lblType);
		
		JComboBox propsTypeField = new JComboBox();
		propsTypeField.setBounds(325, 319, 172, 20);
		add(propsTypeField);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(85, 295, 191, 209);
		add(scrollPane);
		
		JList propsList = new JList();
		scrollPane.setViewportView(propsList);
		
		JLabel lblOutputFormat = new JLabel("Level Format");
		lblOutputFormat.setBounds(20, 189, 84, 14);
		add(lblOutputFormat);
		
		JComboBox serializerBox = new JComboBox();
		serializerBox.setBounds(114, 186, 238, 20);
		add(serializerBox);
		
		JPanel propsPanel = new JPanel();
		propsPanel.setBounds(286, 347, 211, 157);
		add(propsPanel);
		
		JLabel lblExtension = new JLabel("File Extension");
		lblExtension.setBounds(20, 220, 76, 14);
		add(lblExtension);
		
		extensionField = new JTextField();
		extensionField.setBounds(114, 217, 86, 20);
		add(extensionField);
		extensionField.setColumns(10);
		
		final JCheckBox chckbxUseCustomExtension = new JCheckBox("Use Custom Extension");
		chckbxUseCustomExtension.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				checkCustomExtension((chckbxUseCustomExtension.isSelected()));
			}
		});
		chckbxUseCustomExtension.setBounds(206, 216, 146, 23);
		add(chckbxUseCustomExtension);
		checkCustomExtension(chckbxUseCustomExtension.isSelected());
	}
	
	private void checkUseDefault(boolean flag)
	{
		if(flag){
			// set output location to current directory or whatever the default is
		}
		locationField.setEditable(!flag);
		browseButton.setEnabled(!flag);
	}
	
	private void checkCustomExtension(boolean flag){
		extensionField.setEditable(flag);
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
