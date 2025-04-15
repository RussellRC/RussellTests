package org.russell.patterns.visitor;

import com.google.common.collect.ImmutableList;

import java.util.List;

public final class Addition implements Operation {

    private final List<Double> addends;

    public Addition(final List<Double> addends) {
        this.addends = ImmutableList.copyOf(addends);
    }

    public List<Double> getAddends() {
        return addends;
    }

    @Override
    public void accept(final OperationVisitor visitor) {
        visitor.visitAddition(this);
    }
}
