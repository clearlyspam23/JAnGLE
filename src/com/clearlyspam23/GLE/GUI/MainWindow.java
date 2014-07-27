package com.clearlyspam23.GLE.GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.KeyStroke;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.clearlyspam23.GLE.JAnGLEData;
import com.clearlyspam23.GLE.PluginManager;
import com.clearlyspam23.GLE.Template;
import com.clearlyspam23.GLE.GUI.template.TemplateDialog;
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

public class MainWindow extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			PluginManager manager = new PluginManager();
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
			
			final JAnGLEData data = new JAnGLEData(manager);
			
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						MainWindow frame = new MainWindow(data);
						frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public MainWindow(){
		this(null);
	}
	
	private JAnGLEData data;
	
	private JMenu mnFile;
	private JMenuItem mntmNewTemplate;
	private JMenuItem mntmOpenTemplate;
	private JMenuItem mntmCloseTemplate;
	
	private JMenuItem mntmNewLevel;
	private JMenuItem mntmOpenLevel;
	private JMenuItem mntmSaveLevel;
	private JMenuItem mntmSaveLevelAs;
	
	private JMenuItem mntmUndo;
	private JMenuItem mntmRedo;

	/**
	 * Create the frame.
	 */
	public MainWindow(final JAnGLEData data) {
		this.data = data;
		setTitle("JAnGLE");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		
		final TemplateDialog dialog = new TemplateDialog(data.getPlugins());
		dialog.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
//		dialog.setVisible(true);
//		Template t = dialog.getTemplate();
//		TemplateSerializer serializer = new TemplateSerializer(manager);
//		if(t!=null){
//			String s = serializer.serialize(t);
//			PrintWriter w = new PrintWriter(t.getTemplateFile());
//			w.print(s);
//			w.close();
//			Template recreate = serializer.deserialize(s, t.getTemplateFile());
//			System.out.println(recreate);
//		}
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		mntmNewTemplate = new JMenuItem("New Template");
		mntmNewTemplate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dialog.setVisible(true);
				Template t = dialog.getTemplate();
				if(t!=null){
					data.setOpenTemplate(t);
				}
			}
		});
		
		mntmNewLevel = new JMenuItem("New Level");
		mnFile.add(mntmNewLevel);
		
		mntmOpenLevel = new JMenuItem("OpenLevel");
		mnFile.add(mntmOpenLevel);
		
		mntmSaveLevel = new JMenuItem("Save Level");
		mntmSaveLevel.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		mnFile.add(mntmSaveLevel);
		
		mntmSaveLevelAs = new JMenuItem("Save Level As");
		mntmSaveLevelAs.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK | InputEvent.SHIFT_MASK));
		mnFile.add(mntmSaveLevelAs);
		
		JSeparator separator = new JSeparator();
		mnFile.add(separator);
		mnFile.add(mntmNewTemplate);
		
		final JFileChooser fc = new JFileChooser();
		fc.setDialogType(JFileChooser.OPEN_DIALOG);
		FileNameExtensionFilter filter = new FileNameExtensionFilter("JAnGLE Templates (*.jant)", "jant");
		fc.setFileFilter(filter);
		
		mntmOpenTemplate = new JMenuItem("Open Template");
		mntmOpenTemplate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int ret = fc.showSaveDialog(MainWindow.this);
				if(ret==JFileChooser.APPROVE_OPTION){
					File f = fc.getSelectedFile();
					try {
						String contents = new String(Files.readAllBytes(Paths.get(f.toURI())));
						data.setOpenTemplate(data.getSerializer().deserialize(contents, f));
					} catch (IOException e) {
						//TODO something better here, some sort of error maybe
						e.printStackTrace();
					}
				}
			}
		});
		mnFile.add(mntmOpenTemplate);
		
		mntmCloseTemplate = new JMenuItem("Close Template");
		mntmCloseTemplate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				data.setOpenTemplate(null);
			}
		});
		mnFile.add(mntmCloseTemplate);
		
		JMenu mnEdit = new JMenu("Edit");
		menuBar.add(mnEdit);
		
		mntmUndo = new JMenuItem("Undo");
		mntmUndo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_MASK));
		mnEdit.add(mntmUndo);
		
		mntmRedo = new JMenuItem("Redo");
		mntmRedo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y, InputEvent.CTRL_MASK));
		mnEdit.add(mntmRedo);
		
		JMenu mnLevel = new JMenu("Level");
		menuBar.add(mnLevel);
		
		JMenu mnGle = new JMenu("GLE");
		menuBar.add(mnGle);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane);
		
		JPanel levelPanel = new TestLevelPanel();
		tabbedPane.addTab("New tab", null, levelPanel, null);
		
//		TestInternal testInternal = new TestInternal();
//		contentPane.add(testInternal);
		
		JPanel InfoPanel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) InfoPanel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		contentPane.add(InfoPanel, BorderLayout.SOUTH);
		
		JLabel mouseLabel = new JLabel("Mouse");
		InfoPanel.add(mouseLabel);
		
		JLabel mouseLoc = new JLabel("");
		InfoPanel.add(mouseLoc);
		checkMenu();
	}
	
	private void checkMenu(){
		boolean hasData = data!=null;
		mntmNewTemplate.setEnabled(hasData);
		mntmOpenTemplate.setEnabled(hasData);
		mntmCloseTemplate.setEnabled(hasData&&data.getOpenTemplate()!=null);
		mntmNewLevel.setEnabled(hasData&&data.getOpenTemplate()!=null);
		mntmOpenLevel.setEnabled(hasData&&data.getOpenTemplate()!=null);
		mntmSaveLevel.setEnabled(hasData&&data.getCurrentLevel()!=null);
		mntmSaveLevelAs.setEnabled(hasData&&data.getCurrentLevel()!=null);
	}
}
