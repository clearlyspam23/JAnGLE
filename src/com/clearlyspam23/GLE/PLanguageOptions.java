package com.clearlyspam23.GLE;

import com.clearlyspam23.GLE.GUI.PLangSubPanel;

public abstract class PLanguageOptions<T extends PLangSubPanel> {
	
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
