package com.clearlyspam23.GLE.GUI.template;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JButton;

public class LayerPanel extends JPanel {
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	public LayerPanel() {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Layers");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(20, 11, 65, 17);
		add(lblNewLabel);
		
		JList list = new JList();
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
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(247, 62, 156, 20);
		add(comboBox);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(186, 90, 289, 385);
		add(scrollPane);
		
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

}
