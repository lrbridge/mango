package com.mp1;

public abstract class InformedSearch extends Search {

	public InformedSearch(String filename) {
		super(filename);
	}

	public MazeSolution solve() {
		
		Node firstNode = this.findFirstNode(this.maze);
		this.addNodeToFrontier(firstNode);

		// TODO compute int[][] of heuristic (& find goal in maze)

		while (!this.isFrontierEmpty()) {
			
			Node node = this.popNodeOffFrontier();
			this.numNodesExpanded++;
			this.explored.add(node);

			if(this.isGoal(node)) {
				return this.makeSolution(node);
			}
			
			for (ACTIONS action : ACTIONS.values()) {

				Node child = this.getChildNode(node, action);

				if (this.isInMaze(child) && this.isNotAWall(child)
						&& !this.explored.contains(child)
						&& !this.doesFrontierContain(child)) {
					
					this.addNodeToFrontier(child);
				}
				else if(this.doesFrontierContain(child)) {
					// TODO if has less path cost, replace the one on the frontier
				}

			}

		}

		return null; // fail if no solution is found
	}

	
}
