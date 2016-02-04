package com.clearlyspam23.jangle.entity.gui;

import java.util.Optional;

import javafx.event.Event;
import javafx.event.EventType;

public class EntitySelectionEvent extends Event {

    /**
     * this event occurs whenever a node is selected. Note that any events of this type guarantee
     * that getNewSelection().isPresent() will return true, and that
     * getPreviousSelection().isPresent() will return false.
     */
    public static final EventType<EntitySelectionEvent> SELECTED =
            new EventType<EntitySelectionEvent>("SELECTED");
    /**
     * this event occurs whenever a node loses selection. Note that any events of this type
     * guarantee that getPreviousSelection().isPresent() will return true, and that
     * getNewSelection().isPresent() will return false.
     */
    public static final EventType<EntitySelectionEvent> DESELECTED =
            new EventType<EntitySelectionEvent>("DESELECTED");
    /**
     * this event occurs whenever a node is selected or deselected. Events of this type make no
     * guarantee on the presence of getNewSelection() or getPreviousSelection(). these Optional
     * values will need to be queried on presence by calling isPresent() before use.
     */
    public static final EventType<EntitySelectionEvent> CHANGED =
            new EventType<EntitySelectionEvent>("CHANGED");

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private Optional<EntityNode> previousSelection;
    private Optional<EntityNode> newSelection;

    public EntitySelectionEvent(EventType<? extends Event> arg0,
            Optional<EntityNode> previousSelection, Optional<EntityNode> newSelection) {
        super(arg0);
        this.previousSelection = previousSelection;
        this.newSelection = newSelection;
    }

    public Optional<EntityNode> getPreviousSelection() {
        return previousSelection;
    }

    public Optional<EntityNode> getNewSelection() {
        return newSelection;
    }

}
