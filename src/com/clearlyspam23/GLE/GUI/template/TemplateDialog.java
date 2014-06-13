package com.clearlyspam23.GLE.GUI.template;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

import com.clearlyspam23.GLE.PluginManager;
import com.clearlyspam23.GLE.Template;
import com.clearlyspam23.GLE.basic.coordinates.BottomLeft;
import com.clearlyspam23.GLE.basic.coordinates.CenteredDown;
import com.clearlyspam23.GLE.basic.coordinates.CenteredUp;
import com.clearlyspam23.GLE.basic.coordinates.TopLeft;
import com.clearlyspam23.GLE.basic.languages.JavaLanguageOptions;
import com.clearlyspam23.GLE.basic.parameters.CurrentLevelMacro;

public class TemplateDialog extends JDialog implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	
//	private final GeneralPanel generalPanel;
//	private final PLangPanel langPanel;
//	private final LayerPanel layerPanel;
	
	private final List<TemplateSubPanel> subPanels = new ArrayList<TemplateSubPanel>();
	
	private boolean accepted = false;
	
	private Template template;

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
			PluginManager manager = new PluginManager();
			manager.addCoordinateSystems(new TopLeft());
			manager.addCoordinateSystems(new BottomLeft());
			manager.addCoordinateSystems(new CenteredDown());
			manager.addCoordinateSystems(new CenteredUp());
			
			manager.addProgrammingLanguage(new JavaLanguageOptions());
			
			manager.addMacro(new CurrentLevelMacro());
			
			TemplateDialog dialog = new TemplateDialog(manager);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public TemplateDialog(PluginManager manager) {
		
		setBounds(100, 100, 540, 620);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 504, 526);
		contentPanel.add(tabbedPane);
		
		TemplateSubPanel panel = new GeneralPanel(manager);
		subPanels.add(panel);
		tabbedPane.addTab(panel.getName(), null, panel, null);
		
		panel = new PLangPanel(manager);
		subPanels.add(panel);
		tabbedPane.addTab(panel.getName(), null, panel, null);
		
		panel = new LayerPanel(manager);
		subPanels.add(panel);
		tabbedPane.addTab(panel.getName(), null, panel, null);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				okButton.addActionListener(this);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
				getRootPane().setDefaultButton(cancelButton);
				cancelButton.addActionListener(this);
			}
		}
	}
	
	public boolean isAccepted(){
		return accepted;
	}
	
	public void showDialog(){
		accepted = false;
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		accepted = "OK".equals(e.getActionCommand());
		if(accepted)
		{
			Template template = new Template();
			for(TemplateSubPanel p : subPanels)
				p.generateTemplate(template);
		}
		setVisible(false);
	}
}
