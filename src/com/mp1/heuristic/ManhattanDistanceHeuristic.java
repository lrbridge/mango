package com.mp1.heuristic;

import com.mp1.node.State;

public class ManhattanDistanceHeuristic implements Heuristic {
	
	@Override
	public int computeHeuristic(State currentState, State goalState) {
        int xDifference = Math.abs(currentState.x - goalState.x);
        int yDifference = Math.abs(currentState.y - goalState.y);
		return xDifference + yDifference;
	}

}
