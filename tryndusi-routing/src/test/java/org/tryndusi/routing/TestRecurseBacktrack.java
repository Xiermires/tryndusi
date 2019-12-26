package org.tryndusi.routing;

import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class TestRecurseBacktrack extends SomeLayout {

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
