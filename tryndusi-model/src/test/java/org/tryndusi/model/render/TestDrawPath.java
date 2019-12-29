package org.tryndusi.model.render;

import java.util.Arrays;

import org.junit.Test;
import org.tryndusi.model.geometry.BoundingBox;
import org.tryndusi.model.impl.Device;
import org.tryndusi.model.impl.Layout;
import org.tryndusi.model.impl.Moves;
import org.tryndusi.model.impl.Route;
import org.tryndusi.model.impl.Switch;

public class TestDrawPath {

	@Test
	public void shouldDrawSingleMove() {
		final Device dev1 = new Device("dev1", BoundingBox.of(50, 50, 50, 50));
		final Device dev2 = new Device("dev2", BoundingBox.of(50, 100, 50, 100));

		final Route route = Moves.newRoute(Arrays.asList(dev1, dev2));
		System.out.println(new String(new RouteRender().draw(route)));
	}

	@Test
	public void shouldDrawSquare() {
		final Device dev1 = new Device("dev1", BoundingBox.of(50, 50, 50, 50));
		final Device dev2 = new Device("dev2", BoundingBox.of(50, 100, 50, 100));
		final Device dev3 = new Device("dev3", BoundingBox.of(100, 100, 100, 100));
		final Device dev4 = new Device("dev4", BoundingBox.of(100, 50, 100, 50));

		final Route route = Moves.newRoute(Arrays.asList(dev1, dev2, dev3, dev4, dev1));
		System.out.println(new String(new RouteRender().draw(route)));
	}

	@Test
	public void shouldDrawLayoutActorsAndConnections() {
		final Device dev1 = new Device("dev1", BoundingBox.of(30, 30, 60, 60));
		final Switch swch1 = new Switch("swch1", 145, 45);
		final Device dev2 = new Device("dev2", BoundingBox.of(140, 140, 150, 150));

		final Layout layout = new Layout();
		layout.addNode(dev1);
		layout.addNode(swch1);
		layout.addNode(dev2);
		layout.putEdgeValue(dev1, swch1, 5);
		layout.putEdgeValue(swch1, dev2, 5);

		System.out.println(new String(new LayoutRender(true).draw(layout)));
	}

	@Test
	public void shouldDrawLayoutActors() {
		final Device dev1 = new Device("dev1", BoundingBox.of(30, 30, 60, 60));
		final Switch swch1 = new Switch("swch1", 145, 45);
		final Device dev2 = new Device("dev2", BoundingBox.of(140, 140, 150, 150));

		final Layout layout = new Layout();
		layout.addNode(dev1);
		layout.addNode(swch1);
		layout.addNode(dev2);
		layout.putEdgeValue(dev1, swch1, 5);
		layout.putEdgeValue(swch1, dev2, 5);

		System.out.println(new String(new LayoutRender(false).draw(layout)));
	}
}
