package com.clearlyspam23.GLE.GUI.level;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.clearlyspam23.GLE.PropertyTemplate;
import com.clearlyspam23.GLE.Template;
import com.clearlyspam23.GLE.GUI.util.VectorComponent;
import com.clearlyspam23.GLE.level.Level;

public class LevelPropertyPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private HashMap<String, Component> properties = new HashMap<String, Component>();
	
	@SuppressWarnings("rawtypes")
	private HashMap<String, PropertyTemplate> templates = new HashMap<String, PropertyTemplate>();
	
	private VectorComponent dimensions;
	
//	private Template template;

	@SuppressWarnings("rawtypes")
	public LevelPropertyPanel(Template template) {
//		this.template = template;
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblNewLabel = new JLabel("Dimensions");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		add(lblNewLabel, gbc_lblNewLabel);
		
		dimensions = new VectorComponent();
		GridBagConstraints gbc_vectorComponent = new GridBagConstraints();
		gbc_vectorComponent.insets = new Insets(0, 0, 5, 0);
		gbc_vectorComponent.fill = GridBagConstraints.BOTH;
		gbc_vectorComponent.gridx = 1;
		gbc_vectorComponent.gridy = 0;
		add(dimensions, gbc_vectorComponent);
		
//		JLabel lblProperty = new JLabel("Property");
//		GridBagConstraints gbc_lblProperty = new GridBagConstraints();
//		gbc_lblProperty.insets = new Insets(0, 0, 0, 5);
//		gbc_lblProperty.gridx = 0;
//		gbc_lblProperty.gridy = 1;
//		add(lblProperty, gbc_lblProperty);
		
		int i = 1;
		for(PropertyTemplate t : template.getActiveProperties()){
			addProperty(t, i++);
			templates.put(t.getName(), t);
		}
	}
	
	@SuppressWarnings("rawtypes")
	public void addProperty(PropertyTemplate t, int y){
		JLabel lblNewLabel = new JLabel(t.getName());
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = y;
		add(lblNewLabel, gbc_lblNewLabel);
		
		Component component = t.getEditorComponent();
		GridBagConstraints gbc_vectorComponent = new GridBagConstraints();
		gbc_vectorComponent.fill = GridBagConstraints.BOTH;
		gbc_vectorComponent.insets = new Insets(0, 0, 5, 0);
		gbc_vectorComponent.gridx = 1;
		gbc_vectorComponent.gridy = y;
		add(component, gbc_vectorComponent);
		
		properties.put(t.getName(), component);
	}
	
	@SuppressWarnings("unchecked")
	public void setToLevel(Level level){
		dimensions.setXField(level.getWidth());
		dimensions.setYField(level.getHeight());
		for(Entry<String, Object> e : level.getProperties().entrySet()){
			templates.get(e.getKey()).setToValue(properties.get(e.getKey()), e.getValue());
			//do this tommorow, you tired fool
		}
	}
	
	@SuppressWarnings("unchecked")
	public void setLevelTo(Level level){
		level.setDimensions(dimensions.getXField(), dimensions.getYField());
		for(Entry<String, Component> e : properties.entrySet()){
			level.setProperty(e.getKey(), templates.get(e.getKey()).getValueFrom(e.getValue()));
		}
	}

}
