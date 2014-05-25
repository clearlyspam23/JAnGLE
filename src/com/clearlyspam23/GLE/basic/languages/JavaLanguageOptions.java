package com.clearlyspam23.GLE.basic.languages;

import com.clearlyspam23.GLE.PLanguageOptions;
import com.clearlyspam23.GLE.basic.gui.JavaLanguageContainer;

public class JavaLanguageOptions extends PLanguageOptions<JavaLanguageContainer> {

	@Override
	public String getName() {
		return "Java";
	}
	
	@Override
	public String buildRuntimeCall(JavaLanguageContainer panel)
	{
		return System.getProperty("java.home")+System.getProperty("file.separator") + "java";
	}
	
	public JavaLanguageContainer getPanel()
	{
		return new JavaLanguageContainer();
	}

}
