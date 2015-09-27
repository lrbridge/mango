package com.mp1.ghost;

import com.mp1.search.base.Coordinate;

public interface Ghost {

	public Coordinate getCoordinate(Coordinate lastCoordinate);
	
	public boolean exists();

}
