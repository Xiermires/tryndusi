package org.tryndusi.model.impl;

import org.tryndusi.model.Actor;
import org.tryndusi.model.geometry.Point;

public class ImmutableMove extends AbstractMove {

    public ImmutableMove(Actor source, Actor target) {
        super(source, target);
    }

    @Override
    public Point getCurrentPosition() {
        throw new UnsupportedOperationException("not supported.");
    }

    @Override
    public void update(Point position) {
        throw new UnsupportedOperationException("not supported.");
    }
}
