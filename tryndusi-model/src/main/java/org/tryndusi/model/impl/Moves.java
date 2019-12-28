package org.tryndusi.model.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.tryndusi.model.Actor;
import org.tryndusi.model.Move;
import org.tryndusi.model.geometry.BoundingBox;

public class Moves {

	public static Route newRoute(List<Actor> actors) {
		int last = actors.size() - 1;
		return new Route(actors.get(0), actors.get(last), actors.subList(1, last));
	}

	public static BoundingBox newBoxContaining(Route route) {
		final Set<BoundingBox> actorBoxes = new HashSet<>();
		for (Move move : route) {
			actorBoxes.add(move.getSource().getBoundingBox());
			actorBoxes.add(move.getTarget().getBoundingBox());
		}
		return BoundingBox.union(actorBoxes);
	}
}
