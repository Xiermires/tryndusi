package org.tryndusi.common.factory;

import java.util.Iterator;
import java.util.Map;
import java.util.Optional;
import java.util.ServiceLoader;
import java.util.concurrent.ConcurrentHashMap;

import org.tryndusi.common.Args;

public class Service {

    public static <T> Optional<T> forType(Class<T> type) {
        Args.notNull(type, "Null type");

        @SuppressWarnings("unchecked")
        final T overridden = (T) serviceMap.get(type);
        if (overridden != null) {
            return Optional.of(overridden);
        }

        final Iterator<T> it = ServiceLoader.load(type).iterator();
        if (!it.hasNext()) {
            return Optional.empty();
        }

        T candidate = it.next();
        if (it.hasNext()) {
            // TODO selection criteria
            throw new IllegalStateException("Multiple services available for type '" + type + "'.");
        }
        return Optional.of(candidate);
    }

    private static final Map<Class<?>, Object> serviceMap = new ConcurrentHashMap<>();

    public static <T> void register(Class<T> type, T impl) {
        serviceMap.put(type, impl);
    }

    public static void unregister(Class<?> clazz) {
        serviceMap.remove(clazz);
    }
}
