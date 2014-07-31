package com.clearlyspam23.GLE;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map.Entry;

import com.clearlyspam23.GLE.exception.TemplateMismatchException;

public class Level implements Nameable{
	
	private Template template;
	@SuppressWarnings("rawtypes")
	private List<Layer> layers = new ArrayList<Layer>();
	private List<ActionData> undoStack = new ArrayList<ActionData>();
	private List<ActionData> redoStack = new ArrayList<ActionData>();
	private int lastAction;
	private int currentAction;
	private double width;
	private double height;
	private List<Property> properties = new ArrayList<Property>();
	private String name;
	private File saveFile;
	
	@SuppressWarnings("rawtypes")
	public Level(Template template)
	{
		this.template = template;
		for(LayerTemplate t : template.getLayers()){
			layers.add(t.createLayer());
		}
		for(PropertyTemplate t : template.getActiveProperties()){
			properties.add(t.getDefaultValue());
		}
	}
	
	/**
	 * constructs a Level from the given Template and LevelData. 
	 * Will also attempt to validate the LevelData with this template, and may throw an exception if the two are not compatible
	 * @param template
	 * @param data
	 * @throws TemplateMismatchException 
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Level(Template template, LevelData data) throws TemplateMismatchException{
		this.template = template;
		for(LayerData d : data.layers){
			LayerTemplate t = template.getLayerTemplate(d.getName());
			if(t==null)
				throw new TemplateMismatchException(template, this, false, d.getName());
			try{
				Layer l = t.createLayer();
				l.buildFromData(d);
				layers.add(l);
			}
			catch(Exception e){
				throw new TemplateMismatchException(template, this, false, d.getName());
			}
		}
		for(Entry<String, Object> e : data.properties.entrySet()){
			PropertyTemplate t = template.getPropertyTemplate(e.getKey());
			if(t==null)
				throw new TemplateMismatchException(template, this, true, e.getKey());
			try{
//				Layer l = t.createLayer();
//				l.buildFromData(d);
//				layers.add(l);
			}
			catch(Exception exc){
				throw new TemplateMismatchException(template, this, false, e.getKey());
			}
		}
	}
	
	public boolean needsSave(){
		return currentAction!=lastAction;
	}
	
	public void addAction(ActionData data){
		undoStack.add(data);
		redoStack.clear();
	}
	
	public boolean canUndo(){
		return !undoStack.isEmpty();
	}
	
	public ActionData undoAction(){
		return undoStack.remove(undoStack.size()-1);
	}
	
	public boolean canRedo(){
		return !redoStack.isEmpty();
	}
	
	public ActionData redoAction(){
		return redoStack.remove(redoStack.size()-1);
	}
	
	public void setDimensions(double width, double height){
		this.width = width;
		this.height = height;
	}
	
	public double getWidth(){
		return width;
	}
	
	public double getHeight(){
		return height;
	}

	public Template getTemplate() {
		return template;
	}

	public void setTemplate(Template template) {
		this.template = template;
	}
	
	public void addLayer(Layer l)
	{
		layers.add(l);
	}
	
	public List<Layer> getLayers()
	{
		return Collections.unmodifiableList(layers);
	}
	
	public LayerData generateLevelData(){
		return null;
	}
	
	public void setProperty(String name, Object property){
		
	}

	@Override
	public String getName() {
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}

	public File getSaveFile() {
		return saveFile;
	}

	public void setSaveFile(File saveFile) {
		this.saveFile = saveFile;
	}

}
