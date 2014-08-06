package com.clearlyspam23.GLE.GUI;

import java.util.ArrayList;
import java.util.List;

import org.piccolo2d.event.PInputEventListener;

public class LayerEditManager {
	
	private List<LayerDialog> dialogs = new ArrayList<LayerDialog>();
	private List<PInputEventListener> eventListeners = new ArrayList<PInputEventListener>();

	public List<LayerDialog> getDialogs() {
		return dialogs;
	}

	public void setDialogs(List<LayerDialog> dialogs) {
		this.dialogs = dialogs;
	}

	public List<PInputEventListener> getEventListeners() {
		return eventListeners;
	}

	public void setEventListeners(List<PInputEventListener> eventListeners) {
		this.eventListeners = eventListeners;
	}

}
