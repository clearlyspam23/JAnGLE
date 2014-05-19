package com.clearlyspam23.GLE.recognizedlanguages;

import com.clearlyspam23.GLE.PLanguageOptions;

public class JavaLanguageOptions extends PLanguageOptions {

	@Override
	public String getName() {
		return "Java";
	}
	
	@Override
	public String getRuntimeCall()
	{
		return System.getProperty("java.home");
	}

}
