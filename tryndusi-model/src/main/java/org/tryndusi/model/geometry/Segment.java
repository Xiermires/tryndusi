package org.tryndusi.model.geometry;

public class Segment {

	private final float x1;
	private final float y1;

	private final float x2;
	private final float y2;

	public Segment(Point p1, Point p2) {
		this.x1 = p1.getX();
		this.y1 = p1.getY();
		this.x2 = p2.getX();
		this.y2 = p2.getY();
	}

	public float distance(Point p) {
		// ax + by + c
		final float a = y2 - y1;
		final float b = x1 - x2;
		final float c = a * (x1) + b * (y1);

		return (float) (Math.abs(a * p.getX() + b * p.getY() + c) / Math.sqrt(a * a + b * b));
	}

	public Point intersection(Segment other) {
		// this a1x + b1y + c1
		final float a1 = y2 - y1;
		final float b1 = x1 - x2;
		final float c1 = a1 * (x1) + b1 * (y1);

		// other a2x + b2y + c2
		final float a2 = other.getY2() - other.getY1();
		final float b2 = other.getX1() - other.getX2();
		final float c2 = a2 * (other.getX1()) + b2 * (other.getY1());

		final float determinant = a1 * b2 - a2 * b1;
		if (determinant == 0) { // no intersection
			return null;
		} else {
			final float x = (b2 * c1 - b1 * c2) / determinant;
			final float y = (a1 * c2 - a2 * c1) / determinant;
			return new Point(x, y);
		}
	}

	public float getX1() {
		return x1;
	}

	public float getY1() {
		return y1;
	}

	public float getX2() {
		return x2;
	}

	public float getY2() {
		return y2;
	}

	@Override
	public String toString() {
		return "Segment [x1=" + x1 + ", y1=" + y1 + ", x2=" + x2 + ", y2=" + y2 + "]";
	}
}
