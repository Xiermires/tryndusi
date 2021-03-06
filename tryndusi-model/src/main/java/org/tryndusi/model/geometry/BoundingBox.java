package org.tryndusi.model.geometry;

import java.util.Arrays;
import java.util.Collection;

public class BoundingBox {

    private float minX = Float.MAX_VALUE;
    private float minY = Float.MAX_VALUE;
    private float maxX = Float.MIN_VALUE;
    private float maxY = Float.MIN_VALUE;

    private BoundingBox() {
    }

    public static BoundingBox empty() {
        return new BoundingBox();
    }

    public static BoundingBox of(int minX, int minY, int maxX, int maxY) {
        final BoundingBox bb = new BoundingBox();
        bb.minX = minX;
        bb.minY = minY;
        bb.maxX = maxX;
        bb.maxY = maxY;
        return bb;
    }

    public Point center() {
        final float midX = (getMaxX() - getMinX()) / 2;
        final float midY = (getMaxY() - getMinY()) / 2;
        return new Point(getMinX() + midX, getMinY() + midY);
    }

    public void union(BoundingBox otherBox) {
        minX = (Math.min(getMinX(), otherBox.getMinX()));
        minY = (Math.min(getMinY(), otherBox.getMinY()));
        maxX = (Math.max(getMaxX(), otherBox.getMaxX()));
        maxY = (Math.max(getMaxY(), otherBox.getMaxY()));
    }

    public static BoundingBox union(Collection<BoundingBox> boxes) {
        final BoundingBox union = empty();
        for (BoundingBox box : boxes) {
            union.union(box);
        }
        return union;
    }

    public boolean isPoint() {
        return getArea() == 0;
    }

    public boolean isLine() {
        return getWidth() == 0 || getHeight() == 0;
    }

    public float getArea() {
        float height = getHeight();
        float width = getWidth();
        return height * height + width * width;
    }

    public Point closestIntersection(Segment line, Point ref) {
        if (isPoint()) {
            return center();
        }

        final Point topLeft = new Point(getMinX(), getMaxY());
        final Point bottomLeft = new Point(getMinX(), getMinY());
        final Point topRight = new Point(getMaxX(), getMaxY());
        final Point bottomRight = new Point(getMaxX(), getMinY());

        final Segment top = new Segment(topLeft, topRight);
        final Segment left = new Segment(topLeft, bottomLeft);
        final Segment bottom = new Segment(bottomLeft, bottomRight);
        final Segment right = new Segment(topRight, bottomRight);

        float min = Float.MAX_VALUE;
        Segment closer = null;
        for (Segment side : Arrays.asList(top, left, bottom, right)) {
            Point i = null;
            if ((i = side.intersection(line)) != null) {
                final float d = i.distance(ref);
                if (d < min) {
                    min = d;
                    closer = side;
                }
            }
        }
        return closer.intersection(line);
    }

    public float getMinX() {
        return minX;
    }

    public float getMinY() {
        return minY;
    }

    public float getMaxX() {
        return maxX;
    }

    public float getMaxY() {
        return maxY;
    }

    public float getHeight() {
        return maxY - minY;
    }

    public float getWidth() {
        return maxX - minX;
    }

    public int getIntMinX() {
        return (int) Math.rint(minX);
    }

    public int getIntMinY() {
        return (int) Math.rint(minY);
    }

    public int getIntMaxX() {
        return (int) Math.rint(maxX);
    }

    public int getIntMaxY() {
        return (int) Math.rint(maxY);
    }

    public int getIntHeight() {
        return (int) Math.rint(getHeight());
    }

    public int getIntWidth() {
        return (int) Math.rint(getWidth());
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Float.floatToIntBits(maxX);
        result = prime * result + Float.floatToIntBits(maxY);
        result = prime * result + Float.floatToIntBits(minX);
        result = prime * result + Float.floatToIntBits(minY);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        BoundingBox other = (BoundingBox) obj;
        if (Float.floatToIntBits(maxX) != Float.floatToIntBits(other.maxX))
            return false;
        if (Float.floatToIntBits(maxY) != Float.floatToIntBits(other.maxY))
            return false;
        if (Float.floatToIntBits(minX) != Float.floatToIntBits(other.minX))
            return false;
        if (Float.floatToIntBits(minY) != Float.floatToIntBits(other.minY))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Box [minX=" + minX + ", minY=" + minY + ", maxX=" + maxX + ", maxY=" + maxY + "]";
    }

    public void expand(float top, float left, float bottom, float right) {
        maxX += right;
        minX += left;
        maxY += top;
        minY += bottom;
    }
}
