package com.clearlyspam23.GLE;

import java.util.ArrayList;
import java.util.List;

public class JAnGLEData {
	
	private static class EditActionData{
		public final EditAction data;
		public final Layer<?> layer;
		
		public EditActionData(EditAction data, Layer<?> layer){
			this.data = data;
			this.layer = layer;
		}
	}
	
	private static class LevelData{
		public final Level level;
		public final List<EditActionData> undoStack = new ArrayList<EditActionData>();
		
		public LevelData(Level level){
			this.level = level;
		}
		
		public void pushAction(EditAction data, Layer<?> layer){
			undoStack.add(new EditActionData(data, layer));
		}
		
		public EditActionData popAction(){
			return undoStack.remove(undoStack.size()-1);
		}
	}
	
	private LevelData currentLevel;
	private List<LevelData> openLevels;
	private Template openTemplate;

}
