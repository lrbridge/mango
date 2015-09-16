package com.mp1;

import java.util.Stack;

public class DepthFirstSearch extends Search {

	private Stack<Node> frontier;
	
	public DepthFirstSearch(String filename) {
		super(filename);
		
		this.frontier = new Stack<Node>();
	}

	@Override
	protected boolean isFrontierEmpty() {
		return this.frontier.empty();
	}

	@Override
	protected void addNodeToFrontier(Node firstNode) {
		this.frontier.push(firstNode);
	}

	@Override
	protected Node popNodeOffFrontier() {
		return this.frontier.pop();
	}

	@Override
	protected boolean doesFrontierContain(Node child) {
		return this.frontier.contains(child);
	}
	
}
