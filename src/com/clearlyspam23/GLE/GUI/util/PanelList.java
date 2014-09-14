package com.clearlyspam23.GLE.GUI.util;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class PanelList extends JPanel implements FocusListener{
	
	public static void main(String[] args){
		final PanelList pList = new PanelList();
		JPanel panel = new JPanel();
		JButton button = new JButton("nothing");
		panel.add(button);
		JLabel test = new JLabel("test1");
		test.addMouseListener(pList.getMouseListener(panel));
		test.setBackground(new Color(0, 0, 0, 0));
		panel.add(test);
		pList.addPanel(panel);
		pList.addComponent(new JLabel("test2"));
		pList.addComponent(new JLabel("test3"));
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrame frame = new JFrame();
					JScrollPane scroll = new JScrollPane(pList);
					frame.add(scroll);
					frame.setSize(150, 400);
					frame.setVisible(true);
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
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
		if(selectedPanels.remove(panel))
			panel.setBackground(panelColor);
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
	
	private boolean clicked = false;
	
	/**
	 * adds any component to this PanelList
	 * will internally create a JPanel to surround this component
	 * @param component the component to add to this list
	 */
	public void addComponent(Component component){
		final JPanel panel = new JPanel();
		component.setBackground(new Color(0, 0, 0, 0));
		panel.setLayout(new GridBagLayout());
		panel.add(component);
		final int index = panels.size();
		panel.addMouseListener(getMouseListener(panel));
		addPanel(panel);
	}
	
	public MouseListener getMouseListener(final JPanel panel){
		return new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
//				setSelection(panel);
//				System.out.println("clicked on " + index);
//				repaint();
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// intentionally empty
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				clicked = false;
				// intentionally empty
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				clicked = true;
				// intentionally empty
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(clicked){
					if(selectedPanels.contains(panel)&&selectionMode==MULTIPLE_SELECTION)
						clearSelection(panel);
					else
						setSelection(panel);
					repaint();
					clicked = false;
				}
			}
			
		};
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
	
	public Dimension getMaximumSize(){
		return getPreferredSize();
	}

}
