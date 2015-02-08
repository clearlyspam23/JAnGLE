package com.clearlyspam23.GLE.GUI.level;

import com.clearlyspam23.GLE.level.Layer;

public interface LayerContainer {
	
	public void changeLayer(int index);
	
	@SuppressWarnings("rawtypes")
	public Iterable<Layer> getLayers();

}
