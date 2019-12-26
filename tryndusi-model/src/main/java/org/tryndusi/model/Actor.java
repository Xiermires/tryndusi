package org.tryndusi.model;

import org.tryndusi.model.geometry.BoundingBox;

public interface Actor {

	BoundingBox getActorShape();

	ActorState update(Move move);
}
