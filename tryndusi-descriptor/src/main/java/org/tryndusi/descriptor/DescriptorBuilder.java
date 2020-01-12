package org.tryndusi.descriptor;

import org.tryndusi.common.factory.Service;
import org.tryndusi.descriptor.yaml.YAMLDescriptorFactory;

public class DescriptorBuilder {

    public Descriptor build() {
        return Service.forType(DescriptorFactory.class).orElse(new YAMLDescriptorFactory()).create();
    }
}
