package org.tryndusi.model.render;

import org.tryndusi.model.Actor;
import org.tryndusi.model.Move;
import org.tryndusi.model.geometry.BoundingBox;
import org.tryndusi.model.geometry.Point;
import org.tryndusi.model.geometry.Segment;
import org.tryndusi.model.impl.Moves;
import org.tryndusi.model.render.svg.SVGBuilder;
import org.tryndusi.model.render.svg.SVGCircleBuilder;
import org.tryndusi.model.render.svg.SVGLineBuilder;
import org.tryndusi.model.render.svg.SVGRectangleBuilder;

public abstract class AbstractRender<T> implements Render<T> {

	private final SVGBuilder builder = new SVGBuilder();
	private final boolean drawConnections;
	private boolean useDottedLines;

	protected AbstractRender(boolean drawConnections) {
		this.drawConnections = drawConnections;
	}

	protected void useDottedLines(boolean enabled) {
		this.useDottedLines = enabled;
	}

	@Override
	public byte[] draw(T drawable) {
		final Iterable<Move> moves = getMoves(drawable);
		for (Move m : moves) {
			final BoundingBox sourceBox = drawActorBox(m.getSource());
			final BoundingBox targetBox = drawActorBox(m.getTarget());
			if (drawConnections) {
				drawConnection(sourceBox, targetBox).add();
			}
		}

		int[] offsets = getDrawingArea(moves);
		builder.expand(offsets[0], offsets[1]);
		return builder.build().getBytes();
	}

	private BoundingBox drawActorBox(Actor actor) {
		final String color = actor.getState().color();
		final BoundingBox box = BoundingBox.empty();
		box.union(actor.getBoundingBox());
		if (box.isPoint()) {
			// draw points as circles of radius 3 pixel
			drawBoxAsCircle(box.getIntMinX(), box.getIntMinY(), 3).fill(color).add();

		} else if (box.isLine()) {
			drawBoxAsLine(box).fill(color).add();
		} else {
			drawBox(box).fill(color).add();
		}
		return box;
	}

	protected abstract Iterable<Move> getMoves(T drawable);

	protected SVGCircleBuilder drawBoxAsCircle(int cx, int cy, int r) {
		return builder.circle(cx, cy, r);
	}

	protected SVGLineBuilder drawBoxAsLine(BoundingBox box) {
		return builder.line(box.getIntMinX(), box.getIntMinY(), box.getIntMaxX(), box.getIntMaxY());
	}

	protected SVGRectangleBuilder drawBox(BoundingBox box) {
		return builder.rectangle(box.getIntMinX(), box.getIntMinY(), box.getIntWidth(), box.getIntHeight());
	}

	// TODO draw connections as directed arrows
	protected SVGLineBuilder drawConnection(BoundingBox sourceBox, BoundingBox targetBox) {
		final Segment centerToCenter = new Segment(sourceBox.center(), targetBox.center());

		final Point sourceOuter = sourceBox.closestIntersection(centerToCenter, targetBox.center());
		final Point targetOuter = targetBox.closestIntersection(centerToCenter, sourceBox.center());
		final SVGLineBuilder line = builder.line(sourceOuter.getIntX(), sourceOuter.getIntY(), targetOuter.getIntX(),
				targetOuter.getIntY());
		// TODO default values (radius, dash-array, etc. should be globally
		// configurable)
		return useDottedLines ? line.strokeDashArray(5) : line;
	}

	/**
	 * Returns the [width, height] of a minimal bounding box surrounding all moves,
	 * plus an additional offset of 10 (for border items visibility).#
	 * <p>
	 * Items placed on 0, are not correctly rendered. <br>
	 * TODO consider moving all moves some pixels to the right
	 */
	protected int[] getDrawingArea(Iterable<Move> moves) {
		final BoundingBox containment = Moves.newBoxContaining(moves);
		final int widthWithOffset = containment.getIntMinX() + containment.getIntWidth();
		final int heightWithOffset = containment.getIntMinY() + containment.getIntHeight();
		return new int[] { widthWithOffset + 10, heightWithOffset + 10 };
	}
}
