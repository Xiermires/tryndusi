package org.tryndusi.discovery;

import org.tryndusi.common.factory.Factory;
import org.tryndusi.discovery.jimfs.JimfsRegistry;

public interface DiscovererFactory extends Factory<Discoverer> {

	@Override
	default Discoverer create() {
		return new JimfsRegistry();
	}
}
