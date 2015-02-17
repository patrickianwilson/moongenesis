package com.patrickwilson.moongenesis.resource.types;

/**
 * Created by pwilson on 2/16/15.
 */
public interface ModelNode {

    public boolean hasChildren();

    public ModelNode[] getChildren();

    public float[] getPositionTransformation();
    public float[] getRotationTransformation();
    public float[] getScaleTransformation();
}
