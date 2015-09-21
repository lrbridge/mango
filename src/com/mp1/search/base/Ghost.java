package com.mp1.search.base;

public class Ghost {

	private int ghostStartX;
	private int ghostStartY;
	private int ghostLeftY;
	private int ghostRightY;
	
	public Ghost(int ghostStartX, int ghostStartY, int ghostLeftY,
			int ghostRightY) {
		this.ghostStartX = ghostStartX;
		this.ghostStartY = ghostStartY;
		this.ghostLeftY = ghostLeftY;
		this.ghostRightY = ghostRightY;
	}
	
	public int getGhostX() {
		return this.ghostStartX;
	}
	
	public int getGhostY(int moveNumber) {
		return this.ghostStartY; // TODO logic here
	}

}
