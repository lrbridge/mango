package com.mp1.movement;

import com.mp1.node.State;

public class NormalMovement implements Movement {

	private String[] actions = {"LEFT", "UP", "RIGHT", "DOWN"};
	
	public String[] getActions() {
		return actions;
	}
	
	public int getChildX(State state, String action) {
		int x = state.x;
		
		switch (action) {
		case "UP":
			x--;
			break;
		case "DOWN":
			x++;
			break;
		default:
			break;
		}
		
		return x;
	}
	
	public int getChildY(State state, String action) {
		int y = state.y;
		
		switch (action) {
		case "LEFT":
			y--;
			break;
		case "RIGHT":
			y++;
			break;
		default:
			break;
		}
		
		return y;
	}

	public DIRECTION getChildDirectionFacing(State state, String action) {
		return state.directionFacing;
	}
}
