package com.mp1.heuristic;

import com.mp1.node.State;

public class TurnsHeuristic implements Heuristic {
	
	@Override
	public int computeHeuristic(State goalState, int x, int y, int forwardCost, int turnCost) {

		// (Manhattan distance * forward cost) + (min # turns * turn cost)
		
        int xDifference = Math.abs(x - goalState.x);
        int yDifference = Math.abs(y - goalState.y);
		int manhattanDistance = xDifference + yDifference;
		
		int minNumberTurns;
		
		// always assume we are headed toward the goal as best as possible to be admissible (never need 2 turns)
		if(goalState.y == y || goalState.x == x) {
			minNumberTurns = 0; // if in line with me, don't need to turn at all best case
		}
		else {
			minNumberTurns = 1;  // if x & y are different, I'll have to turn at least 1 time to get to the goal.
		}
		
		return (manhattanDistance * forwardCost) + (minNumberTurns * turnCost);
	}

}
