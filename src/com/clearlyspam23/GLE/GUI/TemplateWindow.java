package com.clearlyspam23.GLE.GUI;

import java.awt.EventQueue;

import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

import com.clearlyspam23.GLE.CoordinateSystem;
import com.clearlyspam23.GLE.GUI.template.GeneralPanel;
import com.clearlyspam23.GLE.GUI.template.PLangPanel;
import com.clearlyspam23.GLE.defaultcoordinates.BottomLeft;
import com.clearlyspam23.GLE.defaultcoordinates.CenteredDown;
import com.clearlyspam23.GLE.defaultcoordinates.CenteredUp;
import com.clearlyspam23.GLE.defaultcoordinates.TopLeft;

public class TemplateWindow extends JFrame {

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
					TemplateWindow frame = new TemplateWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private JTabbedPane tabPane;

	/**
	 * Create the frame.
	 */
	public TemplateWindow() {
		
		CoordinateSystem[] possibleCoordinates = new CoordinateSystem[]{
				new TopLeft(),
				new BottomLeft(),
				new CenteredDown(),
				new CenteredUp()
		};
		
		setResizable(false);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 541, 606);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));
		tabPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabPane);
		
		GeneralPanel generalPanel = new GeneralPanel(possibleCoordinates);
		tabPane.addTab("General", null, generalPanel, null);
		
		PLangPanel plangPanel = new PLangPanel();
		tabPane.addTab("Run   ", null, plangPanel, null);
	}
	
	public void addToTabs(JComponent c)
	{
		
	}
}
