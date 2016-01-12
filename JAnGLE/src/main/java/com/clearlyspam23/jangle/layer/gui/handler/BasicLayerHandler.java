package com.clearlyspam23.jangle.layer.gui.handler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;

import com.clearlyspam23.jangle.layer.gui.LayerHandler;
import com.clearlyspam23.jangle.layer.gui.LayerNode;
import com.clearlyspam23.jangle.layer.gui.OverlayNode;

public abstract class BasicLayerHandler<T extends Event> implements LayerHandler, EventHandler<T> {

    private List<EventType<T>> events = new ArrayList<>();

    @SafeVarargs
    public BasicLayerHandler(EventType<T>... events) {
        this.events = Arrays.asList(events);
    }

    @Override
    public void register(LayerNode layer, OverlayNode overlay) {
        for (EventType<T> e : events) {
            layer.addEventHandler(e, this);
        }
    }



}
