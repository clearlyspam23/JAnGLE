package com.clearlyspam23.GLE.level;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.io.FilenameUtils;

import com.clearlyspam23.GLE.ActionData;
import com.clearlyspam23.GLE.Nameable;
import com.clearlyspam23.GLE.PropertyTemplate;
import com.clearlyspam23.GLE.Template;
import com.clearlyspam23.GLE.exception.TemplateMismatchException;

public class Level implements Nameable{
	
	private Template template;
	@SuppressWarnings("rawtypes")
	private List<Layer> layers = new ArrayList<Layer>();
	private List<LevelChangeListener> listeners = new ArrayList<LevelChangeListener>();
	private List<ActionData> undoStack = new ArrayList<ActionData>();
	private List<ActionData> redoStack = new ArrayList<ActionData>();
	private int lastAction;
	private int currentAction;
	private double width;
	private double height;
	private LinkedHashMap<String, Object> properties = new LinkedHashMap<String, Object>();
	private File saveFile;
	
	@SuppressWarnings("rawtypes")
	public Level(Template template)
	{
		this.template = template;
		for(LayerTemplate t : template.getLayers()){
			addLayer(t.createLayer());
		}
		for(PropertyTemplate t : template.getActiveProperties()){
			Object o = t.getDefaultValue();
			properties.put(t.getName(), o);
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
				l.buildFromData(d.data);
				addLayer(l);
			}
			catch(Exception e){
				throw new TemplateMismatchException(template, this, false, d.getName(), e);
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
				throw new TemplateMismatchException(template, this, false, e.getKey(), exc);
			}
		}
	}
	
	public void setToData(LevelData data) throws TemplateMismatchException{
		setDimensions(data.width, data.height);
		for(int i = 0; i < layers.size(); i++){
			try{
				Layer l = layers.get(i);
				l.onResize(width, height);
				l.buildFromData(data.layers[i].data);
			}
			catch(Exception e){
				throw new TemplateMismatchException(template, this, false, data.layers[i].getName(), e);
			}
		}
		for(Entry<String, Object> e : data.properties.entrySet()){
			PropertyTemplate t = template.getPropertyTemplate(e.getKey());
			if(t==null)
				throw new TemplateMismatchException(template, this, true, e.getKey());
			try{
				properties.put(e.getKey(), e.getValue());
			}
			catch(Exception exc){
				throw new TemplateMismatchException(template, this, false, e.getKey(), exc);
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
		for(LevelChangeListener l : listeners){
			l.onResize(width, height);
		}
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
		listeners.add(l);
	}
	
	public List<Layer> getLayers()
	{
		return Collections.unmodifiableList(layers);
	}
	
	public LevelData generateLevelData(){
		LevelData data = new LevelData();
		data.width = width;
		data.height = height;
		data.layers = new LayerData[layers.size()];
		for(int i = 0; i < layers.size(); i++){
			data.layers[i] = new LayerData(layers.get(i).getName(), layers.get(i).getExportData());
		}
		for(Entry<String, Object> p : properties.entrySet()){
			data.properties.put(p.getKey(), p.getValue());
		}
		return data;
	}
	
	public Map<String, Object> getProperties(){
		return properties;
	}
	
	public void setProperty(String name, Object property){
		properties.put(name, property);
	}

	@Override
	public String getName() {
		return saveFile!=null ? FilenameUtils.removeExtension(saveFile.getName()) : "New Level";
	}

	public File getSaveFile() {
		return saveFile;
	}

	public void setSaveFile(File saveFile) {
		this.saveFile = saveFile;
	}
	
	public void addChangeListener(LevelChangeListener listener){
		listeners.add(listener);
	}

}
