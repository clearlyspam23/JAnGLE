package com.clearlyspam23.GLE;

import java.awt.Frame;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.clearlyspam23.GLE.edit.EditorItems;
import com.clearlyspam23.GLE.level.LayerDefinition;
import com.clearlyspam23.GLE.level.LayerTemplate;
import com.clearlyspam23.GLE.level.Level;
import com.clearlyspam23.GLE.level.LevelData;
import com.clearlyspam23.GLE.template.CompressionFormat;
import com.clearlyspam23.GLE.template.LevelSerializer;
import com.clearlyspam23.GLE.template.serializer.TemplateSerializer;

public class JAnGLEData {
	
	public static final String TEMPLATE_EXTENSION = ".jtmp";
	
	private final PluginManager plugins;
	private Level currentLevel;
	private List<Level> openLevels = new ArrayList<Level>();
	private Template openTemplate;
	private Frame frame;
	
	private final TemplateSerializer serializer;
	
	public JAnGLEData(PluginManager manager){
		this.plugins = manager;
		serializer = new TemplateSerializer(manager);
	}
	
	public PluginManager getPlugins() {
		return plugins;
	}
	
	public Level getCurrentLevel() {
		return currentLevel;
	}
	
	public void setCurrentLevel(Level currentLevel) {
		this.currentLevel = currentLevel;
	}
	
	public List<Level> getOpenLevels() {
		return openLevels;
	}
	
	public void addOpenLevel(Level level){
		openLevels.add(level);
	}
	
	public void addOpenLevels(List<Level> levels){
		openLevels.addAll(levels);
	}
	
	public void closeLevel(Level level){
		openLevels.remove(level);
	}
	
	public void closeAllLevels(){
		openLevels.clear();
		currentLevel = null;
	}
	
	public Template getOpenTemplate() {
		return openTemplate;
	}
	
	@SuppressWarnings("rawtypes")
	public List<EditorItems> setOpenTemplate(Template template) {
		if(openTemplate!=null){
			Set<LayerDefinition> seen = new HashSet<LayerDefinition>();
			for(LayerTemplate t : openTemplate.getLayers()){
				if(!seen.contains(t.getDefinition())){
					t.getDefinition().onTemplateClose(openTemplate);
					seen.add(t.getDefinition());
				}
			}
		}
		openTemplate = template;
		List<EditorItems> items = new ArrayList<EditorItems>();
		if(openTemplate!=null){
			Set<LayerDefinition> seen = new HashSet<LayerDefinition>();
			for(LayerTemplate t : openTemplate.getLayers()){
				if(!seen.contains(t.getDefinition())){
					EditorItems i = t.getDefinition().onTemplateOpen(openTemplate);
					if(i!=null)
						items.add(i);
					seen.add(t.getDefinition());
				}
			}
		}
		return items;
	}

	public TemplateSerializer getSerializer() {
		return serializer;
	}
	
	public Level openLevel(File file) throws IOException{
		LevelSerializer serializer = openTemplate.getSerializer();
		CompressionFormat format = openTemplate.getCompression();
		String output = format.decompress(file);
		try {
			System.out.println(output);
			Level level = new Level(openTemplate);
			LevelData l = serializer.deserialize(level, output);
			level.setToData(l);
			level.setSaveFile(file);
			return level;
		} catch (Exception e) {
			e.printStackTrace();
			//TODO something better here
			return null;
		}
	}
	
	public boolean saveTemplate(Template template){
		try {
			PrintWriter w = new PrintWriter(template.getTemplateFile());
			String s = serializer.serialize(template);
			w.print(s);
			w.close();
			return true;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean saveLevel(Level level){
		LevelSerializer serializer = openTemplate.getSerializer();
		String s = serializer.serialize(level.generateLevelData());
		CompressionFormat format = openTemplate.getCompression();
		try {
			format.compress(s, level.getSaveFile());
		} catch (IOException e) {
			return false;
		}
		level.acknowledgeSave();
		return true;
	}

	public Frame getFrame() {
		return frame;
	}

	public void setFrame(Frame frame) {
		this.frame = frame;
	}

}
