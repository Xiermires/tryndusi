package org.tryndusi.model.render.svg;

public abstract class SVGElementBuilder<B extends SVGElementBuilder<B>> {

	protected String stroke = "black";
	protected String strokeWidth = "1";
	protected String strokeOpacity = "1";
	protected String strokeDashArray = "0";
	protected String fill = "black";

	protected abstract B getThis();

	public B stroke(String stroke) {
		this.stroke = stroke;
		return getThis();
	}

	public B strokeWidth(int strokeWidth) {
		this.strokeWidth = String.valueOf(strokeWidth);
		return getThis();
	}

	public B strokeOpacity(double strokeOpacity) {
		this.strokeOpacity = String.valueOf(strokeOpacity);
		return getThis();
	}

	public B strokeDashArray(double strokeDashArray) {
		this.strokeDashArray = String.valueOf(strokeDashArray);
		return getThis();
	}

	public B fill(String fill) {
		this.fill = fill;
		return getThis();
	}

	public abstract void add();
}
