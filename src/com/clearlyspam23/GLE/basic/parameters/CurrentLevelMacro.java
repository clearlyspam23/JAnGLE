package com.clearlyspam23.GLE.basic.parameters;

import com.clearlyspam23.GLE.JAnGLEData;
import com.clearlyspam23.GLE.template.ParameterMacro;

public class CurrentLevelMacro extends ParameterMacro {

	@Override
	protected String getMacroText() {
		return "CurrentLevel";
	}

	@Override
	public String getDescription() {
		return "(The level currently being edited)";
	}

	@Override
	public String getRuntimeText(JAnGLEData info) {
		return null;
	}

}
