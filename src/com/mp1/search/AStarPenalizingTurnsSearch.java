package com.mp1.search;

import com.mp1.movement.DIRECTION;
import com.mp1.movement.PenalizingTurnsMovement;
import com.mp1.node.PenalizeNode;
import com.mp1.node.Node;
import com.mp1.node.State;
import com.mp1.search.base.PenalizedSearch;

public class AStarPenalizingTurnsSearch extends PenalizedSearch {


	public AStarPenalizingTurnsSearch(String filename, int forwardCode, int turnCost) {
//        this.forwardCode = forwardCode;
//        this.turnCost = turnCost;
		super(filename, new PenalizingTurnsMovement(), forwardCode, turnCost);
	}

    protected Node makePNode(int x, int y, DIRECTION directionFacing, Node parent, int new_x, int new_y, int forwardCode, int turnCost) {
        int distanceSoFar = 0;
        if(parent != null) {

            if(new_x == x && new_y == y)
                distanceSoFar = parent.getDistanceSoFar() + turnCost;
            else
                distanceSoFar = parent.getDistanceSoFar() + forwardCode;

        }

        PenalizeNode newNode = new PenalizeNode(new State(new_x, new_y, directionFacing), parent, distanceSoFar);
        if(this.heuristicValues != null) { // check for end node (before heuristics)
            newNode.setExpectedDistanceToGo(this.heuristicValues[new_x][new_y]);
        }
        return newNode;
    }




}
