package org.tryndusi.descriptor;

import org.tryndusi.common.factory.Factory;

public interface DescriptorFactory extends Factory<Descriptor>{
    
    @Override
    Descriptor create();
}
