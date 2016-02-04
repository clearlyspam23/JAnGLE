package com.clearlyspam23.jangle.layer.gui;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.Group;
import javafx.scene.Node;

public class OverlayNode extends Group {
    private Map<String, Node> nodeLookupMap = new HashMap<>();

    public void addNamedNode(String name, Node n) {
        if (nodeLookupMap.containsKey(n)) {
            if (nodeStillValid(n)) {
                throw new IllegalArgumentException("attempted to add duplicate key " + name
                        + " : node with that name already exists");
            } else {
                nodeLookupMap.remove(name);
            }
        }
        getChildren().add(n);
        nodeLookupMap.put(name, n);
    }

    public Node getNamedNode(String name) {
        Node node = nodeLookupMap.get(name);
        if (!nodeStillValid(node)) {
            nodeLookupMap.remove(name);
            return null;
        }
        return node;
    }

    public <T extends Node> T getNamedNode(String name, Class<T> cls) {
        return cls.cast(getNamedNode(name));
    }

    public void removeNamedNode(String name) {
        Node n = nodeLookupMap.remove(name);
        getChildren().remove(n);
    }

    private boolean nodeStillValid(Node n) {
        // specifically check if this node is the parent
        return n.getParent() == this;
    }

}
