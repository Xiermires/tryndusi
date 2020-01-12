package org.tryndusi.model;

import java.util.List;

public interface PathSearch {

    List<Actor> computeBestPath(Actor source, Actor target);
}
