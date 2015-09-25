package com.mp1.ghost;

import com.mp1.movement.DIRECTION;
import com.mp1.node.Node;

public interface Ghost {

	public int getX(Node parent);

	public int getY(Node parent);

	public DIRECTION getDirection(Node parent);

}
