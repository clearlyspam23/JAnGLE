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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.clearlyspam23.GLE.PluginManager;
import com.clearlyspam23.GLE.Template;
import com.clearlyspam23.GLE.GUI.level.LayerChangeListener;
import com.clearlyspam23.GLE.GUI.level.LevelPanel;
import com.clearlyspam23.GLE.GUI.level.LevelPropertyDialog;
import com.clearlyspam23.GLE.GUI.template.GeneralPanel;
import com.clearlyspam23.GLE.GUI.template.LayerPanel;
import com.clearlyspam23.GLE.GUI.template.PLangPanel;
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
import com.clearlyspam23.GLE.edit.EditAction;
import com.clearlyspam23.GLE.edit.EditorItems;
import com.clearlyspam23.GLE.edit.LayerEditManager;
import com.clearlyspam23.GLE.edit.LayerMenuItem;
import com.clearlyspam23.GLE.level.Layer;
import com.clearlyspam23.GLE.level.Level;
import com.clearlyspam23.GLE.level.LevelChangeListener;
import com.clearlyspam23.GLE.util.TwoWayMap;

public class MainWindow extends JFrame implements LayerChangeListener, LevelChangeListener, EditActionListener{

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
			
			manager.addTemplatePanel(new GeneralPanel(data.getPlugins()));
			manager.addTemplatePanel(new LayerPanel(data.getPlugins()));
			manager.addAdvancedTemplatePanel(new PLangPanel(data.getPlugins()));
			
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
	
	private JMenuBar menuBar;
	
	private JMenu mnFile;
	private JMenu mnEdit;
	private JMenu mnLevel;
	private JMenu mnLayer;
	
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
	private JPanel buttonsPanel;
	private JMenuItem mntmProperties;
	
	private LevelPropertyDialog propertyDialog;
	
	private List<JMenu> layerMenus = new ArrayList<JMenu>();
	
	private Map<LayerEditManager<?>, JDialog> editDialogs = new HashMap<LayerEditManager<?>, JDialog>();
	@SuppressWarnings("rawtypes")
	private List<LayerMenuItem> activeItems;
	private JSeparator separator_1;
	private JMenuItem mntmCut;
	private JMenuItem mntmCopy;
	private JMenuItem mntmPaste;
	
	private class LayerButtonAction implements ActionListener{
		
		@SuppressWarnings("rawtypes")
		final LayerMenuItem item;
		
		@SuppressWarnings("rawtypes")
		LayerButtonAction(LayerMenuItem item){
			this.item = item;
		}

		@SuppressWarnings("unchecked")
		@Override
		public void actionPerformed(ActionEvent e) {
			item.performAction(levelPanelMap.getNormal(data.getCurrentLevel()).getCurrentLayer());
		}
		
	}
	
//	private List<LayerMenuItem> currentLayerItems;

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
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		mntmNewTemplate = new JMenuItem("New Template");
		mntmNewTemplate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dialog.showDialog();
				if(dialog.isAccepted()){
					Template t = dialog.getTemplate();
					openTemplate(t);
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
				closeTemplate();
				checkMenu();
			}
		});
		mnFile.add(mntmCloseTemplate);
		
		mnEdit = new JMenu("Edit");
		menuBar.add(mnEdit);
		
		mntmUndo = new JMenuItem("Undo");
		mntmUndo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(data.getCurrentLevel()!=null){
					data.getCurrentLevel().undoAction();
				}
			}
		});
		mntmUndo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_MASK));
		mnEdit.add(mntmUndo);
		
		mntmRedo = new JMenuItem("Redo");
		mntmRedo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(data.getCurrentLevel()!=null){
					data.getCurrentLevel().redoAction();
				}
			}
		});
		mntmRedo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y, InputEvent.CTRL_MASK));
		mnEdit.add(mntmRedo);
		
		separator_1 = new JSeparator();
		mnEdit.add(separator_1);
		
		mntmCut = new JMenuItem("Cut");
		mntmCut.setEnabled(false);
		mntmCut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_MASK));
		mnEdit.add(mntmCut);
		
		mntmCopy = new JMenuItem("Copy");
		mntmCopy.setEnabled(false);
		mntmCopy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK));
		mnEdit.add(mntmCopy);
		
		mntmPaste = new JMenuItem("Paste");
		mntmPaste.setEnabled(false);
		mntmPaste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_MASK));
		mnEdit.add(mntmPaste);
		
		mnLevel = new JMenu("Level");
		menuBar.add(mnLevel);
		
		mntmProperties = new JMenuItem("Properties");
		mntmProperties.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(propertyDialog!=null){
					propertyDialog.showDialog(data.getCurrentLevel());
				}
			}
		});
		mnLevel.add(mntmProperties);
		
		mnLayer = new JMenu("Layer");
		menuBar.add(mnLayer);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.addChangeListener(new ChangeListener() {
			@SuppressWarnings("rawtypes")
			public void stateChanged(ChangeEvent arg0) {
				if(tabbedPane.getSelectedIndex()>=0){
					Component c = tabbedPane.getComponent(tabbedPane.getSelectedIndex());
					if(c instanceof LevelPanel){
						Layer old = null;
						if(data.getCurrentLevel()!=null){
								data.getCurrentLevel().removeChangeListener(MainWindow.this);
								data.getCurrentLevel().removeEditActionListener(MainWindow.this);
							}
						LevelPanel current = levelPanelMap.getNormal(data.getCurrentLevel());
						if(current!=null){
							old = current.getCurrentLayer();
						}
						LevelPanel panel = (LevelPanel)c;
						data.setCurrentLevel(levelPanelMap.getReverse(panel));
						if(data.getCurrentLevel()!=null){
							data.getCurrentLevel().addChangeListener(MainWindow.this);
							data.getCurrentLevel().addEditActionListener(MainWindow.this);
							checkUndoRedo(data.getCurrentLevel());
							checkCutCopyPaste(data.getCurrentLevel());
						}
						if(panel!=null)
							onLayerChange(old, panel.getCurrentLayer(), panel);
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
		
		buttonsPanel = new JPanel();
		contentPane.add(buttonsPanel, BorderLayout.NORTH);
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
	
	private void closeTemplate(){
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
		if(levelPanelMap.getNormal(data.getCurrentLevel())!=null)
			onLayerChange(levelPanelMap.getNormal(data.getCurrentLevel()).getCurrentLayer(), null, levelPanelMap.getNormal(data.getCurrentLevel()));
		data.closeAllLevels();
		tabbedPane.removeAll();
		for(LevelPanel p : levelPanelMap.getValues()){
			p.dispose();
		}
		for(JMenu m : layerMenus){
			menuBar.remove(m);
		}
		levelPanelMap.clear();
		layerMenus.clear();
		data.setOpenTemplate(null);
	}
	
	@SuppressWarnings("rawtypes")
	private void openTemplate(Template t){
		t.setData(data);
		List<EditorItems> items = data.setOpenTemplate(t);
		for(EditorItems i : items){
			for(Object o : i.getMenuItems(t)){
				JMenu m = (JMenu) o;
				layerMenus.add(m);
				menuBar.add(m);
			}
		}
		propertyDialog = new LevelPropertyDialog(data.getOpenTemplate());
		menuBar.revalidate();
	}
	
	private boolean loadTemplate(File f){
		try {
			String contents = new String(Files.readAllBytes(Paths.get(f.toURI())));
			openTemplate(data.getSerializer().deserialize(contents, f));
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
		}
	}
	
	private void openLevel(Level l){
		data.addOpenLevel(l);
		LevelPanel pan = new LevelPanel(l, this, editDialogs);
		pan.addChangeLayerListener(this);
		levelPanelMap.put(l, pan);
		tabbedPane.addTab(l.getName(), pan);
		if(data.getCurrentLevel()==null){
			data.setCurrentLevel(l);
		}
	}
	
	private void checkNames(){
		for(int i = 0; i < tabbedPane.getTabCount(); i++){
			Component comp = tabbedPane.getComponentAt(i);
			if(comp instanceof LevelPanel){
				tabbedPane.setTitleAt(i, ((LevelPanel)comp).getLevelName());
			}
		}
	}
	
	private void saveLevelAs(Level level){
		if(showSaveLevelDialog(level.getName())){
			File f = fc.getSelectedFile();
			String extension = data.getOpenTemplate().getExtension();
			File ans = new File(FilenameUtils.removeExtension(f.getPath())+extension);
			level.setSaveFile(ans);
			saveLevel(level);
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
		checkNames();
	}
	
	private boolean showOpenTemplateDialog(){
		fc.setDialogType(JFileChooser.OPEN_DIALOG);
		fc.setCurrentDirectory(new File(Template.defaultLocation));
		FileNameExtensionFilter filter = new FileNameExtensionFilter("JAnGLE Templates (* " + JAnGLEData.TEMPLATE_EXTENSION + ")", JAnGLEData.TEMPLATE_EXTENSION);
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
		mntmProperties.setEnabled(hasData&&data.getCurrentLevel()!=null);
	}
	
	private void checkUndoRedo(Level l){
		mntmUndo.setEnabled(l.canUndo());
		mntmRedo.setEnabled(l.canRedo());
	}
	
	private void checkCutCopyPaste(Level l){
		LayerEditManager<?> e = this.levelPanelMap.getNormal(l).getCurrentEditManager();
		mntmCut.setEnabled(e.canCut());
		mntmCopy.setEnabled(e.canCopy());
		mntmPaste.setEnabled(e.canPaste());
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void onLayerChange(Layer oldLayer, Layer newLayer, LevelPanel source) {
		if(oldLayer!=null&&activeItems!=null){
			for(LayerMenuItem i : activeItems){
				mnLayer.remove(i.getMenuItem());
				for(ActionListener l : i.getMenuItem().getActionListeners()){
					if(l instanceof LayerButtonAction)
						i.getMenuItem().removeActionListener(l);
				}
				i.onHide(oldLayer);
			}
		}
		if(newLayer!=null&&source.getCurrentEditManager()!=null){
			LayerEditManager manager = source.getCurrentEditManager();
			activeItems = manager.getLayerItems(newLayer);
			for(LayerMenuItem i : activeItems){
				mnLayer.add(i.getMenuItem());
				i.getMenuItem().addActionListener(new LayerButtonAction(i));
				i.onShow(newLayer);
			}
			checkCutCopyPaste(data.getCurrentLevel());
		}
	}

	@Override
	public void onResize(Level level, double width, double height) {
		// TODO Auto-generated method stub
		
	}

//	@Override
//	public void actionApplied(Level level, EditAction e) {
//		checkUndoRedo(level);
//		checkNames();
//	}

	@Override
	public void actionMade(EditAction action) {
		checkUndoRedo(data.getCurrentLevel());
		checkNames();
	}

	@Override
	public void cutAvailabilityChange(boolean isAvailable) {
		checkCutCopyPaste(data.getCurrentLevel());
	}

	@Override
	public void copyAvailabilityChange(boolean isAvailable) {
		checkCutCopyPaste(data.getCurrentLevel());
	}

	@Override
	public void pasteAvailabilityChange(boolean isAvailable) {
		checkCutCopyPaste(data.getCurrentLevel());
	}
}
