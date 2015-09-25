package com.mp1.ghost;

import com.mp1.movement.DIRECTION;
import com.mp1.node.Node;
import com.mp1.search.base.Coordinate;
import com.mp1.search.base.Maze;

public class HorizontalGhost implements Ghost {

	private int ghostStartX;
	private int ghostStartY;
	private int wallToLeftOfGhost;
	private int wallToRightOfGhost;
	
	public HorizontalGhost(Character uppercaseLetter, Maze maze) {
		Character lowercaseLetter = Character.toLowerCase(uppercaseLetter);
		Coordinate ghostInitialLocation = maze.findNode(uppercaseLetter);

		if(ghostInitialLocation == null) {
			return;
		}
				
		this.ghostStartX = ghostInitialLocation.x;
		this.ghostStartY = ghostInitialLocation.y;
		
		wallToLeftOfGhost = ghostStartY;
		while(maze.get(ghostStartX, wallToLeftOfGhost) == lowercaseLetter || maze.get(ghostStartX, wallToLeftOfGhost) == uppercaseLetter) {
			wallToLeftOfGhost--;
		}
		wallToRightOfGhost = ghostStartY;
		while(maze.get(ghostStartX, wallToRightOfGhost) == lowercaseLetter || maze.get(ghostStartX, wallToRightOfGhost) == uppercaseLetter) {
			wallToRightOfGhost++;
        }
	}

	public int getX(Node parent) {
		return this.ghostStartX;
	}

	public int getY(Node parent) {
		
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

		return ghostY;
	}

	public DIRECTION getDirection(Node parent) {
		
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
