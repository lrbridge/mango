package com.mp1.movement;

public class PenalizingTurnsMovement implements Movement {

	public String[] actions = {"TURN LEFT", "TURN RIGHT", "FORWARD"};
	
	public String[] getActions() {
		return actions;
	}
	
	public int getChildX(int x, String action) {
		
		// TODO need to modify this
//		switch (action) {
//		case "FORWARD":
//			if()
//		default:
//			break;
//		}
//		
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
	
	public DIRECTION getChildDirectionFacing(DIRECTION directionFacing, String action) {
		// TODO fill this in
		return directionFacing;
	}
}
