package com.clearlyspam23.GLE.GUI.template;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.clearlyspam23.GLE.CompressionFormat;
import com.clearlyspam23.GLE.CoordinateSystem;
import com.clearlyspam23.GLE.Nameable;
import com.clearlyspam23.GLE.PluginManager;
import com.clearlyspam23.GLE.PropertyDefinition;
import com.clearlyspam23.GLE.PropertyTemplate;
import com.clearlyspam23.GLE.Serializer;
import com.clearlyspam23.GLE.Template;
import com.clearlyspam23.GLE.GUI.SubPanel;

public class GeneralPanel extends TemplateSubPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Create the panel.
	 */
	
	private List<CoordinateSystem> possibleCoordinates;
	private List<CompressionFormat> possibleCompressions;
	private List<Serializer> possibleSerializers;
	@SuppressWarnings("rawtypes")
	private List<PropertyDefinition> possibleProperties;
	private JTextField nameField;
	private JTextField locationField;
	private JTextField propsNameField;
	private JButton browseButton;
	private JTextField extensionField;
	private JPanel propsPanel;
	private JList<String> propsList;
	
	private List<SubPanel> propertiesPanels = new ArrayList<SubPanel>();
	private List<PropWrapper> activeProperties = new ArrayList<PropWrapper>();
	
	private final JComboBox<String> coordBox;
	private final JComboBox<String> propsTypeField;
	private final JComboBox<String> serializerBox;
	private final JComboBox<String> compressionBox;
	
	private PropWrapper currentProp;
	
	private DefaultListModel<String> propsListModel;
	
	private class PropWrapper{
		@SuppressWarnings("rawtypes")
		public PropertyTemplate prop;
		
		@SuppressWarnings("rawtypes")
		public PropWrapper(PropertyTemplate prop){
			this.prop = prop;
		}
	}
	
//	private class PropPair{
//		@SuppressWarnings("rawtypes")
//		public PropertyTemplate prop;
//		@SuppressWarnings("rawtypes")
//		public PropertyDefinition def;
//		
//		public PropPair(){
//		}
//	}
	
	public GeneralPanel(PluginManager pluginManager) {
		super(pluginManager);
		setLayout(null);
		
		JLabel label_2 = new JLabel("Coordinate System");
		label_2.setBounds(20, 140, 117, 14);
		add(label_2);
		
		final JLabel imgLabel = new JLabel("");
		imgLabel.setBounds(369, 140, 128, 128);
		add(imgLabel);
		
		//get list of possible components
		this.possibleCoordinates = pluginManager.getRecognizedCoordinateSystems();
		this.possibleCompressions = pluginManager.getRecognizedCompressions();
		this.possibleSerializers = pluginManager.getRecognizedSerializers();
		this.possibleProperties = pluginManager.getRecognizedProperties();
		
		for(PropertyDefinition<?, ?> p : possibleProperties){
			propertiesPanels.add(p.getLayerComponent());
			System.out.println(p.getLayerComponent());
		}
		
		
		coordBox = new JComboBox<String>();
		coordBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(isValidIndex(coordBox, possibleCoordinates)){
					CoordinateSystem currentCoord = possibleCoordinates.get(coordBox.getSelectedIndex());
					imgLabel.setIcon(currentCoord.getHelperIcon());
				}
			}
		});
		coordBox.setBounds(114, 140, 238, 20);
		add(coordBox);
		
		JLabel lblTemplateProperties = new JLabel("General Properties");
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
		
		JLabel lblFileLocation = new JLabel("Template Location");
		lblFileLocation.setBounds(20, 77, 99, 14);
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
					locationField.setText(f.getAbsolutePath()+".jant");
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
		btnAdd.addActionListener(new ActionListener() {
			@SuppressWarnings({ "rawtypes", "unchecked" })
			public void actionPerformed(ActionEvent arg0) {
				int i = activeProperties.size();
				String defName = "Prop"+i;
				PropertyDefinition def = possibleProperties.get(propsTypeField.getSelectedIndex());
				activeProperties.add(new PropWrapper(def.buildFromGUI(propertiesPanels.get(propsTypeField.getSelectedIndex()), defName)));
				propsListModel.addElement(defName);
				propsList.setSelectedIndex(propsListModel.getSize()-1);
				propsNameField.setText(defName);
				propsTypeField.setSelectedIndex(propsTypeField.getSelectedIndex());
			}
		});
		btnAdd.setBounds(114, 516, 76, 23);
		add(btnAdd);
		
		JButton btnRemove = new JButton("Remove");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int i = propsList.getSelectedIndex();
				if(i>=0){
					currentProp = null;
					activeProperties.remove(propsList.getSelectedIndex());
					propsListModel.remove(propsList.getSelectedIndex());
					if(propsListModel.isEmpty()){
						propsPanel.removeAll();
						validate();
						repaint();
						propsList.setSelectedIndex(-1);
					}
					else{
						if(i<propsListModel.getSize())
							propsList.setSelectedIndex(i);
						else
							propsList.setSelectedIndex(i-1);
					}
				}
			}
		});
		btnRemove.setBounds(200, 516, 76, 23);
		add(btnRemove);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(286, 297, 46, 14);
		add(lblName);
		
		propsNameField = new JTextField();
		propsNameField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				propsListModel.setElementAt(propsNameField.getText(), propsList.getSelectedIndex());
			}
		});
		propsNameField.setBounds(325, 294, 172, 20);
		add(propsNameField);
		propsNameField.setColumns(10);
		
		JLabel lblType = new JLabel("Type");
		lblType.setBounds(286, 322, 46, 14);
		add(lblType);
		
		propsTypeField = new JComboBox<String>();
		propsTypeField.addActionListener(new ActionListener() {
			@SuppressWarnings("unchecked")
			public void actionPerformed(ActionEvent arg0) {
				propsPanel.removeAll();
				SubPanel p = propertiesPanels.get(propsTypeField.getSelectedIndex());
				propsPanel.add(p);
				validate();
				repaint();
			}
		});
		propsTypeField.setBounds(325, 319, 172, 20);
		add(propsTypeField);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(85, 295, 191, 209);
		add(scrollPane);
		
		propsList = new JList<String>();
		propsList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				checkPropsList();
			}
		});
		propsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		propsListModel = new DefaultListModel<String>();
		propsList.setModel(propsListModel);
		scrollPane.setViewportView(propsList);
		
		JLabel lblOutputFormat = new JLabel("Level Format");
		lblOutputFormat.setBounds(20, 174, 84, 14);
		add(lblOutputFormat);
		
		serializerBox = new JComboBox<String>();
		serializerBox.setBounds(114, 171, 238, 20);
		add(serializerBox);
		
		propsPanel = new JPanel();
		propsPanel.setBounds(286, 347, 211, 157);
		add(propsPanel);
		propsPanel.setLayout(new BoxLayout(propsPanel, BoxLayout.X_AXIS));
		
		JLabel lblExtension = new JLabel("Level Extension");
		lblExtension.setBounds(20, 205, 84, 14);
		add(lblExtension);
		
		extensionField = new JTextField();
		extensionField.setBounds(114, 202, 86, 20);
		add(extensionField);
		extensionField.setColumns(10);
		
		final JCheckBox chckbxUseCustomExtension = new JCheckBox("Use Custom Extension");
		chckbxUseCustomExtension.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				checkCustomExtension((chckbxUseCustomExtension.isSelected()));
			}
		});
		chckbxUseCustomExtension.setBounds(206, 198, 146, 23);
		add(chckbxUseCustomExtension);
		
		JLabel lblLevelCompression = new JLabel("Level Compression");
		lblLevelCompression.setBounds(20, 236, 99, 14);
		add(lblLevelCompression);
		
		compressionBox = new JComboBox<String>();
		compressionBox.setBounds(114, 233, 128, 20);
		add(compressionBox);
		
		//set up the models for the comboBoxes
		setModelTo(coordBox, possibleCoordinates);
		setModelTo(compressionBox, possibleCompressions);
		setModelTo(serializerBox, possibleSerializers);
		setModelTo(propsTypeField, possibleProperties);
		
		checkCustomExtension(chckbxUseCustomExtension.isSelected());
		checkPropsList();
	}
	
	private void setModelTo(JComboBox<String> box, List<? extends Nameable> namedList){
		String[] model = new String[namedList.size()];
		for(int i = 0; i < namedList.size(); i++)
			model[i] = namedList.get(i).getName();
		
		box.setModel(new DefaultComboBoxModel<String>(model));
		coordBox.setSelectedIndex(model.length>0 ? 0 : -1);
	}
	
	private boolean isValidIndex(JComboBox<?> box, List<?> list){
		return isValidIndex(box.getSelectedIndex(), list);
	}
	
	private boolean isValidIndex(int index, List<?> list){
		return index>=0&&index<list.size();
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
		if(!flag){
			extensionField.setText('.'+getValue(serializerBox, possibleSerializers).getDefaultExtension());
		}
		extensionField.setEditable(flag);
	}
	
	private <T> T getValue(JComboBox<?> box, List<T> list){
		return list.get(box.getSelectedIndex());
	}
	
	private <T> T tryGetValue(JComboBox<?> box, List<T> list){
		if(isValidIndex(box, list))
			return getValue(box, list);
		return null;
	}
	
	private <T> void trySetIndex(T t, List<T> list, JComboBox<?> box){
		int index = list.indexOf(t);
		if(index>=0)
			box.setSelectedIndex(index);
	}
	
	public void setToTemplate(Template template)
	{
		if(template!=null){
			nameField.setText(template.getTemplateName());
			locationField.setText(locationField.getText());
			extensionField.setText(template.getExtension());
			trySetIndex(template.getCompression(), possibleCompressions, compressionBox);
			trySetIndex(template.getCoordinateSystem(), possibleCoordinates, coordBox);
			trySetIndex(template.getSerializer(), possibleSerializers, serializerBox);
		}
	}
	
	private void checkPropsList(){
		boolean shouldBeEnabled = propsList.getSelectedIndex()>=0;
		if(currentProp!=null){
			buildCurrentProp();
			propsListModel.setElementAt(propsNameField.getText(), activeProperties.indexOf(currentProp));
		}
		propsPanel.removeAll();
		propsTypeField.setEnabled(shouldBeEnabled);
		propsNameField.setEnabled(shouldBeEnabled);
		if(shouldBeEnabled){
			currentProp = activeProperties.get(propsList.getSelectedIndex());
			propsTypeField.setSelectedIndex(possibleProperties.indexOf(currentProp.prop.getDefinition()));
			propsNameField.setText(currentProp.prop.getName());
		}
		else
			propsNameField.setText("");
		validate();
	}
	
	@SuppressWarnings("unchecked")
	private void buildCurrentProp(){
		String text = propsNameField.getText();
		currentProp.prop = possibleProperties.get(propsTypeField.getSelectedIndex()).buildFromGUI((SubPanel) propsPanel.getComponent(0), text);
	}

	@Override
	public String getPanelName() {
		return "Properties";
	}

	@Override
	public void generateTemplate(Template template) {
		template.setTemplateName(nameField.getText());
		template.setTemplateFile(new File(locationField.getText()));
		template.setExtension(extensionField.getText());
		template.setCompression(tryGetValue(compressionBox, possibleCompressions));
		template.setCoordinateSystem(tryGetValue(coordBox, possibleCoordinates));
		template.setSerializer(tryGetValue(serializerBox, possibleSerializers));
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<String> verify() {
		// TODO Auto-generated method stub
		return null;
	}
}
