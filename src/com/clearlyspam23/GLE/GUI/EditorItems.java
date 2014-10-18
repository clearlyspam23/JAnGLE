package com.clearlyspam23.GLE.GUI;

import java.awt.Button;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JMenu;

import com.clearlyspam23.GLE.level.LayerDefinition;

public class EditorItems {
	
	@SuppressWarnings("rawtypes")
	private List<LayerMenuItem> levelItems = new ArrayList<LayerMenuItem>();
	private List<JMenu> menuItems = new ArrayList<JMenu>();
	private List<Button> buttonBarItems = new ArrayList<Button>();
	
	@SuppressWarnings("rawtypes")
	private LayerDefinition def;
	
	@SuppressWarnings("rawtypes")
	public EditorItems(LayerDefinition def){
		this.setDef(def);
	}
	
	@SuppressWarnings("rawtypes")
	public List<LayerMenuItem> getLevelItems() {
		return levelItems;
	}
	@SuppressWarnings("rawtypes")
	public EditorItems addLevelItems(List<LayerMenuItem> levelItems) {
		this.levelItems.addAll(levelItems);
		return this;
	}
	@SuppressWarnings("rawtypes")
	public EditorItems addLevelItem(LayerMenuItem item){
		this.levelItems.add(item);
		return this;
	}
	public List<JMenu> getMenuItems() {
		return menuItems;
	}
	public EditorItems addMenuItems(List<JMenu> menuItems) {
		this.menuItems.addAll(menuItems);
		return this;
	}
	public EditorItems addMenuItem(JMenu menu){
		menuItems.add(menu);
		return this;
	}
	public List<Button> getButtonBarItems() {
		return buttonBarItems;
	}
	public EditorItems addButtonBarItems(List<Button> buttonBarItems) {
		this.buttonBarItems.addAll(buttonBarItems);
		return this;
	}
	public EditorItems addButtonBarItem(Button buttonBarItem){
		buttonBarItems.add(buttonBarItem);
		return this;
	}

	@SuppressWarnings("rawtypes")
	public LayerDefinition getDef() {
		return def;
	}

	@SuppressWarnings("rawtypes")
	public void setDef(LayerDefinition def) {
		this.def = def;
	}

}
