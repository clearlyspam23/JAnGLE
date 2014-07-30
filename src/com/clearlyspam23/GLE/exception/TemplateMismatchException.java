package com.clearlyspam23.GLE.exception;

import com.clearlyspam23.GLE.Level;
import com.clearlyspam23.GLE.Template;

public class TemplateMismatchException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Template template;
	private final Level level;
	
	public TemplateMismatchException(Template t, Level l, boolean property, String name)
	{
		super("Unable to construct level (" + l.getName() + ")  from Template (" + t.getTemplateName() + ") : " + (property ? "Property " : "Layer ") + name + " isn't a part of the template");
		this.template = t;
		this.level = l;
	}

	public Template getTemplate() {
		return template;
	}

	public Level getLevel() {
		return level;
	}
}
