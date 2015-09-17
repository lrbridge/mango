package com.mp1;

import java.util.concurrent.LinkedBlockingDeque;

public class BreadthFirstSearch extends UninformedSearch {

	private LinkedBlockingDeque<Node> frontier;
	
	public BreadthFirstSearch(String filename) {
		super(filename);
		
		this.frontier = new LinkedBlockingDeque<Node>();
	}

	@Override
	protected boolean doesFrontierContain(Node child) {
		return this.frontier.contains(child);
	}

	@Override
	protected Node popNodeOffFrontier() {
		return this.frontier.pop();
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
