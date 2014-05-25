package com.clearlyspam23.GLE;

import java.awt.Component;

public abstract class PLanguageOptions<T extends Component> {
	
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
