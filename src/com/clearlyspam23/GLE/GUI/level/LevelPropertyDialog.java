package com.clearlyspam23.GLE.GUI.level;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.clearlyspam23.GLE.Template;
import com.clearlyspam23.GLE.level.Level;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LevelPropertyDialog extends JDialog implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final LevelPropertyPanel levelPropertyPanel;
	
	private Level openLevel;

	/**
	 * Create the dialog.
	 */
	public LevelPropertyDialog(Template template) {
		setModal(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		levelPropertyPanel = new LevelPropertyPanel(template);
		levelPropertyPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(levelPropertyPanel, BorderLayout.CENTER);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(this);
				okButton.setActionCommand("OK");
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
	}
	
	public void showDialog(Level level){
		openLevel = level;
		levelPropertyPanel.setToLevel(level);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		boolean accepted = "OK".equals(e.getActionCommand());
		if(accepted)
		{
			levelPropertyPanel.setLevelTo(openLevel);
		}
		setVisible(false);
	}

}
