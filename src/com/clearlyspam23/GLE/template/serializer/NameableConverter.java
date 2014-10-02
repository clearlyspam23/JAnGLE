package com.clearlyspam23.GLE.template.serializer;

import java.util.Map;

import com.clearlyspam23.GLE.Nameable;
import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

public class NameableConverter<T extends Nameable> implements Converter {
	
	private Map<String, T> map;
	private Class<T> cls;
	
	public NameableConverter(Class<T> cls, Map<String, T> nameMap){
		this.cls = cls;
		map = nameMap;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public boolean canConvert(Class arg0) {
		return cls.isAssignableFrom(arg0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void marshal(Object arg0, HierarchicalStreamWriter arg1,
			MarshallingContext arg2) {
		arg1.setValue(((T)arg0).getName());
	}

	@Override
	public Object unmarshal(HierarchicalStreamReader arg0,
			UnmarshallingContext arg1) {
		return map.get(arg0.getValue());
	}

}
