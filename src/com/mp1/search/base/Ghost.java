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
		int ghostY = this.ghostStartY;
		
		int move = 0;
		while(move < moveNumber) {
			if(ghostY < this.ghostRightY) {
				ghostY++; // move left to right
			}
			else if(ghostY > this.ghostLeftY) {
				ghostY--;
			}
			move++;
		}
		
		return this.ghostStartY;
	}

}
