package com.clearlyspam23.GLE.basic.layers.tile.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Frame;

import javax.swing.JDialog;

import com.clearlyspam23.GLE.GUI.LayerEditorDialog;
import com.clearlyspam23.GLE.GUI.util.BasicEditorButton;
import com.clearlyspam23.GLE.GUI.util.BasicEditorPanel;

public class TileEditorDialog extends LayerEditorDialog {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TileEditorDialog dialog = new TileEditorDialog(null);
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private TilesetSelectionPanel selectionPanel;
	private BasicEditorPanel panel;

	/**
	 * Create the dialog.
	 */
	public TileEditorDialog(Frame owner) {
		super(owner, "Tile Editor");
		setLayout(new BorderLayout(0, 0));
		panel = new BasicEditorPanel
				(new BasicEditorButton("images/Pencil.png", "Pencil", "Places Tiles"), 
				new BasicEditorButton("images/Eraser.png", "Eraser", "Removes Tiles"));
		this.getContentPane().add(panel, BorderLayout.NORTH);
		selectionPanel = new TilesetSelectionPanel();
		getContentPane().add(selectionPanel, BorderLayout.CENTER);
		setBounds(100, 100, panel.getWidth(), 600);

	}
	
	public BasicEditorPanel getPanel(){
		return panel;
	}
	
	public TilesetSelectionPanel getTilesetSelectionPanel(){
		return selectionPanel;
	}

}
