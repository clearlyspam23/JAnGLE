package com.clearlyspam23.jangle.entity.gui.handler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.clearlyspam23.jangle.entity.gui.EntityHandler;
import com.clearlyspam23.jangle.entity.gui.EntityNode;
import com.clearlyspam23.jangle.util.XFormNode;

import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.input.MouseEvent;

public abstract class BasicEntityHandler implements EventHandler<MouseEvent>, EntityHandler {

    private List<EventType<MouseEvent>> mouseEvents = new ArrayList<>();

    @SafeVarargs
    public final void registerForEvents(EventType<MouseEvent>... events) {
        mouseEvents = Arrays.asList(events);
    }

    @Override
    public void register(EntityNode node, XFormNode overlay) {
        for (EventType<MouseEvent> me : mouseEvents) {
            node.addEventHandler(me, this);
        }
    }

    public void handle(MouseEvent arg0) {
        handleMouseEvent(arg0);
    }

    public abstract void handleMouseEvent(MouseEvent arg0);
}
