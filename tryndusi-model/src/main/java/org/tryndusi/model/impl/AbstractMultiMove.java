package org.tryndusi.model.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.tryndusi.model.Actor;
import org.tryndusi.model.Move;

public abstract class AbstractMultiMove extends AbstractMove implements Iterable<Move> {

	private List<Move> moves = new ArrayList<Move>();

	protected AbstractMultiMove(Actor source, Actor target, List<Actor> shift) {
		super(source, target);
		if (shift.isEmpty()) {
			moves.add(new ImmutableMove(source, target));
		} else {
			moves.add(new ImmutableMove(source, shift.get(0)));
			for (int i = 1; i < shift.size(); i++) {
				moves.add(new ImmutableMove(shift.get(i - 1), shift.get(i)));
			}
			moves.add(new ImmutableMove(shift.get(shift.size() - 1), target));
		}
	}

	@Override
	public Iterator<Move> iterator() {
		return moves.iterator();
	}

	@Override
	public String toString() {
		return moves.toString();
	}
}
