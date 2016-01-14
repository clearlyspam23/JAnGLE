package com.clearlyspam23.jangle.util;

import javafx.beans.property.ObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;

public class ParentCrawler<T extends Node> implements ChangeListener<Node> {

    private final ObjectProperty<T> property;
    private final Class<T> checkClass;

    public ParentCrawler(ObjectProperty<T> property, Class<T> checkClass) {
        this.property = property;
        this.checkClass = checkClass;
    }

    public void changed(ObservableValue<? extends Node> observable, Node oldValue, Node newValue) {
        removeRecursive(oldValue);
        addRecursive(newValue);
    }

    private void removeRecursive(Node old) {
        if (old == null || checkClass.isAssignableFrom(old.getClass())) {
            return;
        }
        old.parentProperty().removeListener(this);
        removeRecursive(old.getParent());
    }

    private void addRecursive(Node parent) {
        if (parent == null) {
            property.set(null);
        } else if (checkClass.isAssignableFrom(parent.getClass())) {
            property.set(checkClass.cast(parent));
        } else {
            parent.parentProperty().addListener(this);
            addRecursive(parent.getParent());
        }
    }

}
