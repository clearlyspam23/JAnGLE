package com.clearlyspam23.GLE.GUI.template.dialog;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.clearlyspam23.GLE.ParameterMacro;
import com.clearlyspam23.GLE.defaultparameters.CurrentLevelMacro;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ParameterDialog extends JDialog implements ActionListener{

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTable table;
	
	private boolean accepted = false;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ParameterDialog dialog = new ParameterDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ParameterDialog() {
		setTitle("Add Parameters");
		final ParameterMacro[] macros = new ParameterMacro[]{
				new CurrentLevelMacro()
		};
		setModal(true);
		setBounds(100, 100, 452, 340);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			textField = new JTextField();
			textField.setBounds(10, 36, 414, 20);
			contentPanel.add(textField);
			textField.setColumns(10);
		}
		{
			JLabel lblParameterMacros = new JLabel("Parameter Macros");
			lblParameterMacros.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblParameterMacros.setBounds(10, 67, 135, 14);
			contentPanel.add(lblParameterMacros);
		}
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 92, 414, 165);
		contentPanel.add(scrollPane);
		
		table = new JTable(){
			public boolean isCellEditable(int row, int column) {                
                return false;               
			};
		};
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getClickCount()==2)
				{
					int i = table.getSelectedRow();
					if(i>=0&&i<table.getRowCount())
					{
						String value = (String) table.getModel().getValueAt(i, 0);
						if(textField.getText()!=null&&textField.getText().trim().length()>0)
							value = " " + value;
						textField.setText(textField.getText() + value);
					}
				}
			}
		});
		Object[][] model = new Object[macros.length][2];
		for(int i = 0; i < macros.length; i++)
		{
			model[i][0] = macros[i].getMacro();
			model[i][1] = macros[i].getDescription();
		}
		table.setModel(new DefaultTableModel(
			model,
			new String[] {
				"Macro", "Value"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(100);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(200);
		table.setColumnSelectionAllowed(true);
		table.setFillsViewportHeight(true);
		scrollPane.setViewportView(table);
		{
			JLabel lblParameters = new JLabel("Parameters");
			lblParameters.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblParameters.setBounds(10, 11, 97, 14);
			contentPanel.add(lblParameters);
		}
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
				cancelButton.addActionListener(this);
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		accepted = "OK".equals(arg0.getActionCommand());
		System.out.println(accepted);
		setVisible(false);
	}
	
	public void showDialog(){
		textField.setText("");
		accepted = false;
		table.clearSelection();
		setVisible(true);
	}
	
	public boolean isAccepted(){
		return accepted;
	}
	
	public String getParameterText()
	{
		return textField.getText();
	}
}
