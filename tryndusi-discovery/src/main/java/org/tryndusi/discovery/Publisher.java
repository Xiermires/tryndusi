package org.tryndusi.discovery;

public interface Publisher {

    void publish(Discoverable discoverable);

    void unpublish(String path);
}
