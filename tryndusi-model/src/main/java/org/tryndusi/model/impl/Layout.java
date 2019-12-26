package org.tryndusi.model.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
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
	private final PathSearchFactory psf = Service.forType(PathSearchFactory.class).orElse(null);
	private final PathSearch pf;

	final Function<Actor, Collection<Actor>> adjacencyOf = (a) -> delegate().successors(a);
	final BiFunction<Actor, Actor, Integer> edgeWeight = //
			(a, b) -> delegate().edgeValue(a, b).orElse(Integer.MAX_VALUE);

	public Layout() {
		if (psf != null) {
			pf = psf.create(adjacencyOf, edgeWeight);
		} else {
			pf = new RecursionPathSearch();
		}
	}

	@Override
	protected MutableValueGraph<Actor, Integer> delegate() {
		return delegate;
	}

	public List<Actor> computeBestPath(Actor source, Actor target) {
		return pf.computeBestPath(source, target);
	}

	private class RecursionPathSearch implements PathSearch {

		private Actor source = null;
		private Actor target = null;

		private int best = Integer.MAX_VALUE;
		private List<Actor> bestPath = new ArrayList<>();
		private List<Actor> currentPath = new ArrayList<>();

		@Override
		public List<Actor> computeBestPath(Actor source, Actor target) {
			this.source = source;
			this.target = target;
			this.best = Integer.MAX_VALUE;
			this.bestPath.clear();
			this.currentPath.clear();
			searchFrom(source);
			bestPath.add(0, source);
			bestPath.add(target);
			return bestPath;
		}

		public void searchFrom(Actor source) {
			Actor current = source;

			for (Actor successor : adjacencyOf.apply(current)) {
				if (target.equals(successor)) {
					final int newWeight = calculateWeight(currentPath);
					if (newWeight < best) {
						bestPath.clear();
						bestPath.addAll(currentPath);
						best = newWeight;
					}
				} else {
					currentPath.add(successor);
					searchFrom(successor);
					currentPath.remove(currentPath.size() - 1);
				}
			}
		}

		private int calculateWeight(List<Actor> currentPath) {
			if (currentPath.isEmpty()) {
				return edgeWeight.apply(source, target);
			}
			long weight = edgeWeight.apply(source, currentPath.get(0));
			for (int i = 1; i < currentPath.size(); i++) {
				weight += edgeWeight.apply(currentPath.get(i - 1), currentPath.get(i));
			}
			weight += edgeWeight.apply(currentPath.get(currentPath.size() - 1), target);
			return weight >= Integer.MAX_VALUE ? Integer.MAX_VALUE : (int) weight;
		}
	}
}
