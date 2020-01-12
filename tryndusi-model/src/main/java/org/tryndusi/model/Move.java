package org.tryndusi.model;

import org.tryndusi.model.geometry.Point;

public interface Move {

    Actor getSource();

    Actor getTarget();

    Point getCurrentPosition();

    void update(Point position);
}
