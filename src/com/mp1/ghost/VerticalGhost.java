package com.mp1.ghost;

import com.mp1.movement.DIRECTION;
import com.mp1.search.base.Coordinate;
import com.mp1.search.base.Maze;

public class VerticalGhost implements Ghost {

    private int ghostStartX;
    private int ghostStartY;
    private int wallAboveGhost;
    private int wallBelowGhost;
	private boolean exists = false;
	
	public boolean exists() {
		return this.exists;
	}
	
    public VerticalGhost(Character uppercaseLetter, Maze maze) {
        Character lowercaseLetter = Character.toLowerCase(uppercaseLetter);
        Coordinate ghostInitialLocation = maze.findNode(uppercaseLetter);

        if(ghostInitialLocation == null) {
            return;
        }
        
        this.exists = true;

        this.ghostStartX = ghostInitialLocation.x;
        this.ghostStartY = ghostInitialLocation.y;

        wallAboveGhost = ghostStartX;
        while(maze.get(wallAboveGhost, ghostStartY) == lowercaseLetter || maze.get(wallAboveGhost, ghostStartY) == uppercaseLetter) {
        	wallAboveGhost--;
        }
        wallBelowGhost = ghostStartX;
        while(maze.get(wallBelowGhost, ghostStartY) == lowercaseLetter || maze.get(wallBelowGhost, ghostStartY) == uppercaseLetter) {
        	wallBelowGhost++;
        }    
    }

	public Coordinate getCoordinate(Coordinate lastCoordinate) {

		int newGhostX;
		int newGhostY = this.ghostStartY;
		DIRECTION newGhostDirection;
		
		if(lastCoordinate == null) { // if no previous coordinates, ghost is in starting position & going right
			newGhostX = this.ghostStartX;
			newGhostDirection = DIRECTION.DOWN;
		}
		else {
			if(this.wallAboveGhost+1 == this.wallBelowGhost-1) {
				newGhostX = lastCoordinate.x;
				newGhostDirection = lastCoordinate.direction;
			}
			else if(lastCoordinate.direction.equals(DIRECTION.DOWN)) { // moving down
				
				if(lastCoordinate.x < (this.wallBelowGhost - 1)) { // still room to move
					newGhostX = lastCoordinate.x + 1;
					newGhostDirection = lastCoordinate.direction;
				}
				else { // change direction
					newGhostX = lastCoordinate.x - 1;
					newGhostDirection = DIRECTION.UP;
				}
				
			}
			else { // moving up
			
				if(lastCoordinate.x > (this.wallAboveGhost + 1)) { // still room to move
					newGhostX = lastCoordinate.x - 1;
					newGhostDirection = lastCoordinate.direction;
				}
				else { // change direction
					newGhostX = lastCoordinate.x + 1;
					newGhostDirection = DIRECTION.DOWN;
				}
				
			}
			
		}
		
		return new Coordinate(newGhostX, newGhostY, newGhostDirection);
	}
}
