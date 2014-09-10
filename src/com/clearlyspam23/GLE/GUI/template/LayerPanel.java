package com.clearlyspam23.GLE.GUI.template;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.clearlyspam23.GLE.PluginManager;
import com.clearlyspam23.GLE.Template;
import com.clearlyspam23.GLE.GUI.SubPanel;
import com.clearlyspam23.GLE.level.LayerDefinition;
import com.clearlyspam23.GLE.level.LayerTemplate;
import com.clearlyspam23.GLE.util.Utility;

public class LayerPanel extends TemplateSubPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField nameField;
	private JScrollPane scrollPane;
	private JComboBox<String> typeBox;
	private JList<String> layerList;
	private DefaultListModel<String> layerListModel;
	
	@SuppressWarnings("rawtypes")
	private List<LayerDefinition> knownLayerDefs;
	
	private List<LayerWrapper> activeLayers = new ArrayList<LayerWrapper>();
	private List<SubPanel> layerPanels = new ArrayList<SubPanel>();
	private LayerWrapper currentLayer;
	
	private static class LayerWrapper{
		public LayerTemplate template;
		
		public LayerWrapper(LayerTemplate template, String name){
			this.template = template;
			template.setName(name);
		}
	}

	/**
	 * Create the panel.
	 */
	public LayerPanel(PluginManager pluginManager) {
		super(pluginManager);
		setLayout(null);
		
		this.knownLayerDefs = pluginManager.getRecognizedLayerDefs();
		
		for(LayerDefinition def : knownLayerDefs){
			layerPanels.add(def.getLayerComponent());
		}
		
		JLabel lblNewLabel = new JLabel("Layers");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(20, 11, 65, 17);
		add(lblNewLabel);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(200, 40, 37, 14);
		add(lblName);
		
		JLabel lblType = new JLabel("Type");
		lblType.setBounds(200, 65, 46, 14);
		add(lblType);
		
		nameField = new JTextField();
		nameField.setBounds(247, 37, 215, 20);
		add(nameField);
		nameField.setColumns(10);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(186, 90, 354, 451);
		add(scrollPane);
		
//		layerPanel = new JPanel();
//		scrollPane.setViewportView(layerPanel);
		
		typeBox = new JComboBox<String>();
		typeBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				propsPanel.removeAll();
//				SubPanel p = propertiesPanels.get(propsTypeField.getSelectedIndex());
//				p.reset();
//				propsPanel.add(p);
//				validate();
//				repaint();
				if(typeBox.getSelectedIndex()>=0){
//					scrollPane.setViewportView(null);
//					layerPanel.removeAll();
					SubPanel p = layerPanels.get(typeBox.getSelectedIndex());
					p.reset();
//					layerPanel.add(p);
					scrollPane.setViewportView(p);
					validate();
					repaint();
				}
			}
		});
		typeBox.setBounds(247, 62, 215, 20);
//		typeBox.setSelectedIndex(knownLayerDefs.size()>0 ? 0 : -1);
		add(typeBox);
		
		JButton btnCreate = new JButton("Create");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(typeBox.getSelectedIndex()>=0){
					int i = activeLayers.size();
					String defName = "Layer"+i;
					layerListModel.addElement(defName);
					layerList.setSelectedIndex(layerListModel.getSize()-1);
				}
			}
		});
		btnCreate.setBounds(20, 484, 72, 23);
		add(btnCreate);
		
		JButton btnNewButton = new JButton("Delete");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				removeLayer(layerList.getSelectedIndex());
				int i = layerList.getSelectedIndex();
				if(i>=0){
					currentLayer = null;
					activeLayers.remove(i);
					layerListModel.remove(i);
					if(layerListModel.isEmpty()){
//						layerPanel.removeAll();
						scrollPane.setViewportView(null);
						validate();
						repaint();
						layerList.setSelectedIndex(-1);
					}
					else{
						if(i-1<0)
							i = 1;
						layerList.setSelectedIndex(i-1);
					}
				}
			}
		});
		btnNewButton.setBounds(102, 484, 72, 23);
		add(btnNewButton);
		
		JButton btnUp = new JButton("Up");
		btnUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(layerList.getSelectedIndex()>0){
					int i = layerList.getSelectedIndex();
					LayerWrapper t = activeLayers.remove(i);
					activeLayers.add(i-1, t);
					buildCurrentProp();
					currentLayer = null;
					layerListModel.remove(i);
					currentLayer = null;
					layerListModel.insertElementAt(t.template.getName(), i-1);
					layerList.setSelectedIndex(i-1);
					validate();
					repaint();
				}
			}
		});
		btnUp.setBounds(20, 518, 72, 23);
		add(btnUp);
		
		JButton btnDown = new JButton("Down");
		btnDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(layerList.getSelectedIndex()<activeLayers.size()-1){
					int i = layerList.getSelectedIndex();
					LayerWrapper t = activeLayers.remove(i);
					activeLayers.add(i+1, t);
					buildCurrentProp();
					currentLayer = null;
					layerListModel.remove(i);
					currentLayer = null;
					layerListModel.insertElementAt(t.template.getName(), i+1);
					layerList.setSelectedIndex(i+1);
					validate();
					repaint();
				}
			}
		});
		btnDown.setBounds(102, 518, 72, 23);
		add(btnDown);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(20, 40, 156, 433);
		add(scrollPane_1);
		
		layerList = new JList<String>();
		layerList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				checkLayers();
			}
		});
		scrollPane_1.setViewportView(layerList);
		
		layerListModel = new DefaultListModel<String>();
		layerList.setModel(layerListModel);
		
		Utility.setModelTo(typeBox, knownLayerDefs);
		
		checkLayers();

	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void checkLayers(){
		boolean shouldBeEnabled = layerList.getSelectedIndex()>=0;
		if(currentLayer!=null){
			buildCurrentProp();
			layerListModel.setElementAt(nameField.getText(), activeLayers.indexOf(currentLayer));
		}
//		layerPanel.removeAll();
		scrollPane.setViewportView(null);
		nameField.setEnabled(shouldBeEnabled);
		typeBox.setEnabled(shouldBeEnabled);
		if(shouldBeEnabled){
			if(layerList.getSelectedIndex()==activeLayers.size()){
				int i = activeLayers.size();
				String defName = "Layer"+i;
				nameField.setText(defName);
				LayerDefinition def = knownLayerDefs.get(typeBox.getSelectedIndex());
				SubPanel sub = layerPanels.get(typeBox.getSelectedIndex());
				sub.reset();
				activeLayers.add(new LayerWrapper(def.buildFromEditorGUI(sub), defName));
			}
			currentLayer = activeLayers.get(layerList.getSelectedIndex());
			typeBox.setSelectedIndex(knownLayerDefs.indexOf(currentLayer.template.getDefinition()));
			currentLayer.template.getDefinition().setEditorGUITo((SubPanel) scrollPane.getViewport().getView(), currentLayer.template);
			nameField.setText(currentLayer.template.getName());
		}
		else
			nameField.setText("");
		validate();
		repaint();
	}
	
	@SuppressWarnings("unchecked")
	private void buildCurrentProp(){
		String text = nameField.getText();
		currentLayer.template = knownLayerDefs.get(typeBox.getSelectedIndex()).buildFromEditorGUI((SubPanel) scrollPane.getViewport().getView());
		currentLayer.template.setName(text);
	}
	
	private void buildFromActiveLayers(){
		currentLayer = null;
		layerListModel.removeAllElements();
		for(LayerWrapper t : activeLayers){
			layerListModel.addElement(t.template.getName());
		}
		if(activeLayers.isEmpty())
			currentLayer = null;
		else
			currentLayer = activeLayers.get(0);
		layerList.setSelectedIndex(activeLayers.size()> 0 ? 0 : -1);
	}
	
	public void setToTemplate(Template template)
	{
		activeLayers.clear();
		for(LayerTemplate t : template.getLayers()){
			activeLayers.add(new LayerWrapper(t, t.getName()));
		}
		buildFromActiveLayers();
	}

	@Override
	public String getPanelName() {
		return "Layers";
	}

	@Override
	public void generateTemplate(Template template) {
		if(currentLayer!=null)
			buildCurrentProp();
		for(LayerWrapper t : activeLayers){
			template.addLayerTemplate(t.template);
		}
	}

	@Override
	public void reset() {
		setToTemplate(new Template());
	}

	@Override
	public List<String> verify() {
		// TODO Auto-generated method stub
		return null;
	}

}
