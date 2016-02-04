package com.clearlyspam23.jangle.entity.gui;

import javafx.scene.Node;

public interface Entity {

    void setEntityX(double x);

    void setEntityY(double y);

    double getEntityX();

    double getEntityY();

    void setEntityRotation(double angle);

    double getEntityRotation();

    Node getNode();

    double getEntityWidth();

    double getEntityHeight();

    void setEntityWidth(double width);

    void setEntityHeight(double height);

}
