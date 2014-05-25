package com.clearlyspam23.GLE.basic.languages;

import com.clearlyspam23.GLE.PLanguageOptions;

public class JavaLanguageOptions extends PLanguageOptions {

	@Override
	public String getName() {
		return "Java";
	}
	
	@Override
	public String getRuntimeCall()
	{
		return System.getProperty("java.home")+System.getProperty("file.separator") + "java";
	}

}
