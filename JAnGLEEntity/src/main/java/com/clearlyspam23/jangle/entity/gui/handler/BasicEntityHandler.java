package com.clearlyspam23.jangle.entity.gui.handler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;

import com.clearlyspam23.jangle.entity.gui.EntityHandler;
import com.clearlyspam23.jangle.entity.gui.EntityNode;
import com.clearlyspam23.jangle.layer.gui.LayerNode;
import com.clearlyspam23.jangle.layer.gui.OverlayNode;

public abstract class BasicEntityHandler<T extends Event> implements EventHandler<T>, EntityHandler {

    private List<EventType<T>> events = new ArrayList<>();

    @SafeVarargs
    public BasicEntityHandler(EventType<T>... events) {
        this.events = Arrays.asList(events);
    }

    @Override
    public void register(EntityNode node, LayerNode layer, OverlayNode overlay) {
        for (EventType<T> me : events) {
            node.addEventHandler(me, this);
        }
    }
}
