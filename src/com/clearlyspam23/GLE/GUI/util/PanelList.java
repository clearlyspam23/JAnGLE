package com.clearlyspam23.GLE.GUI.util;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToggleButton;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class PanelList extends JPanel implements FocusListener{
	
	public static void main(String[] args){
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e1) {
			//honestly, if this doesnt work, whatever we'll use default. should fail silently.
		}
		Image image = null;
		try{
			image = ImageIO.read(new File("images/VisibilityIcon.png"));
		}
		catch(Exception e){
			e.printStackTrace();
			return;
		}
		final Icon ico = new ImageIcon(image);
		final PanelList pList = new PanelList(0, 0, 0, 0);
		final JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setLayout(new BorderLayout(5, 0));
		final JToggleButton button = new PushButton(ico);
		button.setPreferredSize(new Dimension(32, 32));
		panel.add(button, BorderLayout.WEST);
		button.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
				pList.repaint();
				System.out.println("painting");
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				pList.repaint();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				pList.repaint();
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				pList.repaint();
			}
			
		});
		button.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("action!");
				System.out.println(button.isSelected());
			}
			
		});
		JLabel test = new JLabel("test1");
		test.addMouseListener(pList.getMouseListener(panel));
		test.setBackground(new Color(0, 0, 0, 0));
		panel.add(test, BorderLayout.CENTER);
		pList.addPanel(panel);
		pList.addComponent(new JLabel("test2"));
		pList.addComponent(new JLabel("test3"));
		//final JList<String> jList = new JList<String>(new String[]{"test1", "test2", "test3"});
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrame frame = new JFrame();
					frame.getContentPane().setLayout(new GridLayout(1, 1, 0, 0));
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
	
	private JPanel subPanel = new JPanel();
	
	private List<JPanel> panels = new ArrayList<JPanel>();
	
	private List<JPanel> selectedPanels = new ArrayList<JPanel>();
	
	private int selectionMode;
	
//	UIDefaults defaults = javax.swing.UIManager.getDefaults();
//	defaults.getColor("List.selectionBackground");
	
	private Color selectionColor = javax.swing.UIManager.getDefaults().getColor("List.selectionBackground");
	private Color panelColor = javax.swing.UIManager.getDefaults().getColor("List.background");
	private Color outOfFocusColor = Color.LIGHT_GRAY;
	private static final Color DEFAULT_BACKGROUND = javax.swing.UIManager.getDefaults().getColor("List.background");
	
	private ArrayList<ListSelectionListener> listeners = new ArrayList<ListSelectionListener>();

	/**
	 * Create the panel.
	 */
	public PanelList() {
		this(new ArrayList<Component>(), SINGLE_SELECTION);
	}
	
	public PanelList(int insetTop, int insetLeft, int insetBottom, int insetRight){
		this(new ArrayList<Component>(), SINGLE_SELECTION, insetTop, insetLeft, insetBottom, insetRight);
	}
	
	public PanelList(List<Component> components){
		this(components, SINGLE_SELECTION);
	}
	
	public PanelList(List<Component> components, int selectionMode){
		this(components, selectionMode, 0, 5, 0, 5);
	}
	
	public PanelList(List<Component> components, int selectionMode, int insetTop, int insetLeft, int insetBottom, int insetRight){
		setLayout(new BorderLayout());
		JPanel temp = new JPanel();
		temp.setBackground(DEFAULT_BACKGROUND);
		add(temp, BorderLayout.CENTER);
		add(subPanel, BorderLayout.NORTH);
		subPanel.setLayout(new GridLayout(0, 1, 0, 0));
		setBackground(DEFAULT_BACKGROUND);
		subPanel.setBorder(new EmptyBorder(insetTop, insetLeft, insetBottom, insetRight));
		subPanel.setBackground(DEFAULT_BACKGROUND);
		subPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
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
			clearAllSelections(true);
		selectPanel(panel);
		int min = 0;
		int max = panels.size();
		for(JPanel p : selectedPanels){
			min = Math.min(panels.indexOf(p), min);
			max = Math.max(panels.indexOf(p), max);
		}
		ListSelectionEvent e = new ListSelectionEvent(this, min, max, false);
		for(ListSelectionListener l : listeners){
			l.valueChanged(e);
		}
	}
	
	public void clearAllSelections(){
		clearAllSelections(false);
	}
	
	public void clearSelection(JPanel panel){
		if(selectedPanels.remove(panel))
			panel.setBackground(panelColor);
	}
	
	private void clearAllSelections(boolean isAdjusting){
		int min = 0;
		int max = panels.size();
		for(JPanel panel : selectedPanels){
			panel.setBackground(panelColor);
			min = Math.min(panels.indexOf(panel), min);
			max = Math.max(panels.indexOf(panel), max);
		}
		selectedPanels.clear();
		ListSelectionEvent e = new ListSelectionEvent(this, min, max, isAdjusting);
		for(ListSelectionListener l : listeners){
			l.valueChanged(e);
		}
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
		panel.setLayout(new GridLayout(1, 1, 0, 0));
		panel.add(component);
		panel.addMouseListener(getMouseListener(panel));
		addPanel(panel);
	}
	
	public void setInsets(int top, int left, int bottom, int right){
		subPanel.setBorder(new EmptyBorder(top, left, bottom, right));
	}
	
	public MouseListener getMouseListener(final JPanel panel){
		return new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				clicked = false;
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				clicked = true;
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
//		GridBagConstraints constraints = new GridBagConstraints();
//		constraints.anchor = GridBagConstraints.LINE_START;
//		constraints.gridx = 0;
//		constraints.gridy = GridBagConstraints.RELATIVE;
//		constraints.fill = GridBagConstraints.HORIZONTAL;
		panels.add(panel);
		subPanel.add(panel);
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
	
	public void addListSelectionListener(ListSelectionListener listener){
		listeners.add(listener);
	}

}
