package com.mp1.node;

public class Node {
	
	protected State state;
	private Node parent;
	private int distanceSoFar;

	public Node(State state, Node parent, int distanceSoFar) {
		this.state = state;
		this.parent = parent;
		this.distanceSoFar = distanceSoFar;
	}
	
	public State getState() {
		return this.state;
	}

	public Node getParent() {
		return this.parent;
	}
	
	public int getDistanceSoFar() {
		return this.distanceSoFar;
	}
	
	/*
	 * Override equals because we are checking for equality in the explored &
	 * frontier collections (with contains())
	 * 
	 * Nodes are equal if their states are equal
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
