package org.tryndusi.routing.dijkstra;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.function.BiFunction;
import java.util.function.Function;

import org.tryndusi.common.graph.DRTNode;
import org.tryndusi.model.Actor;
import org.tryndusi.model.PathSearch;

import com.google.common.collect.Lists;

public class DijkstraUCS implements PathSearch {

	private final BiFunction<Actor, Actor, Integer> edgeWeight;
	private final Function<Actor, Collection<Actor>> adjacencyOf;

	public DijkstraUCS(Function<Actor, Collection<Actor>> adjacencyOf, BiFunction<Actor, Actor, Integer> edgeWeight) {
		this.adjacencyOf = adjacencyOf;
		this.edgeWeight = edgeWeight;
	}

	@Override
	public List<Actor> computeBestPath(Actor source, Actor target) {
		final Map<Actor, DRTNode<Entry<Actor, Integer>>> knownActors = new HashMap<>();
		final Queue<DRTNode<Entry<Actor, Integer>>> visitable = new PriorityQueue<>(
				Comparator.comparingInt(f -> weight(f)));
		visitable.add(new DRTNode<Entry<Actor, Integer>>(entryOf(source, 0), null));

		while (!visitable.isEmpty()) {
			DRTNode<Entry<Actor, Integer>> curr = visitable.remove();
			if (actor(curr).equals(target)) {
				return Lists.transform(curr.getPathFromRoot(), (e) -> e.getKey());
			}
			if (knownActors.containsKey(actor(curr))) {
				continue;
			}
			knownActors.put(actor(curr), curr);

			for (Actor nbr : adjacencyOf.apply(actor(curr))) {
				final int currWeight = weight(curr) + edgeWeight.apply(actor(curr), nbr);
				DRTNode<Entry<Actor, Integer>> nbrNode = knownActors.get(nbr);
				if (nbrNode == null || weight(nbrNode) > currWeight) {
					nbrNode = new DRTNode<Entry<Actor, Integer>>(entryOf(nbr, currWeight), curr);
					visitable.add(nbrNode);
				}
			}
		}
		return Collections.emptyList();
	}

	private int weight(DRTNode<Entry<Actor, Integer>> node) {
		return node.getData().getValue();
	}

	private Actor actor(DRTNode<Entry<Actor, Integer>> node) {
		return node.getData().getKey();
	}

	private Entry<Actor, Integer> entryOf(final Actor actor, final int weight) {
		return new Entry<Actor, Integer>() {

			@Override
			public Actor getKey() {
				return actor;
			}

			@Override
			public Integer getValue() {
				return weight;
			}

			@Override
			public Integer setValue(Integer value) {
				throw new UnsupportedOperationException("not implemented.");
			}
		};
	}
}
