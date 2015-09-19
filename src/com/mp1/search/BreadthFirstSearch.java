package com.mp1.search;

import java.util.concurrent.LinkedBlockingDeque;

import com.mp1.movement.NormalMovement;
import com.mp1.node.Node;
import com.mp1.search.base.UninformedSearch;

public class BreadthFirstSearch extends UninformedSearch {

	private LinkedBlockingDeque<Node> frontier;
	
	public BreadthFirstSearch(String filename) {
		super(filename, new NormalMovement());
		
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
