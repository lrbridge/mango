package com.mp1;

public class Node implements Comparable<Node> {

	public State state;

	public Node parent;
	
	public int distanceSoFar;
	
	public int expectedDistanceToGo;

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

	/*
	 * When comparing two nodes (used in the priority queue), 
	 */
	@Override
	public int compareTo(Node o) {
//		System.out.println("comparing " + this.state.x + "," + this.state.y + "(" + this.expectedDistanceToGo + ") && " + o.state.x + "," + o.state.y + "(" + o.expectedDistanceToGo + ")");
		if(this.expectedDistanceToGo < o.expectedDistanceToGo) { //System.out.println("distless");
			return -1;
		}
		else if(this.expectedDistanceToGo > o.expectedDistanceToGo) { //System.out.println("distmore");
			return 1;
		}
		else { // if tied
			// always go UP over DOWN
			if(this.state.x < o.state.x) { //System.out.println("up");
				return -1;
			}
			else if(this.state.x > o.state.x) { //System.out.println("down");
				return 1;
			}
			
			// if still tied, then LEFT over RIGHT
			if(this.state.y < o.state.y) { //System.out.println("left");
				return -1;
			}
			else if(this.state.y > o.state.y) { //System.out.println("right");
				return 1;
			}
			
			// if still tied, go with equal
			return 0;
		}
	}

}
