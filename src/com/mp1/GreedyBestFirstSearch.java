package com.mp1;

import java.util.PriorityQueue;

public class GreedyBestFirstSearch extends InformedSearch {

	private PriorityQueue<Node> frontier;
	
	public GreedyBestFirstSearch(String filename) {
		super(filename);

		this.frontier = new PriorityQueue<Node>();
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
	protected void addNodeToFrontier(Node node) {
		if(node != null) {
			this.frontier.add(node);
		}
	}

}
