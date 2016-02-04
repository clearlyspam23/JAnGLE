package com.clearlyspam23.jangle.entity.gui;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Translate;

import com.clearlyspam23.jangle.util.ParentCrawler;

public class EntityNode extends Rectangle implements Entity {

    private final ObjectProperty<EntityLayerNode> entityLayer = new SimpleObjectProperty<>();
    private Translate translation = new Translate();
    private Scale scale = new Scale();
    private Rotate rotation = new Rotate();

    public EntityNode(double x, double y, double width, double height, Image image) {
        super(width, height);
        getTransforms().addAll(translation, rotation, scale);
        parentProperty().addListener(new ParentCrawler<>(entityLayer, EntityLayerNode.class));
        this.xProperty().bind(this.widthProperty().divide(-2));
        this.yProperty().bind(this.heightProperty().divide(-2));
        translation.setX(x);
        translation.setY(y);
        setFill(new ImagePattern(image));
        getStyleClass().add("entity-node");
    }

    public void setLocation(double x, double y) {
        translation.setX(x);
        translation.setY(y);
    }

    public boolean isSelected() {
        if (entityLayer.get() != null && entityLayer.get().getSelectedNode().isPresent()) {
            return entityLayer.get().getSelectedNode().get() == this;
        }
        return false;
    }

    public void setEntityX(double x) {
        translation.setX(x);
    }

    public void setEntityY(double y) {
        translation.setY(y);
    }

    public double getEntityX() {
        return translation.getX();
    }

    public double getEntityY() {
        return translation.getY();
    }

    public void setEntityRotation(double angle) {
        rotation.setAngle(angle);
    }

    public double getEntityRotation() {
        return rotation.getAngle();
    }

    @Override
    public Node getNode() {
        return this;
    }

    @Override
    public double getEntityWidth() {
        return getWidth();
    }

    @Override
    public double getEntityHeight() {
        return getHeight();
    }

    @Override
    public void setEntityWidth(double width) {
        setWidth(width);
    }

    @Override
    public void setEntityHeight(double height) {
        setHeight(height);
    }
}
