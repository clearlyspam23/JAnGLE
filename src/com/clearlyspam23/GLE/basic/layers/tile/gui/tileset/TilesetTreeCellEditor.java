package com.clearlyspam23.GLE.basic.layers.tile.gui.tileset;

import java.awt.Component;

import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellEditor;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeCellEditor;

public class TilesetTreeCellEditor extends DefaultTreeCellEditor {

	public TilesetTreeCellEditor(JTree tree, DefaultTreeCellRenderer renderer) {
		super(tree, renderer);
	}

	public TilesetTreeCellEditor(JTree tree, DefaultTreeCellRenderer renderer,
			TreeCellEditor editor) {
		super(tree, renderer, editor);
	}

	@Override
	public Component getTreeCellEditorComponent(JTree tree, Object value,
			boolean isSelected, boolean expanded, boolean leaf, int row) {
		Component c =  super.getTreeCellEditorComponent(tree, value, isSelected, expanded,
				leaf, row);
		System.out.println(c);
		return c;
	}

	@Override
	public Object getCellEditorValue() {
		Object o = super.getCellEditorValue();
		System.out.println(o);
		return o;
	}

	@Override
	public boolean stopCellEditing() {
		return super.stopCellEditing();
	}

	@Override
	public void cancelCellEditing() {
		super.cancelCellEditing();
	}

}
