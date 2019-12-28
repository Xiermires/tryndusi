package org.tryndusi.model.geometry;

public class Point {

	private final float x;
	private final float y;

	public Point(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public float getX() {
		return x;
	}

	public int getIntX() {
		return (int) Math.rint(x);
	}

	public float getY() {
		return y;
	}

	public int getIntY() {
		return (int) Math.rint(y);
	}

	@Override
	public String toString() {
		return "Point [x=" + x + ", y=" + y + "]";
	}
}
