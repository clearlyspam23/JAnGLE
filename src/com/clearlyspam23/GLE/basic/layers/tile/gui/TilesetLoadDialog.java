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
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.activation.MimetypesFileTypeMap;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.clearlyspam23.GLE.GUI.util.VectorComponent;
import com.clearlyspam23.GLE.basic.layers.tile.TilesetManager;
import com.clearlyspam23.GLE.basic.layers.tile.resources.TilesetFileHandle;
import com.clearlyspam23.GLE.basic.layers.tile.resources.TilesetHandle;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class TilesetLoadDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField nameField;
	private JTextField fileNameField;
	
	private JPanel tilesetGridPanel;
	
	private List<TilesetFileHandle> tilesets;
	private JTextField tagsField;
	private VectorComponent tileSizeComponent;
	private VectorComponent tileSpaceComponent;
	private JList<TilesetFileHandle> list;
	private DefaultListModel<TilesetFileHandle> listModel;
	
	private TilesetFileHandle currentTileset;

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
		setBounds(100, 100, 609, 563);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel listPanel = new JPanel();
		listPanel.setPreferredSize(new Dimension(160, 0));
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
		
		list = new JList<TilesetFileHandle>();
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
			}
		});
		listModel = new DefaultListModel<TilesetFileHandle>();
		list.setModel(listModel);
		list.setMinimumSize(new Dimension(69*2, 0));
		scrollPane.setViewportView(list);
		
		JButton button = new JButton("New");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TilesetFileHandle h = new TilesetFileHandle();
				h.setName("tileset"+tilesets.size());
				h.setTileWidth(16);
				h.setTileHeight(16);
				tilesets.add(h);
				listModel.addElement(h);
				list.setSelectedIndex(listModel.getSize()-1);
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
		gbl_panel_2.columnWidths = new int[]{0, 235, 0, 0, 0};
		gbl_panel_2.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_panel_2.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panel_2.rowWeights = new double[]{0.0, 0.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
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
		gbc_nameField.insets = new Insets(0, 0, 5, 0);
		gbc_nameField.fill = GridBagConstraints.HORIZONTAL;
		gbc_nameField.gridx = 1;
		gbc_nameField.gridy = 0;
		panel_2.add(nameField, gbc_nameField);
		nameField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Tile Size");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
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
		
		fileNameField = new JTextField();
		GridBagConstraints gbc_fileNameField = new GridBagConstraints();
		gbc_fileNameField.gridwidth = 2;
		gbc_fileNameField.insets = new Insets(0, 0, 5, 5);
		gbc_fileNameField.fill = GridBagConstraints.HORIZONTAL;
		gbc_fileNameField.gridx = 1;
		gbc_fileNameField.gridy = 1;
		panel_2.add(fileNameField, gbc_fileNameField);
		fileNameField.setColumns(10);
		
		JButton btnNewButton = new JButton("Browse");
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton.gridx = 3;
		gbc_btnNewButton.gridy = 1;
		panel_2.add(btnNewButton, gbc_btnNewButton);
		
		tileSizeComponent = new VectorComponent();
		GridBagConstraints gbc_tileSizeComponent = new GridBagConstraints();
		gbc_tileSizeComponent.insets = new Insets(0, 0, 5, 5);
		gbc_tileSizeComponent.fill = GridBagConstraints.BOTH;
		gbc_tileSizeComponent.gridx = 1;
		gbc_tileSizeComponent.gridy = 2;
		panel_2.add(tileSizeComponent, gbc_tileSizeComponent);
		
		JLabel lblTags = new JLabel("Tags");
		lblTags.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblTags = new GridBagConstraints();
		gbc_lblTags.anchor = GridBagConstraints.EAST;
		gbc_lblTags.insets = new Insets(0, 0, 0, 5);
		gbc_lblTags.gridx = 0;
		gbc_lblTags.gridy = 4;
		panel_2.add(lblTags, gbc_lblTags);
		
		JLabel lblTileSpacing = new JLabel("Tile Spacing");
		GridBagConstraints gbc_lblTileSpacing = new GridBagConstraints();
		gbc_lblTileSpacing.anchor = GridBagConstraints.EAST;
		gbc_lblTileSpacing.insets = new Insets(0, 0, 5, 5);
		gbc_lblTileSpacing.gridx = 0;
		gbc_lblTileSpacing.gridy = 3;
		panel_2.add(lblTileSpacing, gbc_lblTileSpacing);
		
		tileSpaceComponent = new VectorComponent();
		GridBagConstraints gbc_tileSpaceComponent = new GridBagConstraints();
		gbc_tileSpaceComponent.insets = new Insets(0, 0, 5, 5);
		gbc_tileSpaceComponent.fill = GridBagConstraints.BOTH;
		gbc_tileSpaceComponent.gridx = 1;
		gbc_tileSpaceComponent.gridy = 3;
		panel_2.add(tileSpaceComponent, gbc_tileSpaceComponent);
		
		tagsField = new JTextField();
		tagsField.setToolTipText("A List of Tags identifying this tileset, seperated by commas");
		GridBagConstraints gbc_tagsField = new GridBagConstraints();
		gbc_tagsField.gridwidth = 3;
		gbc_tagsField.insets = new Insets(0, 0, 0, 5);
		gbc_tagsField.fill = GridBagConstraints.HORIZONTAL;
		gbc_tagsField.gridx = 1;
		gbc_tagsField.gridy = 4;
		panel_2.add(tagsField, gbc_tagsField);
		tagsField.setColumns(10);
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
	
	private void toggleFields(boolean flag){
		nameField.setEnabled(flag);
		fileNameField.setEnabled(flag);
		tileSizeComponent.setEnabled(flag);
		tileSpaceComponent.setEnabled(flag);
		if(!flag){
			tilesetGridPanel.removeAll();
			nameField.setText("");
			fileNameField.setText("");
			tileSizeComponent.setXField(0);
			tileSizeComponent.setYField(0);
			tileSpaceComponent.setXField(0);
			tileSpaceComponent.setYField(0);
			tagsField.setText("");
		}
	}
	
	private boolean validateTilesetFileHandle(TilesetFileHandle current){
		if(current.getName()==null||"".equals(current.getName()))
			return false;
		File f = new File(current.getFilename());
        String mimetype= new MimetypesFileTypeMap().getContentType(f);
        String type = mimetype.split("/")[0];
        if(!type.equals("image"))
        	return false;
        if(current.getTileWidth()<=0||current.getTileHeight()<=0)
        	return false;
        if(current.getTileXSpacing()<0||current.getTileYSpacing()<0)
        	return false;
        return true;
	}
	
	private void setToTileset(TilesetFileHandle tileset){
		toggleFields(tileset!=null);
		if(tileset!=null){
			nameField.setText(tileset.getName());
			fileNameField.setText(tileset.getFilename());
			tileSizeComponent.setXField(tileset.getTileWidth());
			tileSizeComponent.setYField(tileset.getTileHeight());
			tileSpaceComponent.setXField(tileset.getTileXSpacing());
			tileSpaceComponent.setYField(tileset.getTileYSpacing());
			StringBuilder output = new StringBuilder();
			boolean first = true;
			for(String s : tileset.getTags()){
				if(first){
					output.append(s);
					first = false;
				}
				else{
					output.append(", ").append(s);
				}
			}
			tagsField.setText(output.toString());
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
