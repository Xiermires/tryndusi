package org.tryndusi.model.impl;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.tryndusi.model.Actor;
import org.tryndusi.model.Move;
import org.tryndusi.model.geometry.BoundingBox;

import com.google.common.graph.EndpointPair;

public class Moves {

    public static Route newRoute(List<Actor> actors) {
        int last = actors.size() - 1;
        return new Route(actors.get(0), actors.get(last), actors.subList(1, last));
    }

    public static BoundingBox newBoxContaining(Iterable<Move> moves) {
        final Set<BoundingBox> actorBoxes = new HashSet<>();
        for (Move move : moves) {
            actorBoxes.add(move.getSource().getBoundingBox());
            actorBoxes.add(move.getTarget().getBoundingBox());
        }
        return BoundingBox.union(actorBoxes);
    }

    public static Set<Move> defined(Layout layout) {
        final Set<Move> moves = new LinkedHashSet<>();
        for (EndpointPair<Actor> edge : layout.edges()) {
            moves.add(new ImmutableMove(edge.source(), edge.target()));
        }
        return moves;
    }
}
