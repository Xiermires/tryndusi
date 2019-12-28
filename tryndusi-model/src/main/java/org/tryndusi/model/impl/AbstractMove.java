package org.tryndusi.model.impl;

import org.tryndusi.model.Actor;
import org.tryndusi.model.Move;

public abstract class AbstractMove implements Move {

	private Actor source;
	private Actor target;

	protected AbstractMove(Actor source, Actor target) {
		this.source = source;
		this.target = target;
	}

	@Override
	public Actor getSource() {
		return source;
	}

	@Override
	public Actor getTarget() {
		return target;
	}

	@Override
	public String toString() {
		return "[" + source + " -> " + target + "]";
	}
}