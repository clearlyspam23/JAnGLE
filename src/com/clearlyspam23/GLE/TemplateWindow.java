package com.clearlyspam23.GLE;

import java.awt.EventQueue;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.ListSelectionModel;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.MatteBorder;
import javax.swing.JComboBox;

public class TemplateWindow extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TemplateWindow frame = new TemplateWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private JTabbedPane tabPane;

	/**
	 * Create the frame.
	 */
	public TemplateWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 541, 606);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		tabPane = new JTabbedPane(JTabbedPane.TOP);
		tabPane.setBounds(10, 11, 505, 545);
		contentPane.add(tabPane);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		tabPane.addTab("General", null, panel, null);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Engine Properties");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(10, 11, 128, 14);
		panel.add(lblNewLabel);
		
		JLabel lblLevelProperties = new JLabel("Level Properties");
		lblLevelProperties.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblLevelProperties.setBounds(10, 194, 128, 14);
		panel.add(lblLevelProperties);
		
		JLabel lblNewLabel_1 = new JLabel("Coordinate System");
		lblNewLabel_1.setBounds(10, 50, 91, 14);
		panel.add(lblNewLabel_1);
		
		JLabel imageLabel = new JLabel("");
		imageLabel.setBounds(312, 50, 128, 128);
		panel.add(imageLabel);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(111, 47, 144, 20);
		panel.add(comboBox);
	}
	
	public void addToTabs(JComponent c)
	{
		
	}
}
