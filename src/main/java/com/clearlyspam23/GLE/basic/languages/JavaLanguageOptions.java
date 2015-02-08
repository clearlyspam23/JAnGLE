package com.clearlyspam23.GLE.basic.languages;

import com.clearlyspam23.GLE.basic.gui.JavaLanguagePanel;
import com.clearlyspam23.GLE.template.PLanguageOptions;

public class JavaLanguageOptions extends PLanguageOptions<JavaLanguagePanel> {

	@Override
	public String getName() {
		return "Java";
	}
	
	@Override
	public String buildRuntimeCall(JavaLanguagePanel panel)
	{
		String output = panel.getJavaTextField().getText();
		if(output==null) 
			output = "";
		else if(output.length()>0)
			output = '"' + output + '"';
		for(int i = 0; i < panel.getList().getModel().getSize(); i++)
		{
			String s = panel.getList().getModel().getElementAt(i);
			if(s.indexOf(' ')>=0)
				s = '"' + s + '"';
			output = output + " " + s;
			
		}
		return output;
	}
	
	public JavaLanguagePanel getPanel()
	{
		return new JavaLanguagePanel(System.getProperty("java.home")+System.getProperty("file.separator") + "java");
	}

}
