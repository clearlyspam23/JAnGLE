package com.clearlyspam23.GLE.GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JButton;
import javax.swing.JMenuItem;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JSeparator;
import javax.swing.KeyStroke;

import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;

import javax.swing.JTabbedPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;

import com.clearlyspam23.GLE.TileLayer;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import javax.swing.SwingConstants;

public class MainWindow extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow frame = new MainWindow();
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
	public MainWindow() {
		setTitle("JAnGLE");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmNewTemplate = new JMenuItem("New Template");
		mntmNewTemplate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("clicked");
			}
		});
		
		JMenuItem mntmNewLevel = new JMenuItem("New Level");
		mnFile.add(mntmNewLevel);
		
		JMenuItem mntmOpenlevel = new JMenuItem("OpenLevel");
		mnFile.add(mntmOpenlevel);
		
		JSeparator separator = new JSeparator();
		mnFile.add(separator);
		mnFile.add(mntmNewTemplate);
		
		JMenuItem mntmOpenTemplate = new JMenuItem("Open Template");
		mnFile.add(mntmOpenTemplate);
		
		JMenu mnEdit = new JMenu("Edit");
		menuBar.add(mnEdit);
		
		JMenuItem mntmUndo = new JMenuItem("Undo");
		mntmUndo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_MASK));
		mnEdit.add(mntmUndo);
		
		JMenuItem mntmRedo = new JMenuItem("Redo");
		mntmRedo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y, InputEvent.CTRL_MASK));
		mnEdit.add(mntmRedo);
		
		JMenu mnLevel = new JMenu("Level");
		menuBar.add(mnLevel);
		
		JMenu mnGle = new JMenu("GLE");
		menuBar.add(mnGle);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane);
		
		LevelPanel levelPanel = new LevelPanel();
		tabbedPane.addTab("New tab", null, levelPanel, null);
		
//		TestInternal testInternal = new TestInternal();
//		contentPane.add(testInternal);
		
		JPanel InfoPanel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) InfoPanel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		contentPane.add(InfoPanel, BorderLayout.SOUTH);
		
		JLabel mouseLabel = new JLabel("Mouse");
		InfoPanel.add(mouseLabel);
		
		JLabel mouseLoc = new JLabel("");
		InfoPanel.add(mouseLoc);
	}
}
