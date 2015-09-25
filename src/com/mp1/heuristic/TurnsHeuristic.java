package com.mp1.heuristic;

import com.mp1.node.State;

public class TurnsHeuristic implements Heuristic {
	
	private int forwardCost;
	private int turnCost;
	
	public TurnsHeuristic(int forwardCost, int turnCost) {
		this.forwardCost = forwardCost;
		this.turnCost = turnCost;
	}
	
	@Override
	public int computeHeuristic(State currentState, State goalState) {
		
        int xDifference = Math.abs(currentState.x - goalState.x);
        int yDifference = Math.abs(currentState.y - goalState.y);
		int manhattanDistance = xDifference + yDifference;
				
		// compute the minimum number of turns we must have from this location/direction facing to be admissible
		int minNumberTurns;
		if(goalIsBehind(currentState, goalState)) {
			minNumberTurns = 2; //System.out.println("BEHIND - 2");
		}
		else if(goalIsDirectlyAhead(currentState, goalState)) {
			minNumberTurns = 0; //System.out.println("DIRECTLY AHEAD - 0");
		}
		else { // goal is ahead but not directly in front of me, or directly to my right or left
			minNumberTurns = 1;  //System.out.println("FRONT SIDES OR SIDES - 1");
		}

		System.out.println("  for child " + currentState.x + " " + currentState.y + " " + currentState.directionFacing + " = " + (manhattanDistance * forwardCost) + (minNumberTurns * turnCost));
		return (manhattanDistance * forwardCost) + (minNumberTurns * turnCost);
	}
	
	private boolean goalIsBehind(State currentState, State goalState) {
		if(currentState.directionFacing.equals("LEFT")) {
			return currentState.y < goalState.y; // goal state is bigger = behind
		}
		else if(currentState.directionFacing.equals("UP")) {
			return currentState.x < goalState.x; // goal state is bigger = behind
		}
		if(currentState.directionFacing.equals("RIGHT")) {
			return currentState.y > goalState.y; // goal state is smaller = behind
		}
		else { // DOWN
			return currentState.x > goalState.x; // goal state is smaller = behind
		}
	}

	private boolean goalIsDirectlyAhead(State currentState, State goalState) {
		if(currentState.directionFacing.equals("LEFT") || currentState.directionFacing.equals("RIGHT")) {
			return currentState.x == goalState.x; // directly ahead if xs are equal (because this is the else if)
		}
		else { // UP or DOWN
			return currentState.y == goalState.y; // directly ahead if ys are equal (because this is the else if)
		}
	}

}
