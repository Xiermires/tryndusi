package org.tryndusi.discovery;

import java.util.Optional;

public interface Discoverer {

    Optional<Discoverable> discover(String discoveryPath);

    <T extends Discoverable> Optional<T> discover(String discoveryPath, Class<T> type);
}
