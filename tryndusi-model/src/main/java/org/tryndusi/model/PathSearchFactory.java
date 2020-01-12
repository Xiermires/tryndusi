package org.tryndusi.model;

import java.util.Collection;
import java.util.function.BiFunction;
import java.util.function.Function;

public interface PathSearchFactory {

    PathSearch create(Function<Actor, Collection<Actor>> adjacencyOf, BiFunction<Actor, Actor, Integer> edgeWeight);
}
