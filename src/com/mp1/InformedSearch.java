package com.mp1;

public abstract class InformedSearch extends Search {

	private int[][] heuristicValues;
	
	public InformedSearch(String filename) {
		super(filename);
	}

	public MazeSolution solve() {

		this.heuristicValues = this.computeHeuristics();

		Node firstNode = this.findNode('P');
		this.addNodeToFrontier(firstNode);
		
		while (!this.isFrontierEmpty()) {

			Node node = this.popNodeOffFrontier();
			this.numNodesExpanded++;
			this.explored.add(node);
			System.out.println("Expanding " + node.state.x + " " + node.state.y);

			if(this.isGoal(node)) {
				return this.makeSolution(node);
			}
			
			for (ACTIONS action : ACTIONS.values()) {

				Node child = this.getChildNode(node, action);

				if (this.isInMaze(child) && this.isNotAWall(child)
						&& !this.explored.contains(child)
						&& !this.doesFrontierContain(child)) {
					// explored & frontier 'contains' checks look for when states are equal
					// because Node is equal when State is equal and State is equal when x & y are same

					this.addNodeToFrontier(child);
				}
				else if(this.doesFrontierContain(child)) {
					// TODO if has less path cost, replace the one on the frontier
				}

			}

		}

		return null; // fail if no solution is found
	}
	
	protected Node makeNode(int x, int y, Node parent) {
		Node newNode = new Node();
		newNode.parent = parent;
		newNode.state = new State(x, y);
		newNode.distanceSoFar = 0;
		if(this.heuristicValues != null) { // check for end node (before heuristics)
			newNode.expectedDistanceToGo = this.heuristicValues[x][y];
		}
		return newNode;
	}

	private int[][] computeHeuristics() {
		int[][] heuristicValues = new int[this.maze.length][this.maze[0].length];
		System.out.println("HEURISTIC VALUES ");
		Node goalNode = this.findNode('.');

		for(int i=0; i<this.maze.length; i++) {
			for(int j=0; j<this.maze[i].length; j++) {
				
				int xDifference = Math.abs(i - goalNode.state.x);
				int yDifference = Math.abs(j - goalNode.state.y);

				int manhattanDistance = xDifference + yDifference;
				
				heuristicValues[i][j] = manhattanDistance;
				System.out.print(manhattanDistance + " ");
			}
			System.out.println("");
		}
		
		return heuristicValues;
	}

	
}
