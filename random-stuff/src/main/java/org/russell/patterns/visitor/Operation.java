package org.russell.patterns.visitor;

public sealed interface Operation permits Addition, Sine, Subtraction {

    void accept(final OperationVisitor visitor);
}
