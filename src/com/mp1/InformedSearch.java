package com.mp1;

import java.util.PriorityQueue;

public abstract class InformedSearch extends Search {

	protected int[][] heuristicValues;
	
	private PriorityQueue<Node> frontier;
	
	public InformedSearch(String filename) {
		super(filename);
		
		this.frontier = new PriorityQueue<Node>();
	}

	public MazeSolution solve() {

		this.heuristicValues = this.computeHeuristics();

		Node firstNode = this.findNode('P');
		this.addNodeToFrontier(firstNode);
		
		while (!this.isFrontierEmpty()) {

			Node node = this.popNodeOffFrontier();
			this.numNodesExpanded++;
			this.explored.add(node);
			System.out.println("Expanding " + node.getState().x + " " + node.getState().y);

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

	private int[][] computeHeuristics() {
		int[][] heuristicValues = new int[this.maze.length][this.maze[0].length];
		System.out.println("HEURISTIC VALUES ");
		Node goalNode = this.findNode('.');

		for(int i=0; i<this.maze.length; i++) {
			for(int j=0; j<this.maze[i].length; j++) {
				
				State state = goalNode.getState();
				int xDifference = Math.abs(i - state.x);
				int yDifference = Math.abs(j - state.y);

				int manhattanDistance = xDifference + yDifference;
				
				heuristicValues[i][j] = manhattanDistance;
				System.out.print(manhattanDistance + " ");
			}
			System.out.println("");
		}
		
		return heuristicValues;
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
