package com.clearlyspam23.GLE.serializers;

import java.util.HashMap;
import java.util.Map;

import com.clearlyspam23.GLE.ExportData;
import com.clearlyspam23.GLE.Level;
import com.clearlyspam23.GLE.Serializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonSerializer extends Serializer {
	
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
	public boolean canSerialize(ExportData data) {
		return true;
	}

	@Override
	public String getName() {
		return "JSON";
	}

	@Override
	public boolean registerDataType(ExportData data) {
		registeredClassesMap.put(data.getClassAlias(), data.getClass());
		return canSerialize(data);
	}

	@Override
	public Level deserialize(String data) {
		return null;
	}

}
