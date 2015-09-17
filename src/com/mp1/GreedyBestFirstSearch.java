package com.mp1;

import java.util.Comparator;
import java.util.PriorityQueue;

public class GreedyBestFirstSearch extends InformedSearch {

	private PriorityQueue<Node> frontier;
	
	public GreedyBestFirstSearch(String filename) {
		super(filename);
		// TODO need to setup priority queue - comparator? natural ordering (path cost?)... but don't want to break other equals/contains
//		this.frontier = new PriorityQueue<Node>(20, pathCostComparator);
		
		// TODO need to store path cost for each node
		
		// TODO how to compute & store heuristic (Manhattan distance)
	}

	@Override
	protected boolean doesFrontierContain(Node child) {
		return this.frontier.contains(child);
	}

	@Override
	protected Node popNodeOffFrontier() {
		return this.frontier.poll();
	}

	@Override
	protected boolean isFrontierEmpty() {
		return this.frontier.isEmpty();
	}

	@Override
	protected void addNodeToFrontier(Node firstNode) {
		this.frontier.add(firstNode);
	}

}
