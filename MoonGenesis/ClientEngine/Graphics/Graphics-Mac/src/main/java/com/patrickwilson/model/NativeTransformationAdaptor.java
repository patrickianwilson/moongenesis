package com.patrickwilson.model;

/**
 * Created by pwilson on 2/16/15.
 * Wrapper class to assist in constructing rotation transformation
 */
public class NativeTransformationAdaptor {

    /**
     * build a graphics primitive representation.
     * @param angle the angle of rotation.
     * @param axisX the 'X' component of the rotation axis
     * @param axisY the 'Y' component of the rotation axis
     * @param axisZ the 'Z' component of the rotation axis
     * @return
     */
    public float[] buildPrimitiveRotationTransformation(float angle, float axisX, float axisY, float axisZ) {
        return new float[] {angle, axisX, axisY, axisZ};
    }

}
