package org.russell.patterns.visitor;

public sealed interface OperationVisitor permits ArithmeticVisitor, TrigonometryVisitor {

    void visitAddition(Addition addition);

    void visitSubtraction(Subtraction subtraction);

    void visitSine(Sine cosine);
}
