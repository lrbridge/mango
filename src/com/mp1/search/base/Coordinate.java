package com.mp1.search.base;

import java.util.Objects;

import com.mp1.movement.DIRECTION;

public class Coordinate {

	public int x;
	public int y;
	public DIRECTION direction; // (optional)
	
	public Coordinate(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Coordinate(int x, int y, DIRECTION direction) {
		this.x = x;
		this.y = y;
		this.direction = direction;
	}

	/*
	 * Override equals so we don't check states multiple times
	 */
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Coordinate)) {
			return false;
		}
		Coordinate otherCoordinate = (Coordinate) obj;
		// coordinates are equal if we are in the same cell on the map and facing the same way (optional)
		return this.x == otherCoordinate.x && 
				this.y == otherCoordinate.y && 
				this.direction == otherCoordinate.direction;
	}

	/*
	 * Override hashCode because you should if you override equals
	 */
	@Override
	public int hashCode() {
		return Objects.hash(Integer.valueOf(this.x), Integer.valueOf(this.y), this.direction);
	}
	
}
