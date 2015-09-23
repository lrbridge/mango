package com.mp1.search;

import com.mp1.heuristic.Heuristic;
import com.mp1.heuristic.ManhattanDistanceHeuristic;
import com.mp1.movement.DIRECTION;
import com.mp1.movement.NormalMovement;
import com.mp1.node.GreedyNode;
import com.mp1.node.Node;
import com.mp1.node.State;
import com.mp1.search.base.InformedSearch;

public class GreedyBestFirstSearch extends InformedSearch {
	
	private Heuristic heuristic;
	
	public GreedyBestFirstSearch(String filename) {
		super(filename, new NormalMovement());
		this.heuristic = new ManhattanDistanceHeuristic();
	}
	
	protected Node makeNode(int x, int y, DIRECTION directionFacing, Node parent, String action) {
		int distanceSoFar = 0;
		if(parent != null) {
			// add 1 to the parent's distance since all steps are equal cost (1)
			distanceSoFar = parent.getDistanceSoFar() + 1;
		}
		
		GreedyNode newNode = new GreedyNode(new State(x, y, directionFacing), parent, distanceSoFar);
		if(this.heuristicValues != null) { // check for end node, which has to be made at the beginning (before heuristics)
			newNode.setExpectedDistanceToGo(this.heuristicValues[x][y]);
		}
		return newNode;
	}
	
    protected int[][] computeHeuristics() {
		int[][] heuristicValues = new int[this.maze.length][this.maze[0].length];
		Node goalNode = this.findNode('.');
		
		for(int i=0; i<this.maze.length; i++) {
			for(int j=0; j<this.maze[i].length; j++) {
                int value = this.heuristic.computeHeuristic(goalNode.getState(), i, j, 0, 0);
				heuristicValues[i][j] = value;
			}
		}
		
		return heuristicValues;
	}

}
