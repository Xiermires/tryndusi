package org.tryndusi.routing.xorshift;

public class Xorshift64 {

    private long seed;

    public Xorshift64() {
        this(System.nanoTime());
    }

    public Xorshift64(long seed) {
        this.seed = seed;
    }

    /**
     * Return an uniformly distributed double number between (0-1].
     * <p>
     * Zero is not inclusive since we are using Xorshift.
     */
    public double nextDouble() {
        double d = xorshift64() / (double) Long.MAX_VALUE;
        // xorshift64() generates values in the whole Long.MIN_VALUE to Long.MAX_VALUE
        // Ensure we positive numbers (0-1].
        return d < 0 ? -d : d;
    }

    public double nextDouble(double min, double max) {
        return min + (nextDouble() * ((max - min)));
    }

    /**
     * Xorshift implementation 2^64 version.
     * <p>
     * Shifting triplet values selected from G. Marsaglia
     * '<a href="https://www.jstatsoft.org/article/view/v008i14">Xorshift RNGs</a>'.</a>'
     */
    private long xorshift64() {
        seed ^= seed << 13;
        seed ^= seed >>> 7;
        seed ^= seed << 17;
        return seed;
    }
}
