package com.clearlyspam23.GLE.defaultparameters;

import com.clearlyspam23.GLE.ParameterMacro;

public class CurrentLevelMacro extends ParameterMacro {

	@Override
	protected String getMacroText() {
		return "CurrentLevel";
	}

	@Override
	public String getDescription() {
		return "(The level currently being edited)";
	}

}
