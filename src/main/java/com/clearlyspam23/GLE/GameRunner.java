package com.clearlyspam23.GLE;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.clearlyspam23.GLE.template.ParameterMacro;

public abstract class GameRunner {
	
	private File executable;
	private List<ParameterMacro> macros = new ArrayList<ParameterMacro>();
	
	public abstract void run(JAnGLEData data);

	public List<ParameterMacro> getMacros() {
		return macros;
	}

	public void addMacro(ParameterMacro macro) {
		macros.add(macro);
	}

	public File getExecutable() {
		return executable;
	}

	public void setExecutable(File executable) {
		this.executable = executable;
	}

}
