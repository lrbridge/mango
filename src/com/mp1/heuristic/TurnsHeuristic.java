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
		if(goalState.y < y) { // if goal is behind (up or down, doesn't matter)
			minNumberTurns = 2; // need to get 180 around (since start facing right)
		}
		else if(goalState.y == y) { // if goal just up or down
			minNumberTurns = 1; // minimally just need to turn up or down
		}
		else if(goalState.x == x) { // if goal is directly in front of me
			minNumberTurns = 0;
		}
		else { // if in front of me & up or down
			minNumberTurns = 1;
		}
		
		return (manhattanDistance * forwardCost) + (minNumberTurns * turnCost);
	}

}
