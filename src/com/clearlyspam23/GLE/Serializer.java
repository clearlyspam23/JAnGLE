package com.clearlyspam23.GLE;

public abstract class Serializer {
	
	public abstract String serialize(Level data);
	public abstract Level deserialize();
	
	public abstract boolean canSerialize(ExportData data);
	
	public abstract boolean registerDataType(ExportData data);
	
	public abstract String getName();

}
