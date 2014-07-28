package com.clearlyspam23.GLE;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map.Entry;

import com.clearlyspam23.GLE.exception.TemplateMismatchException;

public class Level implements Nameable{
	
	private Template template;
	private List<Layer> layers = new ArrayList<Layer>();
	private double width;
	private double height;
	private List<Property> properties = new ArrayList<Property>();
	private String name;
	
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
				throw new TemplateMismatchException(template, this);
			try{
				Layer l = t.createLayer();
				l.buildFromData(d);
				layers.add(l);
			}
			catch(Exception e){
				throw new TemplateMismatchException(template, this);
			}
		}
		for(Entry<String, Object> e : data.properties.entrySet()){
			
		}
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
	
	public LevelData generateLevelData(){
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

}
