package com.clearlyspam23.GLE;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.clearlyspam23.GLE.template.CompressionFormat;
import com.clearlyspam23.GLE.template.LevelSerializer;
import com.clearlyspam23.GLE.template.serializer.TemplateSerializer;

public class JAnGLEData {
	
	private final PluginManager plugins;
	private Level currentLevel;
	private List<Level> openLevels;
	private Template openTemplate;
	
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
	}
	
	public Template getOpenTemplate() {
		return openTemplate;
	}
	
	public void setOpenTemplate(Template openTemplate) {
		this.openTemplate = openTemplate;
	}

	public TemplateSerializer getSerializer() {
		return serializer;
	}
	
	public Level openLevel(File file) throws IOException{
		LevelSerializer serializer = openTemplate.getSerializer();
		CompressionFormat format = openTemplate.getCompression();
		String output = format.decompress(file);
		Level l = serializer.deserialize(output);
		return l;
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
		String s = serializer.serialize(level);
		CompressionFormat format = openTemplate.getCompression();
		try {
			format.compress(s, level.getSaveFile());
		} catch (IOException e) {
			return false;
		}
		return true;
	}

}
