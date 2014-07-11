package com.clearlyspam23.GLE;

public abstract class ParameterMacro {
	
	public final String getMacro()
	{
		return "$(" + getMacroText() + ")";
	}
	
	public String getRawText()
	{
		return getMacro();
	}
	
	protected abstract String getMacroText();
	
	public abstract String getDescription();
	
	public abstract String getRuntimeText(JAnGLEData info);
	
	public boolean isValidMacro(){
		String s = getMacroText();
		if(s.isEmpty())
			return false;
		for (int i = 0; i < s.length(); i++)
			if (Character.isWhitespace(s.charAt(i)))
				return false;
		return true;
	}

}
