package com.mp1.heuristic;

import com.mp1.node.State;

public class ManhattanDistanceHeuristic implements Heuristic {
	
	@Override
	public int computeHeuristic(State goalState, int x, int y, int forwardCost, int turnCost) {
        int xDifference = Math.abs(x - goalState.x);
        int yDifference = Math.abs(y - goalState.y);
		return xDifference + yDifference;
	}

}
