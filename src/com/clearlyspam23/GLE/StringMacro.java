package com.clearlyspam23.GLE;

public class StringMacro extends ParameterMacro
{
	
	private String text;
	
	public StringMacro(String text)
	{
		this.text = text;
	}
	
	public String getRawText()
	{
		return text;
	}

	@Override
	protected String getMacroText() {
		return "";
	}

	@Override
	public String getDescription() {
		return "";
	}

	@Override
	public String getRuntimeText(JAnGLEData info) {
		return text;
	}
	
	public boolean isValidMacro(){
		return true;
	}
	
}
