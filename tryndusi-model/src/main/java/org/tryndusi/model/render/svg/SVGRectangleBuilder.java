package org.tryndusi.model.render.svg;

public class SVGRectangleBuilder extends SVGElementBuilder<SVGRectangleBuilder> {

	private final SVGRectangle svgRect;
	private final SVGBuilder parent;

	SVGRectangleBuilder(SVGBuilder svgBuilder, int x, int y, int width, int height) {
		svgRect = new SVGRectangle(x, y, width, height);
		parent = svgBuilder;
	}

	@Override
	protected SVGRectangleBuilder getThis() {
		return this;
	}

	@Override
	public void add() {
		svgRect.setStroke(stroke);
		svgRect.setStrokeWidth(strokeWidth);
		svgRect.setStrokeOpacity(strokeOpacity);
		svgRect.setFill(fill);
		parent.add(svgRect);
	}

}
