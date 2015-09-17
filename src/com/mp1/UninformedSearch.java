package com.mp1;

public abstract class UninformedSearch extends Search {

	public UninformedSearch(String filename) {
		super(filename);
	}

	public MazeSolution solve() {

		Node firstNode = this.findFirstNode(this.maze);
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

					if (this.isGoal(child)) {
						return this.makeSolution(child);
					}

					this.addNodeToFrontier(child);
				}

			}

		}

		return null; // fail if no solution is found
	}
	
}
