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

import javax.imageio.ImageIO;
import javax.swing.DropMode;
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
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import com.clearlyspam23.GLE.GUI.util.TreeTransferHandler;
import com.clearlyspam23.GLE.basic.layers.tile.TilesetGroupNode;
import com.clearlyspam23.GLE.basic.layers.tile.TilesetTileNode;
import com.clearlyspam23.GLE.basic.layers.tile.TilesetTreeNode;
import com.clearlyspam23.GLE.basic.layers.tile.TilesetTreeNode.Type;
import com.clearlyspam23.GLE.basic.layers.tile.resources.BasicTilesetHandle;

public class TilesetTreeViewPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JTree tilesetTree;
	
	private DefaultMutableTreeNode top = new DefaultMutableTreeNode();
	private DefaultTreeModel model;
	
	//private TwoWayMap<TilesetTreeNode, DefaultMutableTreeNode> nodeMap = new TwoWayMap<TilesetTreeNode, DefaultMutableTreeNode>();
	
	private TilesetViewListener listener;
	
	private TilesetTreeCellEditor editor;
	
	private static final TilesetViewListener DEFAULT_LISTENER = new TilesetViewListener(){

		@Override
		public void onTilesetDoubleClick(TilesetTreeViewPanel panel,
				TilesetTileNode tileNode, MouseEvent e) {}

		@Override
		public void onGroupDoubleClick(TilesetTreeViewPanel panel,
				TilesetGroupNode tileNode, MouseEvent e) {}

		@Override
		public void onTilesetRightClick(TilesetTreeViewPanel panel,
				TilesetTileNode tileNode, MouseEvent e) {}

		@Override
		public void onGroupRightClick(TilesetTreeViewPanel panel,
				TilesetGroupNode tileNode, MouseEvent e) {}

		@Override
		public void onTilesetRenamed(TilesetTreeViewPanel panel,
				TilesetTileNode node) {}

		@Override
		public void onGroupRenamed(TilesetTreeViewPanel panel, TilesetGroupNode node) {}
		
	};
	
	private static void printTree(TilesetTreeNode node, int indentLevel){
		for(int i = 0; i < indentLevel; i++){
			System.out.print("  ");
		}
		System.out.println(node.getName());
		if(node.getType()==Type.GROUP){
			for(TilesetTreeNode n : node.getAsGroup().getChildren())
				printTree(n, indentLevel+1);
		}
	}
	
	private static void printTree(DefaultMutableTreeNode node, int indentLevel){
		for(int i = 0; i < indentLevel; i++){
			System.out.print("  ");
		}
		System.out.println(((TilesetTreeNode)node.getUserObject()).getName());
		for(int i = 0; i < node.getChildCount(); i++)
			printTree((DefaultMutableTreeNode)node.getChildAt(i), indentLevel+1);
	}
	
	public static void main(String[] args){
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e1) {
			//honestly, if this doesnt work, whatever we'll use default. should fail silently.
		}
		final TilesetTreeViewPanel panel = new TilesetTreeViewPanel();
		final TilesetGroupNode root = new TilesetGroupNode("tilesets");
		final TilesetGroupNode group = new TilesetGroupNode("group");
		final TilesetTileNode node1 = new TilesetTileNode(new BasicTilesetHandle("tileset1"));
		final TilesetTileNode node2 = new TilesetTileNode(new BasicTilesetHandle("tileset2"));
		final TilesetTileNode node3 = new TilesetTileNode(new BasicTilesetHandle("tileset3"));
		final JPopupMenu rightPopUp = new JPopupMenu();
		JMenuItem button = new JMenuItem("New Tileset");
		button.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("I was Clicked!");
				panel.insertNode(new TilesetTileNode(new BasicTilesetHandle("New Tileset")));
			}
			
		});
		rightPopUp.add(button);
		button = new JMenuItem("New Group");
		button.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				panel.insertNode(new TilesetGroupNode("New Group"));
			}
			
		});
		rightPopUp.add(button);
		button = new JMenuItem("Run Analysis");
		button.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				printTree(root, 0);
				printTree(panel.top, 0);
			}
			
		});
		rightPopUp.add(button);
		group.addNode(node1);
		group.addNode(node2);
		root.addNode(node3);
		root.addNode(group);
		panel.setToTilesets(root, new TilesetViewListener(){

			@Override
			public void onTilesetDoubleClick(TilesetTreeViewPanel panel,
					TilesetTileNode tileNode, MouseEvent e) {
				System.out.println("Double Click on Tileset - " + tileNode.getName());
			}

			@Override
			public void onGroupDoubleClick(TilesetTreeViewPanel panel,
					TilesetGroupNode tileNode, MouseEvent e) {
				System.out.println("Double Click on Group - " + tileNode.getName());
			}

			@Override
			public void onTilesetRightClick(TilesetTreeViewPanel panel,
					TilesetTileNode tileNode, MouseEvent e) {
				System.out.println("Right Click on Tileset - " + tileNode.getName());
			}

			@Override
			public void onGroupRightClick(TilesetTreeViewPanel panel,
					TilesetGroupNode tileNode, MouseEvent e) {
				System.out.println("Right Click on Group - " + tileNode.getName());
				rightPopUp.show(e.getComponent(), e.getX(), e.getY());
			}

			@Override
			public void onTilesetRenamed(TilesetTreeViewPanel panel,
					TilesetTileNode node) {
				System.out.println("Renamed Tile node!");
			}

			@Override
			public void onGroupRenamed(TilesetTreeViewPanel panel,
					TilesetGroupNode node) {
				System.out.println("Renamed Group node!");
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
	public TilesetTreeViewPanel() {
		setLayout(new GridLayout(1, 1, 0, 0));
		
//		JScrollPane scrollPane = new JScrollPane();
//		add(scrollPane);
		
		model = new DefaultTreeModel(top);
		model.addTreeModelListener(new TreeModelListener(){

			@Override
			public void treeNodesChanged(TreeModelEvent e) {
				TilesetTreeNode out = (TilesetTreeNode)((DefaultMutableTreeNode)e.getChildren()[e.getChildren().length-1]).getUserObject();
				if(out.getType()==Type.GROUP)
					listener.onGroupRenamed(TilesetTreeViewPanel.this, out.getAsGroup());
				else
					listener.onTilesetRenamed(TilesetTreeViewPanel.this, out.getAsTiles());
			}

			@Override
			public void treeNodesInserted(TreeModelEvent e) {
				TilesetGroupNode group = (TilesetGroupNode) ((DefaultMutableTreeNode)e.getTreePath().getLastPathComponent()).getUserObject();
				TilesetTreeNode out = (TilesetTreeNode)((DefaultMutableTreeNode)e.getChildren()[e.getChildren().length-1]).getUserObject();
				out.setParent(group);
			}

			@Override
			public void treeNodesRemoved(TreeModelEvent e) {
			}

			@Override
			public void treeStructureChanged(TreeModelEvent e) {
			}
			
		});
		
		tilesetTree = new JTree(model);
		tilesetTree.setDragEnabled(true);
		tilesetTree.setDropMode(DropMode.ON_OR_INSERT);
		tilesetTree.setTransferHandler(new TreeTransferHandler());
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
			        	listener.onGroupRightClick(TilesetTreeViewPanel.this, node.getAsGroup(), e);
			        }
			        else{
			        	listener.onTilesetRightClick(TilesetTreeViewPanel.this, node.getAsTiles(), e);
			        }
			    }
				else if(SwingUtilities.isLeftMouseButton(e)&&e.getClickCount()==2){
					if(node.getType()==Type.GROUP){
						listener.onGroupDoubleClick(TilesetTreeViewPanel.this, node.getAsGroup(), e);
			        }
			        else{
			        	listener.onTilesetDoubleClick(TilesetTreeViewPanel.this, node.getAsTiles(), e);
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
		add(tilesetTree);
		try {
			Image image = ImageIO.read(new File("images/TilesetIconSmall.png"));
			    tilesetTree.setCellRenderer(new TilesetTreeCellRenderer(new ImageIcon(image)));
			    editor = new TilesetTreeCellEditor(tilesetTree, (DefaultTreeCellRenderer) tilesetTree.getCellRenderer());
			    tilesetTree.setCellEditor(editor);
			    tilesetTree.setEditable(true);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}
	
	private void loadTilesets(TilesetTreeNode node, DefaultMutableTreeNode parent){
		DefaultMutableTreeNode current = new DefaultMutableTreeNode(node);
		current.setAllowsChildren(node.getType()==Type.GROUP);
		//nodeMap.put(node, current);
		model.insertNodeInto(current, parent, parent.getChildCount());
		if(node.getType()==Type.GROUP){
			for(TilesetTreeNode n : node.getAsGroup().getChildren())
				loadTilesets(n, current);
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
		top.removeAllChildren();
		model.reload();
		top.setUserObject(root);
		editor.setTilesetRoot(root);
		for(TilesetTreeNode n : root.getChildren())
			loadTilesets(n, top);
		tilesetTree.expandRow(0);
	}
	
	public void insertNode(TilesetTreeNode node){
		 insertNode(node, (DefaultMutableTreeNode) tilesetTree.getLastSelectedPathComponent(), false);
	}
	
	public void insertNode(TilesetTreeNode node, boolean select){
		insertNode(node, (DefaultMutableTreeNode) tilesetTree.getLastSelectedPathComponent(), select);
	}
	
	public void insertNode(TilesetTreeNode node, DefaultMutableTreeNode treeNode, boolean select){
		TilesetTreeNode tileNode = (TilesetTreeNode) treeNode.getUserObject();
		 int index = treeNode.getChildCount();
		 if(tileNode.getType()!=Type.GROUP){
			 index = treeNode.getParent().getIndex(treeNode)+1;
			 treeNode = (DefaultMutableTreeNode) treeNode.getParent();
		 }
		 DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(node, node.getType()==Type.GROUP);
		 model.insertNodeInto(newNode, treeNode, index);
		 if(select){
			 tilesetTree.setSelectionPath(new TreePath(newNode.getPath()));
		 }
	}
	
	public TilesetTreeNode getSelectedNode(){
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) tilesetTree.getLastSelectedPathComponent();
		if(node!=null)
			return (TilesetTreeNode) node.getUserObject();
		return null;
	}

}
