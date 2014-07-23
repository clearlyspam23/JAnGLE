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
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.clearlyspam23.GLE.PluginManager;
import com.clearlyspam23.GLE.Template;
import com.clearlyspam23.GLE.GUI.PLangSubPanel;
import com.clearlyspam23.GLE.GUI.template.dialog.ParameterDialog;
import com.clearlyspam23.GLE.template.PLanguageOptions;
import com.clearlyspam23.GLE.util.Utility;

public class PLangPanel extends TemplateSubPanel implements ChangeListener{
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
	
	private PLangSubPanel[] panels;
	
	private PLangSubPanel currentPanel;
	
	private JScrollPane scrollPane;

	/**
	 * Create the panel.
	 */
	public PLangPanel(PluginManager pluginManager) {
		super(pluginManager);
		recognizedLanguages = pluginManager.getRecognizedProgrammingLanguages();
		panels = new PLangSubPanel[recognizedLanguages.size()];
		for(int i = 0; i < recognizedLanguages.size(); i++)
		{
			panels[i] = recognizedLanguages.get(i).getPanel();
			panels[i].addChangeListener(this);
		}
		
		displayInputField = new JTextField();
		displayInputField.setBounds(67, 519, 473, 20);
		displayInputField.setEditable(false);
		displayInputField.setColumns(10);
		
		JLabel label = new JLabel("Run Options");
		label.setBounds(10, 11, 128, 20);
		label.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JLabel label_1 = new JLabel("Language");
		label_1.setBounds(10, 76, 69, 14);
		
		comboBox = new JComboBox<String>();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				stateChanged();
			}
		});
		DefaultComboBoxModel<String> cm = new DefaultComboBoxModel<String>();
		for(int i = 0; i < recognizedLanguages.size(); i++)
			cm.addElement(recognizedLanguages.get(i).getName());
		comboBox.setBounds(104, 73, 179, 20);
		comboBox.setModel(cm);
		
		JLabel label_2 = new JLabel("Parameters");
		label_2.setBounds(10, 363, 69, 14);
		
		JLabel label_3 = new JLabel("Full Input");
		label_3.setBounds(10, 522, 69, 14);
		
		JLabel lblExecutableLocation = new JLabel("Game Executable");
		lblExecutableLocation.setBounds(10, 42, 83, 14);
		
		exeFileLoc = new JTextField();
		exeFileLoc.setBounds(103, 42, 358, 20);
		exeFileLoc.setColumns(10);
		
		final JFileChooser fc = new JFileChooser();
		fc.setAcceptAllFileFilterUsed(true);
		JButton btnNewButton = new JButton("Browse");
		btnNewButton.setBounds(471, 38, 69, 23);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int ret = fc.showOpenDialog(PLangPanel.this);
				if(ret==JFileChooser.APPROVE_OPTION)
				{
					exeFileLoc.setText(fc.getSelectedFile().getPath());
					stateChanged();
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
		list_1_model = new DefaultListModel<String>();
		
		JButton btnAdd = new JButton("Add");
		final ParameterDialog pdialog = new ParameterDialog(pluginManager.getRecognizedMacros());
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				pdialog.showDialog();
				if(pdialog.isAccepted())
				{
					ArrayList<String> tokens = Utility.tokenizeBySpaceAndQuote(pdialog.getParameterText());
					int index = list_1.getSelectedIndex()+1;
					if(index<0||index>list_1_model.getSize())
						index = list_1_model.getSize();
					for(int i = 0; i < tokens.size(); i++)
					{
						list_1_model.add(index+i, tokens.get(i));
					}
					stateChanged();
				}
			}
		});
		btnAdd.setBounds(293, 485, 79, 23);
		add(btnAdd);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = list_1.getSelectedIndex();
				if(index>=0&&index<list_1.getModel().getSize()){
					list_1_model.remove(index);
					list_1.setSelectedIndex(index-1);
					stateChanged();
				}
			}
		});
		btnDelete.setBounds(382, 485, 79, 23);
		add(btnDelete);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 101, 530, 249);
		add(scrollPane);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(104, 361, 358, 113);
		add(scrollPane_1);
		
		list_1 = new JList<String>();
		scrollPane_1.setViewportView(list_1);
		list_1.setModel(list_1_model);
		
		list_1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
//		scrollPanel = new JPanel();
//		scrollPane.setViewportView(scrollPanel);
		
		stateChanged();

	}
	
	//private int counter = 0;
	
	public void stateChanged()
	{
		stateChanged(null);
	}
	
	@SuppressWarnings("unchecked")
	public void stateChanged(ChangeEvent e)
	{
		String ans = "";
//		scrollPanel.removeAll();
		if(comboBox.getSelectedIndex()>=0&&comboBox.getSelectedIndex()<recognizedLanguages.size())
		{
			@SuppressWarnings("rawtypes")
			PLanguageOptions lang = recognizedLanguages.get(comboBox.getSelectedIndex());
			currentPanel = panels[comboBox.getSelectedIndex()];
			scrollPane.setViewportView(currentPanel);
			if(currentPanel!=null)
			{
				currentPanel.reset();
			}
			revalidate();
			repaint();
			String text = lang.buildRuntimeCall(currentPanel);
			if(text!=null&&text.length()>0)
				ans += text + " ";
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
	
	public void setToTemplate(Template template)
	{
		list_1_model.clear();
		for(String s : template.getParameterMacros())
		{
			list_1_model.addElement(s);
		}
		stateChanged();
	}

	@Override
	public String getPanelName() {
		return "Run";
	}

	@Override
	public void generateTemplate(Template template) {
		
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<String> verify() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
