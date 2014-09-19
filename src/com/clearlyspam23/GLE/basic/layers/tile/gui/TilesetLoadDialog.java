package com.clearlyspam23.GLE.basic.layers.tile.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
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
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.clearlyspam23.GLE.basic.layers.tile.TilesetManager;
import com.clearlyspam23.GLE.basic.layers.tile.gui.tileset.TilesetEditPanel;
import com.clearlyspam23.GLE.basic.layers.tile.resources.TilesetFileHandle;
import com.clearlyspam23.GLE.basic.layers.tile.resources.TilesetHandle;
import javax.swing.JTree;

public class TilesetLoadDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	
	private List<TilesetHandle> tilesets;
	private DefaultListModel<TilesetFileHandle> listModel;
	
	private TilesetHandle currentTileset;
	private TilesetEditPanel tilesetEditPanel;
	private JTree tilesetTree;

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
		gbl_listPanel.rowHeights = new int[]{383, 0};
		gbl_listPanel.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_listPanel.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		listPanel.setLayout(gbl_listPanel);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 2;
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		listPanel.add(scrollPane, gbc_scrollPane);
		
		tilesetTree = new JTree();
		scrollPane.setViewportView(tilesetTree);
		listModel = new DefaultListModel<TilesetFileHandle>();
		
		tilesetEditPanel = new TilesetEditPanel();
		contentPanel.add(tilesetEditPanel, BorderLayout.CENTER);
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
	
	public List<TilesetHandle> getResultingTilesets(){
		return getValidTilesets();
	}
	
	public boolean allTilesetsValid(){
		for(TilesetHandle h : tilesets)
			if(!isValidTileset(h))
				return false;
		return true;
	}
	
	private List<TilesetHandle> getValidTilesets(){
		ArrayList<TilesetHandle> ans = new ArrayList<TilesetHandle>();
		for(TilesetHandle h : tilesets)
			if(isValidTileset(h))
				ans.add(h);
		return ans;
	}
	
	private boolean isValidTileset(TilesetHandle current){
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
	
	public void showDialog(TilesetManager manager){
		tilesets = new ArrayList<TilesetHandle>();
		for(TilesetHandle h : manager.getAllTilesets()){
			tilesets.add(new TilesetFileHandle(h.getName(), h.getFilename(), h.getTileWidth(), h.getTileHeight(), h.getTileXSpacing(), h.getTileYSpacing()));
		}
		setVisible(true);
	}
}
