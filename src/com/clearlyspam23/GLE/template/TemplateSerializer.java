package com.clearlyspam23.GLE.template;

import java.io.File;
import java.util.HashMap;
import java.util.Map.Entry;

import com.clearlyspam23.GLE.PluginManager;
import com.clearlyspam23.GLE.PropertyDefinition;
import com.clearlyspam23.GLE.PropertyTemplate;
import com.clearlyspam23.GLE.Template;
import com.clearlyspam23.GLE.util.TwoWayMap;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class TemplateSerializer {
	
	private Gson gson;
	private JsonParser parser;
	private TwoWayMap<String, Class> propsMap = new TwoWayMap<String, Class>();
	private TwoWayMap<String, LevelSerializer> serializerMap = new TwoWayMap<String, LevelSerializer>();
	private TwoWayMap<String, CompressionFormat> compressionMap = new TwoWayMap<String, CompressionFormat>();
	private TwoWayMap<String, CoordinateSystem> coordinateMap = new TwoWayMap<String, CoordinateSystem>();
	public TemplateSerializer(PluginManager manager){
		gson = new GsonBuilder().setPrettyPrinting().create();
		parser = new JsonParser();
		for(PropertyDefinition def : manager.getRecognizedProperties())
		{
			propsMap.put(def.getName(), def.getLayerClass());
		}
		for(LevelSerializer ser : manager.getRecognizedSerializers())
		{
			serializerMap.put(ser.getName(), ser);
		}
		for(CompressionFormat com : manager.getRecognizedCompressions())
		{
			compressionMap.put(com.getName(), com);
		}
		for(CoordinateSystem coor : manager.getRecognizedCoordinateSystems())
		{
			coordinateMap.put(coor.getName(), coor);
		}
	}
	
	public String serialize(Template t){
		JsonObject json = gson.toJsonTree(t).getAsJsonObject();
		json.remove("templateFile");
		//place serializer
		json.remove("serializer");
		json.addProperty("serializer", serializerMap.getReverse(t.getSerializer()));
		//place compression
		json.remove("compression");
		json.addProperty("compression", compressionMap.getReverse(t.getCompression()));
		//place coordinate
		json.remove("coordinateSystem");
		json.addProperty("coordinateSystem", coordinateMap.getReverse(t.getCoordinateSystem()));
		//serialize properties
		json.remove("activeProperties");
		JsonObject properties = new JsonObject();
		json.add("activeProperties", properties);
		for(Entry<String, PropertyTemplate> p : t.getProperties()){
			JsonObject prop = gson.toJsonTree(p.getValue()).getAsJsonObject();
			String name = propsMap.getReverse(p.getValue().getClass());
			prop.remove("definition");
			prop.addProperty("definition", name);
			properties.add(p.getKey(), prop);
		}
		return gson.toJson(json);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Template deserialize(String json, File path){
		JsonObject o = parser.parse(json).getAsJsonObject();
		String serializer = o.remove("serializer").getAsString();
		String compression = o.remove("compression").getAsString();
		String coordinateSystem = o.remove("coordinateSystem").getAsString();
		HashMap<String, PropertyTemplate> finalProps = new HashMap<String, PropertyTemplate>();
		JsonObject properties = o.remove("activeProperties").getAsJsonObject();
		for(Entry<String, JsonElement> e : properties.entrySet()){
			JsonObject prop = e.getValue().getAsJsonObject();
			String type = prop.remove("definition").getAsString();
			finalProps.put(e.getKey(), (PropertyTemplate) gson.fromJson(prop, propsMap.getNormal(type)));
		}
		Template ans = gson.fromJson(o, Template.class);
		ans.setSerializer(serializerMap.getNormal(serializer));
		ans.setCompression(compressionMap.getNormal(compression));
		ans.setCoordinateSystem(coordinateMap.getNormal(coordinateSystem));
		ans.setPropertyMap(finalProps);
		ans.setTemplateFile(path);
		return ans;
	}

}
