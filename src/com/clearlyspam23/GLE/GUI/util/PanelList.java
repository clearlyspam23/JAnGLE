package com.clearlyspam23.GLE.GUI.util;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

public class PanelList extends JPanel implements FocusListener{
	
	public static void main(String[] args){
		
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public static final int SINGLE_SELECTION = 0;
	public static final int MULTIPLE_SELECTION = 1;
	
	private List<JPanel> panels = new ArrayList<JPanel>();
	
	private List<JPanel> selectedPanels = new ArrayList<JPanel>();
	
	private int selectionMode;
	
	private Color selectionColor = Color.BLUE;
	private Color panelColor = new Color(0, 0, 0, 0);
	private Color outOfFocusColor = Color.LIGHT_GRAY;
	private static final Color DEFAULT_BACKGROUND = Color.WHITE;

	/**
	 * Create the panel.
	 */
	public PanelList() {
		this(new ArrayList<Component>(), SINGLE_SELECTION);
	}
	
	public PanelList(List<Component> components){
		this(components, SINGLE_SELECTION);
	}
	
	public PanelList(List<Component> components, int selectionMode){
		setLayout(new GridLayout(0, 1, 0, 0));
		setBackground(DEFAULT_BACKGROUND);
		if(!isValidSelectionMode(selectionMode))
			throw new IllegalArgumentException("selection mode needs to be either \"PanelList.SINGLE_SELECTION\" or \"PanelList.MULTIPLE_SELECTION\"");
		for(Component c : components){
			addComponent(c);
		}
	}
	
	private static boolean isValidSelectionMode(int selectionMode){
		return selectionMode==SINGLE_SELECTION||selectionMode==MULTIPLE_SELECTION;
	}
	
	public void setSelection(int index){
		setSelection(panels.get(index));
	}
	
	public void setSelection(JPanel panel){
		if(!panels.contains(panel))
			throw new IllegalArgumentException("cannot select a panel not in the list");
		if(selectionMode==SINGLE_SELECTION)
			clearAllSelections();
		selectPanel(panel);
	}
	
	public void clearAllSelections(){
		for(JPanel panel : selectedPanels){
			panel.setBackground(panelColor);
		}
		selectedPanels.clear();
	}
	
	public void clearSelection(JPanel panel){
		
	}
	
	private void selectPanel(JPanel panel){
		panel.setBackground(selectionColor);
		selectedPanels.add(panel);
	}
	
	public void setSelectionColor(Color color){
		selectionColor = color;
		if(isFocusOwner())
			for(JPanel panel : selectedPanels)
				panel.setBackground(color);
	}
	
	public Color getSelectionColor(){
		return selectionColor;
	}
	
	public void setPanelColor(Color color){
		panelColor = color;
		for(JPanel panel : panels)
			if(!selectedPanels.contains(panel))
				panel.setBackground(color);
	}
	
	public Color getPanelColor(){
		return panelColor;
	}
	
	public Color getOutOfFocusColor() {
		return outOfFocusColor;
	}

	public void setOutOfFocusColor(Color outOfFocusColor) {
		this.outOfFocusColor = outOfFocusColor;
		if(!isFocusOwner())
			for(JPanel panel : selectedPanels)
				panel.setBackground(outOfFocusColor);
	}

	public int getSelectedIndex(){
		if(selectedPanels.isEmpty())
			return -1;
		return panels.indexOf(selectedPanels.get(0));
	}
	
	public int[] getSelectedIndices(){
		int[] ans = new int[selectedPanels.size()];
		for(int i = 0; i < selectedPanels.size(); i++){
			ans[i] = panels.indexOf(selectedPanels.get(i));
		}
		return ans;
	}
	
	public JPanel getSelectedPanel(){
		if(selectedPanels.isEmpty())
			return null;
		return selectedPanels.get(0);
	}
	
	public List<JPanel> getSelectedPanels(){
		List<JPanel> ans = new ArrayList<JPanel>();
		ans.addAll(selectedPanels);
		return ans;
	}
	
	/**
	 * adds any component to this PanelList
	 * will internally create a JPanel to surround this component
	 * @param component the component to add to this list
	 */
	public void addComponent(Component component){
		final JPanel panel = new JPanel();
		panel.add(component);
		panel.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				setSelection(panel);
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// intentionally empty
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// intentionally empty
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// intentionally empty
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// intentionally empty
			}
			
		});
		addPanel(panel);
	}
	
	/**
	 * more advanced method, adds a panel directly to this list.
	 * Will not add the associated mouse listeners, should be added before this, if desired
	 * @param panel
	 */
	public void addPanel(JPanel panel){
		panel.setBackground(panelColor);
		panels.add(panel);
		add(panel);
	}

	@Override
	public void focusGained(FocusEvent arg0) {
		for(JPanel panel : selectedPanels){
			panel.setBackground(selectionColor);
		}
	}

	@Override
	public void focusLost(FocusEvent arg0) {
		for(JPanel panel : selectedPanels){
			panel.setBackground(outOfFocusColor);
		}
	}

}
