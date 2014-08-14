package com.clearlyspam23.GLE.GUI;

import java.awt.Button;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class EditorItems {
	
	private List<JMenuItem> levelItems = new ArrayList<JMenuItem>();
	private List<JMenu> menuItems = new ArrayList<JMenu>();
	private List<Button> buttonBarItems = new ArrayList<Button>();
	public List<JMenuItem> getLevelItems() {
		return levelItems;
	}
	public EditorItems addLevelItems(List<JMenuItem> levelItems) {
		this.levelItems.addAll(levelItems);
		return this;
	}
	public EditorItems addLevelItem(JMenuItem item){
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

}
