package com.clearlyspam23.GLE.basic.layers.tile.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

import com.clearlyspam23.GLE.basic.layers.tile.TilesetManager;
import com.clearlyspam23.GLE.basic.layers.tile.gui.tileset.TilesetLoadPanel;

public class TilesetLoadDialog extends JDialog implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TilesetLoadPanel tilesetLoadPanel;
	
	private TilesetManager currentManager;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			TilesetLoadDialog dialog = new TilesetLoadDialog();
			TilesetManager manager = new TilesetManager();
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
		setModal(true);
		setBounds(100, 100, 609, 563);
		getContentPane().setLayout(new BorderLayout());
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				okButton.addActionListener(this);
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				cancelButton.addActionListener(this);
				buttonPane.add(cancelButton);
			}
		}
		
		tilesetLoadPanel = new TilesetLoadPanel();
		getContentPane().add(tilesetLoadPanel, BorderLayout.CENTER);
	}
	
	public void showDialog(TilesetManager manager){
		currentManager = manager;
		tilesetLoadPanel.setToTileset(manager.getRoot());
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if("OK".equals(e.getActionCommand())){
			if(!tilesetLoadPanel.finish()){
				return;
			}
			currentManager.setRoot(tilesetLoadPanel.getTilesets());
		}
		setVisible(false);
	}
	
	
}
