package com.mp1.search;

import com.mp1.movement.PenalizingTurnsMovement;

public class AStarPenalizingTurnsSearch extends AStarSearch {
	
	public AStarPenalizingTurnsSearch(String filename, int forwardCode, int turnCost) {
		super(filename, new PenalizingTurnsMovement());
	}

}
