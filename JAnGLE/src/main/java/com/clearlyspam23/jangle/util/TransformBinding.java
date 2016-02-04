package com.clearlyspam23.jangle.util;

import java.util.Optional;

import javafx.beans.binding.DoubleBinding;
import javafx.beans.binding.DoubleExpression;
import javafx.beans.binding.ObjectBinding;
import javafx.beans.binding.ObjectExpression;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.transform.Affine;
import javafx.scene.transform.Transform;
import javafx.scene.transform.TransformChangedEvent;

public class TransformBinding extends ObjectBinding<Affine> {

    private Affine affine = new Affine();
    private Optional<Runnable> unbindFunc = Optional.empty();

    public TransformBinding() {

    }

    public TransformBinding(ObjectExpression<? extends Transform> transformProperty) {
        bind(transformProperty);
    }

    public TransformBinding(Affine affine) {
        bind(affine);
    }

    public void bind(ObjectExpression<? extends Transform> transformProperty) {
        unbind();
        ChangeListener<Transform> listener = new ChangeListener<Transform>() {

            @Override
            public void changed(ObservableValue<? extends Transform> observableValue,
                    Transform oldVal, Transform newVal) {
                affine.setToTransform(newVal);
            }

        };
        transformProperty.addListener(listener);
        affine.setToTransform(transformProperty.get());
        unbindFunc = Optional.of(() -> transformProperty.removeListener(listener));
    }

    public void bind(Transform transform) {
        unbind();
        EventHandler<TransformChangedEvent> handler = new EventHandler<TransformChangedEvent>() {

            @Override
            public void handle(TransformChangedEvent arg0) {
                affine.setToTransform(transform);
            }

        };
        transform.addEventHandler(TransformChangedEvent.TRANSFORM_CHANGED, handler);
        affine.setToTransform(transform);
        unbindFunc =
                Optional.of(() -> transform.removeEventHandler(
                        TransformChangedEvent.TRANSFORM_CHANGED, handler));
    }

    public void unbind() {
        unbindFunc.ifPresent(func -> func.run());
        unbindFunc = Optional.empty();
    }

    @Override
    protected Affine computeValue() {
        return affine;
    }

    public DoubleBinding getXBinding(DoubleExpression x, DoubleExpression y) {
        return getXDeltaBinding(x, y).add(affine.txProperty());
    }

    public DoubleBinding getXBinding(DoubleExpression x, DoubleExpression y, DoubleExpression z) {
        return getXDeltaBinding(x, y, z).add(affine.txProperty());
    }

    public DoubleBinding getYBinding(DoubleExpression x, DoubleExpression y) {
        return getYDeltaBinding(x, y).add(affine.tyProperty());
    }

    public DoubleBinding getYBinding(DoubleExpression x, DoubleExpression y, DoubleExpression z) {
        return getYDeltaBinding(x, y, z).add(affine.tyProperty());
    }

    public DoubleBinding getZBinding(DoubleExpression x, DoubleExpression y, DoubleExpression z) {
        return getZDeltaBinding(x, y, z).add(affine.tzProperty());
    }

    public DoubleBinding getXDeltaBinding(DoubleExpression x, DoubleExpression y) {
        return affine.mxxProperty().multiply(x).add(affine.mxyProperty().multiply(y));
    }

    public DoubleBinding getXDeltaBinding(DoubleExpression x, DoubleExpression y, DoubleExpression z) {
        return getXDeltaBinding(x, y).add(affine.mxzProperty().multiply(z));
    }

    public DoubleBinding getYDeltaBinding(DoubleExpression x, DoubleExpression y) {
        return affine.myxProperty().multiply(x).add(affine.myyProperty().multiply(y));
    }

    public DoubleBinding getYDeltaBinding(DoubleExpression x, DoubleExpression y, DoubleExpression z) {
        return getYDeltaBinding(x, y).add(affine.myzProperty().multiply(z));
    }

    public DoubleBinding getZDeltaBinding(DoubleExpression x, DoubleExpression y, DoubleExpression z) {
        return affine.mzxProperty().multiply(x).add(affine.mzyProperty().multiply(y))
                .add(affine.mzzProperty().multiply(z));
    }

    public DoubleBinding getXBinding(double x, double y) {
        return getXDeltaBinding(x, y).add(affine.txProperty());
    }

    public DoubleBinding getXBinding(double x, double y, double z) {
        return getXDeltaBinding(x, y, z).add(affine.txProperty());
    }

    public DoubleBinding getYBinding(double x, double y) {
        return getYDeltaBinding(x, y).add(affine.tyProperty());
    }

    public DoubleBinding getYBinding(double x, double y, double z) {
        return getYDeltaBinding(x, y, z).add(affine.tyProperty());
    }

    public DoubleBinding getZBinding(double x, double y, double z) {
        return getZDeltaBinding(x, y, z).add(affine.tzProperty());
    }

    public DoubleBinding getXDeltaBinding(double x, double y) {
        return affine.mxxProperty().multiply(x).add(affine.mxyProperty().multiply(y));
    }

    public DoubleBinding getXDeltaBinding(double x, double y, double z) {
        return getXDeltaBinding(x, y).add(affine.mxzProperty().multiply(z));
    }

    public DoubleBinding getYDeltaBinding(double x, double y) {
        return affine.myxProperty().multiply(x).add(affine.myyProperty().multiply(y));
    }

    public DoubleBinding getYDeltaBinding(double x, double y, double z) {
        return getYDeltaBinding(x, y).add(affine.myzProperty().multiply(z));
    }

    public DoubleBinding getZDeltaBinding(double x, double y, double z) {
        return affine.mzxProperty().multiply(x).add(affine.mzyProperty().multiply(y))
                .add(affine.mzzProperty().multiply(z));
    }

}
