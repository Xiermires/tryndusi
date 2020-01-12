package org.tryndusi.model.render;

import org.tryndusi.model.Move;
import org.tryndusi.model.impl.Route;

public class RouteRender extends AbstractRender<Route> {

    public RouteRender() {
        super(true);
    }

    @Override
    protected Iterable<Move> getMoves(Route drawable) {
        return drawable;
    }
}
