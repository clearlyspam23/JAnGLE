package com.clearlyspam23.jangle.entity.gui;

import com.clearlyspam23.jangle.layer.gui.LayerNode;
import com.clearlyspam23.jangle.layer.gui.OverlayNode;


public interface EntityHandler {

    public void register(EntityNode node, LayerNode layer, OverlayNode overlay);

}
