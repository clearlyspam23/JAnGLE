package com.clearlyspam23.GLE.GUI;

import com.clearlyspam23.GLE.level.EditAction;

public interface EditActionListener {
	
	public void actionMade(EditAction action);
	
	public void cutAvailabilityChange(boolean isAvailable);
	
	public void copyAvailabilityChange(boolean isAvailable);
	
	public void pasteAvailabilityChange(boolean isAvailable);

}
