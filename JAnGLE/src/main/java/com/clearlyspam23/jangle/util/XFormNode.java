package com.clearlyspam23.jangle.util;

import javafx.scene.Group;
import javafx.scene.transform.Affine;

public class XFormNode extends Group {

    private Affine affine = new Affine();

    public XFormNode() {
        getTransforms().addAll(affine);
    }

    public Affine getSnapshot() {
        return affine.clone();
    }

    public Affine getAffine() {
        return affine;
    }

}
