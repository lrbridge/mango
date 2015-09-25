package com.mp1.search.base;

import java.util.ArrayList;
import java.util.List;

import com.mp1.movement.DIRECTION;
import com.mp1.node.Node;
import com.mp1.solution.MazeSolution;

public abstract class Search {

	protected Maze maze;
	protected List<Node> explored;
	protected int numNodesExpanded;

	public Search(String filename) {		
		this.maze = new Maze(filename);
		this.explored = new ArrayList<Node>();
		this.numNodesExpanded = 0;
	}

	/**
	 * Solve the maze
	 * @return
	 */
	public abstract MazeSolution solve();
	
	protected abstract boolean doesFrontierContain(Node child);

	protected abstract Node popNodeOffFrontier();

	protected abstract boolean isFrontierEmpty();

	protected abstract void addNodeToFrontier(Node firstNode);

	protected abstract Node makeNode(int x, int y, DIRECTION directionFacing, Node node, String action);

}