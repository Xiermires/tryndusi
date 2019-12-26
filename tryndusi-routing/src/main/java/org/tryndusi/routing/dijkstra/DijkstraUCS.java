package org.tryndusi.routing.dijkstra;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Function;

import org.tryndusi.model.Actor;
import org.tryndusi.model.PathSearch;

public class DijkstraUCS implements PathSearch {

	private final BiFunction<Actor, Actor, Integer> edgeWeight;
	private final Function<Actor, Collection<Actor>> adjacencyOf;

	public DijkstraUCS(Function<Actor, Collection<Actor>> adjacencyOf, BiFunction<Actor, Actor, Integer> edgeWeight) {
		this.adjacencyOf = adjacencyOf;
		this.edgeWeight = edgeWeight;
	}

	private Map<Actor, ActorPlusWeight> knownNodes = new HashMap<>();

	@Override
	public List<Actor> computeBestPath(Actor source, Actor target) {
		final Set<Actor> visited = new HashSet<>();
		final Queue<ActorPlusWeight> remaining = new PriorityQueue<>(Comparator.comparingInt(f -> f.weightFromSource));
		remaining.add(new ActorPlusWeight(source, 0, null));

		while (!remaining.isEmpty()) {
			ActorPlusWeight curr = remaining.remove();
			if (curr.actor.equals(target)) {
				return asList(curr);
			}
			if (visited.contains(curr.actor)) {
				continue;
			}
			visited.add(curr.actor);
			for (Actor nbr : adjacencyOf.apply(curr.actor)) {
				final int currWeight = curr.weightFromSource + edgeWeight.apply(curr.actor, nbr);
				ActorPlusWeight nbrNode = knownNodes.get(nbr);
				if (nbrNode == null || nbrNode.weightFromSource > currWeight) {
					nbrNode = new ActorPlusWeight(nbr, currWeight, curr);
					remaining.add(nbrNode);
				}
			}
		}
		return Collections.emptyList();
	}

	private List<Actor> asList(ActorPlusWeight curr) {
		final List<Actor> result = new ArrayList<>();
		result.add(curr.actor);
		while (curr.predecessor != null) {
			result.add(0, curr.predecessor.actor);
			curr = curr.predecessor;
		}
		return result;
	}

	private static class ActorPlusWeight {
		final Actor actor;
		final int weightFromSource;
		final ActorPlusWeight predecessor;

		ActorPlusWeight(Actor a, int weight, ActorPlusWeight predecessor) {
			this.actor = a;
			this.weightFromSource = weight;
			this.predecessor = predecessor;
		}

		@Override
		public String toString() {
			return "ActorPlusWeight [actor=" + actor + ", weightFromSource=" + weightFromSource + "]";
		}
	}
}
