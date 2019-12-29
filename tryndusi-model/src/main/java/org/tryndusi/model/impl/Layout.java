package org.tryndusi.model.impl;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;

import org.tryndusi.common.factory.Service;
import org.tryndusi.model.Actor;
import org.tryndusi.model.PathSearch;
import org.tryndusi.model.PathSearchFactory;

import com.google.common.graph.MutableValueGraph;
import com.google.common.graph.ValueGraphBuilder;

public class Layout extends ForwardingMutableValueGraph<Actor, Integer> {

	private final MutableValueGraph<Actor, Integer> delegate = ValueGraphBuilder.directed().build();
	private PathSearch ps;

	final Function<Actor, Collection<Actor>> adjacencyOf = (a) -> delegate().successors(a);
	final BiFunction<Actor, Actor, Integer> edgeWeight = //
			(a, b) -> delegate().edgeValue(a, b).orElse(Integer.MAX_VALUE);

	@Override
	protected MutableValueGraph<Actor, Integer> delegate() {
		return delegate;
	}

	public List<Actor> computeBestPath(Actor source, Actor target) {
		return getPathSearch().computeBestPath(source, target);
	}

	private PathSearch getPathSearch() {
		if (ps == null) {
			final Optional<PathSearchFactory> psf = Service.forType(PathSearchFactory.class);
			ps = psf.orElseThrow(() -> new IllegalStateException("No PathSearchFactory available.."))//
					.create(adjacencyOf, edgeWeight);
		}
		return ps;
	}
}
