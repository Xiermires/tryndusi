package org.tryndusi.model.impl;

import org.tryndusi.model.geometry.BoundingBox;

public class Switch extends Device {

	public Switch(String name, int x, int y) {
		super(name, BoundingBox.of(x, y, x, y));
	}
}
