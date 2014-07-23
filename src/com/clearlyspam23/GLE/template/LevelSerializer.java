package com.clearlyspam23.GLE.template;

import com.clearlyspam23.GLE.ExportData;
import com.clearlyspam23.GLE.Level;
import com.clearlyspam23.GLE.Nameable;

public abstract class LevelSerializer implements Nameable{
	
	public abstract String serialize(Level data);
	public abstract Level deserialize(String data);
	
	public abstract boolean canSerialize(ExportData data);
	
	public abstract boolean registerDataType(ExportData data);
	
	public abstract String getDefaultExtension();

}
