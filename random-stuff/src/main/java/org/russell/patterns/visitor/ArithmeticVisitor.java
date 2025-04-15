package org.russell.patterns.visitor;

public final class ArithmeticVisitor implements OperationVisitor {

  private double result;

  @Override
  public void visitAddition(final Addition addition) {
    result = addition.getAddends().stream().mapToDouble(Double::doubleValue).sum();
  }

  @Override
  public void visitSubtraction(Subtraction subtraction) {
      result = subtraction.minuend() - subtraction.subtrahend();
  }

  @Override
  public void visitSine(Sine sine) {
    throw new RuntimeException("Invalid operation");
  }
}
