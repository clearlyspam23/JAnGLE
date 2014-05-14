package com.clearlyspam23.GLE;

import java.awt.GridLayout;

import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TileLayer extends JPanel {
	
	private JLabel[][] grid;

	/**
	 * Create the panel.
	 */
	public TileLayer() {
		this(400, 400, 16, 16);

	}
	
	public TileLayer(int width, int height, int gridWidth, int gridHeight){
		setSize(width, height);
		setLayout(new GridLayout(width/gridWidth, height/gridHeight, 0, 0));
		grid = new JLabel[width/gridWidth][height/gridHeight];
		for(int j = 0; j < (grid.length > 0 ? grid[0].length : 0); j++)
		{
			for(int i = 0; i < grid.length; i++)
			{
				grid[i][j] = new JLabel();
				grid[i][j].setText("test");
				add(grid[i][j]);
			}
		}
	}
	
	public void setTile(int x, int y, Icon icon)
	{
		grid[x][y].setIcon(icon);
		grid[x][y].setOpaque(icon!=null);
	}
	
	public void clearTile(int x, int y)
	{
		setTile(x, y, null);
	}

}
