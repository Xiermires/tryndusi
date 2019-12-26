package org.tryndusi.routing.ucs;

import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertThat;

import java.util.Collection;
import java.util.function.BiFunction;
import java.util.function.Function;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.tryndusi.common.factory.Service;
import org.tryndusi.model.Actor;
import org.tryndusi.model.PathSearch;
import org.tryndusi.model.PathSearchFactory;
import org.tryndusi.routing.SomeLayout;
import org.tryndusi.routing.dijkstra.DijkstraUCS;

public class TestDijkstra extends SomeLayout {

	@BeforeClass
	public static void pre() {
		Service.register(PathSearchFactory.class, new PathSearchFactory() {

			@Override
			public PathSearch create(Function<Actor, Collection<Actor>> adjacencyOf,
					BiFunction<Actor, Actor, Integer> edgeWeight) {
				return new DijkstraUCS(adjacencyOf, edgeWeight);
			}
		});
	}

	@AfterClass
	public static void post() {
		Service.unregister(PathSearchFactory.class);
	}

	@Test
	public void shouldFindBestPath() {
		assertThat(layout.computeBestPath(dev1, dev5), contains(dev1, dev2, dev3, dev4, dev5));
	}

	@Test
	public void shouldFindNewBestPath() {
		assertThat(layout.computeBestPath(dev1, dev3), contains(dev1, dev2, dev3));

		layout.removeEdge(dev1, dev2);
		assertThat(layout.computeBestPath(dev1, dev3), contains(dev1, dev3));
	}
}
