package com.clearlyspam23.GLE.template.serializer;

import java.util.HashMap;
import java.util.Map;

import org.yaml.snakeyaml.constructor.AbstractConstruct;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.nodes.Node;
import org.yaml.snakeyaml.nodes.ScalarNode;
import org.yaml.snakeyaml.nodes.Tag;

import com.clearlyspam23.GLE.PropertyDefinition;
import com.clearlyspam23.GLE.level.LayerDefinition;
import com.clearlyspam23.GLE.template.CompressionFormat;
import com.clearlyspam23.GLE.template.CoordinateSystem;
import com.clearlyspam23.GLE.template.LevelSerializer;

public class CustomConstructor extends Constructor {
	
	private HashMap<String, CoordinateSystem> coordMap = new HashMap<String, CoordinateSystem>();
	private HashMap<String, LevelSerializer> serializerMap = new HashMap<String, LevelSerializer>();
	private HashMap<String, CompressionFormat> compressionMap = new HashMap<String, CompressionFormat>();
	@SuppressWarnings("rawtypes")
	private HashMap<String, LayerDefinition> definitionMap = new HashMap<String, LayerDefinition>();
	@SuppressWarnings("rawtypes")
	private HashMap<String, PropertyDefinition> propsMap = new HashMap<String, PropertyDefinition>();
	
	private class ConstructNameable extends AbstractConstruct {
		
		public Map<String, ? extends Object> map;
		
		public ConstructNameable(Map<String, ? extends Object> map){
			this.map = map;
		}
		
		@Override
		public Object construct(Node arg0) {
			String val = (String) constructScalar((ScalarNode) arg0);
			return map.get(val);
		}
		
	}
	
	public CustomConstructor(){
		this.yamlConstructors.put(new Tag(TemplateSerializer.COORD_TAG), new ConstructNameable(coordMap));
		this.yamlConstructors.put(new Tag(TemplateSerializer.SERIALIZER_TAG), new ConstructNameable(serializerMap));
		this.yamlConstructors.put(new Tag(TemplateSerializer.COMPRESSION_TAG), new ConstructNameable(compressionMap));
		this.yamlConstructors.put(new Tag(TemplateSerializer.DEF_TAG), new ConstructNameable(definitionMap));
		this.yamlConstructors.put(new Tag(TemplateSerializer.PROP_TAG), new ConstructNameable(propsMap));
	}
	
    public void registerCoord(CoordinateSystem system){
    	coordMap.put(system.getName(), system);
    }
    
    public void registerSerializer(LevelSerializer serializer){
    	serializerMap.put(serializer.getName(), serializer);
    }
    
    public void registerCompression(CompressionFormat format){
    	compressionMap.put(format.getName(), format);
    }
    
    public void registerLayerDef(LayerDefinition definition){
    	definitionMap.put(definition.getName(), definition);
    }
    
    public void registerPropDef(PropertyDefinition definition){
    	propsMap.put(definition.getName(), definition);
    }

}
