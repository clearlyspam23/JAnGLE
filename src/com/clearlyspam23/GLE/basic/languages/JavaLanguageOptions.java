package com.clearlyspam23.GLE.basic.languages;

import com.clearlyspam23.GLE.PLanguageOptions;
import com.clearlyspam23.GLE.basic.gui.JavaLanguagePanel;

public class JavaLanguageOptions extends PLanguageOptions<JavaLanguagePanel> {

	@Override
	public String getName() {
		return "Java";
	}
	
	@Override
	public String buildRuntimeCall(JavaLanguagePanel panel)
	{
		return panel.getJavaTextField().getText();
	}
	
	public JavaLanguagePanel getPanel()
	{
		return new JavaLanguagePanel(System.getProperty("java.home")+System.getProperty("file.separator") + "java");
	}

}
