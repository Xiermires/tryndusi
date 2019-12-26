package org.tryndusi.descriptor;

import java.io.InputStream;

public interface Descriptor {

    String stringify(Object o);

    <T> T objectify(InputStream is, Class<T> type);
}
