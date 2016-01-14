package com.clearlyspam23.jangle.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.shape.Rectangle;

import org.junit.Before;
import org.junit.Test;

public class ParentCrawlerTest {

    private class TestGroup extends Group {

    }

    private ObjectProperty<TestGroup> prop;
    private Node node;
    private TestGroup test;

    @Before
    public void setup() {
        node = new Rectangle();
        prop = new SimpleObjectProperty<>();
        node.parentProperty().addListener(new ParentCrawler<>(prop, TestGroup.class));
        test = new TestGroup();
        // sanity check
        assertNotEquals(test, prop.get());
    }

    @Test
    public void directParentShouldBeFound() {
        test.getChildren().add(node);
        assertEquals(test, prop.get());
    }

    @Test
    public void distantParentShouldBeFoundChildAddedLast() {
        Group group = new Group();
        test.getChildren().add(group);
        group.getChildren().add(node);
        assertEquals(test, prop.get());
    }

    @Test
    public void distantParentShouldBeFoundChildAddedFirst() {
        Group group = new Group();
        group.getChildren().add(node);
        test.getChildren().add(group);
        assertEquals(test, prop.get());
    }

    @Test
    public void noParentShouldBeFound() {
        Group group = new Group();
        group.getChildren().add(node);
        assertEquals(null, prop.get());
    }

    @Test
    public void afterUpdateParentShouldNoLongerBeFound() {
        Group group = new Group();
        test.getChildren().add(group);
        group.getChildren().add(node);
        assertEquals(test, prop.get());
        test.getChildren().remove(group);
        assertEquals(null, prop.get());
    }

    @Test
    public void updatedParentShouldBeFound() {
        TestGroup decoy = new TestGroup();
        Group group = new Group();
        decoy.getChildren().add(group);
        group.getChildren().add(node);
        decoy.getChildren().remove(group);
        test.getChildren().add(group);
        assertEquals(test, prop.get());
    }

    @Test
    public void updatedDeepParentShouldBeFoundAndNotDecoy() {
        TestGroup decoy = new TestGroup();
        Group group1 = new Group();
        Group group2 = new Group();
        Group group3 = new Group();
        decoy.getChildren().add(group3);
        group3.getChildren().add(group2);
        group2.getChildren().add(group1);
        group1.getChildren().add(node);
        assertEquals(decoy, prop.get());
        group2.getChildren().remove(group1);
        test.getChildren().add(group1);
        TestGroup secondDecoy = new TestGroup();
        decoy.getChildren().remove(group3);
        secondDecoy.getChildren().add(group3);
        assertEquals(test, prop.get());
    }
}
