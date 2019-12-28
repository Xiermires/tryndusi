package org.tryndusi.model.impl;

import java.util.List;

import org.tryndusi.model.Actor;
import org.tryndusi.model.Move;
import org.tryndusi.model.geometry.Point;

public class Route extends AbstractMultiMove implements Iterable<Move> {

	private Point currentPosition = null;

	public Route(Actor source, Actor target, List<Actor> shift) {
		super(source, target, shift);
		this.currentPosition = source.getBoundingBox().center();
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
