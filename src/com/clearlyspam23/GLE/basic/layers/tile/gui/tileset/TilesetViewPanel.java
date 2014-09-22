package com.clearlyspam23.GLE.basic.layers.tile.gui.tileset;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import com.clearlyspam23.GLE.basic.layers.tile.TilesetGroupNode;
import com.clearlyspam23.GLE.basic.layers.tile.TilesetTileNode;
import com.clearlyspam23.GLE.basic.layers.tile.TilesetTreeNode;
import com.clearlyspam23.GLE.basic.layers.tile.TilesetTreeNode.Type;
import com.clearlyspam23.GLE.basic.layers.tile.resources.TilesetFileHandle;
import com.clearlyspam23.GLE.util.TwoWayMap;

public class TilesetViewPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JTree tilesetTree;
	
	private DefaultMutableTreeNode top = new DefaultMutableTreeNode();
	private DefaultTreeModel model;
	
	private TilesetGroupNode base;
	
	private TwoWayMap<TilesetTreeNode, DefaultMutableTreeNode> nodeMap = new TwoWayMap<TilesetTreeNode, DefaultMutableTreeNode>();
	
	private TilesetViewListener listener;
	
	private static final TilesetViewListener DEFAULT_LISTENER = new TilesetViewListener(){

		@Override
		public void onTilesetDoubleClick(TilesetViewPanel panel,
				TilesetTileNode tileNode, MouseEvent e) {}

		@Override
		public void onGroupDoubleClick(TilesetViewPanel panel,
				TilesetGroupNode tileNode, MouseEvent e) {}

		@Override
		public void onTilesetRightClick(TilesetViewPanel panel,
				TilesetTileNode tileNode, MouseEvent e) {}

		@Override
		public void onGroupRightClick(TilesetViewPanel panel,
				TilesetGroupNode tileNode, MouseEvent e) {}
		
	};
	
	public static void main(String[] args){
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e1) {
			//honestly, if this doesnt work, whatever we'll use default. should fail silently.
		}
		final TilesetViewPanel panel = new TilesetViewPanel();
		final TilesetGroupNode root = new TilesetGroupNode("tilesets");
		final TilesetGroupNode group = new TilesetGroupNode("group");
		final TilesetTileNode node1 = new TilesetTileNode("tileset1", new TilesetFileHandle());
		final TilesetTileNode node2 = new TilesetTileNode("tileset2", new TilesetFileHandle());
		final TilesetTileNode node3 = new TilesetTileNode("tileset3", new TilesetFileHandle());
		final JPopupMenu rightPopUp = new JPopupMenu();
		JMenuItem button = new JMenuItem("New Tileset");
		button.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("I was Clicked!");
				group.addNode(new TilesetTileNode("tileset4", new TilesetFileHandle()));
				panel.refreshTilesets();
			}
			
		});
		rightPopUp.add(button);
		button = new JMenuItem("Not Me!");
		button.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Why'd you click me?!?");
			}
			
		});
		rightPopUp.add(button);
		group.addNode(node1);
		group.addNode(node2);
		root.addNode(node3);
		root.addNode(group);
		panel.setToTilesets(root, new TilesetViewListener(){

			@Override
			public void onTilesetDoubleClick(TilesetViewPanel panel,
					TilesetTileNode tileNode, MouseEvent e) {
				System.out.println("Double Click on Tileset - " + tileNode.getName());
			}

			@Override
			public void onGroupDoubleClick(TilesetViewPanel panel,
					TilesetGroupNode tileNode, MouseEvent e) {
				System.out.println("Double Click on Group - " + tileNode.getName());
			}

			@Override
			public void onTilesetRightClick(TilesetViewPanel panel,
					TilesetTileNode tileNode, MouseEvent e) {
				System.out.println("Right Click on Tileset - " + tileNode.getName());
			}

			@Override
			public void onGroupRightClick(TilesetViewPanel panel,
					TilesetGroupNode tileNode, MouseEvent e) {
				System.out.println("Right Click on Group - " + tileNode.getName());
				rightPopUp.show(e.getComponent(), e.getX(), e.getY());
			}
			
		});
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrame frame = new JFrame();
					frame.getContentPane().setLayout(new GridLayout(1, 1, 0, 0));
					JScrollPane scroll = new JScrollPane(panel);
					frame.add(scroll);
					frame.setSize(150, 400);
					frame.setVisible(true);
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the panel.
	 */
	public TilesetViewPanel() {
		setLayout(new GridLayout(1, 1, 0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane);
		
		model = new DefaultTreeModel(top);
		
		tilesetTree = new JTree(model);
		tilesetTree.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent e) {
				TreePath selPath = tilesetTree.getPathForLocation(e.getX(), e.getY());
		        if(selPath==null)
		        	return;
		        int row = tilesetTree.getClosestRowForLocation(e.getX(), e.getY());
		        tilesetTree.setSelectionRow(row);
		        TilesetTreeNode node = (TilesetTreeNode) ((DefaultMutableTreeNode)selPath.getLastPathComponent()).getUserObject();
				if (SwingUtilities.isRightMouseButton(e)) {
			        if(node.getType()==Type.GROUP){
			        	listener.onGroupRightClick(TilesetViewPanel.this, node.getAsGroup(), e);
			        }
			        else{
			        	listener.onTilesetRightClick(TilesetViewPanel.this, node.getAsTiles(), e);
			        }
			    }
				else if(SwingUtilities.isLeftMouseButton(e)&&e.getClickCount()==2){
					if(node.getType()==Type.GROUP){
						listener.onGroupDoubleClick(TilesetViewPanel.this, node.getAsGroup(), e);
			        }
			        else{
			        	listener.onTilesetDoubleClick(TilesetViewPanel.this, node.getAsTiles(), e);
			        }
				}
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
			}
			
		});
		scrollPane.setViewportView(tilesetTree);
		try {
			Image image = ImageIO.read(new File("images/TilesetIconSmall.png"));
			    tilesetTree.setCellRenderer(new TilesetTreeCellRenderer(new ImageIcon(image)));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	private void loadTilesets(TilesetTreeNode node, DefaultMutableTreeNode parent){
		DefaultMutableTreeNode current = new DefaultMutableTreeNode(node);
		nodeMap.put(node, current);
		model.insertNodeInto(current, parent, parent.getChildCount());
		if(node.getType()==Type.GROUP){
			for(TilesetTreeNode n : node.getAsGroup().getChildren())
				loadTilesets(n, current);
		}
	}
	
	private void conditionalLoadTilesets(TilesetTreeNode node, DefaultMutableTreeNode parent, Set<TilesetTreeNode> nodes){
		DefaultMutableTreeNode current;
		nodes.add(node);
		System.out.println("parent:" + ((TilesetTreeNode)parent.getUserObject()).getName() + " child: " + node.getName());
		if((current = nodeMap.getNormal(node))==null){
			current = new DefaultMutableTreeNode(node);
			nodeMap.put(node, current);
			model.insertNodeInto(current, parent, parent.getChildCount());
		}
		if(node.getType()==Type.GROUP){
			for(TilesetTreeNode n : node.getAsGroup().getChildren())
				conditionalLoadTilesets(n, current, nodes);
		}
	}
	
	public void refreshTilesets(){
		HashSet<TilesetTreeNode> nodes = new HashSet<TilesetTreeNode>();
		nodes.add(base);
		for(TilesetTreeNode n : base.getChildren()){
			conditionalLoadTilesets(n, top, nodes);
		}
		List<TilesetTreeNode> toRemove = new ArrayList<TilesetTreeNode>();
		for(TilesetTreeNode node : nodeMap.getEntries()){
			if(!nodes.contains(node)){
				nodeMap.getNormal(node).removeFromParent();
				toRemove.add(node);
			}
		}
		for(TilesetTreeNode n : toRemove)
			nodeMap.removeNormal(n);
		DefaultTreeModel model = (DefaultTreeModel) tilesetTree.getModel();
		System.out.println(model);
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
            if(value==null)
            	return this;
			TilesetTreeNode node = (TilesetTreeNode) ((DefaultMutableTreeNode)value).getUserObject();
			if(node==null)
				return this;
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
		setToTilesets(root, DEFAULT_LISTENER);
	}
	
	public void setToTilesets(TilesetGroupNode root, TilesetViewListener listener){
		this.listener = listener;
		base = root;
		top.removeAllChildren();
		top.setUserObject(root);
		nodeMap.clear();
		nodeMap.put(root, top);
		for(TilesetTreeNode n : root.getChildren())
			loadTilesets(n, top);
		tilesetTree.expandRow(0);
	}

}
