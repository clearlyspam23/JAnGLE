package com.clearlyspam23.GLE.GUI;

import java.awt.Frame;

import javax.swing.JDialog;

public class LayerEditorDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Create the dialog.
	 */
	public LayerEditorDialog(Frame owner, String title) {
		super(owner, title);
		super.setModal(false);
		super.setAlwaysOnTop(true);
	}
	
//	public void setModal(boolean modal){
//		throw new UnsupportedOperationException("Layer Editors cannot be modal");
//	}

}
