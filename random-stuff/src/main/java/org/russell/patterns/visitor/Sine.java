package org.russell.patterns.visitor;

public record Sine(double oppositeSide, double hypotenuse) implements Operation {

    @Override
    public void accept(final OperationVisitor visitor) {
        visitor.visitSine(this);
    }
}
