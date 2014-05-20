package com.clearlyspam23.GLE;

public abstract class ParameterMacro {
	
	public final String getMacro()
	{
		return "$(" + getMacroText() + ")";
	}
	
	protected abstract String getMacroText();
	
	public abstract String getDescription();

}
