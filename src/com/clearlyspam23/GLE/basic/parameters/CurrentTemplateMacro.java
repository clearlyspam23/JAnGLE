package com.clearlyspam23.GLE.basic.parameters;

import com.clearlyspam23.GLE.JAnGLEData;
import com.clearlyspam23.GLE.template.ParameterMacro;

public class CurrentTemplateMacro extends ParameterMacro {

	@Override
	protected String getMacroText() {
		return "TemplateDirectory";
	}

	@Override
	public String getDescription() {
		return "(The Directory of this Template)";
	}

	@Override
	public String getRuntimeText(JAnGLEData info) {
		return null;
	}

}
