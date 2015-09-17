package com.mp1;

public abstract class UninformedSearch extends Search {

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

			for (ACTIONS action : ACTIONS.values()) {

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
	
	protected Node makeNode(int x, int y, Node parent) {
		int distanceSoFar = 0;
		if(parent != null) {
			// add 1 to the parent's distance since all steps are equal cost (1)
			distanceSoFar = parent.distanceSoFar + 1;
		}
		
		Node newNode = new Node();
		newNode.parent = parent;
		newNode.state = new State(x, y);
		newNode.distanceSoFar = distanceSoFar;
		return newNode;
	}

}
