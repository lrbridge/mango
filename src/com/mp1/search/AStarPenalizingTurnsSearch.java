package com.mp1.search;

import com.mp1.heuristic.Heuristic;
import com.mp1.movement.DIRECTION;
import com.mp1.movement.PenalizingTurnsMovement;
import com.mp1.node.AStarNode;
import com.mp1.node.Node;
import com.mp1.node.State;
import com.mp1.search.base.InformedSearch;

public class AStarPenalizingTurnsSearch extends InformedSearch {

	private Heuristic heuristic;
	private int forwardCost;
	private int turnCost;
	
	public AStarPenalizingTurnsSearch(String filename, int forwardCost, int turnCost, Heuristic heuristic) {
		super(filename, new PenalizingTurnsMovement());
		this.forwardCost = forwardCost;
		this.turnCost = turnCost;
		this.heuristic = heuristic;
	}

    protected Node makeNode(int x, int y, DIRECTION directionFacing, Node parent, String action) {
        int distanceSoFar = 0;
        if(parent != null) {
        	if(action == "FORWARD")
                distanceSoFar = parent.getDistanceSoFar() + this.forwardCost;
            else
                distanceSoFar = parent.getDistanceSoFar() + this.turnCost;
        }
//System.out.println("dist so far: " + distanceSoFar);
        AStarNode newNode = new AStarNode(new State(x, y, directionFacing), parent, distanceSoFar);
        if(this.heuristicValues != null) { // check for end node (before heuristics)
            newNode.setExpectedDistanceToGo(this.heuristicValues[x][y]);
//            System.out.println("to go: " + this.heuristicValues[x][y]);
        }
        return newNode;
    }
    
    protected int[][] computeHeuristics() {
		int[][] heuristicValues = new int[this.maze.length][this.maze[0].length];
		Node goalNode = this.findNode('.');
		
		for(int i=0; i<this.maze.length; i++) {
			for(int j=0; j<this.maze[i].length; j++) {
				int value = this.heuristic.computeHeuristic(goalNode.getState(), i, j, this.forwardCost, this.turnCost);
				heuristicValues[i][j] = value;
			}
		}
		
		return heuristicValues;
	}

}
