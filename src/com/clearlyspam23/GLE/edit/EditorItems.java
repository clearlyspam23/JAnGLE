package com.clearlyspam23.GLE.edit;

import java.awt.Button;
import java.util.List;

import javax.swing.JMenu;

import com.clearlyspam23.GLE.Template;
import com.clearlyspam23.GLE.level.Layer;
import com.clearlyspam23.GLE.level.LayerDefinition;

public abstract class EditorItems<T extends Layer<?>> {
	
	private LayerDefinition<?, ?> def;
	
	public EditorItems(LayerDefinition<?, ?> def){
		this.setDef(def);
	}
	
	public LayerDefinition<?, ?> getDef() {
		return def;
	}

	public void setDef(LayerDefinition<?, ?> def) {
		this.def = def;
	}
	
	public abstract List<JMenu> getMenuItems(Template template);
	
	public abstract List<Button> getButtonBarItems(Template template);

}
