package com.clearlyspam23.GLE.GUI;

import java.awt.Button;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JMenu;

import com.clearlyspam23.GLE.Template;
import com.clearlyspam23.GLE.level.Layer;
import com.clearlyspam23.GLE.level.LayerDefinition;

public class DefaultEditorItems<T extends Layer<?>> extends EditorItems<T>{
	
	private List<LayerMenuItem<T, ?>> layerItems = new ArrayList<LayerMenuItem<T, ?>>();
	private List<JMenu> menuItems = new ArrayList<JMenu>();
	private List<Button> buttonBarItems = new ArrayList<Button>();
	
	public DefaultEditorItems(LayerDefinition<?, ?> def) {
		super(def);
		// TODO Auto-generated constructor stub
	}
	
	public List<LayerMenuItem<T, ?>> getLayerItems(T layer) {
		return layerItems;
	}
	public DefaultEditorItems<T> addLayerItems(List<LayerMenuItem<T, ?>> levelItems) {
		this.layerItems.addAll(levelItems);
		return this;
	}
	public DefaultEditorItems<T> addLayerItem(LayerMenuItem<T, ?> item){
		this.layerItems.add(item);
		return this;
	}
	public List<JMenu> getMenuItems(Template template) {
		return menuItems;
	}
	public DefaultEditorItems<T> addMenuItems(List<JMenu> menuItems) {
		this.menuItems.addAll(menuItems);
		return this;
	}
	public DefaultEditorItems<T> addMenuItem(JMenu menu){
		menuItems.add(menu);
		return this;
	}
	public List<Button> getButtonBarItems(Template template) {
		return buttonBarItems;
	}
	public DefaultEditorItems<T> addButtonBarItems(List<Button> buttonBarItems) {
		this.buttonBarItems.addAll(buttonBarItems);
		return this;
	}
	public DefaultEditorItems<T> addButtonBarItem(Button buttonBarItem){
		buttonBarItems.add(buttonBarItem);
		return this;
	}

}
