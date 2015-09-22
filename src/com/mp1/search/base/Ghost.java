package com.mp1.search.base;

public class Ghost {

	private int ghostStartX;
	private int ghostStartY;
	private int wallToLeftOfGhost;
	private int wallToRightOfGhost;
	
	public Ghost(int ghostStartX, int ghostStartY, int wallToLeftOfGhost,
			int wallToRightOfGhost) {
		this.ghostStartX = ghostStartX;
		this.ghostStartY = ghostStartY;
		this.wallToLeftOfGhost = wallToLeftOfGhost;
		this.wallToRightOfGhost = wallToRightOfGhost;
	}

	public int getGhostX() {
		return this.ghostStartX;
	}
	
	public int getGhostY(int moveNumber) {
		boolean isMovingRight = true;
		
		int ghostY = this.ghostStartY;
		//System.out.println(moveNumber + " " + ghostY + " " + this.wallToLeftOfGhost + " " + this.wallToRightOfGhost);
		int move = 0;
		while(move < moveNumber) {
			if(isMovingRight && ghostY < (this.wallToRightOfGhost-1)) {
				ghostY++; //System.out.println('R');
			}
			else if(!isMovingRight && ghostY > (this.wallToLeftOfGhost+1)) {
				ghostY--;  //System.out.println('L');
			}
			else if(isMovingRight) { // moving right but nowhere left to go, go left
				ghostY--;  //System.out.println("changeL");
				isMovingRight = false;
			}
			else { // moving left but nowhere to go, go right
				ghostY++; //System.out.println("changeR"); 
				isMovingRight = true;
			}
			
			move++;
		}
		
		return ghostY;
	}

}
