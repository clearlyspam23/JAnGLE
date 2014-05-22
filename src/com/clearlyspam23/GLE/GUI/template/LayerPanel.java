package com.clearlyspam23.GLE.GUI.template;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JList;

public class LayerPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public LayerPanel() {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Layers");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(10, 11, 65, 17);
		add(lblNewLabel);
		
		JList list = new JList();
		list.setBounds(20, 39, 156, 456);
		add(list);

	}

}
