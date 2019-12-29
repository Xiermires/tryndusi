package org.tryndusi.model;

import org.tryndusi.model.geometry.BoundingBox;

public interface Actor {

	BoundingBox getBoundingBox();

	ActorState update(Move move);

	ActorState getState();

	void setState(ActorState state);
}
