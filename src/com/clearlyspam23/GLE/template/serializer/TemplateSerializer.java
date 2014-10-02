package com.clearlyspam23.GLE.template.serializer;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import com.clearlyspam23.GLE.Nameable;
import com.clearlyspam23.GLE.PluginManager;
import com.clearlyspam23.GLE.Template;
import com.clearlyspam23.GLE.level.LayerDefinition;
import com.clearlyspam23.GLE.template.CompressionFormat;
import com.clearlyspam23.GLE.template.CoordinateSystem;
import com.clearlyspam23.GLE.template.LevelSerializer;
import com.thoughtworks.xstream.XStream;

public class TemplateSerializer {
	
	private XStream xstream;
	
	@SuppressWarnings("rawtypes")
	public TemplateSerializer(PluginManager manager){
		xstream = new XStream();
		xstream.alias("Template", Template.class);
		xstream.registerConverter(new NameableConverter<CoordinateSystem>(CoordinateSystem.class, generateMap(manager.getRecognizedCoordinateSystems())));
		xstream.registerConverter(new NameableConverter<LevelSerializer>(LevelSerializer.class, generateMap(manager.getRecognizedSerializers())));
		xstream.registerConverter(new NameableConverter<CompressionFormat>(CompressionFormat.class, generateMap(manager.getRecognizedCompressions())));
		xstream.registerConverter(new NameableConverter<LayerDefinition>(LayerDefinition.class, generateMap(manager.getRecognizedLayerDefs())));
	}
	
	private <T extends Nameable> Map<String, T> generateMap(Iterable<T> iterable){
		HashMap<String, T> ansMap = new HashMap<String, T>();
		for(T t : iterable){
			ansMap.put(t.getName(), t);
		}
		return ansMap;
	}
	
	public String serialize(Template t){
		return xstream.toXML(t);
	}
	
	public Template deserialize(String data, File path){
		Template output = (Template) xstream.fromXML(data);
		output.setTemplateFile(path);
		return output;
	}
	
	static final String COORD_TAG = "!coord";
	static final String SERIALIZER_TAG = "!serialize";
	static final String COMPRESSION_TAG = "!compress";
	static final String DEF_TAG = "!definition";
	static final String PROP_TAG = "!prop";
//	
//	private Yaml yaml;
//	private CustomConstructor construct;
//	private CustomRepresenter represent;
//	
//	public TemplateSerializer(PluginManager manager){
//		construct = new CustomConstructor();
//		represent = new CustomRepresenter();
//		for(CoordinateSystem s : manager.getRecognizedCoordinateSystems()){
//			construct.registerCoord(s);
//			represent.registerCoord(s);
//		}
//		for(LevelSerializer s : manager.getRecognizedSerializers()){
//			construct.registerSerializer(s);
//			represent.registerSerializer(s);
//		}
//		for(CompressionFormat s : manager.getRecognizedCompressions()){
//			construct.registerCompression(s);
//			represent.registerCompression(s);
//		}
//		for(LayerDefinition d : manager.getRecognizedLayerDefs()){
//			construct.registerLayerDef(d);
//			represent.registerLayerDef(d);
//		}
//		for(PropertyDefinition d : manager.getRecognizedProperties()){
//			construct.registerPropDef(d);
//			represent.registerPropDef(d);
//		}
//		yaml = new Yaml(construct, represent);
//	}
//	
//	public String serialize(Template t){
//		return yaml.dump(t);
//	}
//	
//	public Template deserialize(String data, File path){
//		Template template = (Template) yaml.load(data);
//		template.setTemplateFile(path);
//		return template;
//	}

}
