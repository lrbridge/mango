package com.mp1.movement;

public class NormalMovement implements Movement {

	private String[] actions = {"LEFT", "UP", "RIGHT", "DOWN"};
	
	public String[] getActions() {
		return actions;
	}
	
	public int getChildX(int x, String action) {
		
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
	
	public int getChildY(int y, String action) {
		
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

	public DIRECTION getChildDirectionFacing(DIRECTION directionFacing, String action) {
		return directionFacing;
	}
}
