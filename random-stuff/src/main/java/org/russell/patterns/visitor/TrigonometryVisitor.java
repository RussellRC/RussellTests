package org.russell.patterns.visitor;

public non-sealed class TrigonometryVisitor implements OperationVisitor {

    private double result;

    @Override
    public void visitAddition(Addition addition) {
        // do nothing
    }

    @Override
    public void visitSubtraction(Subtraction subtraction) {
        // do nothing
    }

    @Override
    public void visitSine(Sine sine) {
        result = sine.oppositeSide() / sine.hypotenuse();
    }
}
