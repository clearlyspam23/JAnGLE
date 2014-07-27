package com.clearlyspam23.GLE;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import com.clearlyspam23.GLE.template.serializer.TemplateSerializer;

public class JAnGLEData {
	
	private static class EditActionData{
		public final EditAction data;
		public final Layer<?> layer;
		
		public EditActionData(EditAction data, Layer<?> layer){
			this.data = data;
			this.layer = layer;
		}
	}
	
	private class LevelData{
		private final Level level;
		private final List<EditActionData> undoStack = new ArrayList<EditActionData>();
		private int lastAction;
		private int lastSave;
		private File saveFile;
		
		public LevelData(Level level){
			this.level = level;
		}
		
		public void pushAction(EditAction data, Layer<?> layer){
			undoStack.add(new EditActionData(data, layer));
			lastAction++;
		}
		
		public EditActionData popAction(){
			lastAction--;
			return undoStack.remove(undoStack.size()-1);
		}

		public Level getLevel() {
			return level;
		}
		
		public boolean save(){
			String output = openTemplate.getSerializer().serialize(level);
			try {
				openTemplate.getCompression().compress(output, saveFile);
				return true;
			} catch (IOException e) {
				e.printStackTrace();
			}
			return false;
		}
		
		public boolean saveAs(File file){
			saveFile = file;
			return save();
		}
		
		public boolean hasChanged(){
			return lastAction!=lastSave;
		}
	}
	
	private final PluginManager plugins;
	private LevelData currentLevel;
	private List<LevelData> openLevels;
	private Template openTemplate;
	
	private final TemplateSerializer serializer;
	
	public JAnGLEData(PluginManager manager){
		this.plugins = manager;
		serializer = new TemplateSerializer(manager);
	}
	
	public PluginManager getPlugins() {
		return plugins;
	}
	
	public LevelData getCurrentLevel() {
		return currentLevel;
	}
	
	public void setCurrentLevel(LevelData currentLevel) {
		this.currentLevel = currentLevel;
	}
	
	public List<LevelData> getOpenLevels() {
		return openLevels;
	}
	
	public void setOpenLevels(List<LevelData> openLevels) {
		this.openLevels = openLevels;
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

}
