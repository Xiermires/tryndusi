package org.tryndusi.model.geometry;

public class BoundingBox {

	private int minX;
	private int minY;
	private int maxX;
	private int maxY;

	public static BoundingBox of(int minX, int minY, int maxX, int maxY) {
		final BoundingBox bb = new BoundingBox();
		bb.minX = minX;
		bb.minY = minY;
		bb.maxX = maxX;
		bb.maxY = maxY;
		return bb;
	}

	public Point center() {
		return null;
	}

	public int getMinX() {
		return minX;
	}

	public void setMinX(int minX) {
		this.minX = minX;
	}

	public int getMinY() {
		return minY;
	}

	public void setMinY(int minY) {
		this.minY = minY;
	}

	public int getMaxX() {
		return maxX;
	}

	public void setMaxX(int maxX) {
		this.maxX = maxX;
	}

	public int getMaxY() {
		return maxY;
	}

	public void setMaxY(int maxY) {
		this.maxY = maxY;
	}
}
