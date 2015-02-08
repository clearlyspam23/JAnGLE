package com.clearlyspam23.GLE.basic.serializers;

import java.util.HashMap;
import java.util.Map;

import com.clearlyspam23.GLE.level.Layer;
import com.clearlyspam23.GLE.level.LayerData;
import com.clearlyspam23.GLE.level.Level;
import com.clearlyspam23.GLE.level.LevelData;
import com.clearlyspam23.GLE.template.LevelSerializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class JsonSerializer extends LevelSerializer {
	
	private Gson gson;
	private JsonParser parser;
	
	private Map<String, Class<?>> registeredClassesMap = new HashMap<String, Class<?>>();
	
	public JsonSerializer(){
		gson = new GsonBuilder().setPrettyPrinting().create();
		parser = new JsonParser();
	}

	@Override
	public String serialize(LevelData data) {
		String s = gson.toJson(data);
		return s;
	}

	@Override
	public boolean canSerialize(LayerData data) {
		return true;
	}

	@Override
	public String getName() {
		return "JSON";
	}

	@Override
	public void registerDataType(String name, Object data) {
		registeredClassesMap.put(name, data.getClass());
	}

	@Override
	public LevelData deserialize(Level level, String data) {
		JsonObject o = parser.parse(data).getAsJsonObject();
		JsonArray layers = o.remove("layers").getAsJsonArray();
		LayerData[] lDataLayers = new LayerData[layers.size()];
		for(int i = 0; i < layers.size(); i++){
			Layer layer = level.getLayers().get(i);
			JsonObject ldat = layers.get(i).getAsJsonObject();
			lDataLayers[i] = new LayerData(ldat.get("name").getAsString(), gson.fromJson(ldat.get("data"), layer.getExportDataClass()));
		}
		LevelData lData = gson.fromJson(o, LevelData.class);
		lData.layers = lDataLayers;
		return lData;
	}

	@Override
	public String getDefaultExtension() {
		return ".json";
	}

}
