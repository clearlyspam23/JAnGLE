package com.clearlyspam23.GLE.GUI;

import java.util.ArrayList;
import java.util.List;

import org.piccolo2d.event.PInputEventListener;

public class EditorContainer {
	
	private List<LayerEditorDialog> dialogs = new ArrayList<LayerEditorDialog>();
	
	private List<PInputEventListener> listeners = new ArrayList<PInputEventListener>();

	public List<LayerEditorDialog> getDialogs() {
		return dialogs;
	}

	public void addDialogs(List<LayerEditorDialog> dialogs) {
		this.dialogs.addAll(dialogs);
	}

	public List<PInputEventListener> getListeners() {
		return listeners;
	}

	public void addListener(PInputEventListener listener) {
		listeners.add(listener);
	}

}
