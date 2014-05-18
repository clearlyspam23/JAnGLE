package com.clearlyspam23.GLE;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import com.clearlyspam23.GLE.defaultcoordinates.BottomLeft;
import com.clearlyspam23.GLE.defaultcoordinates.CenteredDown;
import com.clearlyspam23.GLE.defaultcoordinates.CenteredUp;
import com.clearlyspam23.GLE.defaultcoordinates.TopLeft;
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
	
	private CoordinateSystem[] possibleCoordinates;
	
	private JTabbedPane tabPane;
	
	private JComboBox comboBox;
	private JTextField textField_1;

	/**
	 * Create the frame.
	 */
	public TemplateWindow() {
		
		possibleCoordinates = new CoordinateSystem[]{
				new TopLeft(),
				new BottomLeft(),
				new CenteredDown(),
				new CenteredUp()
		};
		
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
		lblNewLabel.setBounds(10, 11, 128, 20);
		panel.add(lblNewLabel);
		
		JLabel lblLevelProperties = new JLabel("Level Properties");
		lblLevelProperties.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblLevelProperties.setBounds(10, 194, 128, 14);
		panel.add(lblLevelProperties);
		
		JLabel lblNewLabel_1 = new JLabel("Coordinate System");
		lblNewLabel_1.setBounds(10, 50, 117, 14);
		panel.add(lblNewLabel_1);
		
		final JLabel imageLabel = new JLabel("");
		imageLabel.setBounds(312, 50, 128, 128);
		panel.add(imageLabel);
		
		comboBox = new JComboBox();
		String[] model = new String[possibleCoordinates.length];
		for(int i = 0; i < possibleCoordinates.length; i++)
			model[i] = possibleCoordinates[i].getName();
		comboBox.setModel(new DefaultComboBoxModel(model));
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(comboBox.getSelectedIndex()>=0&&comboBox.getSelectedIndex()<possibleCoordinates.length)
				{
					imageLabel.setIcon(new ImageIcon(possibleCoordinates[comboBox.getSelectedIndex()].getHelperImage()));
				}
			}
		});
		comboBox.setSelectedIndex(0);
		comboBox.setBounds(125, 47, 162, 20);
		panel.add(comboBox);
		
		JPanel panel_1 = new JPanel();
		tabPane.addTab("Runtime", null, panel_1, null);
		panel_1.setLayout(null);
		
		JLabel lblRuntimeOptions = new JLabel("Run Options");
		lblRuntimeOptions.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblRuntimeOptions.setBounds(10, 11, 128, 20);
		panel_1.add(lblRuntimeOptions);
		
		JLabel lblLanguage = new JLabel("Language");
		lblLanguage.setBounds(10, 84, 69, 14);
		panel_1.add(lblLanguage);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(116, 81, 135, 20);
		panel_1.add(comboBox_1);
		
		JLabel lblNewLabel_2 = new JLabel("Parameters");
		lblNewLabel_2.setBounds(10, 262, 69, 14);
		panel_1.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Full Input");
		lblNewLabel_3.setBounds(10, 471, 69, 14);
		panel_1.add(lblNewLabel_3);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setBounds(89, 468, 368, 20);
		panel_1.add(textField_1);
		textField_1.setColumns(10);
	}
	
	public void loadComboBox(JComboBox box)
	{
		String[] model = new String[1];
	}
	
	public void addToTabs(JComponent c)
	{
		
	}
}
