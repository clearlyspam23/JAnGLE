package com.clearlyspam23.GLE.basic.parameters;

import com.clearlyspam23.GLE.JAnGLEData;
import com.clearlyspam23.GLE.ParameterMacro;

public class WorkingDirectoryMacro extends ParameterMacro {

	@Override
	protected String getMacroText() {
		return "WorkingDirectory";
	}

	@Override
	public String getDescription() {
		return "(The Working Directory of JAnGLE)";
	}

	@Override
	public String getRuntimeText(JAnGLEData info) {
		return null;
	}

}
