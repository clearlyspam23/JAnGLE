package com.clearlyspam23.GLE.GUI;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.event.InputEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import org.piccolo2d.PNode;
import org.piccolo2d.event.PInputEventFilter;
import org.piccolo2d.event.PMouseWheelZoomEventHandler;
import org.piccolo2d.extras.pswing.PSwingCanvas;

import com.clearlyspam23.GLE.Layer;
import com.clearlyspam23.GLE.Level;
import com.clearlyspam23.GLE.Template;
import com.clearlyspam23.GLE.basic.layers.tile.TileLayer;
import com.clearlyspam23.GLE.basic.layers.tile.TileLayerPNode;
import com.clearlyspam23.GLE.basic.layers.tile.TileLayerTemplate;
import com.clearlyspam23.GLE.basic.layers.tile.Tileset;
import com.clearlyspam23.GLE.basic.layers.tile.TilesetEditorData;
import com.clearlyspam23.GLE.basic.layers.tile.commands.PlaceTileCommand;

public class TestLevelPanel extends JPanel {

//	static protected Line2D gridLine = new Line2D.Double();
//    static protected Rectangle2D rect = new Rectangle2D.Double();
//    static protected Color gridPaint = Color.BLACK;
//    static protected double gridSpacing = 20;
//    static protected float lineSpacing = (float) (gridSpacing/4);
//    static protected Stroke gridStroke = new BasicStroke(1, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER, 1.0f, new float[]{lineSpacing, lineSpacing}, lineSpacing/2);
//    static protected Stroke startStroke = new BasicStroke(1, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER, 1.0f, new float[]{lineSpacing/2, lineSpacing}, 0);
	
	public TestLevelPanel()
	{
//		final double gridSpacing = 10;
        
        
        
		
		BufferedImage tile = null;
		BufferedImage[] tiles = null;
		try {
			File f = new File("images/testboxes.png");
			BufferedImage temp  = ImageIO.read(f);
			tile = temp.getSubimage(0, 0, 64, 64);
			tiles = new BufferedImage[4];
			for(int i = 0; i < tiles.length; i++)
			{
				tiles[i] = temp.getSubimage(64*i, 0, 64, 64);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(tile==null)
		{
			System.err.println("unable to load the image");
			return;
		}
		
		
		
		
		setLayout(new BorderLayout());
		
		Template t = new Template();
		
		TileLayerTemplate template = new TileLayerTemplate();
		template.setGridWidth(32);
		template.setGridHeight(32);
		
		Level level = new Level(t);
		level.setDimensions(320, 320);
		level.addLayer(template.createLayer(level));
		
		final PSwingCanvas canvas = new PSwingCanvas();
		add(canvas, BorderLayout.CENTER);
		
		PNode base = new PNode();
		canvas.getLayer().addChild(base);
		
		for(Layer l : level.getLayers())
		{
			System.out.println("here");
			base.addChild(l.getLayerGUI());
			if(l instanceof TileLayer)
			{
				TileLayer tl = (TileLayer) l;
				TileLayerPNode n = (TileLayerPNode) tl.getLayerGUI().getChild(0);
				for(int i = 0; i < n.getNodeGrid().length; i++)
					for(int j = 0; j < n.getNodeGrid()[i].length; j++)
						n.getNodeGrid()[i][j].setImage(tiles[(int) (Math.random()*tiles.length)]);
			}
		}
		
		TilesetEditorData data = new TilesetEditorData();
		data.setCurrentTileset(new Tileset(new Image[][]{{tile}}));
		data.setSelectedIndex(0, 0);
		
		canvas.addInputEventListener(new PlaceTileCommand(canvas, data));
		
		canvas.getPanEventHandler().setEventFilter(new PInputEventFilter(InputEvent.BUTTON3_MASK));
        canvas.removeInputEventListener(canvas.getZoomEventHandler());
        PMouseWheelZoomEventHandler eh = new PMouseWheelZoomEventHandler();
        eh.zoomAboutMouse();
        eh.setScaleFactor(-0.1);
        canvas.addInputEventListener(eh); 
		

        this.setVisible(true);
        this.validate();
	}

}
