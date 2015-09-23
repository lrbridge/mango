package com.mp1.search.base;

import com.mp1.movement.DIRECTION;
import com.mp1.node.Node;

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
	
	public int getGhostY(Node parent) {
		
		if(parent == null) { // if no parent, ghost is in starting position
			return this.ghostStartY;
		}
		
		int parentGhostY = parent.getState().ghostY;
		DIRECTION parentGhostDirection = parent.getState().ghostDirection;
		
		int ghostY;
		
		if(this.wallToLeftOfGhost+1 == this.wallToRightOfGhost-1) {
			// if there is only 1 G (no g's), don't change location
			ghostY = parentGhostY;
		}
		else if(parentGhostDirection.equals(DIRECTION.RIGHT)) { // moving right
			
			if(parentGhostY < (this.wallToRightOfGhost - 1)) { // still room to move
				ghostY = parentGhostY + 1;
			}
			else { // change direction
				ghostY = parentGhostY - 1;
			}
			
		}
		else { // moving left
		
			if(parentGhostY > (this.wallToLeftOfGhost + 1)) { // still room to move
				ghostY = parentGhostY - 1;
			}
			else { // change direction
				ghostY = parentGhostY + 1;
			}
			
		}

		System.out.println("GHOST Y  " + ghostY);
		return ghostY;
	}

	public DIRECTION getGhostDirection(Node parent) {
		
		if(parent == null) { // if no parent, ghost starts off right
			return DIRECTION.RIGHT;
		}
		
		int parentGhostY = parent.getState().ghostY;
		DIRECTION parentGhostDirection = parent.getState().ghostDirection;
		
		DIRECTION newDirection = parentGhostDirection;
		
		if(parentGhostDirection.equals(DIRECTION.RIGHT) && parentGhostY >= (this.wallToRightOfGhost - 1)) {
			newDirection = DIRECTION.LEFT;
		}
		else if(parentGhostDirection.equals(DIRECTION.LEFT) && parentGhostY <= (this.wallToLeftOfGhost + 1)){
			newDirection = DIRECTION.RIGHT;
		}

		return newDirection;
	}

}
