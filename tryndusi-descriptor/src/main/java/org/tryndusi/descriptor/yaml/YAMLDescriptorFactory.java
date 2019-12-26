package org.tryndusi.descriptor.yaml;

import org.tryndusi.descriptor.DescriptorFactory;

public class YAMLDescriptorFactory implements DescriptorFactory {

    @Override
    public YAMLDescriptor create() {
	return new YAMLDescriptor();
    }
}
