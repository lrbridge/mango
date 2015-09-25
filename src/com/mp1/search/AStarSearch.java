package com.mp1.search;

import com.mp1.heuristic.Heuristic;
import com.mp1.heuristic.ManhattanDistanceHeuristic;
import com.mp1.movement.DIRECTION;
import com.mp1.movement.Movement;
import com.mp1.movement.NormalMovement;
import com.mp1.node.AStarNode;
import com.mp1.node.Node;
import com.mp1.node.State;
import com.mp1.search.base.InformedSearch;

public class AStarSearch extends InformedSearch {
	
	private Heuristic heuristic;
	
	public AStarSearch(String filename) {
		super(filename, new NormalMovement());
		this.heuristic = new ManhattanDistanceHeuristic();
	}
	
	public AStarSearch(String filename, Movement movement) {
		super(filename, movement);
		this.heuristic = new ManhattanDistanceHeuristic();
	}
	
	protected Node makeNode(int x, int y, DIRECTION directionFacing, Node parent, String action) {
		int distanceSoFar = 0;
		if(parent != null) {
			// add 1 to the parent's distance since all steps are equal cost (1)
			distanceSoFar = parent.getDistanceSoFar() + 1;
		}
		
		AStarNode newNode = new AStarNode(new State(x, y, directionFacing), parent, distanceSoFar);
        if(this.goalNode != null) { // if the goalNode is set (when we are making the goalNode at the beginning, we need this check)
            newNode.setExpectedDistanceToGo(this.computeHeuristic(x, y));
        }
		return newNode;
	}

    protected int computeHeuristic(int x, int y) {
    	return this.heuristic.computeHeuristic(this.goalNode.getState(), x, y, 0, 0);
	}

}
