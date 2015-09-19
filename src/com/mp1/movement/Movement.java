package com.mp1.movement;

public interface Movement {
	
	public String[] getActions();
	
	public int getChildX(int x, String action);
	
	public int getChildY(int y, String action);

	public DIRECTION getChildDirectionFacing(DIRECTION directionFacing, String action);
}
