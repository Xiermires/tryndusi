package org.tryndusi.model.render;

import java.util.Arrays;

import org.tryndusi.model.Actor;
import org.tryndusi.model.Move;
import org.tryndusi.model.geometry.BoundingBox;
import org.tryndusi.model.geometry.Point;
import org.tryndusi.model.impl.Moves;
import org.tryndusi.model.render.svg.SVGBuilder;
import org.tryndusi.model.render.svg.SVGCircleBuilder;
import org.tryndusi.model.render.svg.SVGLineBuilder;
import org.tryndusi.model.render.svg.SVGRectangleBuilder;

public abstract class AbstractRender<T> implements Render<T> {

	private final SVGBuilder builder = new SVGBuilder();
	private final boolean drawConnections;

	protected AbstractRender(boolean drawConnections) {
		this.drawConnections = drawConnections;
	}

	@Override
	public byte[] draw(T drawable) {
		final Iterable<Move> moves = getMoves(drawable);
		for (Move m : moves) {

			final Actor source = m.getSource();
			final Actor target = m.getTarget();
			for (Actor actor : Arrays.asList(source, target)) {
				final String color = actor.getState().color();
				final BoundingBox box = actor.getBoundingBox();
				if (box.isPoint()) {
					// draw points as circles of radius 3 pixel
					drawBoxAsCircle(box.getIntMinX(), box.getIntMinY(), 3).fill(color).add();
				} else if (box.isLine()) {
					drawBoxAsLine(box).fill(color).add();
				} else {
					drawBox(box).fill(color).add();
				}
			}

			if (drawConnections) {
				drawConnection(source.getBoundingBox().center(), target.getBoundingBox().center()).add();
			}
		}

		int[] offsets = getDrawingArea(moves);
		builder.expand(offsets[0], offsets[1]);
		return builder.build().getBytes();
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

	// TODO draw connections from source closer point to target
	// TODO draw connections as directed arrows
	protected SVGLineBuilder drawConnection(Point p1, Point p2) {
		return builder.line(p1.getIntX(), p1.getIntY(), p2.getIntX(), p2.getIntY());
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
