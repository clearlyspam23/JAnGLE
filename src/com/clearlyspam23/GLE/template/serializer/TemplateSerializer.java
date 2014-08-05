package com.clearlyspam23.GLE.template.serializer;

import java.io.File;

import org.yaml.snakeyaml.Yaml;

import com.clearlyspam23.GLE.LayerDefinition;
import com.clearlyspam23.GLE.LayerTemplate;
import com.clearlyspam23.GLE.PluginManager;
import com.clearlyspam23.GLE.Template;
import com.clearlyspam23.GLE.template.CompressionFormat;
import com.clearlyspam23.GLE.template.CoordinateSystem;
import com.clearlyspam23.GLE.template.LevelSerializer;

public class TemplateSerializer {
	
	static final String COORD_TAG = "!coord";
	static final String SERIALIZER_TAG = "!serialize";
	static final String COMPRESSION_TAG = "!compress";
	static final String DEF_TAG = "!definition";
	
	private Yaml yaml;
	private CustomConstructor construct;
	private CustomRepresenter represent;
	
	public TemplateSerializer(PluginManager manager){
		construct = new CustomConstructor();
		represent = new CustomRepresenter();
		for(CoordinateSystem s : manager.getRecognizedCoordinateSystems()){
			construct.registerCoord(s);
			represent.registerCoord(s);
		}
		for(LevelSerializer s : manager.getRecognizedSerializers()){
			construct.registerSerializer(s);
			represent.registerSerializer(s);
		}
		for(CompressionFormat s : manager.getRecognizedCompressions()){
			construct.registerCompression(s);
			represent.registerCompression(s);
		}
		for(LayerDefinition d : manager.getRecognizedLayerDefs()){
			construct.registerLayerDef(d);
			represent.registerLayerDef(d);
		}
		yaml = new Yaml(construct, represent);
	}
	
	public String serialize(Template t){
		return yaml.dump(t);
	}
	
	public Template deserialize(String data, File path){
		Template template = (Template) yaml.load(data);
		template.setTemplateFile(path);
		return template;
	}

}
