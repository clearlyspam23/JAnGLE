package com.clearlyspam23.GLE.basic.parameters;

import com.clearlyspam23.GLE.JAnGLEData;
import com.clearlyspam23.GLE.template.ParameterMacro;

public class ExecutableLocationMacro extends ParameterMacro {

	@Override
	protected String getMacroText() {
		return "ExecutableLocation";
	}

	@Override
	public String getDescription() {
		return "The Path to the Executable File";
	}

	@Override
	public String getRuntimeText(JAnGLEData info) {
		return null;
	}

}
