package org.russell.patterns.visitor;

public record Subtraction(double minuend, double subtrahend) implements Operation {

    @Override
    public void accept(final OperationVisitor visitor) {
        visitor.visitSubtraction(this);
    }
}
