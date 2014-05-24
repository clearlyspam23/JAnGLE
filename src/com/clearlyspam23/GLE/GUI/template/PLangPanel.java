package com.clearlyspam23.GLE.GUI.template;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import com.clearlyspam23.GLE.PLanguageOptions;
import com.clearlyspam23.GLE.ParameterMacro;
import com.clearlyspam23.GLE.GUI.template.dialog.ParameterDialog;

public class PLangPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField displayInputField;
	private JTextField exeFileLoc;
	
	private JComboBox<String> comboBox;
	
	private JList<String> list_1;
	private DefaultListModel<String> list_1_model;
	
	private List<PLanguageOptions> recognizedLanguages;

	/**
	 * Create the panel.
	 */
	public PLangPanel(List<PLanguageOptions> rLangs, List<ParameterMacro> macros) {
		
		recognizedLanguages = rLangs;
		
		displayInputField = new JTextField();
		displayInputField.setBounds(67, 458, 405, 20);
		displayInputField.setEditable(false);
		displayInputField.setColumns(10);
		
		JLabel label = new JLabel("Run Options");
		label.setBounds(10, 11, 128, 20);
		label.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JLabel label_1 = new JLabel("Language");
		label_1.setBounds(10, 102, 69, 14);
		
		comboBox = new JComboBox<String>();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calculateText();
			}
		});
		DefaultComboBoxModel<String> cm = new DefaultComboBoxModel<String>();
		for(int i = 0; i < recognizedLanguages.size(); i++)
			cm.addElement(recognizedLanguages.get(i).getName());
		comboBox.setBounds(104, 99, 142, 20);
		comboBox.setModel(cm);
		
		JLabel label_2 = new JLabel("Parameters");
		label_2.setBounds(10, 252, 69, 14);
		
		JLabel label_3 = new JLabel("Full Input");
		label_3.setBounds(10, 461, 69, 14);
		
		JLabel lblExecutableLocation = new JLabel("Game Executable");
		lblExecutableLocation.setBounds(10, 62, 83, 14);
		
		exeFileLoc = new JTextField();
		exeFileLoc.setBounds(104, 59, 283, 20);
		exeFileLoc.setColumns(10);
		
		final JFileChooser fc = new JFileChooser();
		fc.setAcceptAllFileFilterUsed(true);
		JButton btnNewButton = new JButton("Browse");
		btnNewButton.setBounds(405, 58, 67, 23);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int ret = fc.showOpenDialog(PLangPanel.this);
				if(ret==JFileChooser.APPROVE_OPTION)
				{
					exeFileLoc.setText(fc.getSelectedFile().getPath());
					calculateText();
				}
			}
		});
		
		JList<String> list = new JList<String>();
		list.setBounds(104, 265, 1, 1);
		setLayout(null);
		add(label);
		add(label_3);
		add(displayInputField);
		add(lblExecutableLocation);
		add(label_1);
		add(label_2);
		add(list);
		add(comboBox);
		add(exeFileLoc);
		add(btnNewButton);
		
		list_1 = new JList<String>();
		list_1_model = new DefaultListModel<String>();
		list_1.setModel(list_1_model);
		
		list_1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list_1.setBounds(104, 251, 283, 148);
		add(list_1);
		
		JButton btnAdd = new JButton("Add");
		final ParameterDialog pdialog = new ParameterDialog(macros);
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				pdialog.showDialog();
				if(pdialog.isAccepted())
				{
					ArrayList<String> tokens = tokenizeBySpaceAndQuote(pdialog.getParameterText());
					int index = list_1.getSelectedIndex()+1;
					if(index<0||index>list_1_model.getSize())
						index = list_1_model.getSize();
					for(int i = 0; i < tokens.size(); i++)
					{
						list_1_model.add(index+i, tokens.get(i));
					}
					calculateText();
				}
			}
		});
		btnAdd.setBounds(219, 410, 79, 23);
		add(btnAdd);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = list_1.getSelectedIndex();
				if(index>=0&&index<list_1.getModel().getSize()){
					list_1_model.remove(index);
					list_1.setSelectedIndex(index-1);
					calculateText();
				}
			}
		});
		btnDelete.setBounds(308, 410, 79, 23);
		add(btnDelete);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 127, 462, 114);
		add(scrollPane);
		
		calculateText();

	}
	
	private void calculateText()
	{
		String ans = "";
		if(comboBox.getSelectedIndex()>=0&&comboBox.getSelectedIndex()<recognizedLanguages.size())
		{
			PLanguageOptions lang = recognizedLanguages.get(comboBox.getSelectedIndex());
			if(lang.getRuntimeCall()!=null&&lang.getRuntimeCall().length()>0)
				ans += '"' + recognizedLanguages.get(comboBox.getSelectedIndex()).getRuntimeCall() + '"' + " ";
		}
		if(exeFileLoc.getText()!=null&&exeFileLoc.getText().trim().length()>0)
			ans+= '"' + exeFileLoc.getText() + '"';
		for(int i = 0; i < list_1.getModel().getSize(); i++)
		{
			String s = list_1.getModel().getElementAt(i);
			if(s.indexOf(' ')>=0)
				s = '"' + s + '"';
			ans+=" " + s;
		}
		displayInputField.setText(ans);
	}
	
	private ArrayList<String> tokenizeBySpaceAndQuote(String s)
	{
		//i dont know how to regex
		ArrayList<String> ans = new ArrayList<String>();
		StringBuffer buf = new StringBuffer();
		for(int i = 0; i < s.length(); i++)
		{
			if(Character.isWhitespace(s.charAt(i)))
			{
				if(buf.length()>0)
				{
					ans.add(buf.toString());
					buf = new StringBuffer();
				}
			}
			else if(s.charAt(i)=='"')
			{
				for(i++; i < s.length(); i++)
				{
					if(s.charAt(i)=='"')
					{
						ans.add(buf.toString());
						buf = new StringBuffer();
						break;
					}
					else
						buf.append(s.charAt(i));
				}
			}
			else
				buf.append(s.charAt(i));
		}
		ans.add(buf.toString());
		return ans;
	}
}
