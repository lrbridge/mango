package com.mp1.node;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.mp1.movement.DIRECTION;
import com.mp1.search.base.Coordinate;

public class State {

	// x & y coordinates in maze.  Top Left is 0, 0.  x is across, y is down.
	public int x;
	public int y;
	
	public DIRECTION directionFacing; // (OPTIONAL) the direction facing
	
	public List<Coordinate> ghostCoordinates = new ArrayList<Coordinate>(); // (OPTIONAL)

	public State(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public State(int x, int y, DIRECTION directionFacing) {
		this.x = x;
		this.y = y;
		this.directionFacing = directionFacing;
	}
	
	public State(int x, int y, List<Coordinate> ghostCoordinates) {
		this.x = x;
		this.y = y;
		this.ghostCoordinates = ghostCoordinates;
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
				this.ghostCoordinates.containsAll(otherState.ghostCoordinates) &&
				otherState.ghostCoordinates.containsAll(this.ghostCoordinates);
	}

	/*
	 * Override hashCode because you should if you override equals
	 */
	@Override
	public int hashCode() {
		return Objects.hash(Integer.valueOf(this.x), Integer.valueOf(this.y));
	}
}
