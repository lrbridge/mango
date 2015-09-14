package com.mp1;

public class Node {

	public State state;

	public Node parent;

	/*
	 * Override equals because we are checking for equality in the explored &
	 * frontier collections
	 */
	@Override
	public boolean equals(Object otherNode) {
		if (!(otherNode instanceof Node)) {
			return false;
		}
		// nodes are equal if their states are equal
		return state.equals(((Node) otherNode).state);
	}

	/*
	 * Override hashCode because you should if you override equals
	 */
	@Override
	public int hashCode() {
		return state.hashCode(); // hash code is state's hash code
	}

}
