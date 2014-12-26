package com.clearlyspam23.GLE.debug;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

public class TreeTest extends JFrame {

	private JPanel contentPane;

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
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TreeTest frame = new TreeTest();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TreeTest() {
		Image image = null;
		try {
			image = ImageIO.read(new File("images/TilesetIconSmall.png"));
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		DefaultMutableTreeNode top = new DefaultMutableTreeNode("Tilesets");
		DefaultMutableTreeNode group = new DefaultMutableTreeNode("Group");
		DefaultMutableTreeNode group2 = new DefaultMutableTreeNode("Group2", true);
		top.add(group);
		top.add(group2);
		DefaultMutableTreeNode testNode1 = new DefaultMutableTreeNode("node1");
		group.add(testNode1);
		DefaultMutableTreeNode testNode2 = new DefaultMutableTreeNode("node2");
		group.add(testNode2);
		JTree tree = new JTree(top);
		tree.setEditable(true);
		add(tree);
		DefaultTreeCellRenderer renderer = 
		        new DefaultTreeCellRenderer();
		    renderer.setLeafIcon(new ImageIcon(image));
		    tree.setCellRenderer(renderer);
	}

}
