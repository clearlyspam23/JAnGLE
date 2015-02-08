package com.clearlyspam23.GLE.basic.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import com.clearlyspam23.GLE.GUI.PLangSubPanel;
import com.clearlyspam23.GLE.util.Utility;
import javax.swing.JScrollPane;

public class JavaLanguagePanel extends PLangSubPanel<JavaLanguagePanel.JavaLanguageData> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField javaTextField;
	private JTextField textField_1;
	private JList<String> list;
	private DefaultListModel<String> list_model;
	
	public static class JavaLanguageData{
		public String javaLocation;
		public List<String> parameters;
	}

	public JavaLanguagePanel(String javaLocation) {

		JLabel lblJavaLocation = new JLabel("Java Location");
		lblJavaLocation.setBounds(7, 12, 66, 14);

		javaTextField = new JTextField();
		javaTextField.setBounds(91, 10, 259, 20);
		javaTextField.setColumns(10);
		javaTextField.setText(javaLocation);

		final JFileChooser fc = new JFileChooser();
		fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		//fc.setAcceptAllFileFilterUsed(true);
		
		JButton btnBrowse = new JButton("Browse");
		btnBrowse.setBounds(360, 8, 89, 23);
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
		lblJvmArguments.setBounds(7, 52, 74, 14);
		
		list_model = new DefaultListModel<String>();
		
		textField_1 = new JTextField();
		textField_1.setBounds(91, 152, 212, 20);
		textField_1.setColumns(10);

		JButton btnAdd = new JButton("Add");
		btnAdd.setBounds(313, 151, 63, 23);
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

		JButton btnRemove = new JButton("Delete");
		btnRemove.setBounds(386, 151, 63, 23);
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int index = list.getSelectedIndex();
				if(index>=0&&index<list.getModel().getSize()){
					list_model.remove(index);
					list.setSelectedIndex(index-1);
					registerChange();
				}
			}
		});
		setLayout(null);
		add(lblJavaLocation);
		add(lblJvmArguments);
		add(textField_1);
		add(btnAdd);
		add(btnRemove);
		add(javaTextField);
		add(btnBrowse);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(91, 50, 358, 93);
		add(scrollPane);
				
						list = new JList<String>();
						scrollPane.setViewportView(list);
						list.setModel(list_model);
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

	@Override
	public JavaLanguageData getData() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setToData(JavaLanguageData data) {
		// TODO Auto-generated method stub
		
	}
}
