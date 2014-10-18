package com.clearlyspam23.GLE.GUI.level;

import com.clearlyspam23.GLE.level.Layer;

public interface ChangeLayerListener {
	
	@SuppressWarnings("rawtypes")
	public void onLayerChange(Layer oldLayer, Layer newLayer);

}
