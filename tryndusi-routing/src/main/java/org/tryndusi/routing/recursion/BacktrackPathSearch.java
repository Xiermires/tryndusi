package org.tryndusi.routing.recursion;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Function;

import org.tryndusi.common.graph.DRTNode;
import org.tryndusi.model.Actor;
import org.tryndusi.model.PathSearch;

public class BacktrackPathSearch implements PathSearch {

	private final Function<Actor, Collection<Actor>> adjacencyOf;
	private final BiFunction<Actor, Actor, Integer> edgeWeight;

	public BacktrackPathSearch(Function<Actor, Collection<Actor>> adjacencyOf,
			BiFunction<Actor, Actor, Integer> edgeWeight) {
		this.adjacencyOf = adjacencyOf;
		this.edgeWeight = edgeWeight;
	}

	@Override
	public List<Actor> computeBestPath(Actor source, Actor target) {
		int best = Integer.MAX_VALUE;
		final List<Actor> bestPath = new ArrayList<>();
		final List<Actor> currentPath = new ArrayList<>();

		final Set<DRTNode<Actor>> visited = new HashSet<>();
		final Deque<DRTNode<Actor>> deque = new ArrayDeque<>();
		deque.add(new DRTNode<Actor>(source, null));

		while (!deque.isEmpty()) {
			final DRTNode<Actor> current = deque.poll();

			if (visited.contains(current)) {
				continue;
			}
			visited.add(current);

			for (Actor successor : adjacencyOf.apply(current.getData())) {
				if (target.equals(successor)) {
					currentPath.clear();
					currentPath.addAll(current.getPathFromRoot());
					currentPath.add(target);
					final int newWeight = calculateWeight(currentPath);
					if (newWeight < best) {
						bestPath.clear();
						bestPath.addAll(currentPath);
						best = newWeight;
					}
				} else {
					deque.add(new DRTNode<Actor>(successor, current));
				}
			}
		}
		return bestPath;
	}

	private int calculateWeight(List<Actor> currentPath) {
		long weight = 0;
		for (int i = 1; i < currentPath.size(); i++) {
			weight += edgeWeight.apply(currentPath.get(i - 1), currentPath.get(i));
		}
		return weight >= Integer.MAX_VALUE ? Integer.MAX_VALUE : (int) weight;
	}
}