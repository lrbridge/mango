package com.mp1.node;

import java.util.Objects;

import com.mp1.movement.DIRECTION;

public class State {

	// x & y coordinates in maze.  Top Left is 0, 0.  x is across, y is down.
	public int x;
	public int y;
	
	public DIRECTION directionFacing; // (OPTIONAL) the direction facing
	
	public int ghostX; // (OPTIONAL)
	public int ghostY; // (OPTIONAL)
	public DIRECTION ghostDirection; // (OPTIONAL) the direction moving - left or right

    public int fGhostX; // (OPTIONAL)
    public int fGhostY; // (OPTIONAL)
    public DIRECTION fGhostDirection; // (OPTIONAL) the direction moving - left or right

    public int AGhostX; // (OPTIONAL)
    public int AGhostY; // (OPTIONAL)
    public DIRECTION AGhostDirection; // (OPTIONAL) the direction moving - left or right

    public int VGhostX; // (OPTIONAL)
    public int VGhostY; // (OPTIONAL)
    public DIRECTION VGhostDirection; // (OPTIONAL) the direction moving - left or right

	public State(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public State(int x, int y, DIRECTION directionFacing) {
		this.x = x;
		this.y = y;
		this.directionFacing = directionFacing;
	}
	
	public State(int x, int y, int ghostX, int ghostY, DIRECTION ghostDirection) {
		this.x = x;
		this.y = y;
		this.ghostX = ghostX;
		this.ghostY = ghostY;
		this.ghostDirection = ghostDirection;
	}

    public State(int x, int y, int ghostX, int ghostY, DIRECTION ghostDirection, int fGhostX, int fGhostY, DIRECTION fGhostDirection) {
        this.x = x;
        this.y = y;
        this.ghostX = ghostX;
        this.ghostY = ghostY;
        this.ghostDirection = ghostDirection;
        this.fGhostX = fGhostX;
        this.fGhostY = fGhostY;
        this.fGhostDirection = fGhostDirection;
    }

    public State(int x, int y, int ghostX, int ghostY, DIRECTION ghostDirection, int fGhostX, int fGhostY, DIRECTION fGhostDirection,
                 int AGhostX, int AGhostY, DIRECTION AGhostDirection, int VGhostX,  int VGhostY, DIRECTION VGhostDirection) {
        this.x = x;
        this.y = y;
        this.ghostX = ghostX;
        this.ghostY = ghostY;
        this.ghostDirection = ghostDirection;
        this.fGhostX = fGhostX;
        this.fGhostY = fGhostY;
        this.fGhostDirection = fGhostDirection;
        this.AGhostX = AGhostX;
        this.AGhostY = AGhostY;
        this.AGhostDirection = AGhostDirection;
        this.VGhostX = VGhostX;
        this.VGhostY = VGhostY;
        this.VGhostDirection = VGhostDirection;
    }


    public State(int x, int y, int ghostX, int ghostY, DIRECTION ghostDirection, int fGhostX, int fGhostY, DIRECTION fGhostDirection,
                 int AGhostX, int AGhostY, DIRECTION AGhostDirection) {
        this.x = x;
        this.y = y;
        this.ghostX = ghostX;
        this.ghostY = ghostY;
        this.ghostDirection = ghostDirection;
        this.fGhostX = fGhostX;
        this.fGhostY = fGhostY;
        this.fGhostDirection = fGhostDirection;
        this.AGhostX = AGhostX;
        this.AGhostY = AGhostY;
        this.AGhostDirection = AGhostDirection;
    }

	/*
	 * Override equals because we are checking for equality in the explored &
	 * frontier collections
	 */
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof State)) {
			return false;
		}
		State otherState = (State) obj;
		// states are equal if we are in the same cell on the map and facing the same way (optional)
		return this.x == otherState.x && 
				this.y == otherState.y && 
				this.directionFacing == otherState.directionFacing &&
				this.ghostX == otherState.ghostX &&
				this.ghostY == otherState.ghostY &&
				this.ghostDirection == otherState.ghostDirection;
	}

	/*
	 * Override hashCode because you should if you override equals
	 */
	@Override
	public int hashCode() {
		return Objects.hash(Integer.valueOf(this.x), Integer.valueOf(this.y));
	}
}
