package com.clearlyspam23.GLE.GUI.template;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import com.clearlyspam23.GLE.LayerDefinition;
import com.clearlyspam23.GLE.Template;

public class LayerPanel extends JPanel implements TemplateSubPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JScrollPane scrollPane;
	private JComboBox<String> comboBox;
	
	private List<LayerDefinition<?>> knownLayerDefs;

	/**
	 * Create the panel.
	 */
	public LayerPanel(List<LayerDefinition<?>> layerDefs) {
		setLayout(null);
		
		this.knownLayerDefs = layerDefs;
		
		JLabel lblNewLabel = new JLabel("Layers");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(20, 11, 65, 17);
		add(lblNewLabel);
		
		JList<String> list = new JList<String>();
		list.setBounds(20, 39, 156, 370);
		add(list);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(200, 40, 37, 14);
		add(lblName);
		
		JLabel lblType = new JLabel("Type");
		lblType.setBounds(200, 65, 46, 14);
		add(lblType);
		
		textField = new JTextField();
		textField.setBounds(247, 37, 156, 20);
		add(textField);
		textField.setColumns(10);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(186, 90, 289, 385);
		add(scrollPane);
		
		comboBox = new JComboBox<String>();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetLayerArea();
			}
		});
		comboBox.setBounds(247, 62, 156, 20);
		comboBox.setSelectedIndex(knownLayerDefs.size()>0 ? 0 : -1);
		add(comboBox);
		
		JButton btnCreate = new JButton("Create");
		btnCreate.setBounds(20, 420, 72, 23);
		add(btnCreate);
		
		JButton btnNewButton = new JButton("Delete");
		btnNewButton.setBounds(102, 420, 72, 23);
		add(btnNewButton);
		
		JButton btnUp = new JButton("Up");
		btnUp.setBounds(20, 452, 72, 23);
		add(btnUp);
		
		JButton btnDown = new JButton("Down");
		btnDown.setBounds(104, 454, 72, 23);
		add(btnDown);

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

}
