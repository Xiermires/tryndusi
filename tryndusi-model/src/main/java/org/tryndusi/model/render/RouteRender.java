package org.tryndusi.model.render;

import java.util.Arrays;

import org.tryndusi.model.Move;
import org.tryndusi.model.geometry.BoundingBox;
import org.tryndusi.model.geometry.Point;
import org.tryndusi.model.impl.Moves;
import org.tryndusi.model.impl.Route;
import org.tryndusi.model.render.svg.SVGBuilder;
import org.tryndusi.model.render.svg.SVGLineBuilder;
import org.tryndusi.model.render.svg.SVGRectangleBuilder;

public class RouteRender implements Render<Route> {

	private boolean routeOnly;

	public RouteRender(boolean routeOnly) {
		this.routeOnly = routeOnly;
	}

	@Override
	public byte[] draw(Route drawable) {
		final BoundingBox containment = Moves.newBoxContaining(drawable);
		final SVGBuilder svgBuilder = new SVGBuilder();
		for (Move m : drawable) {
			BoundingBox source = m.getSource().getBoundingBox();
			BoundingBox target = m.getTarget().getBoundingBox();
			if (!routeOnly) {
				for (BoundingBox box : Arrays.asList(source, target)) {
					final int offset;
					if (box.getArea() == 0) {
						offset = 1;
					} else {
						offset = 0;
					}
					final SVGRectangleBuilder rect = svgBuilder.rectangle(//
							box.getIntMinX(), //
							box.getIntMinY(), //
							box.getIntWidth() + offset, //
							box.getIntHeight() + offset);
					rect.stroke("green").strokeWidth(5);
					rect.add();
				}
			}
			final Point c1 = source.center();
			final Point c2 = target.center();
			final SVGLineBuilder line = svgBuilder.line(c1.getIntX(), c1.getIntY(), c2.getIntX(), c2.getIntY());
			line.stroke("red");
			line.add();
		}
		final int widthWithOffset = containment.getIntMinX() + containment.getIntWidth() + 10; // +10 stroke
		final int heightWithOffset = containment.getIntMinY() + containment.getIntHeight() + 10; // +10 stroke
		svgBuilder.expand(widthWithOffset, heightWithOffset);
		return svgBuilder.build().getBytes();
	}
}
