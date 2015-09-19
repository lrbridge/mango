package com.mp1.movement;

public class NormalMovement {

	public String[] ACTIONS = {"LEFT", "UP", "RIGHT", "DOWN"};
	
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
}
