package org.tryndusi.model.render.svg;

public class SVGCircleBuilder extends SVGElementBuilder<SVGCircleBuilder> {

	private final SVGCircle svgCircle;
	private final SVGBuilder parent;

	SVGCircleBuilder(SVGBuilder svgBuilder, int cx, int cy, int r) {
		svgCircle = new SVGCircle(cx, cy, r);
		parent = svgBuilder;
	}

	@Override
	protected SVGCircleBuilder getThis() {
		return this;
	}

	@Override
	public void add() {
		svgCircle.setStroke(stroke);
		svgCircle.setStrokeWidth(strokeWidth);
		svgCircle.setStrokeOpacity(strokeOpacity);
		svgCircle.setStrokeDashArray(strokeDashArray);
		svgCircle.setFill(fill);
		parent.add(svgCircle);
	}
}
