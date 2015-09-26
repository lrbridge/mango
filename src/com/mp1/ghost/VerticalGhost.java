package com.mp1.ghost;

import com.mp1.movement.DIRECTION;
import com.mp1.node.Node;
import com.mp1.search.base.Coordinate;
import com.mp1.search.base.Maze;

public class VerticalGhost implements Ghost {

    private int ghostStartX;
    private int ghostStartY;
    private int wallToUpOfGhost;
    private int wallToDownOfGhost;

    public VerticalGhost(Character uppercaseLetter, Maze maze) {
        Character lowercaseLetter = Character.toLowerCase(uppercaseLetter);
        Coordinate ghostInitialLocation = maze.findNode(uppercaseLetter);

        if(ghostInitialLocation == null) {
            return;
        }

        this.ghostStartX = ghostInitialLocation.x;
        this.ghostStartY = ghostInitialLocation.y;

        wallToUpOfGhost = ghostStartX;
        while(maze.get(ghostStartX, wallToUpOfGhost) == lowercaseLetter || maze.get(ghostStartX, wallToUpOfGhost) == uppercaseLetter) {
            wallToUpOfGhost--;
        }
        wallToDownOfGhost = ghostStartX;
        while(maze.get(ghostStartX, wallToDownOfGhost) == lowercaseLetter || maze.get(ghostStartX, wallToDownOfGhost) == uppercaseLetter) {
            wallToDownOfGhost++;
        }
    }



    public int getX(Node parent) {

        if(parent == null) { // if no parent, ghost is in starting position
            return this.ghostStartX;
        }

        int parentGhostX = parent.getState().ghostX;
        DIRECTION parentGhostDirection = parent.getState().ghostDirection;

        int ghostX;

        if(this.wallToUpOfGhost+1 == this.wallToDownOfGhost-1) {
            // if there is only 1 G (no g's), don't change location
            ghostX = parentGhostX;
        }
        else if(parentGhostDirection.equals(DIRECTION.DOWN)) { // moving up

            if(parentGhostX < (this.wallToDownOfGhost - 1)) { // still room to move
                ghostX = parentGhostX + 1;
            }
            else { // change direction
                ghostX = parentGhostX - 1;
            }

        }
        else { // moving down

            if(parentGhostX > (this.wallToDownOfGhost + 1)) { // still room to move
                ghostX = parentGhostX - 1;
            }
            else { // change direction
                ghostX = parentGhostX + 1;
            }

        }

        return ghostX;
    }

    public int getY(Node parent) {
        return this.ghostStartY;
    }

    public DIRECTION getDirection(Node parent) {

        if(parent == null) { // if no parent, ghost starts off down
            return DIRECTION.DOWN;
        }

        int parentGhostX = parent.getState().ghostX;
        DIRECTION parentGhostDirection = parent.getState().ghostDirection;

        DIRECTION newDirection = parentGhostDirection;

        if(parentGhostDirection.equals(DIRECTION.DOWN) && parentGhostX >= (this.wallToDownOfGhost - 1)) {
            newDirection = DIRECTION.UP;
        }
        else if(parentGhostDirection.equals(DIRECTION.UP) && parentGhostX <= (this.wallToUpOfGhost + 1)){
            newDirection = DIRECTION.DOWN;
        }

        return newDirection;
    }

}
