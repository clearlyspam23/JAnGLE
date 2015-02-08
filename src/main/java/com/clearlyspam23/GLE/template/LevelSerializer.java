package com.clearlyspam23.GLE.template;

import com.clearlyspam23.GLE.Nameable;
import com.clearlyspam23.GLE.level.LayerData;
import com.clearlyspam23.GLE.level.Level;
import com.clearlyspam23.GLE.level.LevelData;

public abstract class LevelSerializer implements Nameable{
	
	public abstract String serialize(LevelData data);
	public abstract LevelData deserialize(Level level, String data);
	
	public abstract boolean canSerialize(LayerData data);
	
	public abstract void registerDataType(String name, Object data);
	
	public abstract String getDefaultExtension();

}
