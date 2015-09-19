package com.mp1.search.base;

import com.mp1.movement.NormalMovement;
import com.mp1.node.Node;
import com.mp1.node.State;
import com.mp1.solution.MazeSolution;

public abstract class UninformedSearch extends Search {
	
	private NormalMovement movement = new NormalMovement();
	
	public UninformedSearch(String filename) {
		super(filename);
	}

	public MazeSolution solve() {

		Node firstNode = this.findNode('P');
		this.addNodeToFrontier(firstNode);

		if (this.isGoal(firstNode)) {
			return this.makeSolution(firstNode);
		}

		while (!this.isFrontierEmpty()) {

			Node node = this.popNodeOffFrontier();
			this.numNodesExpanded++;
			this.explored.add(node);

			for (String action : this.movement.ACTIONS) {

				Node child = this.getChildNode(node, action);

				if (this.isInMaze(child) && this.isNotAWall(child)
						&& !this.explored.contains(child)
						&& !this.doesFrontierContain(child)) {
					// explored & frontier 'contains' checks look for when
					// states are equal
					// because Node is equal when State is equal and State is
					// equal when x & y are same

					if (this.isGoal(child)) {
						return this.makeSolution(child);
					}

					this.addNodeToFrontier(child);
				}

			}

		}

		return null; // fail if no solution is found

	}
	
	private Node getChildNode(Node node, String action) {
		State state = node.getState();
		int x = this.movement.getChildX(state.x, action);
		int y = this.movement.getChildY(state.y, action);
		return this.makeNode(x, y, node);
	}

	protected Node makeNode(int x, int y, Node parent) {
		int distanceSoFar = 0;
		if(parent != null) {
			// add 1 to the parent's distance since all steps are equal cost (1)
			distanceSoFar = parent.getDistanceSoFar() + 1;
		}
		
		return new Node(new State(x,y), parent, distanceSoFar);
	}

}
