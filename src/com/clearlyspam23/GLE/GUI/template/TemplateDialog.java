package com.clearlyspam23.GLE.GUI.template;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

import com.clearlyspam23.GLE.JAnGLEData;
import com.clearlyspam23.GLE.PluginManager;
import com.clearlyspam23.GLE.Template;
import com.clearlyspam23.GLE.basic.compression.NoCompression;
import com.clearlyspam23.GLE.basic.compression.ZipCompression;
import com.clearlyspam23.GLE.basic.coordinates.BottomLeft;
import com.clearlyspam23.GLE.basic.coordinates.CenteredDown;
import com.clearlyspam23.GLE.basic.coordinates.CenteredUp;
import com.clearlyspam23.GLE.basic.coordinates.TopLeft;
import com.clearlyspam23.GLE.basic.languages.JavaLanguageOptions;
import com.clearlyspam23.GLE.basic.layers.tile.TileLayerDefinition;
import com.clearlyspam23.GLE.basic.parameters.CurrentLevelMacro;
import com.clearlyspam23.GLE.basic.parameters.CurrentTemplateMacro;
import com.clearlyspam23.GLE.basic.parameters.ExecutableDirectoryMacro;
import com.clearlyspam23.GLE.basic.parameters.ExecutableLocationMacro;
import com.clearlyspam23.GLE.basic.parameters.WorkingDirectoryMacro;
import com.clearlyspam23.GLE.basic.properties.IntPropertyDefinition;
import com.clearlyspam23.GLE.basic.properties.VectorPropertyDefinition;
import com.clearlyspam23.GLE.basic.serializers.JsonSerializer;
import com.clearlyspam23.GLE.level.LayerDefinition;
import com.clearlyspam23.GLE.level.LayerTemplate;
import com.clearlyspam23.GLE.template.serializer.TemplateSerializer;

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
	
	private JTabbedPane tabbedPane;

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
			JAnGLEData data = new JAnGLEData(manager);
			manager.addCoordinateSystems(new TopLeft());
			manager.addCoordinateSystems(new BottomLeft());
			manager.addCoordinateSystems(new CenteredDown());
			manager.addCoordinateSystems(new CenteredUp());
			
			manager.addProgrammingLanguage(new JavaLanguageOptions());
			
			manager.addMacro(new CurrentLevelMacro());
			manager.addMacro(new CurrentTemplateMacro());
			manager.addMacro(new ExecutableDirectoryMacro());
			manager.addMacro(new ExecutableLocationMacro());
			manager.addMacro(new WorkingDirectoryMacro());
			
			manager.addProperty(new IntPropertyDefinition());
			manager.addProperty(new VectorPropertyDefinition());
			
			manager.addCompression(new NoCompression());
			manager.addCompression(new ZipCompression());
			
			manager.addSerializer(new JsonSerializer());
			
			manager.addLayerDefinition(new TileLayerDefinition());
			
			manager.addTemplatePanel(new GeneralPanel(data.getPlugins()));
			manager.addTemplatePanel(new LayerPanel(data.getPlugins()));
			manager.addAdvancedTemplatePanel(new PLangPanel(data.getPlugins()));
			
			TemplateDialog dialog = new TemplateDialog(data);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.showDialog();
//			Template t = dialog.getTemplate();
//			TemplateSerializer serializer = new TemplateSerializer(manager);
//			if(t!=null){
//				String s = serializer.serialize(t);
//				PrintWriter w = new PrintWriter(t.getTemplateFile());
//				w.print(s);
//				w.close();
//				Template recreate = serializer.deserialize(s, t.getTemplateFile());
//				System.out.println(recreate);
//			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private JAnGLEData data;

	/**
	 * Create the dialog.
	 */
	public TemplateDialog(JAnGLEData data) {
		setModal(true);
		this.data = data;
		
		//this code should be moved somewhere else eventually
		for(TemplateSubPanel p : data.getPlugins().getTemplatePanels()){
			subPanels.add(p);
		}
		for(TemplateSubPanel p : data.getPlugins().getAdvancedTemplatePanels())
			subPanels.add(p);
		subPanels.add(new GeneralPanel(data.getPlugins()));
		subPanels.add(new PLangPanel(data.getPlugins()));
		subPanels.add(new LayerPanel(data.getPlugins()));
		setResizable(false);
		
		setBounds(100, 100, 580, 680);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPanel.add(tabbedPane);
		
		for(TemplateSubPanel panel : data.getPlugins().getTemplatePanels())
			tabbedPane.addTab(panel.getPanelName(), panel);
		JPanel aPanel = new JPanel();
		aPanel.setLayout(new BorderLayout());
		JTabbedPane advanced = new JTabbedPane();
		aPanel.add(advanced, BorderLayout.CENTER);
		for(TemplateSubPanel p : data.getPlugins().getAdvancedTemplatePanels()){
			advanced.addTab(p.getPanelName(), p);
		}
		tabbedPane.addTab("Advanced", advanced);
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
	
	public void showDialog(Template template){
		accepted = false;
		this.template = template;
		for(TemplateSubPanel p : subPanels)
			p.reset();
		if(template!=null)
			for(TemplateSubPanel p : subPanels)
				p.setToTemplate(template);
		setVisible(true);
		tabbedPane.setSelectedIndex(0);
	}
	
	public void showDialog(){
		showDialog(new Template());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		accepted = "OK".equals(e.getActionCommand());
		if(accepted)
		{
			Template template = new Template();
			for(TemplateSubPanel p : subPanels)
				p.generateTemplate(template);
			Set<LayerDefinition> seen = new HashSet<LayerDefinition>();
			for(LayerTemplate lt : template.getLayers()){
				if(!seen.contains(lt.getDefinition())){
					lt.getDefinition().onTemplateCreation(template);
					seen.add(lt.getDefinition());
				}
			}
			this.template = template;
			if(!data.saveTemplate(template)){
				//TODO this is an error, handle it somehow
				return;
			}
		}
		setVisible(false);
	}

	public Template getTemplate() {
		return template;
	}
	
	public void reset(){
		for(TemplateSubPanel p : subPanels)
			p.reset();
	}
}
