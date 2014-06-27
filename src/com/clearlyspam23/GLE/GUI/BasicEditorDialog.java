package com.clearlyspam23.GLE.GUI;

import java.awt.Frame;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class BasicEditorDialog extends LayerEditorDialog {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final List<ChangeListener> listeners = new ArrayList<ChangeListener>();

//	public static void main(String[] args) {
//		try {
//			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//		} catch (ClassNotFoundException | InstantiationException
//				| IllegalAccessException | UnsupportedLookAndFeelException e1) {
//			//honestly, if this doesnt work, whatever we'll use default. should fail silently.
//		}
//		try {
//			BasicEditorDialog dialog = new BasicEditorDialog(null, "test");
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//			System.out.println(dialog.getSize());
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
	
	private List<BasicEditorButton> buttons;
	private int width;
	private int height;
	private BasicEditorButton selected;
	
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
		getContentPane().setLayout(null);
		buttons = editorButtons;
		width = prefWidth*columns + spacing*(columns+1);
		height = prefHeight*((buttons.size()+columns-1)/columns)+spacing*((buttons.size()+columns-1)/columns+1);
		int x = spacing;
		int count = 0;
		int y = spacing;
		for(BasicEditorButton b : buttons){
			getContentPane().add(b);
			b.setLocation(x, y);
			b.setSize(prefHeight, prefHeight);
			x+=prefWidth+spacing;
			if(++count>=columns)
			{
				y+=prefHeight+spacing;
				x = spacing;
			}
			b.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent arg0) {
					BasicEditorButton b = (BasicEditorButton) arg0.getSource();
					helperSelectButton(b);
					ChangeEvent e = new ChangeEvent(BasicEditorDialog.this);
					for(ChangeListener l : listeners)
						l.stateChanged(e);
				}
				
			});
		}
	}
	
	public void addChangeListener(ChangeListener l){
		listeners.add(l);
	}
	
	public int getSelectedButtonIndex()
	{
		return buttons.indexOf(selected);
	}
	
	public BasicEditorButton getSelectedButton()
	{
		return selected;
	}
	
	public void selectButton(int index)
	{
		if(index>=0&&index<buttons.size())
			helperSelectButton(buttons.get(index));
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
	
	public void setVisible(boolean flag){
		super.setVisible(flag);
		if(true){
			Insets insets = getInsets();
			System.out.println(insets);
			System.out.println(getSize());
			setSize(Math.max(width+insets.left+insets.right, getWidth()), height+insets.bottom+insets.top);
		}
	}
	
//	protected List<BasicEditorButton> getButtons()
//	{
//		List<BasicEditorButton> buttons = new ArrayList<BasicEditorButton>();
//		BufferedImage icon = null;
//		try {
//			File f = new File("images/Pencil.png");
//			icon  = ImageIO.read(f);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		if(icon==null)
//		{
//			System.err.println("unable to load the image");
//			return buttons;
//		}
//		ImageIcon ico = new ImageIcon(icon);
//		buttons.add(new BasicEditorButton(ico, "pencil", "test1"));
//		buttons.add(new BasicEditorButton(ico, "pencil2", "the pencil tool"));
//		buttons.add(new BasicEditorButton(ico, "pencil3", "the pen tool"));
//		return buttons;
//	}

}
