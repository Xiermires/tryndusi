package org.tryndusi.model.impl;

import java.util.List;

import org.tryndusi.model.Actor;
import org.tryndusi.model.geometry.Point;

public class Path extends AbstractMove {

	private Point currentPosition = null;

	public Path(Actor source, Actor target, List<Actor> goesThrough) {
		super(source, target);
		currentPosition = source.getActorShape().center();
	}

	@Override
	public Point getCurrentPosition() {
		return currentPosition;
	}

	@Override
	public void update(Point position) {
		currentPosition = position;
	}
}
