package org.tryndusi.model.render.svg;

import com.thoughtworks.xstream.XStream;

public class SVGBuilder {

	private final XStream xstream = new XStream();
	{
		xstream.processAnnotations(SVGLine.class);
		xstream.processAnnotations(SVGCircle.class);
		xstream.processAnnotations(SVGRectangle.class);
	}

	private final StringBuilder svgElements = new StringBuilder();
	private final String lineSeparator = System.getProperty("line.separator");

	private int width;
	private int height;

	public SVGBuilder() {
	}

	public SVGBuilder(int width, int height) {
		this.width = width;
		this.height = height;
	}

	public void expand(int widthOffset, int heightOffset) {
		this.width += widthOffset;
		this.height += heightOffset;
	}

	public SVGLineBuilder line(int x1, int y1, int x2, int y2) {
		return new SVGLineBuilder(this, x1, y1, x2, y2);
	}

	public SVGCircleBuilder circle(int cx, int cy, int r) {
		return new SVGCircleBuilder(this, cx, cy, r);
	}

	public SVGRectangleBuilder rectangle(int x, int y, int width, int height) {
		return new SVGRectangleBuilder(this, x, y, width, height);
	}

	public String build() {
		final StringBuilder svg = new StringBuilder();
		svg.append("<svg width=\"" + width + "\" height=\"" + height + "\">");
		svg.append(svgElements);
		return svg.append(lineSeparator).append("</svg>").toString();
	}

	void add(SVGElement svgElement) {
		svgElements.append(lineSeparator).append(xstream.toXML(svgElement));
	}
}
