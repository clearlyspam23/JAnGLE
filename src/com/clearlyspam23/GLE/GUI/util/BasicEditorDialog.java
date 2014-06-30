package com.clearlyspam23.GLE.GUI.util;

import java.awt.Frame;
import java.awt.Insets;
import java.util.Arrays;
import java.util.List;

import com.clearlyspam23.GLE.GUI.LayerEditorDialog;

public class BasicEditorDialog extends LayerEditorDialog {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private BasicEditorPanel panel;
	
	public BasicEditorDialog(Frame owner, String title) {
		this(owner, title, 4, 32, 32, 5);
	}
	
	/**
	 * @wbp.parser.constructor
	 */
	public BasicEditorDialog(Frame owner, String title, int columns, int prefWidth, int prefHeight, int spacing, BasicEditorButton... editorButtons) {
		this(owner, title, columns, prefWidth, prefHeight, spacing, Arrays.asList(editorButtons));
	}
	
	public BasicEditorDialog(Frame owner, String title, BasicEditorButton...editorButtons){
		this(owner, title, 4, 32, 32, 5, Arrays.asList(editorButtons));
	}
	
	public BasicEditorDialog(Frame owner, String title, int columns, int prefWidth, int prefHeight, int spacing, List<BasicEditorButton> editorButtons){
		super(owner, title);
		this.setResizable(false);
		this.pack();
		Insets i = getInsets();
		panel = new BasicEditorPanel(columns, prefWidth, prefHeight, spacing, editorButtons);
		getContentPane().add(panel);
		setSize(Math.max(panel.getPreferredSize().width+i.left+i.right, getWidth()), panel.getPreferredSize().height+i.bottom+i.top);
	}
	
	public BasicEditorPanel getPanel(){
		return panel;
	}
	
//	public void addChangeListener(ChangeListener l){
//		panel.addChangeListener(l);
//	}
//	
//	public int getSelectedButtonIndex()
//	{
//		return panel.getSelectedButtonIndex();
//	}
//	
//	public BasicEditorButton getSelectedButton()
//	{
//		return panel.getSelectedButton();
//	}
//	
//	public void selectButton(int index)
//	{
//		panel.selectButton(index);
//	}

}
