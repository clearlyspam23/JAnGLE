package com.clearlyspam23.GLE;

import com.clearlyspam23.GLE.GUI.SubPanel;

public abstract class PLanguageOptions<T extends SubPanel> {
	
	public abstract String getName();
	
	public String buildRuntimeCall(T panel)
	{
		return null;
	}
	
	public T getPanel()
	{
		return null;
	}

}
