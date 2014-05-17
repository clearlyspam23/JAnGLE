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
		tabPane.addTab("General", null, panel, null);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Level Dimensions");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(10, 11, 128, 14);
		panel.add(lblNewLabel);
		
		JLabel lblDefault = new JLabel("Default");
		lblDefault.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblDefault.setBounds(10, 50, 75, 14);
		panel.add(lblDefault);
		
		JLabel lblNewLabel_1 = new JLabel("Minimum");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(10, 83, 75, 14);
		panel.add(lblNewLabel_1);
		
		JLabel lblMaximum = new JLabel("Maximum");
		lblMaximum.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblMaximum.setBounds(10, 112, 75, 14);
		panel.add(lblMaximum);
		
		JLabel lblLevelProperties = new JLabel("Level Properties");
		lblLevelProperties.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblLevelProperties.setBounds(10, 169, 128, 14);
		panel.add(lblLevelProperties);
	}
	
	public void addToTabs(JComponent c)
	{
		
	}
}
