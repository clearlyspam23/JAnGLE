package com.clearlyspam23.GLE.template;

import com.clearlyspam23.GLE.LayerData;
import com.clearlyspam23.GLE.Level;
import com.clearlyspam23.GLE.Nameable;

public abstract class LevelSerializer implements Nameable{
	
	public abstract String serialize(Level data);
	public abstract Level deserialize(String data);
	
	public abstract boolean canSerialize(LayerData data);
	
	public abstract boolean registerDataType(LayerData data);
	
	public abstract String getDefaultExtension();

}
