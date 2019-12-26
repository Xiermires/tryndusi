package org.tryndusi.discovery;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Optional;

import org.junit.Test;
import org.tryndusi.discovery.jimfs.JimfsRegistry;

public class TestDiscovery {

	@Test
	public void shouldPublish() {
		final Registry registry = new JimfsRegistry();
		final Service discoverable = Service.create("foo", "localhost", 5557);
		registry.publish(discoverable);
	}

	@Test
	public void shouldDiscover() {
		final Registry registry = new JimfsRegistry();
		final Service discoverable = Service.create("foo", "localhost", 5557);
		registry.publish(discoverable);
		assertThat(registry.discover("foo", Service.class).get(), is(discoverable));
	}

	@Test(expected = NullPointerException.class)
	public void shouldFailMissingDiscovery() {
		new JimfsRegistry().publish(Service.create(null, "localhost", 5557));
	}

	@Test
	public void shouldNotDiscover() {
		final Registry registry = new JimfsRegistry();
		assertThat(registry.discover("foo"), is(Optional.empty()));
	}

	@Test
	public void shouldUnpublish() {
		final Registry registry = new JimfsRegistry();
		registry.publish(Service.create("foo", "localhost", 5557));
		registry.unpublish("foo");
		assertThat(registry.discover("foo"), is(Optional.empty()));
	}
}
