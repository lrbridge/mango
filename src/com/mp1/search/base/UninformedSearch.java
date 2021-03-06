package com.mp1.search.base;

import com.mp1.movement.DIRECTION;
import com.mp1.movement.Movement;
import com.mp1.node.Node;
import com.mp1.node.State;
import com.mp1.solution.MazeSolution;

public abstract class UninformedSearch extends Search {
	
	private Movement movement;
	
	public UninformedSearch(String filename, Movement movement) {
		super(filename);
		
		this.movement = movement;
	}

	public MazeSolution solve() {

		Coordinate startXY = this.maze.findNode('P');
		Node firstNode = this.makeNode(startXY.x, startXY.y, DIRECTION.RIGHT, null, "no-action");
		this.addNodeToFrontier(firstNode);

		if (this.maze.isGoal(firstNode)) {
			return new MazeSolution(this.maze, firstNode, this.numNodesExpanded);
		}

		while (!this.isFrontierEmpty()) {

			Node node = this.popNodeOffFrontier();
			this.numNodesExpanded++;
			this.explored.add(node);

			for (String action : this.movement.getActions()) {

				Node child = this.getChildNode(node, action);

				if (this.maze.isInMaze(child) && this.maze.isNotAWall(child)
						&& !this.explored.contains(child)
						&& !this.doesFrontierContain(child)) {
					// explored & frontier 'contains' checks look for when
					// states are equal
					// because Node is equal when State is equal and State is
					// equal when x & y are same

					if (this.maze.isGoal(child)) {
						return new MazeSolution(this.maze, child, this.numNodesExpanded);
					}

					this.addNodeToFrontier(child);
				}

			}

		}

		return null; // fail if no solution is found

	}
	
	private Node getChildNode(Node node, String action) {
		State state = node.getState();
		int x = this.movement.getChildX(state, action);
		int y = this.movement.getChildY(state, action);
		DIRECTION directionFacing = this.movement.getChildDirectionFacing(state, action);
		return this.makeNode(x, y, directionFacing, node, action);
	}

	protected Node makeNode(int x, int y, DIRECTION directionFacing, Node parent, String action) {
		int distanceSoFar = 0;
		if(parent != null) {
			// add 1 to the parent's distance since all steps are equal cost (1)
			distanceSoFar = parent.getDistanceSoFar() + 1;
		}
		
		return new Node(new State(x, y, directionFacing), parent, distanceSoFar);
	}

}
