package com.mp1.movement;

public class PenalizingTurnsMovement {

	public String[] ACTIONS = {"TURN LEFT", "TURN RIGHT", "FORWARD"};
	
	
	
	public int getChildX(int x, String action) {
		
		switch (action) {
		case "TURN LEFT":
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
	
		// TODO need to modify this
//		switch (action) {
//		case "LEFT":
//			y--;
//			break;
//		case "RIGHT":
//			y++;
//			break;
//		default:
//			break;
//		}
		
		return y;
	}
}
