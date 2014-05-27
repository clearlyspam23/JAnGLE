package com.clearlyspam23.GLE.basic.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import com.clearlyspam23.GLE.GUI.SubPanel;
import com.clearlyspam23.GLE.util.Utility;

public class JavaLanguagePanel extends SubPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField javaTextField;
	private JTextField textField_1;
	private JList<String> list;
	private DefaultListModel<String> list_model;

	public JavaLanguagePanel(String javaLocation) {

		JLabel lblJavaLocation = new JLabel("Java Location");

		javaTextField = new JTextField();
		javaTextField.setColumns(10);
		javaTextField.setText(javaLocation);

		final JFileChooser fc = new JFileChooser();
		fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		//fc.setAcceptAllFileFilterUsed(true);
		
		JButton btnBrowse = new JButton("Browse");
		btnBrowse.setToolTipText("The directory of your java executable");
		btnBrowse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int ret = fc.showOpenDialog(JavaLanguagePanel.this);
				if(ret==JFileChooser.APPROVE_OPTION)
				{
					javaTextField.setText(fc.getSelectedFile().getPath());
					registerChange();
				}
			}
		});

		JLabel lblJvmArguments = new JLabel("JVM Arguments");

		list = new JList<String>();
		
		list_model = new DefaultListModel<String>();
		list.setModel(list_model);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);

		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				ArrayList<String> tokens = Utility.tokenizeBySpaceAndQuote(textField_1.getText());
				int index = list.getSelectedIndex()+1;
				if(index<0||index>list_model.getSize())
					index = list_model.getSize();
				for(int i = 0; i < tokens.size(); i++)
				{
					list_model.add(index+i, tokens.get(i));
				}
				textField_1.setText("");
				registerChange();
			}
		});

		JButton btnRemove = new JButton("Remove");
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(7)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblJavaLocation)
						.addGroup(groupLayout.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblJvmArguments)))
					.addGap(10)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnAdd, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnRemove))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(javaTextField, GroupLayout.PREFERRED_SIZE, 194, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnBrowse, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE))
								.addComponent(list, GroupLayout.PREFERRED_SIZE, 289, GroupLayout.PREFERRED_SIZE))
							.addGap(10))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(7)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(lblJavaLocation))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(2)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(javaTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnBrowse))))
					.addGap(19)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblJvmArguments)
						.addComponent(list, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnAdd)
						.addComponent(btnRemove))
					.addContainerGap(14, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
	}

	public JTextField getJavaTextField() {
		return javaTextField;
	}

	public JTextField getTextField_1() {
		return textField_1;
	}

	public JList<String> getList() {
		return list;
	}

	@Override
	public void reset() {
		//txtWhatever.setText("");
		textField_1.setText("");
	}
}
