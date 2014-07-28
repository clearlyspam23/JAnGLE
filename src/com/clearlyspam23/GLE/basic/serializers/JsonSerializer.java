package com.clearlyspam23.GLE.basic.serializers;

import java.util.HashMap;
import java.util.Map;

import com.clearlyspam23.GLE.LayerData;
import com.clearlyspam23.GLE.Level;
import com.clearlyspam23.GLE.template.LevelSerializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonSerializer extends LevelSerializer {
	
	private Gson gson;
	
	private Map<String, Class<?>> registeredClassesMap = new HashMap<String, Class<?>>();
	
	public JsonSerializer(){
		gson = new GsonBuilder().create();
	}

	@Override
	public String serialize(Level data) {
		return null;
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
	public boolean registerDataType(LayerData data) {
		registeredClassesMap.put(data.getClassAlias(), data.getClass());
		return canSerialize(data);
	}

	@Override
	public Level deserialize(String data) {
		return null;
	}

	@Override
	public String getDefaultExtension() {
		return ".json";
	}

}
