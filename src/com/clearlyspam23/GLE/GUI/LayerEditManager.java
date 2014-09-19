package com.clearlyspam23.GLE.GUI;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Icon;

import org.piccolo2d.event.PInputEvent;
import org.piccolo2d.event.PInputEventListener;

import com.clearlyspam23.GLE.Nameable;

public abstract class LayerEditManager implements PInputEventListener, Nameable{
	
	private ComponentData mainComponent;
	
	public static class ComponentData{
		public Component component;
		public String name;
		public Icon icon;
		
		public ComponentData(Component component, String name){
			this.component = component;
			this.name = name;
		}
		
		public ComponentData(Component component, String name, Icon icon){
			this.component = component;
			this.name = name;
			this.icon = icon;
		}
	}
	private List<ComponentData> subComponents = new ArrayList<ComponentData>();
//	private List<LayerEditorDialog> dialogs = new ArrayList<LayerEditorDialog>();
	private List<PInputEventListener> eventListeners = new ArrayList<PInputEventListener>();

	public List<PInputEventListener> getEventListeners() {
		return eventListeners;
	}

	public void setEventListeners(List<PInputEventListener> eventListeners) {
		this.eventListeners = eventListeners;
	}

	@Override
	public void processEvent(PInputEvent arg0, int arg1) {
		for(PInputEventListener l : eventListeners){
			l.processEvent(arg0, arg1);
		}
	}
	
	public ComponentData getMainComponent(){
		return mainComponent;
	}

	public void setMainComponent(Component mainComponent, String name) {
		this.mainComponent = new ComponentData(mainComponent, name);
	}
	
	public void setMainComponent(Component mainComponent, String name, Icon icon) {
		this.mainComponent = new ComponentData(mainComponent, name, icon);
	}

	public List<ComponentData> getSubComponents() {
		return subComponents;
	}

	public void addSubComponents(Component subComponent, String name) {
		subComponents.add(new ComponentData(subComponent, name));
	}
	
	public void addSubComponents(Component subComponent, String name, Icon icon) {
		subComponents.add(new ComponentData(subComponent, name, icon));
	}

}