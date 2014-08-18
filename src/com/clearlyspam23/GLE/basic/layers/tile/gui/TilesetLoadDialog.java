package com.clearlyspam23.GLE.basic.layers.tile.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.clearlyspam23.GLE.GUI.util.VectorComponent;
import com.clearlyspam23.GLE.basic.layers.tile.TilesetManager;
import com.clearlyspam23.GLE.basic.layers.tile.resources.TilesetFileHandle;
import com.clearlyspam23.GLE.basic.layers.tile.resources.TilesetHandle;

public class TilesetLoadDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField nameField;
	private JTextField textField;
	
	private JPanel tilesetGridPanel;
	
	private List<TilesetFileHandle> tilesets;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			TilesetLoadDialog dialog = new TilesetLoadDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public TilesetLoadDialog() {
		setBounds(100, 100, 513, 529);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel listPanel = new JPanel();
		listPanel.setPreferredSize(new Dimension(69*2, 0));
		listPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.add(listPanel, BorderLayout.WEST);
		GridBagLayout gbl_listPanel = new GridBagLayout();
		gbl_listPanel.columnWidths = new int[]{69, 69, 0};
		gbl_listPanel.rowHeights = new int[]{383, 23, 0};
		gbl_listPanel.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_listPanel.rowWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		listPanel.setLayout(gbl_listPanel);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 2;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		listPanel.add(scrollPane, gbc_scrollPane);
		
		JList list = new JList();
		list.setMinimumSize(new Dimension(69*2, 0));
		scrollPane.setViewportView(list);
		
		JButton button = new JButton("New");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		GridBagConstraints gbc_button = new GridBagConstraints();
		gbc_button.fill = GridBagConstraints.HORIZONTAL;
		gbc_button.anchor = GridBagConstraints.NORTH;
		gbc_button.insets = new Insets(0, 0, 0, 5);
		gbc_button.gridx = 0;
		gbc_button.gridy = 1;
		listPanel.add(button, gbc_button);
		
		JButton button_1 = new JButton("Delete");
		GridBagConstraints gbc_button_1 = new GridBagConstraints();
		gbc_button_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_button_1.anchor = GridBagConstraints.NORTH;
		gbc_button_1.gridx = 1;
		gbc_button_1.gridy = 1;
		listPanel.add(button_1, gbc_button_1);
		
		JPanel panel = new JPanel();
		contentPanel.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(5, 5));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		panel.add(scrollPane_1, BorderLayout.CENTER);
		
		tilesetGridPanel = new JPanel();
		scrollPane_1.setViewportView(tilesetGridPanel);
		tilesetGridPanel.setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel panel_2 = new JPanel();
		panel.add(panel_2, BorderLayout.SOUTH);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_panel_2.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_panel_2.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_2.rowWeights = new double[]{0.0, 0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		panel_2.setLayout(gbl_panel_2);
		
		JLabel lblName = new JLabel("Name");
		GridBagConstraints gbc_lblName = new GridBagConstraints();
		gbc_lblName.insets = new Insets(0, 0, 5, 5);
		gbc_lblName.anchor = GridBagConstraints.EAST;
		gbc_lblName.gridx = 0;
		gbc_lblName.gridy = 0;
		panel_2.add(lblName, gbc_lblName);
		
		nameField = new JTextField();
		GridBagConstraints gbc_nameField = new GridBagConstraints();
		gbc_nameField.gridwidth = 3;
		gbc_nameField.insets = new Insets(0, 0, 5, 0);
		gbc_nameField.fill = GridBagConstraints.HORIZONTAL;
		gbc_nameField.gridx = 1;
		gbc_nameField.gridy = 0;
		panel_2.add(nameField, gbc_nameField);
		nameField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Tile Size");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 2;
		panel_2.add(lblNewLabel, gbc_lblNewLabel);
		
		JLabel lblImageFile = new JLabel("Image File");
		GridBagConstraints gbc_lblImageFile = new GridBagConstraints();
		gbc_lblImageFile.anchor = GridBagConstraints.EAST;
		gbc_lblImageFile.insets = new Insets(0, 0, 5, 5);
		gbc_lblImageFile.gridx = 0;
		gbc_lblImageFile.gridy = 1;
		panel_2.add(lblImageFile, gbc_lblImageFile);
		
		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.gridwidth = 2;
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 1;
		panel_2.add(textField, gbc_textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Browse");
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton.gridx = 3;
		gbc_btnNewButton.gridy = 1;
		panel_2.add(btnNewButton, gbc_btnNewButton);
		
		VectorComponent vectorComponent = new VectorComponent();
		GridBagConstraints gbc_vectorComponent = new GridBagConstraints();
		gbc_vectorComponent.gridwidth = 2;
		gbc_vectorComponent.insets = new Insets(0, 0, 5, 5);
		gbc_vectorComponent.fill = GridBagConstraints.BOTH;
		gbc_vectorComponent.gridx = 1;
		gbc_vectorComponent.gridy = 2;
		panel_2.add(vectorComponent, gbc_vectorComponent);
		
		JLabel lblTileSpacing = new JLabel("Tile Spacing");
		GridBagConstraints gbc_lblTileSpacing = new GridBagConstraints();
		gbc_lblTileSpacing.insets = new Insets(0, 0, 0, 5);
		gbc_lblTileSpacing.gridx = 0;
		gbc_lblTileSpacing.gridy = 3;
		panel_2.add(lblTileSpacing, gbc_lblTileSpacing);
		
		VectorComponent vectorComponent_1 = new VectorComponent();
		GridBagConstraints gbc_vectorComponent_1 = new GridBagConstraints();
		gbc_vectorComponent_1.insets = new Insets(0, 0, 0, 5);
		gbc_vectorComponent_1.gridwidth = 2;
		gbc_vectorComponent_1.fill = GridBagConstraints.BOTH;
		gbc_vectorComponent_1.gridx = 1;
		gbc_vectorComponent_1.gridy = 3;
		panel_2.add(vectorComponent_1, gbc_vectorComponent_1);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	public List<TilesetFileHandle> getResultingTilesets(){
		return tilesets;
	}
	
	public void showDialog(TilesetManager manager){
		tilesets = new ArrayList<TilesetFileHandle>();
		for(TilesetHandle h : manager.getAllTilesets()){
			tilesets.add(new TilesetFileHandle(h.getName(), h.getFilename(), h.getTileWidth(), h.getTileHeight(), h.getTileXSpacing(), h.getTileYSpacing()));
		}
		setVisible(true);
	}
}
