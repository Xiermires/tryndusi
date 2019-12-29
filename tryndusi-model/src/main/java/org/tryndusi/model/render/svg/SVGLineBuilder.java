package org.tryndusi.model.render.svg;

public class SVGLineBuilder extends SVGElementBuilder<SVGLineBuilder> {

	private final SVGLine svgLine;
	private final SVGBuilder parent;

	SVGLineBuilder(SVGBuilder svgBuilder, int x1, int y1, int x2, int y2) {
		svgLine = new SVGLine(x1, y1, x2, y2);
		parent = svgBuilder;
	}

	@Override
	protected SVGLineBuilder getThis() {
		return this;
	}

	@Override
	public void add() {
		svgLine.setStroke(stroke);
		svgLine.setStrokeWidth(strokeWidth);
		svgLine.setStrokeOpacity(strokeOpacity);
		svgLine.setStrokeDashArray(strokeDashArray);
		svgLine.setFill(fill);
		parent.add(svgLine);
	}
}
