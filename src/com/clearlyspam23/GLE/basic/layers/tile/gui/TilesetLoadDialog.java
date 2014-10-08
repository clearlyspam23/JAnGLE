package com.clearlyspam23.GLE.basic.layers.tile.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.clearlyspam23.GLE.basic.layers.tile.TilesetGroupNode;
import com.clearlyspam23.GLE.basic.layers.tile.TilesetHandle;
import com.clearlyspam23.GLE.basic.layers.tile.TilesetManager;
import com.clearlyspam23.GLE.basic.layers.tile.TilesetTileNode;
import com.clearlyspam23.GLE.basic.layers.tile.TilesetTreeNode;
import com.clearlyspam23.GLE.basic.layers.tile.gui.tileset.TilesetLoadPanel;
import com.clearlyspam23.GLE.basic.layers.tile.resources.BasicTilesetHandle;

public class TilesetLoadDialog extends JDialog implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TilesetLoadPanel tilesetLoadPanel;
	
	private TilesetManager currentManager;
	
	private static void recursivePrint(TilesetGroupNode node, int indent){
		for(TilesetTreeNode n : node.getChildren()){
			for(int i = 0; i < indent; i++)
				System.out.print('\t');
			System.out.println(n.getType() + " : " + n.getName());
			if(n.getType()==TilesetTreeNode.Type.GROUP)
				recursivePrint(n.getAsGroup(), indent+1);
		}
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e1) {
			//honestly, if this doesnt work, whatever we'll use default. should fail silently.
		}
		try {
//			ResourceManager.get().registerResourceType(Tileset.class);
//			ResourceManager.get().registerResourceType(TilesetHandle.class);
			TilesetLoadDialog dialog = new TilesetLoadDialog();
			TilesetManager manager = new TilesetManager();
			TilesetGroupNode root = new TilesetGroupNode("Tilesets");
			TilesetHandle h = new BasicTilesetHandle("Pipes", "images/Pipes.png", 64, 64);
			root.addNode(new TilesetTileNode(h));
			TilesetGroupNode group = new TilesetGroupNode("group");
			h = new BasicTilesetHandle("Boxes", "images/testboxes.png", 64, 64);
			group.addNode(new TilesetTileNode(h));
			root.addNode(group);
			manager.setRoot(root);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.showDialog(manager);
			recursivePrint(manager.getRoot(), 0);
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
		tilesetLoadPanel = new TilesetLoadPanel();
		getContentPane().add(tilesetLoadPanel, BorderLayout.CENTER);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				okButton.addActionListener(this);
				buttonPane.add(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				cancelButton.addActionListener(this);
				buttonPane.add(cancelButton);
			}
		}
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
