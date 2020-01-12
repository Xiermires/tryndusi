package org.tryndusi.routing;

import org.tryndusi.model.geometry.BoundingBox;
import org.tryndusi.model.impl.Device;
import org.tryndusi.model.impl.Layout;

public abstract class SomeLayout {

    protected final Device dev1 = new Device("dev1", BoundingBox.of(0, 0, 1, 1));
    protected final Device dev2 = new Device("dev2", BoundingBox.of(1, 1, 2, 2));
    protected final Device dev3 = new Device("dev3", BoundingBox.of(1, 1, 4, 4));
    protected final Device dev4 = new Device("dev4", BoundingBox.of(1, 1, 1, 1));
    protected final Device dev5 = new Device("dev5", BoundingBox.of(2, 2, 1, 1));
    protected final Device lone = new Device("lone", BoundingBox.of(2, 2, 1, 1));

    protected final Layout layout = new Layout();
    {
        layout.addNode(dev1);
        layout.addNode(dev2);
        layout.addNode(dev3);
        layout.addNode(dev4);
        layout.addNode(dev5);
        layout.putEdgeValue(dev1, dev2, 2);
        layout.putEdgeValue(dev1, dev3, 4);
        layout.putEdgeValue(dev2, dev3, 1);
        layout.putEdgeValue(dev2, dev4, 3);
        layout.putEdgeValue(dev3, dev4, 1);
        layout.putEdgeValue(dev4, dev5, 1);
        layout.putEdgeValue(dev2, dev5, 5);
    }
}
