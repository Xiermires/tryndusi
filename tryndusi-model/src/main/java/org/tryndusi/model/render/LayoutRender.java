package org.tryndusi.model.render;

import java.util.Set;

import org.tryndusi.model.Move;
import org.tryndusi.model.impl.Layout;
import org.tryndusi.model.impl.Moves;

public class LayoutRender extends AbstractRender<Layout> {

	public LayoutRender(boolean drawConnections) {
		super(drawConnections);
	}

	@Override
	protected Set<Move> getMoves(Layout layout) {
		return Moves.defined(layout);
	}
}
