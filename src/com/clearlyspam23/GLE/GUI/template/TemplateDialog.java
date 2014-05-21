package com.clearlyspam23.GLE.GUI.template;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

import com.clearlyspam23.GLE.CoordinateSystem;
import com.clearlyspam23.GLE.defaultcoordinates.BottomLeft;
import com.clearlyspam23.GLE.defaultcoordinates.CenteredDown;
import com.clearlyspam23.GLE.defaultcoordinates.CenteredUp;
import com.clearlyspam23.GLE.defaultcoordinates.TopLeft;

public class TemplateDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();

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
			TemplateDialog dialog = new TemplateDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public TemplateDialog() {
		CoordinateSystem[] possibleCoordinates = new CoordinateSystem[]{
				new TopLeft(),
				new BottomLeft(),
				new CenteredDown(),
				new CenteredUp()
		};
		
		setBounds(100, 100, 540, 620);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 504, 526);
		contentPanel.add(tabbedPane);
		
		GeneralPanel generalPanel = new GeneralPanel(possibleCoordinates);
		tabbedPane.addTab("General", null, generalPanel, null);
		
		PLangPanel langPanel = new PLangPanel();
		tabbedPane.addTab("Run   ", null, langPanel, null);
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
}
