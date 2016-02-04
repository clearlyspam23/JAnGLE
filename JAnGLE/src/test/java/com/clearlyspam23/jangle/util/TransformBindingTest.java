package com.clearlyspam23.jangle.util;

import static org.junit.Assert.assertEquals;

import java.util.function.DoubleSupplier;

import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.transform.Affine;
import javafx.scene.transform.Transform;

import org.junit.Before;
import org.junit.Test;

public class TransformBindingTest {

    private interface DoubleBinding2Input {
        DoubleBinding apply(double input1, double input2);
    }

    private interface DoubleBinding3Input {
        DoubleBinding apply(double input1, double input2, double input3);
    }

    private interface PropBinding2Input {
        DoubleBinding apply(DoubleProperty input1, DoubleProperty input2);
    }

    private interface PropBinding3Input {
        DoubleBinding apply(DoubleProperty input1, DoubleProperty input2, DoubleProperty input3);
    }

    private Affine testMatrix;
    private ObjectProperty<Transform> testProperty;

    private TransformBinding binding;

    private double testXDouble;
    private double testYDouble;
    private double testZDouble;

    private DoubleProperty testXProp;
    private DoubleProperty testYProp;
    private DoubleProperty testZProp;

    private static final double epsilon = 1.0e-6;

    @Before
    public void setup() {
        testMatrix = new Affine();
        testMatrix.transform(1, 2, 3);
        testMatrix.appendRotation(50);
        testProperty = new SimpleObjectProperty<Transform>();
        testProperty.set(new Affine(1, 25, 67, 0, 4, 5, 6, 7, 8, 9, 10, 100));
        testXDouble = 2;
        testYDouble = 7;
        testZDouble = 4;
        testXProp = new SimpleDoubleProperty(2);
        testYProp = new SimpleDoubleProperty(7);
        testZProp = new SimpleDoubleProperty(4);
    }

    @Test
    public void basicBindingAffineShouldResultInSameMatrix() {
        binding = new TransformBinding();
        binding.bind(testMatrix);
        assertMatrixEquality(testMatrix, binding.get());
        testMatrix.appendScale(2, 3, 5);
        assertMatrixEquality(testMatrix, binding.get());
    }

    @Test
    public void constructorBindingAffineShouldResultInSameMatrix() {
        binding = new TransformBinding(testMatrix);
        assertMatrixEquality(testMatrix, binding.get());
        testMatrix.appendScale(2, 3, 5);
        assertMatrixEquality(testMatrix, binding.get());
    }

    @Test
    public void basicBindingPropertyShouldResultInSameMatrix() {
        binding = new TransformBinding();
        binding.bind(testProperty);
        assertMatrixEquality(testProperty.get(), binding.get());
        testMatrix.appendScale(2, 3, 5);
        assertMatrixEquality(testProperty.get(), binding.get());
    }

    @Test
    public void constructorBindingPropertyShouldResultInSameMatrix() {
        binding = new TransformBinding(testProperty);
        assertMatrixEquality(testProperty.get(), binding.get());
        testProperty.set(Transform.scale(50, 27));
        assertMatrixEquality(testProperty.get(), binding.get());
    }

    @Test
    public void unbindingAffineShouldNotRegisterNewChangesInOriginal() {
        binding = new TransformBinding(testMatrix);
        binding.unbind();
        Affine verifyMatrix = testMatrix.clone();
        testMatrix.appendScale(4, 5);
        assertMatrixEquality(verifyMatrix, binding.get());
    }

    @Test
    public void unbindingPropertyShouldNotRegisterNewChangesInOriginal() {
        binding = new TransformBinding(testProperty);
        binding.unbind();
        Transform verifyMatrix = testProperty.get().clone();
        testProperty.set(Transform.scale(6, 7));
        assertMatrixEquality(verifyMatrix, binding.get());
    }

    @Test
    public void rebindingFromAffineToPropertyShouldEqualProperty() {
        binding = new TransformBinding(testMatrix);
        binding.bind(testProperty);
        testMatrix.append(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12);
        assertMatrixEquality(testProperty.get(), binding.get());
    }

    @Test
    public void rebindingFromPropertyToAffineShouldEqualAffine() {
        binding = new TransformBinding(testProperty);
        binding.bind(testMatrix);
        testProperty.set(Transform.translate(5, 8));
        assertMatrixEquality(testMatrix, binding.get());
    }

    @Test
    public void xBinding2dWithPrimitivesShouldRegisterChangesToMatrix() {
        binding = new TransformBinding(testMatrix);
        binding2dWithPrimitivesBase(testMatrix::getMxx, testMatrix::getMxy, testMatrix::getTx,
                binding::getXBinding);
    }

    @Test
    public void xBinding2dWithBindingsShouldRegisterChangesToEither() {
        binding = new TransformBinding(testMatrix);
        binding2dWithBindingsBase(testMatrix::getMxx, testMatrix::getMxy, testMatrix::getTx,
                binding::getXBinding);
    }

    @Test
    public void xBinding3dWithPrimitivesShouldRegisterChangesToMatrix() {
        binding = new TransformBinding(testMatrix);
        binding3dWithPrimitivesBase(testMatrix::getMxx, testMatrix::getMxy, testMatrix::getMxz,
                testMatrix::getTx, binding::getXBinding);
    }

    @Test
    public void xBinding3dWithBindingsShouldRegisterChangesToEither() {
        binding = new TransformBinding(testMatrix);
        binding3dWithBindingsBase(testMatrix::getMxx, testMatrix::getMxy, testMatrix::getMxz,
                testMatrix::getTx, binding::getXBinding);
    }

    @Test
    public void xDeltaBinding2dWithPrimitivesShouldRegisterChangesToMatrix() {
        binding = new TransformBinding(testMatrix);
        binding2dWithPrimitivesBase(testMatrix::getMxx, testMatrix::getMxy, () -> 0,
                binding::getXDeltaBinding);
    }

    @Test
    public void xDeltaBinding2dWithBindingsShouldRegisterChangesToEither() {
        binding = new TransformBinding(testMatrix);
        binding2dWithBindingsBase(testMatrix::getMxx, testMatrix::getMxy, () -> 0,
                binding::getXDeltaBinding);
    }

    @Test
    public void xDeltaBinding3dWithPrimitivesShouldRegisterChangesToMatrix() {
        binding = new TransformBinding(testMatrix);
        binding3dWithPrimitivesBase(testMatrix::getMxx, testMatrix::getMxy, testMatrix::getMxz,
                () -> 0, binding::getXDeltaBinding);
    }

    @Test
    public void xDeltaBinding3dWithBindingsShouldRegisterChangesToEither() {
        binding = new TransformBinding(testMatrix);
        binding3dWithBindingsBase(testMatrix::getMxx, testMatrix::getMxy, testMatrix::getMxz,
                () -> 0, binding::getXDeltaBinding);
    }

    @Test
    public void yBinding2dWithPrimitivesShouldRegisterChangesToMatrix() {
        binding = new TransformBinding(testMatrix);
        binding2dWithPrimitivesBase(testMatrix::getMyx, testMatrix::getMyy, testMatrix::getTy,
                binding::getYBinding);
    }

    @Test
    public void yBinding2dWithBindingsShouldRegisterChangesToEither() {
        binding = new TransformBinding(testMatrix);
        binding2dWithBindingsBase(testMatrix::getMyx, testMatrix::getMyy, testMatrix::getTy,
                binding::getYBinding);
    }

    @Test
    public void yBinding3dWithPrimitivesShouldRegisterChangesToMatrix() {
        binding = new TransformBinding(testMatrix);
        binding3dWithPrimitivesBase(testMatrix::getMyx, testMatrix::getMyy, testMatrix::getMyz,
                testMatrix::getTy, binding::getYBinding);
    }

    @Test
    public void yBinding3dWithBindingsShouldRegisterChangesToEither() {
        binding = new TransformBinding(testMatrix);
        binding3dWithBindingsBase(testMatrix::getMyx, testMatrix::getMyy, testMatrix::getMyz,
                testMatrix::getTy, binding::getYBinding);
    }

    @Test
    public void yDeltaBinding2dWithPrimitivesShouldRegisterChangesToMatrix() {
        binding = new TransformBinding(testMatrix);
        binding2dWithPrimitivesBase(testMatrix::getMyx, testMatrix::getMyy, () -> 0,
                binding::getYDeltaBinding);
    }

    @Test
    public void yDeltaBinding2dWithBindingsShouldRegisterChangesToEither() {
        binding = new TransformBinding(testMatrix);
        binding2dWithBindingsBase(testMatrix::getMyx, testMatrix::getMyy, () -> 0,
                binding::getYDeltaBinding);
    }

    @Test
    public void yDeltaBinding3dWithPrimitivesShouldRegisterChangesToMatrix() {
        binding = new TransformBinding(testMatrix);
        binding3dWithPrimitivesBase(testMatrix::getMyx, testMatrix::getMyy, testMatrix::getMyz,
                () -> 0, binding::getYDeltaBinding);
    }

    @Test
    public void yDeltaBinding3dWithBindingsShouldRegisterChangesToEither() {
        binding = new TransformBinding(testMatrix);
        binding3dWithBindingsBase(testMatrix::getMyx, testMatrix::getMyy, testMatrix::getMyz,
                () -> 0, binding::getYDeltaBinding);
    }

    @Test
    public void zBinding3dWithPrimitivesShouldRegisterChangesToMatrix() {
        binding = new TransformBinding(testMatrix);
        binding3dWithPrimitivesBase(testMatrix::getMzx, testMatrix::getMzy, testMatrix::getMzz,
                testMatrix::getTz, binding::getZBinding);
    }

    @Test
    public void zBinding3dWithBindingsShouldRegisterChangesToEither() {
        binding = new TransformBinding(testMatrix);
        binding3dWithBindingsBase(testMatrix::getMzx, testMatrix::getMzy, testMatrix::getMzz,
                testMatrix::getTz, binding::getZBinding);
    }

    @Test
    public void zDeltaBinding3dWithPrimitivesShouldRegisterChangesToMatrix() {
        binding = new TransformBinding(testMatrix);
        binding3dWithPrimitivesBase(testMatrix::getMzx, testMatrix::getMzy, testMatrix::getMzz,
                () -> 0, binding::getZDeltaBinding);
    }

    @Test
    public void zDeltaBinding3dWithBindingsShouldRegisterChangesToEither() {
        binding = new TransformBinding(testMatrix);
        binding3dWithBindingsBase(testMatrix::getMzx, testMatrix::getMzy, testMatrix::getMzz,
                () -> 0, binding::getZDeltaBinding);
    }

    private void binding2dWithPrimitivesBase(DoubleSupplier assertX, DoubleSupplier assertY,
            DoubleSupplier assertT, DoubleBinding2Input bindingSupplier) {
        binding = new TransformBinding(testMatrix);
        DoubleBinding binding = bindingSupplier.apply(testXDouble, testYDouble);
        assertDouble(assertX.getAsDouble(), assertY.getAsDouble(), assertT.getAsDouble(), binding);
        testMatrix.append(1, 5, 3, 4, 2, 5, 4, 6, 7, 6, 5, 8);
        assertDouble(assertX.getAsDouble(), assertY.getAsDouble(), assertT.getAsDouble(), binding);
    }

    private void binding3dWithPrimitivesBase(DoubleSupplier assertX, DoubleSupplier assertY,
            DoubleSupplier assertZ, DoubleSupplier assertT, DoubleBinding3Input bindingSupplier) {
        binding = new TransformBinding(testMatrix);
        DoubleBinding binding = bindingSupplier.apply(testXDouble, testYDouble, testZDouble);
        assertDouble(assertX.getAsDouble(), assertY.getAsDouble(), assertZ.getAsDouble(),
                assertT.getAsDouble(), binding);
        testMatrix.append(1, 5, 3, 4, 2, 5, 4, 6, 7, 6, 5, 8);
        assertDouble(assertX.getAsDouble(), assertY.getAsDouble(), assertZ.getAsDouble(),
                assertT.getAsDouble(), binding);
    }

    private void binding2dWithBindingsBase(DoubleSupplier assertX, DoubleSupplier assertY,
            DoubleSupplier assertT, PropBinding2Input bindingSupplier) {
        binding = new TransformBinding(testMatrix);
        DoubleBinding binding = bindingSupplier.apply(testXProp, testYProp);
        assertProp(assertX.getAsDouble(), assertY.getAsDouble(), assertT.getAsDouble(), binding);
        testMatrix.append(1, 5, 3, 4, 2, 5, 4, 6, 7, 6, 5, 8);
        assertProp(assertX.getAsDouble(), assertY.getAsDouble(), assertT.getAsDouble(), binding);
        testXProp.set(10);
        testYProp.set(3);
        assertProp(assertX.getAsDouble(), assertY.getAsDouble(), assertT.getAsDouble(), binding);
    }

    private void binding3dWithBindingsBase(DoubleSupplier assertX, DoubleSupplier assertY,
            DoubleSupplier assertZ, DoubleSupplier assertT, PropBinding3Input bindingSupplier) {
        binding = new TransformBinding(testMatrix);
        DoubleBinding binding = bindingSupplier.apply(testXProp, testYProp, testZProp);
        assertProp(assertX.getAsDouble(), assertY.getAsDouble(), assertZ.getAsDouble(),
                assertT.getAsDouble(), binding);
        testMatrix.append(1, 5, 3, 4, 2, 5, 4, 6, 7, 6, 5, 8);
        assertProp(assertX.getAsDouble(), assertY.getAsDouble(), assertZ.getAsDouble(),
                assertT.getAsDouble(), binding);
        testXProp.set(10);
        testYProp.set(3);
        assertProp(assertX.getAsDouble(), assertY.getAsDouble(), assertZ.getAsDouble(),
                assertT.getAsDouble(), binding);
    }

    private void assertDouble(double x, double y, double transform, DoubleBinding binding) {
        assertEquals(testXDouble * x + testYDouble * y + transform, binding.get(), epsilon);
    }

    private void assertDouble(double x, double y, double z, double transform, DoubleBinding binding) {
        assertEquals(testXDouble * x + testYDouble * y + +testZDouble * z + transform,
                binding.get(), epsilon);
    }

    private void assertProp(double x, double y, double transform, DoubleBinding binding) {
        assertEquals(testXProp.get() * x + testYProp.get() * y + transform, binding.get(), epsilon);
    }

    private void assertProp(double x, double y, double z, double transform, DoubleBinding binding) {
        assertEquals(testXProp.get() * x + testYProp.get() * y + +testZProp.get() * z + transform,
                binding.get(), epsilon);
    }

    private void assertMatrixEquality(Transform t1, Transform t2) {
        assertEquals(t1.getMxx(), t2.getMxx(), epsilon);
        assertEquals(t1.getMxy(), t2.getMxy(), epsilon);
        assertEquals(t1.getMxz(), t2.getMxz(), epsilon);
        assertEquals(t1.getTx(), t2.getTx(), epsilon);
        assertEquals(t1.getMyx(), t2.getMyx(), epsilon);
        assertEquals(t1.getMyy(), t2.getMyy(), epsilon);
        assertEquals(t1.getMyz(), t2.getMyz(), epsilon);
        assertEquals(t1.getTy(), t2.getTy(), epsilon);
        assertEquals(t1.getMzx(), t2.getMzx(), epsilon);
        assertEquals(t1.getMzy(), t2.getMzy(), epsilon);
        assertEquals(t1.getMzz(), t2.getMzz(), epsilon);
        assertEquals(t1.getTz(), t2.getTz(), epsilon);
    }

}
