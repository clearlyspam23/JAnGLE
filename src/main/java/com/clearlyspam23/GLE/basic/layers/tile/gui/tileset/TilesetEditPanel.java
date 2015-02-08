package com.clearlyspam23.GLE.basic.layers.tile.gui.tileset;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.clearlyspam23.GLE.GUI.util.VectorComponent;
import com.clearlyspam23.GLE.basic.layers.tile.TilesetHandle;
import com.clearlyspam23.GLE.basic.layers.tile.resources.BasicTilesetHandle;

public class TilesetEditPanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField nameField;
	private JTextField fileNameField;
	
	private VectorComponent tileSizeComponent;
	private VectorComponent tileSpaceComponent;
	
	private TilesetViewPanel tilesetGridPanel;
	private JButton browseButton;
	
	private int id = 0;
	
	public static void main(String[] args){
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e1) {
			//honestly, if this doesnt work, whatever we'll use default. should fail silently.
		}
//		ResourceManager.get().registerResourceType(Tileset.class);
//		ResourceManager.get().registerResourceType(TilesetHandle.class);
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BasicTilesetHandle basic = new BasicTilesetHandle("test", "bad file", 10, 10, 0, 0);
					TilesetEditPanel panel = new TilesetEditPanel();
					panel.setToTileset(basic);
					JFrame frame = new JFrame();
					frame.getContentPane().setLayout(new GridLayout(1, 1, 0, 0));
					frame.getContentPane().add(panel);
					//JScrollPane scroll = new JScrollPane(panel);
					//frame.getContentPane().add(scroll);
					frame.setSize(500, 400);
					frame.setVisible(true);
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private JFileChooser imageChooser = new JFileChooser();

	/**
	 * Create the panel.
	 */
	public TilesetEditPanel() {
		setBorder(new EmptyBorder(4, 4, 4, 4));
		setLayout(new BorderLayout(0, 4));
		//setMinimumSize(new Dimension(370, 300));
		

//		imageChooser.addChoosableFileFilter();
		imageChooser.setFileFilter(new FileNameExtensionFilter("Image files", ImageIO.getReaderFileSuffixes()));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		add(scrollPane_1, BorderLayout.CENTER);
		
		tilesetGridPanel = new TilesetViewPanel();
		tilesetGridPanel.toggleButtonSelection(false);
		tilesetGridPanel.toggleBorders(true);
		
		scrollPane_1.setViewportView(tilesetGridPanel);
		
		JPanel panel_2 = new JPanel();
		add(panel_2, BorderLayout.SOUTH);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[]{40, 235, 0, 0, 0};
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
		fileNameField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateTilesetView();
			}
		});
		GridBagConstraints gbc_fileNameField = new GridBagConstraints();
		gbc_fileNameField.gridwidth = 2;
		gbc_fileNameField.insets = new Insets(0, 0, 5, 5);
		gbc_fileNameField.fill = GridBagConstraints.HORIZONTAL;
		gbc_fileNameField.gridx = 1;
		gbc_fileNameField.gridy = 1;
		panel_2.add(fileNameField, gbc_fileNameField);
		fileNameField.setColumns(10);
		
		browseButton = new JButton("Browse");
		browseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				imageChooser.setSelectedFile(new File(fileNameField.getText()));
				if(imageChooser.showOpenDialog(TilesetEditPanel.this)==JFileChooser.APPROVE_OPTION){
					fileNameField.setText(imageChooser.getSelectedFile().getPath());
					updateTilesetView();
				}
			}
		});
		GridBagConstraints gbc_browseButton = new GridBagConstraints();
		gbc_browseButton.insets = new Insets(0, 0, 5, 0);
		gbc_browseButton.gridx = 3;
		gbc_browseButton.gridy = 1;
		panel_2.add(browseButton, gbc_browseButton);
		
		tileSizeComponent = new VectorComponent(false, false);
		tileSizeComponent.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				updateTilesetView();
			}
			
		});
		GridBagConstraints gbc_tileSizeComponent = new GridBagConstraints();
		gbc_tileSizeComponent.insets = new Insets(0, 0, 5, 5);
		gbc_tileSizeComponent.fill = GridBagConstraints.BOTH;
		gbc_tileSizeComponent.gridx = 1;
		gbc_tileSizeComponent.gridy = 2;
		panel_2.add(tileSizeComponent, gbc_tileSizeComponent);
		
		JLabel lblTileSpacing = new JLabel("Tile Spacing");
		GridBagConstraints gbc_lblTileSpacing = new GridBagConstraints();
		gbc_lblTileSpacing.anchor = GridBagConstraints.EAST;
		gbc_lblTileSpacing.insets = new Insets(0, 0, 5, 5);
		gbc_lblTileSpacing.gridx = 0;
		gbc_lblTileSpacing.gridy = 3;
		panel_2.add(lblTileSpacing, gbc_lblTileSpacing);
		
		tileSpaceComponent = new VectorComponent(false, false);
		tileSpaceComponent.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				updateTilesetView();
			}
			
		});
		GridBagConstraints gbc_tileSpaceComponent = new GridBagConstraints();
		gbc_tileSpaceComponent.insets = new Insets(0, 0, 5, 5);
		gbc_tileSpaceComponent.fill = GridBagConstraints.BOTH;
		gbc_tileSpaceComponent.gridx = 1;
		gbc_tileSpaceComponent.gridy = 3;
		panel_2.add(tileSpaceComponent, gbc_tileSpaceComponent);
		toggleFields(false);
		
		
	}
	
	private void updateTilesetView(){
		tilesetGridPanel.setToTileset(null);
		if(isImageFileValid()&&isSizeValid()){
			try{
				TilesetHandle h = getTileset();
				tilesetGridPanel.setToTileset(h);
			}
			catch(Exception e){
				e.printStackTrace();
				tilesetGridPanel.setToTileset(null); //no matter how many tiles were placed properly, cut them all out
			}
		}
		else{
			if(!isImageFileValid()){
				System.out.println(fileNameField.getText() + " is not valid");
			}
			else{
				System.out.println("size is not valid");
			}
		}
		revalidate();
		repaint();
	}
	
	private void toggleFields(boolean flag){
		nameField.setEnabled(flag);
		fileNameField.setEnabled(flag);
		tileSizeComponent.setEnabled(flag);
		tileSpaceComponent.setEnabled(flag);
		browseButton.setEnabled(flag);
		if(!flag){
			nameField.setText("");
			fileNameField.setText("");
			tileSizeComponent.setXField(0);
			tileSizeComponent.setYField(0);
			tileSpaceComponent.setXField(0);
			tileSpaceComponent.setYField(0);
		}
	}
	
	public void setToTileset(TilesetHandle tileset){
		toggleFields(tileset!=null);
		id = 0;
		if(tileset!=null){
			nameField.setText(tileset.getName());
			fileNameField.setText(tileset.getFilename());
			tileSizeComponent.setXField(tileset.getTileWidth());
			tileSizeComponent.setYField(tileset.getTileHeight());
			tileSpaceComponent.setXField(tileset.getTileXSpacing());
			tileSpaceComponent.setYField(tileset.getTileYSpacing());
			id = tileset.getID();
		}
		updateTilesetView();
	}
	
	public void updateName(String name){
		nameField.setText(name);
	}
	
	public BasicTilesetHandle getTileset(){
		BasicTilesetHandle tileset = new BasicTilesetHandle(nameField.getText(), fileNameField.getText(), 
				(int) tileSizeComponent.getVector().x, (int) tileSizeComponent.getVector().y, 
				(int) tileSpaceComponent.getVector().x, (int) tileSpaceComponent.getVector().y, id);
		return tileset;
	}
	
	public boolean isNameValid(){
		return nameField.getText()!=null&&!"".equals(nameField.getText());
	}
	
	public boolean isImageFileValid(){
		File f = new File(fileNameField.getText());
        return f.exists()&&f.canRead();
	}
	
	public boolean isSizeValid(){
		return tileSizeComponent.getVector().x>0&&tileSizeComponent.getVector().y>0;
	}

}
