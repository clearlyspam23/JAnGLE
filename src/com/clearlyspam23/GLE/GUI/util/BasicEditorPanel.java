package com.clearlyspam23.GLE.GUI.util;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class BasicEditorPanel extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<BasicEditorButton> buttons;
	private BasicEditorButton selected;
	private final List<ChangeListener> listeners = new ArrayList<ChangeListener>();
	
	public BasicEditorPanel() {
		this(4, 32, 32, 7);
	}
	
	/**
	 * @wbp.parser.constructor
	 */
	public BasicEditorPanel(int columns, int prefWidth, int prefHeight, int spacing, BasicEditorButton... editorButtons) {
		this(columns, prefWidth, prefHeight, spacing, Arrays.asList(editorButtons));
	}
	
	public BasicEditorPanel(BasicEditorButton...editorButtons){
		this(4, 32, 32, 7, Arrays.asList(editorButtons));
	}
	
	public BasicEditorPanel(int columns, int prefWidth, int prefHeight, int spacing, List<BasicEditorButton> editorButtons){
		setLayout(new FlowLayout(FlowLayout.LEADING, spacing, spacing));
		buttons = editorButtons;
		int width = prefWidth*columns + spacing*(columns+1);
		int height = prefHeight*((buttons.size()+columns-1)/columns)+spacing*((buttons.size()+columns-1)/columns+1);
		for(BasicEditorButton b : buttons){
			add(b);
//			b.setLocation(x, y);
//			b.setSize(prefHeight, prefHeight);
			b.setPreferredSize(new Dimension(prefHeight, prefHeight));
			b.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent arg0) {
					BasicEditorButton b = (BasicEditorButton) arg0.getSource();
					helperSelectButton(b);
					ChangeEvent e = new ChangeEvent(BasicEditorPanel.this);
					for(ChangeListener l : listeners)
						l.stateChanged(e);
				}
				
			});
		}
		setPreferredSize(new Dimension(width, height));
	}
	
	public BasicEditorButton getSelectedButton()
	{
		return selected;
	}
	
	public void selectButton(int index)
	{
		if(index>=0&&index<buttons.size())
			buttons.get(index).doClick();
		else
			clearSelection();
	}
	
	private void helperSelectButton(BasicEditorButton button){
		for(BasicEditorButton b : buttons){
			b.setSelected(false);
		}
		button.setSelected(true);
		selected = button;
	}
	
	private void clearSelection()
	{
		for(BasicEditorButton b : buttons){
			b.setSelected(false);
		}
		selected = null;
	}
	
	public int getSelectedButtonIndex()
	{
		return buttons.indexOf(selected);
	}
	
	public void addChangeListener(ChangeListener l){
		listeners.add(l);
	}

}
