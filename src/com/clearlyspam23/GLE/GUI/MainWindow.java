package com.clearlyspam23.GLE.GUI;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.commons.io.FilenameUtils;

import com.clearlyspam23.GLE.JAnGLEData;
import com.clearlyspam23.GLE.LastRunInfo;
import com.clearlyspam23.GLE.Level;
import com.clearlyspam23.GLE.PluginManager;
import com.clearlyspam23.GLE.Template;
import com.clearlyspam23.GLE.GUI.level.LevelPanel;
import com.clearlyspam23.GLE.GUI.template.TemplateDialog;
import com.clearlyspam23.GLE.GUI.util.ConfirmationFileChooser;
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
import com.clearlyspam23.GLE.util.TwoWayMap;

public class MainWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;

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
	
	private final JTabbedPane tabbedPane;
	
	private final JFileChooser fc;
	
	private LastRunInfo lastRunInfo = new LastRunInfo();
	
	private TwoWayMap<Level, LevelPanel> levelPanelMap = new TwoWayMap<Level, LevelPanel>();

	/**
	 * Create the frame.
	 */
	public MainWindow(final JAnGLEData data) {
		this.data = data;
		data.setFrame(this);
		setTitle("JAnGLE");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		
		final TemplateDialog dialog = new TemplateDialog(data);
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
				dialog.showDialog();
				if(dialog.isAccepted()){
					Template t = dialog.getTemplate();
					data.setOpenTemplate(t);
				}
				checkMenu();
			}
		});
		
		mntmNewLevel = new JMenuItem("New Level");
		mntmNewLevel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newLevel();
				checkMenu();
			}
		});
		mnFile.add(mntmNewLevel);
		
		mntmOpenLevel = new JMenuItem("OpenLevel");
		mntmOpenLevel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openLevels();
				checkMenu();
			}
		});
		mnFile.add(mntmOpenLevel);
		
		mntmSaveLevel = new JMenuItem("Save Level");
		mntmSaveLevel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				saveLevel(data.getCurrentLevel());
				checkMenu();
			}
		});
		mntmSaveLevel.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		mnFile.add(mntmSaveLevel);
		
		mntmSaveLevelAs = new JMenuItem("Save Level As");
		mntmSaveLevelAs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveLevelAs(data.getCurrentLevel());
				checkMenu();
			}
		});
		mntmSaveLevelAs.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK | InputEvent.SHIFT_MASK));
		mnFile.add(mntmSaveLevelAs);
		
		JSeparator separator = new JSeparator();
		mnFile.add(separator);
		mnFile.add(mntmNewTemplate);
		
		fc = new ConfirmationFileChooser();
		
		
		mntmOpenTemplate = new JMenuItem("Open Template");
		mntmOpenTemplate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(showOpenTemplateDialog()){
					File f = fc.getSelectedFile();
					loadTemplate(f);
				}
				checkMenu();
			}
		});
		mnFile.add(mntmOpenTemplate);
		
		mntmCloseTemplate = new JMenuItem("Close Template");
		mntmCloseTemplate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(Level l : data.getOpenLevels()){
					if(l.needsSave()){
						int i = JOptionPane.showConfirmDialog(MainWindow.this, l.getName() + " Has Unsaved Changes. Would You Like to Save?", "Save Level", JOptionPane.YES_NO_CANCEL_OPTION);
						if(i==JOptionPane.YES_OPTION){
							saveLevel(l);
						}
						else if(i==JOptionPane.CANCEL_OPTION){
							return;
						}
					}
				}
				data.closeAllLevels();
				tabbedPane.removeAll();
				for(LevelPanel p : levelPanelMap.getValues()){
					p.dispose();
				}
				levelPanelMap.clear();
				data.setOpenTemplate(null);
				checkMenu();
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
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				if(tabbedPane.getSelectedIndex()>=0){
					Component c = tabbedPane.getComponent(tabbedPane.getSelectedIndex());
					if(c instanceof LevelPanel){
						LevelPanel current = levelPanelMap.getNormal(data.getCurrentLevel());
						if(current!=null){
							
						}
						LevelPanel panel = (LevelPanel)c;
						data.setCurrentLevel(levelPanelMap.getReverse(panel));
						System.out.println("changed current level");
					}
				}
			}
		});
		contentPane.add(tabbedPane);
		
		JPanel InfoPanel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) InfoPanel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		contentPane.add(InfoPanel, BorderLayout.SOUTH);
		
		JLabel mouseLabel = new JLabel("Mouse");
		InfoPanel.add(mouseLabel);
		
		JLabel mouseLoc = new JLabel("");
		InfoPanel.add(mouseLoc);
		if(lastRunInfo.load()){
			if(lastRunInfo.hasOpenTemplate()){
				File f = lastRunInfo.getTemplateFile();
				if(f.exists()&&loadTemplate(f)){
					int num = 0;
					for(File file : lastRunInfo.getOpenLevels()){
						try{
							Level l = data.openLevel(file);
							openLevel(l);
							if(lastRunInfo.isCurrentLevel(file)){
								data.setCurrentLevel(l);
								tabbedPane.setSelectedIndex(num);
							}
							num++;
						}
						catch(IOException e){
							//JOptionPane.showMessageDialog(this, "Unable to Open Level " + f.getAbsolutePath() + " : Access Might Be Denied", "Error Opening Level", JOptionPane.ERROR_MESSAGE);
						}
						catch(Exception e){
							//this failure will fail subtly
							//JOptionPane.showMessageDialog(this, "Unable to Deserialize " + f.getAbsolutePath() + " : A Mismatching Template Might Be Open", "Error Opening Level", JOptionPane.ERROR_MESSAGE);
						}
					}
				}
			}
		}
		addWindowListener(new WindowAdapter(){
			
			@Override
			public void windowClosing(WindowEvent e) {
			    lastRunInfo.save(data);
			}
		});
		checkMenu();
	}
	
	private boolean loadTemplate(File f){
		try {
			String contents = new String(Files.readAllBytes(Paths.get(f.toURI())));
			data.setOpenTemplate(data.getSerializer().deserialize(contents, f));
			return true;
		} catch (IOException e) {
			//TODO something better here, some sort of error maybe
			e.printStackTrace();
		}
		return false;
	}
	
	private void newLevel(){
		Level l = data.getOpenTemplate().generateLevel();
		l.setDimensions(data.getOpenTemplate().getDefaultSize().x, data.getOpenTemplate().getDefaultSize().y);
		openLevel(l);
	}
	
	private void openLevels(File[] files){
		for(File f : fc.getSelectedFiles()){
			try{
				openLevel(data.openLevel(f));
			}
			catch(IOException e){
				JOptionPane.showMessageDialog(this, "Unable to Open Level " + f.getAbsolutePath() + " : Access Might Be Denied", "Error Opening Level", JOptionPane.ERROR_MESSAGE);
			}
			catch(Exception e){
				JOptionPane.showMessageDialog(this, "Unable to Deserialize " + f.getAbsolutePath() + " : A Mismatching Template Might Be Open", "Error Opening Level", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	private void openLevels(){
		if(showOpenLevelDialog()){
			openLevels(fc.getSelectedFiles());
//			for(File f : fc.getSelectedFiles()){
//				try{
//					openLevel(data.openLevel(f));
//				}
//				catch(IOException e){
//					JOptionPane.showMessageDialog(this, "Unable to Open Level " + f.getAbsolutePath() + " : Access Might Be Denied", "Error Opening Level", JOptionPane.ERROR_MESSAGE);
//				}
//				catch(Exception e){
//					JOptionPane.showMessageDialog(this, "Unable to Deserialize " + f.getAbsolutePath() + " : A Mismatching Template Might Be Open", "Error Opening Level", JOptionPane.ERROR_MESSAGE);
//				}
//			}
		}
	}
	
	private void openLevel(Level l){
		data.addOpenLevel(l);
		LevelPanel pan = new LevelPanel(l, this);
		levelPanelMap.put(l, pan);
		tabbedPane.addTab(l.getName(), pan);
		if(data.getCurrentLevel()==null){
			data.setCurrentLevel(l);
		}
	}
	
	private void saveLevelAs(Level level){
		if(showSaveLevelDialog(level.getName())){
			File f = fc.getSelectedFile();
			String extension = data.getOpenTemplate().getExtension();
			File ans = new File(FilenameUtils.removeExtension(f.getPath())+extension);
			level.setSaveFile(ans);
			saveLevel(level);
			for(int i = 0; i < tabbedPane.getTabCount(); i++){
				Component comp = tabbedPane.getComponentAt(i);
				System.out.println("index : " + tabbedPane.getTitleAt(i) + " : " + comp);
				if(comp instanceof LevelPanel){
					System.out.println("new title : " + ((LevelPanel)comp).getLevelName());
					tabbedPane.setTitleAt(i, ((LevelPanel)comp).getLevelName());
				}
			}
		}
	}
	
	private void saveLevel(Level level){
		if(level.getSaveFile()==null){
			saveLevelAs(level);
			return;
		}
		if(!data.saveLevel(level)){
			//TODO display an error here.
		}
	}
	
	private boolean showOpenTemplateDialog(){
		fc.setDialogType(JFileChooser.OPEN_DIALOG);
		fc.setCurrentDirectory(new File(Template.defaultLocation));
		FileNameExtensionFilter filter = new FileNameExtensionFilter("JAnGLE Templates (*.jant)", "jant");
		fc.setFileFilter(filter);
		fc.setMultiSelectionEnabled(false);
		int ret = fc.showOpenDialog(MainWindow.this);
		return ret==JFileChooser.APPROVE_OPTION;
	}
	
	private boolean showSaveLevelDialog(String name){
		fc.setDialogType(JFileChooser.SAVE_DIALOG);
		String extension = data.getOpenTemplate().getExtension();
		fc.setSelectedFile(new File(name+extension));
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Level File (*"+extension+")", extension.replaceAll("\\.", ""));
		fc.setFileFilter(filter);
		fc.setMultiSelectionEnabled(false);
		int ret = fc.showSaveDialog(MainWindow.this);
		return ret==JFileChooser.APPROVE_OPTION;
	}
	
	private boolean showOpenLevelDialog(){
		fc.setDialogType(JFileChooser.OPEN_DIALOG);
		String extension = data.getOpenTemplate().getExtension();
		fc.setSelectedFile(new File(""));
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Level File (*"+extension+")", extension.replaceAll("\\.", ""));
		fc.setFileFilter(filter);
		fc.setMultiSelectionEnabled(true);
		int ret = fc.showOpenDialog(MainWindow.this);
		return ret==JFileChooser.APPROVE_OPTION;
	}
	
	private void checkMenu(){
		boolean hasData = data!=null;
		mntmNewTemplate.setEnabled(hasData&&data.getOpenTemplate()==null);
		mntmOpenTemplate.setEnabled(hasData&&data.getOpenTemplate()==null);
		mntmCloseTemplate.setEnabled(hasData&&data.getOpenTemplate()!=null);
		mntmNewLevel.setEnabled(hasData&&data.getOpenTemplate()!=null);
		mntmOpenLevel.setEnabled(hasData&&data.getOpenTemplate()!=null);
		mntmSaveLevel.setEnabled(hasData&&data.getCurrentLevel()!=null);
		mntmSaveLevelAs.setEnabled(hasData&&data.getCurrentLevel()!=null);
	}
}
