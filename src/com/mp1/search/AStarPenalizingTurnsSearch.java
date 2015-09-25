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

        AStarNode newNode = new AStarNode(new State(x, y, directionFacing), parent, distanceSoFar);
        if(this.goalNode != null) { // if the goalNode is set (when we are making the goalNode at the beginning, we need this check)
            newNode.setExpectedDistanceToGo(this.heuristic.computeHeuristic(newNode.getState(), this.goalNode.getState()));
        }
        return newNode;
    }

}
