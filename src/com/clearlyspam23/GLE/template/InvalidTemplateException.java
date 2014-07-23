package com.clearlyspam23.GLE.template;

public class InvalidTemplateException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidTemplateException(String property, String name){
		super("The property '" + property + "' in template '" + name + "' is invalid. Either the file has been corrupted, or plugins are missing!");
	}

}
