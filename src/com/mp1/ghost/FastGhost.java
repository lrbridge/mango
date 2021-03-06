package com.mp1.ghost;

import com.mp1.movement.DIRECTION;
import com.mp1.search.base.Coordinate;
import com.mp1.search.base.Maze;

public class FastGhost implements Ghost{

    private int ghostStartX;
    private int ghostStartY;
    private int wallToLeftOfGhost;
    private int wallToRightOfGhost;
	private boolean exists = false;
	
	public boolean exists() {
		return this.exists;
	}
	
	public FastGhost(Character uppercaseLetter, Maze maze) {
		Character lowercaseLetter = Character.toLowerCase(uppercaseLetter);
		Coordinate ghostInitialLocation = maze.findNode(uppercaseLetter);

		if(ghostInitialLocation == null) {
			return;
		}
			
		this.exists = true;
		
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

	public Coordinate getCoordinate(Coordinate lastCoordinate) {
		
		int newGhostX = this.ghostStartX;
		int newGhostY;
		DIRECTION newGhostDirection;
		
		if(lastCoordinate == null) { // if no previous coordinates, ghost is in starting position & going right
			newGhostY = this.ghostStartY;
			newGhostDirection = DIRECTION.RIGHT;
		}
		
		else {
			
			if(this.wallToLeftOfGhost+2 == this.wallToRightOfGhost-2) {
		        // if there is only 1 F (no f's), don't change location
				newGhostY = lastCoordinate.y;
				newGhostDirection = lastCoordinate.direction;
			}
		    else if(lastCoordinate.direction.equals(DIRECTION.RIGHT)) { // moving right

		        if(lastCoordinate.y < (this.wallToRightOfGhost - 2)) { // still room to move
		        	newGhostY = lastCoordinate.y + 2;
					newGhostDirection = lastCoordinate.direction;
		        }
		        else if(lastCoordinate.y < (this.wallToRightOfGhost - 1)) { // bounces back
		        	newGhostY = lastCoordinate.y;
					newGhostDirection = DIRECTION.LEFT;
		        }
		        else { // change direction
		        	newGhostY = lastCoordinate.y - 2;
					newGhostDirection = DIRECTION.LEFT;
		        }

		    }
		    else { // moving left

		        if(lastCoordinate.y > (this.wallToLeftOfGhost + 2)) { // still room to move
		        	newGhostY = lastCoordinate.y - 2;
					newGhostDirection = lastCoordinate.direction;
		        }
		        else if(lastCoordinate.y > (this.wallToLeftOfGhost + 1)) { // still room to move
		        	newGhostY = lastCoordinate.y;
					newGhostDirection = DIRECTION.RIGHT;
		        }
		        else { // change direction
		        	newGhostY = lastCoordinate.y + 2;
					newGhostDirection = DIRECTION.RIGHT;
		        }

		    }
		}
			
		return new Coordinate(newGhostX, newGhostY, newGhostDirection);
	}

}
