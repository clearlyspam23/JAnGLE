package com.clearlyspam23.GLE.GUI.template;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import com.clearlyspam23.GLE.LayerDefinition;
import com.clearlyspam23.GLE.PluginManager;
import com.clearlyspam23.GLE.Template;

public class LayerPanel extends TemplateSubPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JScrollPane scrollPane;
	private JComboBox<String> comboBox;
	
	private List<LayerDefinition<?, ?>> knownLayerDefs;

	/**
	 * Create the panel.
	 */
	public LayerPanel(PluginManager pluginManager) {
		super(pluginManager);
		setLayout(null);
		
		this.knownLayerDefs = pluginManager.getRecognizedLayerDefs();
		
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
		
		textField = new JTextField();
		textField.setBounds(247, 37, 215, 20);
		add(textField);
		textField.setColumns(10);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(186, 90, 354, 451);
		add(scrollPane);
		
		comboBox = new JComboBox<String>();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetLayerArea();
			}
		});
		comboBox.setBounds(247, 62, 215, 20);
		comboBox.setSelectedIndex(knownLayerDefs.size()>0 ? 0 : -1);
		add(comboBox);
		
		JButton btnCreate = new JButton("Create");
		btnCreate.setBounds(20, 484, 72, 23);
		add(btnCreate);
		
		JButton btnNewButton = new JButton("Delete");
		btnNewButton.setBounds(102, 484, 72, 23);
		add(btnNewButton);
		
		JButton btnUp = new JButton("Up");
		btnUp.setBounds(20, 518, 72, 23);
		add(btnUp);
		
		JButton btnDown = new JButton("Down");
		btnDown.setBounds(102, 518, 72, 23);
		add(btnDown);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(20, 40, 156, 433);
		add(scrollPane_1);
		
		JList<String> list = new JList<String>();
		scrollPane_1.setViewportView(list);

	}
	
	private void resetLayerArea()
	{
		scrollPane.removeAll();
		int i = comboBox.getSelectedIndex();
		if(i>=0&&i<knownLayerDefs.size())
		{
			scrollPane.add(knownLayerDefs.get(i).getLayerComponent());
		}
	}
	
	public void setToTemplate(Template template)
	{
		
	}

	@Override
	public String getPanelName() {
		return "Layers";
	}

	@Override
	public void generateTemplate(Template template) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}

}
