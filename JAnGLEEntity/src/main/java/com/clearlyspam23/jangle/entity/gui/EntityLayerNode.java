package com.clearlyspam23.jangle.entity.gui;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javafx.collections.ListChangeListener;
import javafx.scene.Node;

import com.clearlyspam23.jangle.layer.gui.LayerNode;

public class EntityLayerNode extends LayerNode {

    private Set<EntityNode> knownEntityNodes = new HashSet<>();

    private Optional<EntityNode> selectedNode = Optional.empty();

    public EntityLayerNode() {
        this.getChildren().addListener(new ListChangeListener<Node>() {

            @Override
            public void onChanged(
                    javafx.collections.ListChangeListener.Change<? extends Node> change) {
                while (change.next()) {
                    for (Node n : change.getAddedSubList()) {
                        if (n instanceof EntityNode) {
                            knownEntityNodes.add((EntityNode) n);
                        }
                    }
                    for (Node n : change.getRemoved()) {
                        if (n instanceof EntityNode) {
                            knownEntityNodes.remove((EntityNode) n);
                        }
                    }
                }
            }

        });
    }

    public void selectNode(EntityNode newNode) {
        Optional<EntityNode> previousSelection = selectedNode;
        Optional<EntityNode> newSelection = Optional.ofNullable(newNode);
        if (previousSelection.equals(newSelection)) {
            return; // no events fired if the nodes are the same
        }
        if (previousSelection.isPresent()) {
            fireEvent(new EntitySelectionEvent(EntitySelectionEvent.DESELECTED, previousSelection,
                    Optional.empty()));
        }
        selectedNode = newSelection;
        if (selectedNode.isPresent()) {
            fireEvent(new EntitySelectionEvent(EntitySelectionEvent.SELECTED, Optional.empty(),
                    selectedNode));
        }
        fireEvent(new EntitySelectionEvent(EntitySelectionEvent.CHANGED, previousSelection,
                selectedNode));
    }

    public Optional<EntityNode> getSelectedNode() {
        return selectedNode;
    }

}
