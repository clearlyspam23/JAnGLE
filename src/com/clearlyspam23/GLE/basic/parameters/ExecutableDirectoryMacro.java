package com.clearlyspam23.GLE.basic.parameters;

import com.clearlyspam23.GLE.JAnGLEData;
import com.clearlyspam23.GLE.template.ParameterMacro;

public class ExecutableDirectoryMacro extends ParameterMacro {

	@Override
	protected String getMacroText() {
		return "ExecutableDirectory";
	}

	@Override
	public String getDescription() {
		return "(The Directory of the Executable being run)";
	}

	@Override
	public String getRuntimeText(JAnGLEData info) {
		return null;
	}

}
