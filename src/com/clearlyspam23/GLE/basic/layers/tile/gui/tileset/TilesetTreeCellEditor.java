package com.clearlyspam23.GLE.basic.layers.tile.gui.tileset;

import java.awt.Component;
import java.awt.Container;

import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellEditor;
import javax.swing.tree.DefaultTreeCellRenderer;

import com.clearlyspam23.GLE.basic.layers.tile.TilesetTreeNode;

public class TilesetTreeCellEditor extends DefaultTreeCellEditor{
	
	public TilesetTreeCellEditor(JTree arg0, DefaultTreeCellRenderer arg1) {
		super(arg0, arg1);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TilesetTreeNode node;
	private JTextField field;

	@Override
	public Component getTreeCellEditorComponent(JTree tree, Object value,
			boolean isSelected, boolean expanded, boolean leaf, int row) {
		Container comp = (Container) super.getTreeCellEditorComponent(tree, value, isSelected, expanded, leaf, row);
		node = (TilesetTreeNode) ((DefaultMutableTreeNode) value).getUserObject();
	    field = (JTextField)comp.getComponent(0);
	    field.setText(node.getName());
	    return comp;
	}

	@Override
	public Object getCellEditorValue() {
		node.setName(field.getText());
		//System.out.println("get Cell Editor Value "+ o);
		return node;
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
