package com.clearlyspam23.GLE.template.serializer;

import org.yaml.snakeyaml.introspector.Property;
import org.yaml.snakeyaml.nodes.Node;
import org.yaml.snakeyaml.nodes.NodeTuple;
import org.yaml.snakeyaml.nodes.Tag;
import org.yaml.snakeyaml.representer.Represent;
import org.yaml.snakeyaml.representer.Representer;

import com.clearlyspam23.GLE.Nameable;
import com.clearlyspam23.GLE.Template;
import com.clearlyspam23.GLE.template.CompressionFormat;
import com.clearlyspam23.GLE.template.CoordinateSystem;
import com.clearlyspam23.GLE.template.LevelSerializer;

public class CustomRepresenter extends Representer {

    private class RepresentNameable implements Represent {
    	
    	private String tag;
    	
    	public RepresentNameable(String tag){
    		this.tag = tag;
    	}
    	
        public Node representData(Object data) {
            Nameable obj = (Nameable) data;
            return representScalar(new Tag(tag), obj.getName());
        }
    }
    
    @Override
    protected NodeTuple representJavaBeanProperty(Object javaBean, Property property,
            Object propertyValue, Tag customTag) {
        if (javaBean instanceof Template&&"templateFile".equals(property.getName())) {
        	System.out.println("here");
            return null;
        } else {
            return super.representJavaBeanProperty(javaBean, property, propertyValue, customTag);
        }
    }
    
    private RepresentNameable coordNameable = new RepresentNameable(TemplateSerializer.COORD_TAG);
    public void registerCoord(CoordinateSystem system){
    	this.representers.put(system.getClass(), coordNameable);
    }
    
    private RepresentNameable serializerNameable = new RepresentNameable(TemplateSerializer.SERIALIZER_TAG);
    public void registerSerializer(LevelSerializer system){
    	this.representers.put(system.getClass(), serializerNameable);
    }
    
    private RepresentNameable compressNameable = new RepresentNameable(TemplateSerializer.COMPRESSION_TAG);
    public void registerCompression(CompressionFormat system){
    	this.representers.put(system.getClass(), compressNameable);
    }

}
