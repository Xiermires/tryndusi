package org.tryndusi.descriptor.yaml;

import java.io.IOException;
import java.io.InputStream;

import org.tryndusi.common.Args;
import org.tryndusi.descriptor.Descriptor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

class YAMLDescriptor implements Descriptor {

    final ObjectMapper mapper = new ObjectMapper(new YAMLFactory())
	    .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    @Override
    public String stringify(Object o) {
	Args.notNull(o, "Cannot parse null values.");
	try {
	    return mapper.writeValueAsString(o);
	} catch (JsonProcessingException e) {
	    throw new IllegalArgumentException("Cannot read object '" + o + "'.", e);
	}
    }

    @Override
    public <T> T objectify(InputStream is, Class<T> type) {
	Args.notNull(is, "Undefined input stream.");
	Args.notNull(type, "Undefined type.");
	try {
	    return mapper.readValue(is, type);
	} catch (IOException e) {
	    throw new IllegalArgumentException("Cannot convert to type '" + type + "'.", e);
	}
    }
}
