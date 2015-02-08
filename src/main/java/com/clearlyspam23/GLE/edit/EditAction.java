package com.clearlyspam23.GLE.edit;

public interface EditAction {
	
	public void undoAction();
	
	public void doAction();
	
	public String getDescription();

}
