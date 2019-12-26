package org.tryndusi.common;

public class Args {

	public static void notNull(Object o) {
		notNull(o, "");
	}

	public static void notNull(Object o, String comment) {
		if (o == null) {
			throw new NullPointerException(comment);
		}
	}
}
