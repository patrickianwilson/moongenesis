package com.patrickwilson.moongenesis.resource.types;

import java.io.Serializable;

/**
 * Created by pwilson on 2/16/15.
 */
public class SceneNode implements Serializable {

    private String name;

    //either point to a model file
    private String modelResourcePath;

    //or define the data.  This suggests it is a leaf node.
    public Point3D[] pointIndex;

    public Face3D[] faces;

    private float[] positionTransformation = new float[3];
    private float[] rotationTransformation = new float[4];
    private float[] scaleTransformation = new float[] {1.0f, 1.0f, 1.0f};

    private SceneNode[] children;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModelResourcePath() {
        return modelResourcePath;
    }

    public void setModelResourcePath(String modelResourcePath) {
        this.modelResourcePath = modelResourcePath;
    }

    public Point3D[] getPointIndex() {
        return pointIndex;
    }

    public void setPointIndex(Point3D[] pointIndex) {
        this.pointIndex = pointIndex;
    }

    public Face3D[] getFaces() {
        return faces;
    }

    public void setFaces(Face3D[] faces) {
        this.faces = faces;
    }

    public float[] getPositionTransformation() {
        return positionTransformation;
    }

    public void setPositionTransformation(float[] positionTransformation) {
        this.positionTransformation = positionTransformation;
    }

    public float[] getRotationTransformation() {
        return rotationTransformation;
    }

    public void setRotationTransformation(float[] rotationTransformation) {
        this.rotationTransformation = rotationTransformation;
    }

    public float[] getScaleTransformation() {
        return scaleTransformation;
    }

    public void setScaleTransformation(float[] scaleTransformation) {
        this.scaleTransformation = scaleTransformation;
    }

    public SceneNode[] getChildren() {
        return children;
    }

    public void setChildren(SceneNode[] children) {
        this.children = children;
    }
}
