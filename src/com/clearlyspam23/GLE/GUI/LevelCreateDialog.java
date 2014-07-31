package com.clearlyspam23.GLE.GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.GridBagLayout;

import javax.swing.JLabel;

import java.awt.GridBagConstraints;
import java.awt.Font;

import javax.swing.JTextField;

import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.clearlyspam23.GLE.GUI.util.VectorComponent;
import com.clearlyspam23.GLE.util.Vector2;

import javax.swing.SwingConstants;

public class LevelCreateDialog extends JDialog implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private VectorComponent vectorComp;
	
	private String name;
	private Vector2 dimensions;
	private boolean accepted;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			LevelCreateDialog dialog = new LevelCreateDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public LevelCreateDialog() {
		setModal(true);
		setTitle("New Level");
		setBounds(100, 100, 340, 170);
		BorderLayout borderLayout = new BorderLayout();
		borderLayout.setVgap(10);
		borderLayout.setHgap(10);
		getContentPane().setLayout(borderLayout);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel nameLabel = new JLabel("Name");
			nameLabel.setHorizontalAlignment(SwingConstants.LEFT);
			nameLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
			GridBagConstraints gbc_nameLabel = new GridBagConstraints();
			gbc_nameLabel.insets = new Insets(0, 0, 5, 5);
			gbc_nameLabel.anchor = GridBagConstraints.WEST;
			gbc_nameLabel.gridx = 0;
			gbc_nameLabel.gridy = 0;
			contentPanel.add(nameLabel, gbc_nameLabel);
		}
		{
			textField = new JTextField();
			GridBagConstraints gbc_textField = new GridBagConstraints();
			gbc_textField.insets = new Insets(0, 0, 5, 0);
			gbc_textField.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField.gridx = 1;
			gbc_textField.gridy = 0;
			contentPanel.add(textField, gbc_textField);
			textField.setColumns(10);
		}
		{
			JLabel lblSize = new JLabel("Size");
			lblSize.setHorizontalAlignment(SwingConstants.LEFT);
			lblSize.setFont(new Font("Tahoma", Font.PLAIN, 12));
			GridBagConstraints gbc_lblSize = new GridBagConstraints();
			gbc_lblSize.anchor = GridBagConstraints.WEST;
			gbc_lblSize.insets = new Insets(0, 0, 0, 5);
			gbc_lblSize.gridx = 0;
			gbc_lblSize.gridy = 1;
			contentPanel.add(lblSize, gbc_lblSize);
		}
		{
			vectorComp = new VectorComponent();
			GridBagConstraints gbc_vectorComponent = new GridBagConstraints();
			gbc_vectorComponent.fill = GridBagConstraints.BOTH;
			gbc_vectorComponent.gridx = 1;
			gbc_vectorComponent.gridy = 1;
			contentPanel.add(vectorComp, gbc_vectorComponent);
		}
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
	}
	
	public void showDialog(Vector2 defaultValue){
		name = null;
		dimensions = null;
		accepted = false;
		vectorComp.setToVector(defaultValue);
		setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		accepted = "OK".equals(e.getActionCommand());
		if(accepted)
		{
			name = textField.getText();
			if(name==null||name.length()<=0){
				JOptionPane.showMessageDialog(this, "Name Cannot Be Empty", "Error Creating Level", JOptionPane.ERROR_MESSAGE);
				name = null;
				accepted = false;
				return;
			}
			dimensions = vectorComp.getVector();
			if(dimensions.x==0||dimensions.y==0){
				JOptionPane.showMessageDialog(this, "Dimensions Cannot Be 0", "Error Creating Level", JOptionPane.ERROR_MESSAGE);
				name = null;
				dimensions = null;
				accepted = false;
				return;
			}
		}
		setVisible(false);
	}
	
	public boolean isAccepted(){
		return accepted;
	}
	
	public String getLevelName(){
		return name;
	}
	
	public Vector2 getLevelDimensions(){
		return dimensions;
	}

}
