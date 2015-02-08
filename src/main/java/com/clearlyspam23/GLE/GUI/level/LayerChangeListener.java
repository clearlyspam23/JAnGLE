package com.clearlyspam23.GLE.GUI.level;

import com.clearlyspam23.GLE.level.Layer;

public interface LayerChangeListener {
	
	public void onLayerChange(Layer<?> oldLayer, Layer<?> newLayer, LevelPanel source);

}
