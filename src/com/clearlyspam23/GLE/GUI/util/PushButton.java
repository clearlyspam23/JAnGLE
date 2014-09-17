package com.clearlyspam23.GLE.GUI.util;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JToggleButton;

public class PushButton extends JToggleButton {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Icon upIcon;
	
	private Icon downIcon;
	
	public static void main(String[] args){
		Image image = null;
		try{
			image = ImageIO.read(new File("images/VisibilityIcon.png"));
		}
		catch(Exception e){
			e.printStackTrace();
			return;
		}
		final Icon ico = new ImageIcon(image);
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrame frame = new JFrame();
					frame.setLayout(null);
					PushButton b = new PushButton(ico);
					b.setSelected(true);
					b.setLocation(32, 32);
					b.setSize(32, 32);
					frame.add(b);
					frame.setSize(150, 400);
					frame.setVisible(true);
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public PushButton(){
		this(new EmptyIcon(), new EmptyIcon());
	}
	
	public PushButton(Icon downIcon){
		this(new EmptyIcon(downIcon.getIconWidth(), downIcon.getIconHeight()), downIcon);
	}
	
	public PushButton(Icon upIcon, Icon downIcon){
		this.upIcon = upIcon;
		this.downIcon = downIcon;
//		this.setBorderPainted(false);
//		this.setOpaque(true);
		//this.setContentAreaFilled(false);
	}
	
	private static final Color GRAY = new Color(100, 100, 100);
	private static final Color LIGHTEST_GRAY = new Color(215, 215, 215);
	
	@Override
    protected void paintComponent(Graphics g){
		int w = getWidth();
		int h = getHeight();
		g.setColor(getBackground());
		g.drawRect(0, 0, w, h);
		boolean sel = isSelected();
		if(sel){
			downIcon.paintIcon(this, g, 0, 0);
		}
		else{
			upIcon.paintIcon(this, g, 0, 0);
		}
		g.setColor(sel ? LIGHTEST_GRAY : Color.BLACK);
		g.drawLine(w-1, 0, w-1, h);
		g.drawLine(0, h-1, w, h-1);
		g.setColor(sel ? GRAY : LIGHTEST_GRAY);
		g.drawLine(0, 0, 0, h);
		g.drawLine(0, 0, w, 0);
		if(sel){
			g.setColor(Color.BLACK);
			g.drawLine(1, 1, 1, h-2);
			g.drawLine(1, 1, w-2, 1);
		}
		else{
			g.setColor(GRAY);
			g.drawLine(w-2, 2, w-2, h-2);
			g.drawLine(2, h-2, w-1, h-2);
		}
		
	}
	
//	public void updateUI(){
//		
//	}

}
