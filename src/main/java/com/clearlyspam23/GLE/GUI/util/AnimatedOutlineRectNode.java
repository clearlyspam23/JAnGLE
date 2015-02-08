package com.clearlyspam23.GLE.GUI.util;

import java.awt.BasicStroke;
import java.awt.Color;

import javax.swing.SwingUtilities;

import org.piccolo2d.PCamera;
import org.piccolo2d.activities.PActivity;
import org.piccolo2d.extras.PFrame;

public class AnimatedOutlineRectNode extends FixedWidthOutlineRectNode implements Animatable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private FixedWidthStroke[] strokes = new FixedWidthStroke[12];
	private int state = 0;
	private AnimationActivity activity = new AnimationActivity();
	
	public static void main(String[] args){
		SwingUtilities.invokeLater(new Runnable() {
			 
            @Override
            public void run() {
                PFrame frame = new PFrame();
                AnimatedOutlineRectNode rect = new AnimatedOutlineRectNode(frame.getCanvas().getCamera(), Color.RED, LEFT+RIGHT+TOP);
                AnimatedOutlineRectNode botRect = new AnimatedOutlineRectNode(frame.getCanvas().getCamera(), LEFT+RIGHT+BOTTOM);
                rect.setBounds(0, 0, 200, 200);
                botRect.setBounds(0, 200, 200, 200);
                frame.getCanvas().getLayer().addChild(rect);
                frame.getCanvas().getLayer().addChild(botRect);
            }
        });
	}
	
	public class AnimationActivity extends PActivity{

		public AnimationActivity() {
			super(-1, 100);
		}
		
		protected void activityStep(long elapsedTime) {
            super.activityStep(elapsedTime);
                            
            incrementState();
            repaint();
    }
		
	}
	
	public AnimatedOutlineRectNode(PCamera camera){
		this(camera, Color.BLACK, ALL);
	}
	
	public AnimatedOutlineRectNode(PCamera camera, int drawOptions){
		this(camera, Color.BLACK, drawOptions);
	}
	
	public AnimatedOutlineRectNode(PCamera camera, Color color){
		this(camera, Color.BLACK, ALL);
	}
	
	public AnimatedOutlineRectNode(PCamera camera, Color color, int drawOptions){
		super(1, camera, color, drawOptions);
		for(int i =0 ; i < strokes.length; i++){
			strokes[i] = new FixedWidthStroke(2, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER, 1.0f, new float[]{6, 6}, i, camera);
		}
		setStroke(strokes[0]);
		Animatable.registerAnimation(this, this);
	}
	
	
	public void incrementState(){
		state = (state+1)%strokes.length;
		setStroke(strokes[state]);
	}

	@Override
	public PActivity getAnimationActivity() {
		return activity;
	}

}
