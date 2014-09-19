package com.clearlyspam23.GLE.basic.layers.tile.gui.tileset;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

import com.clearlyspam23.GLE.basic.layers.tile.TilesetGroupNode;
import com.clearlyspam23.GLE.basic.layers.tile.TilesetTreeNode;
import com.clearlyspam23.GLE.basic.layers.tile.TilesetTreeNode.Type;
import com.clearlyspam23.GLE.basic.layers.tile.resources.TilesetHandle;

public class TilesetViewPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JTree tilesetTree;
	
	private DefaultMutableTreeNode top = new DefaultMutableTreeNode("Tilesets");

	/**
	 * Create the panel.
	 */
	public TilesetViewPanel() {
		setLayout(new GridLayout(1, 1, 0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane);
		
		tilesetTree = new JTree(top);
		scrollPane.setViewportView(tilesetTree);
		try {
			Image image = ImageIO.read(new File("images/TilesetIconSmall.png"));
			    tilesetTree.setCellRenderer(new TilesetTreeCellRenderer(new ImageIcon(image)));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	private void loadTilesets(TilesetGroupNode node, DefaultMutableTreeNode current){
		for(TilesetTreeNode t : node.getChildren()){
			if(t.getType()==Type.TILE){
				for(TilesetHandle h : t.getTilesets()){
					current.add(new DefaultMutableTreeNode(h, false));
				}
			}
			else{
				DefaultMutableTreeNode child = new DefaultMutableTreeNode(t);
				for(TilesetTreeNode c : t.getAsGroup().getChildren())
					loadTilesets(c.getAsGroup(), child);
			}
		}
	}
	
	private static class TilesetTreeCellRenderer extends DefaultTreeCellRenderer{
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private TilesetTreeCellRenderer(Icon tilesetIcon){
			setLeafIcon(tilesetIcon);
		}
		@Override
        public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {
            super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
			TilesetTreeNode node = (TilesetTreeNode) value;
            this.setText(node.getName());
            if(node.getType()==Type.GROUP){
            	if(expanded)
            		this.setIcon(this.openIcon);
            	else
            		this.setIcon(this.closedIcon);
            }
            else{
            	setIcon(this.leafIcon);
            }
            return this;
        }
	}
	
	public void setToTilesets(TilesetGroupNode root){
		top.removeAllChildren();
		loadTilesets(root, top);
	}

}
