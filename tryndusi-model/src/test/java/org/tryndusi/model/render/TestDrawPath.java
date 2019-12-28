package org.tryndusi.model.render;

import java.util.Arrays;

import org.junit.Test;
import org.tryndusi.model.geometry.BoundingBox;
import org.tryndusi.model.impl.Device;
import org.tryndusi.model.impl.Moves;
import org.tryndusi.model.impl.Route;

public class TestDrawPath {

	@Test
	public void shouldDrawLine() {
		final Device dev1 = new Device("dev1", BoundingBox.of(50, 50, 50, 50));
		final Device dev2 = new Device("dev2", BoundingBox.of(50, 100, 50, 100));

		final Route route = Moves.newRoute(Arrays.asList(dev1, dev2));
		System.out.println(new String(new RouteRender(true).draw(route)));
	}

	@Test
	public void shouldDrawActorsAndLine() {
		final Device dev1 = new Device("dev1", BoundingBox.of(50, 50, 50, 50));
		final Device dev2 = new Device("dev2", BoundingBox.of(50, 100, 50, 100));

		final Route route = Moves.newRoute(Arrays.asList(dev1, dev2));
		System.out.println(new String(new RouteRender(false).draw(route)));
	}
}
