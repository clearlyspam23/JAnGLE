package com.clearlyspam23.GLE.GUI.util;

import java.awt.Color;
import java.awt.Graphics;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class BasicEditorButton extends JButton{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static Color backgroundColor = new Color(0.706f, 0.831f, 0.957f);
	
	public BasicEditorButton(String iconFileName, String name, String info){
		ImageIcon icon = null;
		try {
			icon = new ImageIcon(ImageIO.read(new File(iconFileName)));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		setIcon(icon);
		this.setToolTipText(name + " Tool: " + info);
		setBorderPainted(false);
		setContentAreaFilled(false);
	}
	
	public BasicEditorButton(Icon icon, String name, String info)
	{
		super(icon);
		this.setToolTipText(name + " Tool: " + info);
		setBorderPainted(false);
		setContentAreaFilled(false);
	}
	
	public void setSelected(boolean flag){
		super.setSelected(flag);
		if(flag){
			setBorderPainted(true);
		}
		else {
			setBorderPainted(false);
		}
	}
	
	@Override
    protected void paintComponent(Graphics g) {
        if (getModel().isPressed()) {
            g.setColor(backgroundColor);
        } else if (getModel().isRollover()) {
            g.setColor(backgroundColor);
        } else {
            g.setColor(getBackground());
        }
        if(isSelected())
        	g.setColor(backgroundColor);
        g.fillRect(0, 0, getWidth(), getHeight());
        super.paintComponent(g);
    }

}
